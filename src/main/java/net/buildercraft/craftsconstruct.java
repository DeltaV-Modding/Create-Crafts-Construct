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
import net.buildercraft.block.create.drain.PaintedItemDrainRenderer;
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

            java.lang.reflect.Constructor<com.simibubi.create.content.fluids.tank.FluidTankModel> tankModelConstructor;
            try {
                tankModelConstructor = com.simibubi.create.content.fluids.tank.FluidTankModel.class.getDeclaredConstructor(
                    net.minecraft.client.resources.model.BakedModel.class,
                    com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry.class,
                    com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry.class,
                    com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry.class
                );
                tankModelConstructor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Could not find FluidTankModel constructor", e);
            }

            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_FLUID_TANKS) {
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(block.get(), net.minecraft.client.renderer.RenderType.cutoutMipped());
                
                String path = block.getId().getPath();
                com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry sideShift = 
                    com.simibubi.create.foundation.block.connected.CTSpriteShifter.getCT(
                        com.simibubi.create.foundation.block.connected.AllCTTypes.RECTANGLE,
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + path + "/" + path),
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + path + "/" + path + "_connected")
                    );
                com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry topShift = 
                    com.simibubi.create.foundation.block.connected.CTSpriteShifter.getCT(
                        com.simibubi.create.foundation.block.connected.AllCTTypes.RECTANGLE,
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + path + "/" + path + "_top"),
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + path + "/" + path + "_top_connected")
                    );
                com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry innerShift = 
                    com.simibubi.create.foundation.block.connected.CTSpriteShifter.getCT(
                        com.simibubi.create.foundation.block.connected.AllCTTypes.RECTANGLE,
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + path + "/" + path + "_inner"),
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, "block/" + path + "/" + path + "_inner_connected")
                    );

                final java.lang.reflect.Constructor<com.simibubi.create.content.fluids.tank.FluidTankModel> constructor = tankModelConstructor;
                com.simibubi.create.CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    bakedModel -> {
                        try {
                            return constructor.newInstance(bakedModel, sideShift, topShift, innerShift);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to instantiate FluidTankModel via reflection", e);
                        }
                    }
                );
            }

            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_FLUID_PIPES) {
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(block.get(), net.minecraft.client.renderer.RenderType.cutoutMipped());
                
                String path = block.getId().getPath();
                String material = path.split("_")[0];
                String targetPrefix = "block/" + material + "_fluid_pipe/" + material + "_fluid_pipe";

                net.createmod.catnip.render.SpriteShiftEntry pipeShift = 
                    net.createmod.catnip.render.SpriteShifter.get(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("create", "block/pipes"),
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, targetPrefix)
                    );
                net.createmod.catnip.render.SpriteShiftEntry pipeConnectedShift = 
                    net.createmod.catnip.render.SpriteShifter.get(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("create", "block/pipes_connected"),
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, targetPrefix + "_connected")
                    );

                com.simibubi.create.CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    bakedModel -> {
                        net.minecraft.client.resources.model.BakedModel attachmentModel = 
                            com.simibubi.create.content.fluids.PipeAttachmentModel.withAO(bakedModel);
                        return new SpriteShiftingBakedModel(attachmentModel, pipeShift, pipeConnectedShift);
                    }
                );
                com.simibubi.create.CreateClient.MODEL_SWAPPER.getCustomItemModels().register(
                    block.getId(),
                    bakedModel -> new SpriteShiftingBakedModel(bakedModel, pipeShift, pipeConnectedShift)
                );
            }

            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_SMART_FLUID_PIPES) {
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(block.get(), net.minecraft.client.renderer.RenderType.cutoutMipped());
                
                String path = block.getId().getPath();
                String material = path.split("_")[0];
                String targetPrefix = "block/" + material + "_fluid_pipe/" + material + "_fluid_pipe";

                net.createmod.catnip.render.SpriteShiftEntry pipeShift = 
                    net.createmod.catnip.render.SpriteShifter.get(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("create", "block/pipes"),
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, targetPrefix)
                    );
                net.createmod.catnip.render.SpriteShiftEntry pipeConnectedShift = 
                    net.createmod.catnip.render.SpriteShifter.get(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("create", "block/pipes_connected"),
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MOD_ID, targetPrefix + "_connected")
                    );

                com.simibubi.create.CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    bakedModel -> {
                        net.minecraft.client.resources.model.BakedModel attachmentModel = 
                            com.simibubi.create.content.fluids.PipeAttachmentModel.withAO(bakedModel);
                        return new SpriteShiftingBakedModel(attachmentModel, pipeShift, pipeConnectedShift);
                    }
                );
                com.simibubi.create.CreateClient.MODEL_SWAPPER.getCustomItemModels().register(
                    block.getId(),
                    bakedModel -> new SpriteShiftingBakedModel(bakedModel, pipeShift, pipeConnectedShift)
                );
            }

            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_ITEM_DRAINS) {
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(block.get(), net.minecraft.client.renderer.RenderType.cutoutMipped());
            }
        }

        public static class SpriteShiftingBakedModel extends net.neoforged.neoforge.client.model.BakedModelWrapper {
            private final net.createmod.catnip.render.SpriteShiftEntry spriteShift;
            private final net.createmod.catnip.render.SpriteShiftEntry connectedSpriteShift;

            public SpriteShiftingBakedModel(net.minecraft.client.resources.model.BakedModel originalModel, 
                                             net.createmod.catnip.render.SpriteShiftEntry spriteShift,
                                             net.createmod.catnip.render.SpriteShiftEntry connectedSpriteShift) {
                super(originalModel);
                this.spriteShift = spriteShift;
                this.connectedSpriteShift = connectedSpriteShift;
            }

            @Override
            public java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> getQuads(
                    net.minecraft.world.level.block.state.BlockState state, 
                    net.minecraft.core.Direction side, 
                    net.minecraft.util.RandomSource rand) {
                return getQuads(state, side, rand, net.neoforged.neoforge.client.model.data.ModelData.EMPTY, null);
            }

            @Override
            public java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> getQuads(
                    net.minecraft.world.level.block.state.BlockState state, 
                    net.minecraft.core.Direction side, 
                    net.minecraft.util.RandomSource rand, 
                    net.neoforged.neoforge.client.model.data.ModelData extraData, 
                    net.minecraft.client.renderer.RenderType renderType) {
                
                java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> originalQuads = 
                    super.getQuads(state, side, rand, extraData, renderType);
                
                if (state == null) {
                    System.out.println("DEBUG SpriteShiftingBakedModel item quads size: " + originalQuads.size());
                }

                java.util.List<net.minecraft.client.renderer.block.model.BakedQuad> shiftedQuads = 
                    new java.util.ArrayList<>(originalQuads.size());

                for (net.minecraft.client.renderer.block.model.BakedQuad quad : originalQuads) {
                    net.minecraft.client.renderer.texture.TextureAtlasSprite quadSprite = quad.getSprite();
                    if (state == null && quadSprite != null) {
                        System.out.println("DEBUG quadSprite: " + quadSprite.contents().name() + " original: " + (spriteShift != null ? spriteShift.getOriginal().contents().name() : "null"));
                    }
                    net.createmod.catnip.render.SpriteShiftEntry shift = null;
                    if (quadSprite != null) {
                        if (spriteShift != null && quadSprite == spriteShift.getOriginal()) {
                            shift = spriteShift;
                            if (state == null) {
                                System.out.println("DEBUG shifted quad sprite to: " + shift.getTarget().contents().name());
                            }
                        } else if (connectedSpriteShift != null && quadSprite == connectedSpriteShift.getOriginal()) {
                            shift = connectedSpriteShift;
                        }
                    }

                    if (shift != null) {
                        net.minecraft.client.renderer.block.model.BakedQuad clonedQuad = 
                            com.simibubi.create.foundation.model.BakedQuadHelper.clone(quad);
                        int[] vertices = clonedQuad.getVertices();
                        for (int i = 0; i < 4; i++) {
                            float u = com.simibubi.create.foundation.model.BakedQuadHelper.getU(vertices, i);
                            float v = com.simibubi.create.foundation.model.BakedQuadHelper.getV(vertices, i);
                            com.simibubi.create.foundation.model.BakedQuadHelper.setU(vertices, i, shift.getTargetU(u));
                            com.simibubi.create.foundation.model.BakedQuadHelper.setV(vertices, i, shift.getTargetV(v));
                        }
                        shiftedQuads.add(clonedQuad);
                    } else {
                        shiftedQuads.add(quad);
                    }
                }
                return shiftedQuads;
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
            event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_ITEM_DRAIN.get(), PaintedItemDrainRenderer::new);
        }
    }
}
