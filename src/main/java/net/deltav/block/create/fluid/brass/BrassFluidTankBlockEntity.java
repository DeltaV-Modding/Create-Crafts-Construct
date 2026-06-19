package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class BrassFluidTankBlockEntity extends FluidTankBlockEntity {
    public BrassFluidTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BRASS_FLUID_TANK.get(), pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return fluidCapability;
    }
}