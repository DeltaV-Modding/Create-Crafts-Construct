package net.deltav.block.create.fluid;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyRenderer;
import com.simibubi.create.content.fluids.pump.PumpRenderer;
import com.simibubi.create.content.fluids.spout.SpoutRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;

import net.deltav.craftsconstruct;
import net.deltav.registry.ModBlockEntityTypes;
import net.deltav.registry.ModBlocks;
import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.fluid.copper.*;
import net.deltav.block.create.fluid.train.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public final class FluidClient {
    private FluidClient() {
    }

    public static void registerVisualizers() {
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.ANDESITE_MECHANICAL_PUMP.get()).factory(SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG)).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.BRASS_MECHANICAL_PUMP.get()).factory(SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG)).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.TRAIN_MECHANICAL_PUMP.get()).factory(SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG)).skipVanillaRender(be -> true).apply();

        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.ANDESITE_FLUID_VALVE.get()).factory(AndesiteFluidValveVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.BRASS_FLUID_VALVE.get()).factory(BrassFluidValveVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.TRAIN_FLUID_VALVE.get()).factory(TrainFluidValveVisual::new).skipVanillaRender(be -> true).apply();

        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.ANDESITE_HOSE_PULLEY.get()).factory(HosePulleyVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.BRASS_HOSE_PULLEY.get()).factory(HosePulleyVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.TRAIN_HOSE_PULLEY.get()).factory(HosePulleyVisual::new).skipVanillaRender(be -> true).apply();
    }

    public static void registerRenderLayers() {
        for (var block : ModBlocks.PAINTED_SPOUTS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
        for (var block : ModBlocks.PAINTED_FLUID_VALVES) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
        for (var block : ModBlocks.PAINTED_FLUID_TANKS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
        for (var block : ModBlocks.PAINTED_HORIZONTAL_FLUID_TANKS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
    }

    public static void registerModelSwappers() {
        for (var block : ModBlocks.PAINTED_FLUID_TANKS) {
            String path = block.getId().getPath();
            CTSpriteShiftEntry sideShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + path + "/" + path),
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + path + "/" + path + "_connected")
            );
            CTSpriteShiftEntry topShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + path + "/" + path + "_top"),
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + path + "/" + path + "_top_connected")
            );
            CTSpriteShiftEntry innerShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + path + "/" + path + "_inner"),
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + path + "/" + path + "_inner_connected")
            );

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    bakedModel -> {
                        if (path.startsWith("andesite")) {
                            return new AndesiteFluidTankModel(bakedModel, sideShift, topShift, innerShift, false);
                        } else if (path.startsWith("brass")) {
                            return new BrassFluidTankModel(bakedModel, sideShift, topShift, innerShift, false);
                        } else if (path.startsWith("train")) {
                            return new TrainFluidTankModel(bakedModel, sideShift, topShift, innerShift, false);
                        }
                        return bakedModel;
                    }
            );
        }

        for (var block : ModBlocks.PAINTED_HORIZONTAL_FLUID_TANKS) {
            String path = block.getId().getPath();
            String baseTankPath = path.replace("horizontal_", "");

            String namespace;
            String texturePath;
            String connectedPath;
            String topPath;
            String topConnectedPath;
            String innerPath;
            String innerConnectedPath;

            if (baseTankPath.startsWith("copper")) {
                namespace = "create";
                texturePath = "block/fluid_tank";
                connectedPath = "block/fluid_tank_connected";
                topPath = "block/fluid_tank_top";
                topConnectedPath = "block/fluid_tank_top_connected";
                innerPath = "block/fluid_tank_inner";
                innerConnectedPath = "block/fluid_tank_inner_connected";
            } else {
                namespace = craftsconstruct.MOD_ID;
                texturePath = "block/" + baseTankPath + "/" + baseTankPath;
                connectedPath = "block/" + baseTankPath + "/" + baseTankPath + "_connected";
                topPath = "block/" + baseTankPath + "/" + baseTankPath + "_top";
                topConnectedPath = "block/" + baseTankPath + "/" + baseTankPath + "_top_connected";
                innerPath = "block/" + baseTankPath + "/" + baseTankPath + "_inner";
                innerConnectedPath = "block/" + baseTankPath + "/" + baseTankPath + "_inner_connected";
            }

            CTSpriteShiftEntry sideShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    ResourceLocation.fromNamespaceAndPath(namespace, texturePath),
                    ResourceLocation.fromNamespaceAndPath(namespace, connectedPath)
            );
            CTSpriteShiftEntry topShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    ResourceLocation.fromNamespaceAndPath(namespace, topPath),
                    ResourceLocation.fromNamespaceAndPath(namespace, topConnectedPath)
            );
            CTSpriteShiftEntry innerShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    ResourceLocation.fromNamespaceAndPath(namespace, innerPath),
                    ResourceLocation.fromNamespaceAndPath(namespace, innerConnectedPath)
            );

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    bakedModel -> {
                        if (baseTankPath.startsWith("andesite")) {
                            return new AndesiteFluidTankModel(bakedModel, sideShift, topShift, innerShift, true);
                        } else if (baseTankPath.startsWith("brass")) {
                            return new BrassFluidTankModel(bakedModel, sideShift, topShift, innerShift, true);
                        } else if (baseTankPath.startsWith("train")) {
                            return new TrainFluidTankModel(bakedModel, sideShift, topShift, innerShift, true);
                        } else if (baseTankPath.startsWith("copper")) {
                            return new CopperFluidTankModel(bakedModel, sideShift, topShift, innerShift, true);
                        }
                        return bakedModel;
                    }
            );
        }
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AllBlockEntityTypes.FLUID_TANK.get(), AndesiteFluidTankRenderer::new);
        
        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_MECHANICAL_PUMP.get(), PumpRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_MECHANICAL_PUMP.get(), PumpRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_MECHANICAL_PUMP.get(), PumpRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_FLUID_VALVE.get(), AndesiteFluidValveRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_FLUID_VALVE.get(), BrassFluidValveRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_FLUID_VALVE.get(), TrainFluidValveRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_FLUID_TANK.get(), AndesiteFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_FLUID_TANK.get(), BrassFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_FLUID_TANK.get(), TrainFluidTankRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_HORIZONTAL_FLUID_TANK.get(), AndesiteFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_HORIZONTAL_FLUID_TANK.get(), BrassFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.COPPER_HORIZONTAL_FLUID_TANK.get(), com.simibubi.create.content.fluids.tank.FluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_HORIZONTAL_FLUID_TANK.get(), TrainFluidTankRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_SPOUT.get(), AndesiteSpoutRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_SPOUT.get(), BrassSpoutRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_SPOUT.get(), TrainSpoutRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_HOSE_PULLEY.get(), HosePulleyRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_HOSE_PULLEY.get(), HosePulleyRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_HOSE_PULLEY.get(), HosePulleyRenderer::new);
    }
}
