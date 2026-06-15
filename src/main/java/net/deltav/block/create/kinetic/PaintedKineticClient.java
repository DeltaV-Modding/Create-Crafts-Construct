package net.deltav.block.create.kinetic;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.decoration.steamWhistle.WhistleRenderer;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterRenderer;
import com.simibubi.create.content.kinetics.deployer.DeployerRenderer;
import com.simibubi.create.content.kinetics.deployer.DeployerVisual;
import com.simibubi.create.content.kinetics.drill.DrillRenderer;
import com.simibubi.create.content.kinetics.fan.EncasedFanRenderer;
import com.simibubi.create.content.kinetics.fan.FanVisual;
import com.simibubi.create.content.kinetics.gearbox.GearboxRenderer;
import com.simibubi.create.content.kinetics.gearbox.GearboxVisual;
import com.simibubi.create.content.kinetics.millstone.MillstoneRenderer;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.content.kinetics.mixer.MixerVisual;
import com.simibubi.create.content.kinetics.press.MechanicalPressRenderer;
import com.simibubi.create.content.kinetics.press.PressVisual;
import com.simibubi.create.content.kinetics.saw.SawRenderer;
import com.simibubi.create.content.kinetics.saw.SawVisual;
import com.simibubi.create.content.logistics.depot.DepotRenderer;
import com.simibubi.create.content.logistics.depot.EjectorRenderer;
import com.simibubi.create.content.logistics.depot.EjectorVisual;
import com.simibubi.create.content.kinetics.gauge.GaugeRenderer;
import com.simibubi.create.content.kinetics.gauge.GaugeVisual;
import com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsRenderer;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;

import net.deltav.registry.ModBlockEntityTypes;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class PaintedKineticClient {
    private PaintedKineticClient() {
    }

    public static void registerVisualizers() {
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_ENCASED_CHAIN_DRIVE.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_STEAM_ENGINE.get()).factory(com.simibubi.create.content.kinetics.steamEngine.SteamEngineVisual::new).skipVanillaRender(be -> true).apply();
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_ENCASED_CHAIN_DRIVE.get(), ShaftRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_STEAM_ENGINE.get(), com.simibubi.create.content.kinetics.steamEngine.SteamEngineRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_STEAM_WHISTLE.get(), WhistleRenderer::new);
    }
}
