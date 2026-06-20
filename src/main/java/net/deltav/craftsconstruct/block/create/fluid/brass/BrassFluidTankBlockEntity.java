package net.deltav.craftsconstruct.block.create.fluid.brass;

import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class BrassFluidTankBlockEntity extends FluidTankBlockEntity {
    public BrassFluidTankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return fluidCapability;
    }
}
