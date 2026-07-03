package net.deltav.craftsconstruct.block.create.fluid.andesite;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import net.deltav.craftsconstruct.block.create.fluid.FluidValveVisualBase;

public class AndesiteFluidValveVisual extends FluidValveVisualBase {
    public AndesiteFluidValveVisual(VisualizationContext context, FluidValveBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick, AndesiteFluidValveRenderer::getPointer);
    }
}
