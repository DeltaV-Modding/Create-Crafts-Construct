package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.core.Direction;

import java.util.function.Supplier;

public class BrassHorizontalFluidTankBlock extends FluidTankBlock {
    public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private final Supplier<BlockEntityType<? extends FluidTankBlockEntity>> blockEntityType;

    public BrassHorizontalFluidTankBlock(BlockBehaviour.Properties properties,
                                     Supplier<BlockEntityType<? extends FluidTankBlockEntity>> blockEntityType) {
        super(properties, false);
        this.blockEntityType = blockEntityType;
        this.registerDefaultState(this.defaultBlockState().setValue(HORIZONTAL_AXIS, Direction.Axis.X));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction.Axis axis = context.getHorizontalDirection().getAxis();
        BlockState state = super.getStateForPlacement(context);
        if (state != null) {
            return state.setValue(HORIZONTAL_AXIS, axis);
        }
        return this.defaultBlockState().setValue(HORIZONTAL_AXIS, axis);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HORIZONTAL_AXIS);
    }

    @Override
    public BlockEntityType<? extends FluidTankBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}