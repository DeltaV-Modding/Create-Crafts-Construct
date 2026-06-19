package net.deltav.block.create.drain;

import net.deltav.registry.ModBlockEntityTypes;
import net.deltav.registry.ModBlocks;
import net.deltav.block.create.drain.andesite.*;
import net.deltav.block.create.drain.brass.*;
import net.deltav.block.create.drain.train.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

public final class ItemDrainClient {
    private ItemDrainClient() {
    }

    public static void registerModelLayers() {
        for (DeferredBlock<Block> block : ModBlocks.PAINTED_ITEM_DRAINS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.ANDESITE_ITEM_DRAIN.get(), AndesiteItemDrainRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.BRASS_ITEM_DRAIN.get(), BrassItemDrainRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.TRAIN_ITEM_DRAIN.get(), TrainItemDrainRenderer::new);
    }
}
