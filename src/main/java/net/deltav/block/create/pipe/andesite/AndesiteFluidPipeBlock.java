package net.deltav.block.create.pipe.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class AndesiteFluidPipeBlock extends FluidPipeBlock {
    public static final BooleanProperty GLASS = BooleanProperty.create("glass");
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    private final Supplier<BlockEntityType<? extends FluidPipeBlockEntity>> blockEntityType;

    public AndesiteFluidPipeBlock(BlockBehaviour.Properties properties,
                                 Supplier<BlockEntityType<? extends FluidPipeBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
        this.registerDefaultState(this.defaultBlockState()
                .setValue(GLASS, false)
                .setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    public BlockEntityType<? extends FluidPipeBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GLASS, AXIS);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState updateBlockState(BlockState state, Direction preferredDirection, @Nullable Direction ignore,
                                       BlockAndTintGetter world, BlockPos pos) {
        if (state.getValue(GLASS)) {
            Direction.Axis axis = state.getValue(AXIS);
            for (Direction d : net.createmod.catnip.data.Iterate.directions) {
                state = state.setValue(PROPERTY_BY_DIRECTION.get(d), d.getAxis() == axis);
            }
            return state;
        }
        return super.updateBlockState(state, preferredDirection, ignore, world, pos);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        if (tryRemoveBracket(context))
            return InteractionResult.SUCCESS;

        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();

        if (state.getValue(GLASS)) {
            if (world.isClientSide)
                return InteractionResult.SUCCESS;

            FluidTransportBehaviour.cacheFlows(world, pos);

            BlockState newState = this.defaultBlockState()
                    .setValue(GLASS, false)
                    .setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED));

            Direction.Axis axis = state.getValue(AXIS);
            Direction side = Direction.get(Direction.AxisDirection.POSITIVE, axis);
            newState = updateBlockState(newState.setValue(PROPERTY_BY_DIRECTION.get(side), true)
                    .setValue(PROPERTY_BY_DIRECTION.get(side.getOpposite()), true), side, null, world, pos);

            world.setBlockAndUpdate(pos, newState);
            FluidTransportBehaviour.loadFlows(world, pos);
            return InteractionResult.SUCCESS;
        }

        Direction.Axis axis = FluidPropagator.getStraightPipeAxis(state);
        if (axis == null) {
            Vec3 clickLocation = context.getClickLocation()
                    .subtract(pos.getX(), pos.getY(), pos.getZ());
            double closest = Float.MAX_VALUE;
            Direction argClosest = Direction.UP;
            for (Direction direction : net.createmod.catnip.data.Iterate.directions) {
                if (clickedFace.getAxis() == direction.getAxis())
                    continue;
                Vec3 centerOf = Vec3.atCenterOf(direction.getNormal());
                double distance = centerOf.distanceToSqr(clickLocation);
                if (distance < closest) {
                    closest = distance;
                    argClosest = direction;
                }
            }
            axis = argClosest.getAxis();
        }

        if (clickedFace.getAxis() == axis)
            return InteractionResult.PASS;

        if (!world.isClientSide) {
            withBlockEntityDo(world, pos, fpte -> fpte.getBehaviour(FluidTransportBehaviour.TYPE).interfaces.values()
                    .stream()
                    .filter(pc -> pc != null && pc.hasFlow())
                    .findAny()
                    .ifPresent($ -> com.simibubi.create.foundation.advancement.AllAdvancements.GLASS_PIPE.awardTo(context.getPlayer())));

            FluidTransportBehaviour.cacheFlows(world, pos);

            BlockState newState = this.defaultBlockState()
                    .setValue(GLASS, true)
                    .setValue(AXIS, axis)
                    .setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED));

            for (Direction d : net.createmod.catnip.data.Iterate.directions) {
                newState = newState.setValue(PROPERTY_BY_DIRECTION.get(d), d.getAxis() == axis);
            }

            world.setBlockAndUpdate(pos, newState);
            FluidTransportBehaviour.loadFlows(world, pos);
        }
        return InteractionResult.SUCCESS;
    }
}