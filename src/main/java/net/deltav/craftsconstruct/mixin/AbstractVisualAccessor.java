package net.deltav.craftsconstruct.mixin;

import dev.engine_room.flywheel.lib.visual.AbstractVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = AbstractVisual.class, remap = false)
public interface AbstractVisualAccessor {
    @Accessor("visualizationContext")
    VisualizationContext crafts_construct$getVisualizationContext();
}
