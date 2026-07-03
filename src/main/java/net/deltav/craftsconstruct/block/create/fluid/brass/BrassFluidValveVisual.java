package net.deltav.craftsconstruct.block.create.fluid.brass;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import net.deltav.craftsconstruct.block.create.fluid.FluidValveVisualBase;

public class BrassFluidValveVisual extends FluidValveVisualBase {
    public BrassFluidValveVisual(VisualizationContext context, FluidValveBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick, BrassFluidValveRenderer::getPointer);
    }
}
