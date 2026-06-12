package net.buildercraft.block.create.portableInterface;

import com.simibubi.create.content.contraptions.actors.psi.PSIVisual;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;

import net.buildercraft.registry.ModBlockEntityTypes;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class PaintedPortableFluidInterfaceClient {
    private PaintedPortableFluidInterfaceClient() {
    }

    public static void registerVisualizers() {
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_PORTABLE_FLUID_INTERFACE.get()).factory(PSIVisual::new).skipVanillaRender(be -> true).apply();
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_PORTABLE_FLUID_INTERFACE.get(), PortableStorageInterfaceRenderer::new);
    }
}
