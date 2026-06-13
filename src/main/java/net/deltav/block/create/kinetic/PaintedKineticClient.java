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
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_PRESS.get()).factory(PressVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_GEARBOX.get()).factory(GearboxVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_ENCASED_CHAIN_DRIVE.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_ENCASED_FAN.get()).factory(FanVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MILLSTONE.get()).factory(SingleAxisRotatingVisual.of(AllPartialModels.MILLSTONE_COG)).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_SAW.get()).factory(SawVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_MIXER.get()).factory(MixerVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_DEPLOYER.get()).factory(DeployerVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_DRILL.get()).factory(OrientedRotatingVisual.of(AllPartialModels.DRILL_HEAD)).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_STEAM_ENGINE.get()).factory(com.simibubi.create.content.kinetics.steamEngine.SteamEngineVisual::new).skipVanillaRender(be -> true).apply();
        
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_WEIGHTED_EJECTOR.get()).factory(EjectorVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_CLUTCH.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_GEARSHIFT.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_SPEEDOMETER.get()).factory(GaugeVisual.Speed::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_STRESSOMETER.get()).factory(GaugeVisual.Stress::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_ADJUSTABLE_CHAIN_GEARSHIFT.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_PRESS.get(), MechanicalPressRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_GEARBOX.get(), GearboxRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_ENCASED_CHAIN_DRIVE.get(), ShaftRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_ENCASED_FAN.get(), EncasedFanRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MILLSTONE.get(), MillstoneRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_SAW.get(), SawRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_MIXER.get(), MechanicalMixerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_DEPLOYER.get(), DeployerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_DRILL.get(), DrillRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_CRAFTER.get(), MechanicalCrafterRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_STEAM_ENGINE.get(), com.simibubi.create.content.kinetics.steamEngine.SteamEngineRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_STEAM_WHISTLE.get(), WhistleRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_DEPOT.get(), DepotRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_WEIGHTED_EJECTOR.get(), EjectorRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_CLUTCH.get(), ShaftRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_GEARSHIFT.get(), ShaftRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_SPEEDOMETER.get(), GaugeRenderer::speed);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_STRESSOMETER.get(), GaugeRenderer::stress);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_ADJUSTABLE_CHAIN_GEARSHIFT.get(), ShaftRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_CONTRAPTION_CONTROLS.get(), ContraptionControlsRenderer::new);
    }
}
