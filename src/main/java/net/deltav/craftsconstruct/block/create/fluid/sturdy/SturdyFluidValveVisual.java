package net.deltav.craftsconstruct.block.create.fluid.sturdy;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import net.deltav.craftsconstruct.block.create.fluid.FluidValveVisualBase;

public class SturdyFluidValveVisual extends FluidValveVisualBase {
    public SturdyFluidValveVisual(VisualizationContext context, FluidValveBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick, SturdyFluidValveRenderer::getPointer);
    }
}
