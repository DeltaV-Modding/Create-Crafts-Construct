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
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

public final class PaintedFluidClient {
    private PaintedFluidClient() {
    }

    public static void registerVisualizers() {
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_PUMP.get()).factory(SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG)).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_FLUID_VALVE.get()).factory(PaintedFluidValveVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get()).factory(HosePulleyVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_FLUID_PIPE.get())
                .factory((ctx, be, partialTick) -> new net.deltav.block.create.pipe.PaintedGlassPipeVisual(ctx, (net.deltav.block.create.pipe.PaintedFluidPipeBlockEntity) be, partialTick))
                .neverSkipVanillaRender()
                .apply();
    }

    public static void registerModelSwappers() {
        for (DeferredBlock<Block> block : ModBlocks.PAINTED_SPOUTS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
        for (DeferredBlock<Block> block : ModBlocks.PAINTED_FLUID_VALVES) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }

        for (DeferredBlock<Block> block : ModBlocks.PAINTED_FLUID_TANKS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());

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
                    bakedModel -> new PaintedFluidTankModel(bakedModel, sideShift, topShift, innerShift)
            );
        }

        for (DeferredBlock<Block> block : ModBlocks.PAINTED_HORIZONTAL_FLUID_TANKS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());

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
                    bakedModel -> new PaintedFluidTankModel(bakedModel, sideShift, topShift, innerShift)
            );
        }
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AllBlockEntityTypes.FLUID_TANK.get(), PaintedFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_PUMP.get(), PumpRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_FLUID_VALVE.get(), PaintedFluidValveRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_FLUID_TANK.get(), PaintedFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.HORIZONTAL_FLUID_TANK.get(), PaintedFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_SPOUT.get(), PaintedSpoutRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get(), HosePulleyRenderer::new);
    }
}
