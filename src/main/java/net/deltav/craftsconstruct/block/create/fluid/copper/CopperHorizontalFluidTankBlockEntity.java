package net.deltav.craftsconstruct.block.create.fluid.copper;

import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class CopperHorizontalFluidTankBlockEntity extends FluidTankBlockEntity {
    public CopperHorizontalFluidTankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return fluidCapability.orElse(null);
    }
    public Direction.Axis getMainConnectionAxis() {
        BlockState state = getBlockState();
        if (state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            return state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
        }
        return Direction.Axis.X;
    }
    public void notifyMultiUpdated() {
        BlockState state = this.getBlockState();
        if (state.getBlock() instanceof CopperHorizontalFluidTankBlock) {
            Direction.Axis axis = getMainConnectionAxis();
            int posCoordinate = axis == Direction.Axis.X ? getBlockPos().getX() : getBlockPos().getZ();
            int controllerCoordinate = axis == Direction.Axis.X ? getController().getX() : getController().getZ();

            state = state.setValue(FluidTankBlock.BOTTOM, controllerCoordinate == posCoordinate);
            state = state.setValue(FluidTankBlock.TOP, controllerCoordinate + height - 1 == posCoordinate);
            level.setBlock(getBlockPos(), state, 6);
        }
        if (isController())
            setWindows(window);
        onFluidStackChanged(tankInventory.getFluid());
        updateBoilerState();
        setChanged();
    }
}
