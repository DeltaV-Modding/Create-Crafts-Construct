package net.buildercraft.block.create.fluid;

import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class PaintedFluidTankBlockEntity extends FluidTankBlockEntity {
    public PaintedFluidTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_FLUID_TANK.get(), pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return fluidCapability;
    }
}
