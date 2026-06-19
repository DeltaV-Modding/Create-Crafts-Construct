package net.deltav.block.create.fluid.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class AndesiteHorizontalFluidTankBlockEntity extends FluidTankBlockEntity {
    public AndesiteHorizontalFluidTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.ANDESITE_HORIZONTAL_FLUID_TANK.get(), pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return fluidCapability;
    }

    @Override
    public Direction.Axis getMainConnectionAxis() {
        BlockState state = getBlockState();
        if (state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            return state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
        }
        return Direction.Axis.X;
    }

    @Override
    public void notifyMultiUpdated() {
        BlockState state = this.getBlockState();
        if (state.getBlock() instanceof AndesiteHorizontalFluidTankBlock) {
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