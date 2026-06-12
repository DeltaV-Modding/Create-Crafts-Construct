package net.buildercraft.block.create.drain;

import net.buildercraft.registry.ModBlockEntityTypes;
import net.buildercraft.registry.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

public final class PaintedItemDrainClient {
    private PaintedItemDrainClient() {
    }

    public static void registerModelLayers() {
        for (DeferredBlock<Block> block : ModBlocks.PAINTED_ITEM_DRAINS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_ITEM_DRAIN.get(), PaintedItemDrainRenderer::new);
    }
}
