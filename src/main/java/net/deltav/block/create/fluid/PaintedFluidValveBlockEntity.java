package net.deltav.block.create.fluid;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedFluidValveBlockEntity extends FluidValveBlockEntity {
    public PaintedFluidValveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_FLUID_VALVE.get(), pos, state);
    }
}
