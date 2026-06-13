package net.deltav.block.create.pipe;

import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedFluidPipeBlockEntity extends FluidPipeBlockEntity {
    public PaintedFluidPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_FLUID_PIPE.get(), pos, state);
    }
}
