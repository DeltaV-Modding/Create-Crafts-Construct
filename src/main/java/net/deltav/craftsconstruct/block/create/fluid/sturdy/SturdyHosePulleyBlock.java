package net.deltav.craftsconstruct.block.create.fluid.sturdy;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class SturdyHosePulleyBlock extends HosePulleyBlock {
    private final Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType;

    public SturdyHosePulleyBlock(BlockBehaviour.Properties properties,
                                  Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public Axis getRotationAxis(BlockState state) {
        return state.getValue(HORIZONTAL_FACING).getClockWise().getAxis();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction preferredHorizontalFacing = getPreferredHorizontalFacing(context);
        return defaultBlockState().setValue(HORIZONTAL_FACING,
                preferredHorizontalFacing != null ? preferredHorizontalFacing.getCounterClockWise()
                        : context.getHorizontalDirection().getOpposite());
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(HORIZONTAL_FACING).getClockWise() == face;
    }

    public static boolean hasPipeTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(HORIZONTAL_FACING).getCounterClockWise() == face;
    }

    @Override
    public Direction getPreferredHorizontalFacing(BlockPlaceContext context) {
        Direction fromParent = super.getPreferredHorizontalFacing(context);
        if (fromParent != null)
            return fromParent;

        Direction preferredSide = null;
        for (Direction facing : Iterate.horizontalDirections) {
            BlockPos pos = context.getClickedPos().relative(facing);
            BlockState blockState = context.getLevel().getBlockState(pos);
            if (FluidPipeBlock.canConnectTo(context.getLevel(), pos, blockState, facing))
                if (preferredSide != null && preferredSide.getAxis() != facing.getAxis()) {
                    preferredSide = null;
                    break;
                } else
                    preferredSide = facing;
        }
        return preferredSide == null ? null : preferredSide.getOpposite();
    }

    @Override
    public BlockEntityType<? extends HosePulleyBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
