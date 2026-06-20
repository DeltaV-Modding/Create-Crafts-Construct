package net.deltav.craftsconstruct.block.create.fluid.train;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;

import java.util.function.Supplier;

public class TrainPumpBlock extends PumpBlock {
    private final Supplier<BlockEntityType<? extends PumpBlockEntity>> blockEntityType;

    public TrainPumpBlock(BlockBehaviour.Properties properties,
                            Supplier<BlockEntityType<? extends PumpBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockState getRotatedBlockState(BlockState originalState, Direction targetedFace) {
        return originalState.setValue(FACING, originalState.getValue(FACING).getOpposite());
    }

    @Override
    public Axis getRotationAxis(BlockState state) {
        return state.getValue(FACING).getAxis();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AllShapes.PUMP.get(state.getValue(FACING));
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block otherBlock, BlockPos neighborPos,
                                boolean isMoving) {
        DebugPackets.sendNeighborsUpdatePacket(world, pos);
        Direction direction = FluidPropagator.validateNeighbourChange(state, world, pos, otherBlock, neighborPos, isMoving);
        if (direction == null)
            return;
        if (!isOpenAt(state, direction))
            return;
        world.scheduleTick(pos, this, 1, TickPriority.HIGH);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false)
                : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState, LevelAccessor world,
                                  BlockPos pos, BlockPos neighbourPos) {
        if (state.getValue(BlockStateProperties.WATERLOGGED))
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        return state;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState toPlace = super.getStateForPlacement(context);
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        boolean isShiftKeyDown = context.getPlayer() != null && context.getPlayer().isShiftKeyDown();
        toPlace = ProperWaterloggedBlock.withWater(level, toPlace, pos);

        Direction nearestLookingDirection = context.getNearestLookingDirection();
        Direction targetDirection = isShiftKeyDown ? nearestLookingDirection : nearestLookingDirection.getOpposite();
        Direction bestConnectedDirection = null;
        double bestDistance = Double.MAX_VALUE;

        for (Direction direction : Iterate.directions) {
            BlockPos adjPos = pos.relative(direction);
            BlockState adjState = level.getBlockState(adjPos);
            if (!FluidPipeBlock.canConnectTo(level, adjPos, adjState, direction))
                continue;
            double distance = Vec3.atLowerCornerOf(direction.getNormal())
                    .distanceTo(Vec3.atLowerCornerOf(targetDirection.getNormal()));
            if (distance > bestDistance)
                continue;
            bestDistance = distance;
            bestConnectedDirection = direction;
        }

        if (bestConnectedDirection != null && bestConnectedDirection.getAxis() != targetDirection.getAxis() && !isShiftKeyDown)
            return toPlace.setValue(FACING, bestConnectedDirection);

        return toPlace;
    }

    public static boolean isPump(BlockState state) {
        return state.getBlock() instanceof TrainPumpBlock;
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, world, pos, oldState, isMoving);
        if (world.isClientSide)
            return;
        if (state != oldState)
            world.scheduleTick(pos, this, 1, TickPriority.HIGH);

        if (isPump(state) && isPump(oldState) && state.getValue(FACING) == oldState.getValue(FACING).getOpposite()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (!(blockEntity instanceof TrainPumpBlockEntity pump))
                return;
            pump.pressureUpdate = true;
        }
    }

    public static boolean isOpenAt(BlockState state, Direction direction) {
        return direction.getAxis() == state.getValue(FACING).getAxis();
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        FluidPropagator.propagateChangedPipe(world, pos, state);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        boolean blockTypeChanged = !state.is(newState.getBlock());
        if (blockTypeChanged && !world.isClientSide)
            FluidPropagator.propagateChangedPipe(world, pos, state);
        super.onRemove(state, world, pos, newState, isMoving);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public BlockEntityType<? extends PumpBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
