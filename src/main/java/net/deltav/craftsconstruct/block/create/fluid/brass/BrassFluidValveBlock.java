package net.deltav.craftsconstruct.block.create.fluid.brass;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;

import java.util.function.Supplier;

public class BrassFluidValveBlock extends FluidValveBlock {
    private final Supplier<BlockEntityType<? extends FluidValveBlockEntity>> blockEntityType;

    public BrassFluidValveBlock(BlockBehaviour.Properties properties,
                                  Supplier<BlockEntityType<? extends FluidValveBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AllShapes.FLUID_VALVE.get(getPipeAxis(state));
    }
    protected boolean prefersConnectionTo(LevelReader reader, BlockPos pos, Direction facing, boolean shaftAxis) {
        if (!shaftAxis) {
            BlockPos offset = pos.relative(facing);
            BlockState blockState = reader.getBlockState(offset);
            return FluidPipeBlock.canConnectTo(reader, offset, blockState, facing);
        }
        return super.prefersConnectionTo(reader, pos, facing, shaftAxis);
    }
    public Axis getAxis(BlockState state) {
        return getPipeAxis(state);
    }
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        boolean blockTypeChanged = !state.is(newState.getBlock());
        if (blockTypeChanged && !world.isClientSide)
            FluidPropagator.propagateChangedPipe(world, pos, state);
        super.onRemove(state, world, pos, newState, isMoving);
    }
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, world, pos, oldState, isMoving);
        if (world.isClientSide)
            return;
        if (state != oldState)
            world.scheduleTick(pos, this, 1, TickPriority.HIGH);
    }
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

    public static boolean isOpenAt(BlockState state, Direction direction) {
        return direction.getAxis() == getPipeAxis(state);
    }
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        FluidPropagator.propagateChangedPipe(world, pos, state);
    }
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return withWater(super.getStateForPlacement(context), context);
    }
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState, LevelAccessor world,
                                  BlockPos pos, BlockPos neighbourPos) {
        updateWater(world, state, pos);
        return state;
    }
    public FluidState getFluidState(BlockState state) {
        return fluidState(state);
    }
    public BlockEntityType<? extends FluidValveBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
