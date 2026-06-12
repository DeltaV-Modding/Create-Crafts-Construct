package net.buildercraft.block.create.fluid;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyRenderer;
import com.simibubi.create.content.fluids.pump.PumpRenderer;
import com.simibubi.create.content.fluids.spout.SpoutRenderer;
import com.simibubi.create.content.fluids.tank.FluidTankModel;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;

import net.buildercraft.craftsconstruct;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.buildercraft.registry.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.lang.reflect.Constructor;

public final class PaintedFluidClient {
    private PaintedFluidClient() {
    }

    public static void registerVisualizers() {
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_MECHANICAL_PUMP.get()).factory(SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG)).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_FLUID_VALVE.get()).factory(PaintedFluidValveVisual::new).skipVanillaRender(be -> true).apply();
        SimpleBlockEntityVisualizer.builder(ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get()).factory(HosePulleyVisual::new).skipVanillaRender(be -> true).apply();
    }

    public static void registerModelSwappers() {
        Constructor<FluidTankModel> tankModelConstructor;
        try {
            tankModelConstructor = FluidTankModel.class.getDeclaredConstructor(
                    BakedModel.class,
                    CTSpriteShiftEntry.class,
                    CTSpriteShiftEntry.class,
                    CTSpriteShiftEntry.class
            );
            tankModelConstructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Could not find FluidTankModel constructor", e);
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
                    bakedModel -> {
                        try {
                            return tankModelConstructor.newInstance(bakedModel, sideShift, topShift, innerShift);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to instantiate FluidTankModel via reflection", e);
                        }
                    }
            );
        }
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AllBlockEntityTypes.FLUID_TANK.get(), PaintedFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_MECHANICAL_PUMP.get(), PumpRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_FLUID_VALVE.get(), PaintedFluidValveRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_FLUID_TANK.get(), PaintedFluidTankRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_SPOUT.get(), SpoutRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get(), HosePulleyRenderer::new);
    }
}
