package net.deltav.craftsconstruct.block.create.pipe.brass;

import com.simibubi.create.foundation.advancement.AllAdvancements;
import net.createmod.catnip.data.Iterate;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import net.deltav.craftsconstruct.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BrassFluidPipeBlock extends FluidPipeBlock {

    private final Supplier<BlockEntityType<? extends FluidPipeBlockEntity>> blockEntityType;

    public BrassFluidPipeBlock(BlockBehaviour.Properties properties,
                                Supplier<BlockEntityType<? extends FluidPipeBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends FluidPipeBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        if (tryRemoveBracket(context))
            return InteractionResult.SUCCESS;

        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();

        Direction.Axis axis = getAxis(world, pos, state);
        if (axis == null) {
            Vec3 clickLocation = context.getClickLocation()
                    .subtract(pos.getX(), pos.getY(), pos.getZ());
            double closest = Float.MAX_VALUE;
            Direction argClosest = Direction.UP;
            for (Direction direction : Iterate.directions) {
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
                    .ifPresent($ -> AllAdvancements.GLASS_PIPE.awardTo(context.getPlayer())));

            FluidTransportBehaviour.cacheFlows(world, pos);
            world.setBlockAndUpdate(pos, ModBlocks.BRASS_GLASS_PIPE.getDefaultState()
                    .setValue(BrassGlassPipeBlock.AXIS, axis)
                    .setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED)));
            FluidTransportBehaviour.loadFlows(world, pos);
        }
        return InteractionResult.SUCCESS;
    }


    @Nullable
    private Direction.Axis getAxis(BlockGetter world, BlockPos pos, BlockState state) {
        return FluidPropagator.getStraightPipeAxis(state);
    }
}