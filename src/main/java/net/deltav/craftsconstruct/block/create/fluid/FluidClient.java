package net.deltav.craftsconstruct.block.create.fluid;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.CreateClient;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.registry.ModBlocks;
import net.deltav.craftsconstruct.block.create.fluid.andesite.*;
import net.deltav.craftsconstruct.block.create.fluid.brass.*;
import net.deltav.craftsconstruct.block.create.fluid.copper.*;
import net.deltav.craftsconstruct.block.create.fluid.train.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import com.tterrag.registrate.util.entry.BlockEntry;

import java.util.List;

public final class FluidClient {
    private FluidClient() {
    }

    public static void registerRenderLayers() {
        setCutoutLayer(ModBlocks.PAINTED_SPOUTS);
        setCutoutLayer(ModBlocks.PAINTED_FLUID_VALVES);
        setCutoutLayer(ModBlocks.PAINTED_FLUID_TANKS);
        setCutoutLayer(ModBlocks.PAINTED_HORIZONTAL_FLUID_TANKS);
    }

    public static void registerModelSwappers() {
        for (var block : ModBlocks.PAINTED_FLUID_TANKS) {
            String path = block.getId().getPath();
            CTSpriteShiftEntry sideShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    modBlockTexture(path + "/" + path),
                    modBlockTexture(path + "/" + path + "_connected")
            );
            CTSpriteShiftEntry topShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    modBlockTexture(path + "/" + path + "_top"),
                    modBlockTexture(path + "/" + path + "_top_connected")
            );
            CTSpriteShiftEntry innerShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    modBlockTexture(path + "/" + path + "_inner"),
                    modBlockTexture(path + "/" + path + "_inner_connected")
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
                    texture(namespace, texturePath),
                    texture(namespace, connectedPath)
            );
            CTSpriteShiftEntry topShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    texture(namespace, topPath),
                    texture(namespace, topConnectedPath)
            );
            CTSpriteShiftEntry innerShift = CTSpriteShifter.getCT(
                    AllCTTypes.RECTANGLE,
                    texture(namespace, innerPath),
                    texture(namespace, innerConnectedPath)
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
    }

    private static void setCutoutLayer(List<BlockEntry<? extends Block>> blocks) {
        for (var block : blocks) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
    }

    private static ResourceLocation modBlockTexture(String path) {
        return texture(craftsconstruct.MOD_ID, "block/" + path);
    }

    private static ResourceLocation texture(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}
