package net.deltav.block.create.fluid.train;

import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class TrainFluidTankBlockEntity extends FluidTankBlockEntity {
    public TrainFluidTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TRAIN_FLUID_TANK.get(), pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return fluidCapability;
    }
}