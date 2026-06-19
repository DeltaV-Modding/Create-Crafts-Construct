package net.deltav.block.create.kinetic;

import com.simibubi.create.content.decoration.steamWhistle.WhistleRenderer;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.base.ShaftVisual;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;

import net.deltav.registry.ModBlockEntityTypes;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.kinetic.train.*;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class KineticClient {
    private KineticClient() {
    }

    public static void registerVisualizers() {
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.ANDESITE_ENCASED_CHAIN_DRIVE.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.BRASS_ENCASED_CHAIN_DRIVE.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.TRAIN_ENCASED_CHAIN_DRIVE.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();

        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.ANDESITE_STEAM_ENGINE.get()).factory(AndesiteSteamEngineVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.BRASS_STEAM_ENGINE.get()).factory(BrassSteamEngineVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.TRAIN_STEAM_ENGINE.get()).factory(TrainSteamEngineVisual::new).skipVanillaRender(be -> true).apply();
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_ENCASED_CHAIN_DRIVE.get(), ShaftRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_ENCASED_CHAIN_DRIVE.get(), ShaftRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_ENCASED_CHAIN_DRIVE.get(), ShaftRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_STEAM_ENGINE.get(), AndesiteSteamEngineRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_STEAM_ENGINE.get(), BrassSteamEngineRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_STEAM_ENGINE.get(), TrainSteamEngineRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_STEAM_WHISTLE.get(), WhistleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_STEAM_WHISTLE.get(), WhistleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_STEAM_WHISTLE.get(), WhistleRenderer::new);
    }
}
