package util;

import net.buildercraft.craftsconstruct;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, name));
        }
        public static final TagKey<Block> PAINTABLE_BLOCKS = BlockTags.create(ResourceLocation.parse(craftsconstruct.MOD_ID + ":tags/block/paint/paintable"));
    }

    public static class Items {
        //public static final TagKey<Item> EXAMPLE_TAG = createTag("example");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, name));
        }
    }
}
