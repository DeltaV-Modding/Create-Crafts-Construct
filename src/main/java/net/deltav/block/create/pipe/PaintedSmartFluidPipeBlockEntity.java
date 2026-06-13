package net.deltav.block.create.pipe;

import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedSmartFluidPipeBlockEntity extends SmartFluidPipeBlockEntity {
    public PaintedSmartFluidPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_SMART_FLUID_PIPE.get(), pos, state);
    }
}
