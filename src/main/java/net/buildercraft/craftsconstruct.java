package net.buildercraft;

import com.mojang.logging.LogUtils;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;
import com.simibubi.create.content.decoration.steamWhistle.WhistleRenderer;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyRenderer;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveRenderer;
import com.simibubi.create.content.fluids.pump.PumpRenderer;
import com.simibubi.create.content.fluids.spout.SpoutRenderer;
import com.simibubi.create.content.fluids.tank.FluidTankRenderer;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterRenderer;
import com.simibubi.create.content.kinetics.deployer.DeployerRenderer;
import com.simibubi.create.content.kinetics.drill.DrillRenderer;
import com.simibubi.create.content.kinetics.fan.EncasedFanRenderer;
import com.simibubi.create.content.kinetics.gearbox.GearboxRenderer;
import com.simibubi.create.content.kinetics.millstone.MillstoneRenderer;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.content.kinetics.press.MechanicalPressRenderer;
import com.simibubi.create.content.kinetics.saw.SawRenderer;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import com.simibubi.create.content.kinetics.press.PressVisual;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveVisual;
import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.content.kinetics.gearbox.GearboxVisual;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import com.simibubi.create.content.kinetics.fan.FanVisual;
import com.simibubi.create.content.kinetics.saw.SawVisual;
import com.simibubi.create.content.kinetics.mixer.MixerVisual;
import com.simibubi.create.content.kinetics.deployer.DeployerVisual;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineVisual;
import com.simibubi.create.content.contraptions.actors.psi.PSIVisual;
import net.buildercraft.registry.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import util.ModCreativeModeTabs;

@Mod(craftsconstruct.MOD_ID)
public class craftsconstruct {
    public static final String MOD_ID = "crafts_construct";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);
    private static final StackWalker STACK_WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }


    public craftsconstruct(IEventBus modEventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(modEventBus);

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        ModFluids.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);
        ModCapabilities.register(modEventBus);
        modEventBus.addListener(this::onAddBlocksToBE);


        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void onAddBlocksToBE(net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent event) {
        for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_FLUID_TANKS) {
            event.modify(com.simibubi.create.AllBlockEntityTypes.FLUID_TANK.get(), block.get());
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {});
        //ModCapabilities.register();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {}

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_PRESS.get()).factory(PressVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_PUMP.get()).factory(SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG)).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_FLUID_VALVE.get()).factory(FluidValveVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get()).factory(HosePulleyVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_GEARBOX.get()).factory(GearboxVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_ENCASED_CHAIN_DRIVE.get()).factory(ShaftVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_ENCASED_FAN.get()).factory(FanVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MILLSTONE.get()).factory(SingleAxisRotatingVisual.of(AllPartialModels.MILLSTONE_COG)).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_SAW.get()).factory(SawVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_MIXER.get()).factory(MixerVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_DEPLOYER.get()).factory(DeployerVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_DRILL.get()).factory(OrientedRotatingVisual.of(AllPartialModels.DRILL_HEAD)).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_STEAM_ENGINE.get()).factory(SteamEngineVisual::new).skipVanillaRender(be -> true).apply();
            SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_PORTABLE_FLUID_INTERFACE.get()).factory(PSIVisual::new).skipVanillaRender(be -> true).apply();

            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_FLUID_TANKS) {
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(block.get(), net.minecraft.client.renderer.RenderType.cutoutMipped());
                com.simibubi.create.CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    com.simibubi.create.content.fluids.tank.FluidTankModel::standard
                );
            }

            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_FLUID_PIPES) {
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(block.get(), net.minecraft.client.renderer.RenderType.cutoutMipped());
                com.simibubi.create.CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    com.simibubi.create.content.fluids.PipeAttachmentModel::withAO
                );
            }

            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_SMART_FLUID_PIPES) {
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(block.get(), net.minecraft.client.renderer.RenderType.cutoutMipped());
                com.simibubi.create.CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    com.simibubi.create.content.fluids.PipeAttachmentModel::withAO
                );
            }
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_PRESS.get(), MechanicalPressRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_PUMP.get(), PumpRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_FLUID_VALVE.get(), FluidValveRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_FLUID_TANK.get(), FluidTankRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_SPOUT.get(), SpoutRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get(), HosePulleyRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_GEARBOX.get(), GearboxRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_ENCASED_CHAIN_DRIVE.get(), ShaftRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_ENCASED_FAN.get(), EncasedFanRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MILLSTONE.get(), MillstoneRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_SAW.get(), SawRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_MIXER.get(), MechanicalMixerRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_DEPLOYER.get(), DeployerRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_DRILL.get(), DrillRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_CRAFTER.get(), MechanicalCrafterRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_STEAM_ENGINE.get(), SteamEngineRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_STEAM_WHISTLE.get(), WhistleRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_PORTABLE_FLUID_INTERFACE.get(), PortableStorageInterfaceRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_SMART_FLUID_PIPE.get(), com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer::new);
        }
    }
}
