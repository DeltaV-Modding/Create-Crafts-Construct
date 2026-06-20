package net.deltav.craftsconstruct.block.create.drain;

import net.deltav.craftsconstruct.registry.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public final class ItemDrainClient {
    private ItemDrainClient() {
    }

    public static void registerModelLayers() {
        for (var block : ModBlocks.PAINTED_ITEM_DRAINS) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
    }

}
