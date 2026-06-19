package net.deltav.block.create.portableInterface;

import com.simibubi.create.content.contraptions.actors.psi.PSIVisual;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;

import net.deltav.registry.ModBlockEntityTypes;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class PortableFluidInterfaceClient {
    private PortableFluidInterfaceClient() {
    }

    public static void registerVisualizers() {
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.ANDESITE_PORTABLE_FLUID_INTERFACE.get()).factory(PSIVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.BRASS_PORTABLE_FLUID_INTERFACE.get()).factory(PSIVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.TRAIN_PORTABLE_FLUID_INTERFACE.get()).factory(PSIVisual::new).skipVanillaRender(be -> true).apply();
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_PORTABLE_FLUID_INTERFACE.get(), PortableStorageInterfaceRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_PORTABLE_FLUID_INTERFACE.get(), PortableStorageInterfaceRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_PORTABLE_FLUID_INTERFACE.get(), PortableStorageInterfaceRenderer::new);
    }
}
