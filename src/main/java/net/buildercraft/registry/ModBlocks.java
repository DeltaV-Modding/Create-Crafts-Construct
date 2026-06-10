package net.buildercraft.registry;

import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.buildercraft.block.SugarBeetCropBlock;
import net.buildercraft.block.create.drain.BrassItemDrainBlock;
import net.buildercraft.craftsconstruct;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static net.buildercraft.craftsconstruct.REGISTRATE;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(craftsconstruct.MOD_ID);

    public static final DeferredBlock<Block> WHITE_TEXTILE = registerBlock("white_textile_block", () -> new Block(BlockBehaviour.Properties.of()
                    .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIGHT_GRAY_TEXTILE = registerBlock("light_gray_textile_block", () -> new Block(BlockBehaviour.Properties.of()
                    .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> GRAY_TEXTILE = registerBlock("gray_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BLACK_TEXTILE = registerBlock("black_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BROWN_TEXTILE = registerBlock("brown_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> RED_TEXTILE = registerBlock("red_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> ORANGE_TEXTILE = registerBlock("orange_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> YELLOW_TEXTILE = registerBlock("yellow_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIME_TEXTILE = registerBlock("lime_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> GREEN_TEXTILE = registerBlock("green_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> CYAN_TEXTILE = registerBlock("cyan_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIGHT_BLUE_TEXTILE = registerBlock("light_blue_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BLUE_TEXTILE = registerBlock("blue_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> PURPLE_TEXTILE = registerBlock("purple_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> MAGENTA_TEXTILE = registerBlock("magenta_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> PINK_TEXTILE = registerBlock("pink_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));


    public static final DeferredBlock<Block> WHITE_CHECKERED_TEXTILE = registerBlock("white_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIGHT_GRAY_CHECKERED_TEXTILE = registerBlock("light_gray_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> GRAY_CHECKERED_TEXTILE = registerBlock("gray_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BLACK_CHECKERED_TEXTILE = registerBlock("black_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BROWN_CHECKERED_TEXTILE = registerBlock("brown_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> RED_CHECKERED_TEXTILE = registerBlock("red_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> ORANGE_CHECKERED_TEXTILE = registerBlock("orange_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> YELLOW_CHECKERED_TEXTILE = registerBlock("yellow_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIME_CHECKERED_TEXTILE = registerBlock("lime_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> GREEN_CHECKERED_TEXTILE = registerBlock("green_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> CYAN_CHECKERED_TEXTILE = registerBlock("cyan_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIGHT_CHECKERED_BLUE_TEXTILE = registerBlock("light_blue_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BLUE_CHECKERED_TEXTILE = registerBlock("blue_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> PURPLE_CHECKERED_TEXTILE = registerBlock("purple_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> MAGENTA_CHECKERED_TEXTILE = registerBlock("magenta_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> PINK_CHECKERED_TEXTILE = registerBlock("pink_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));


    public static final DeferredBlock<Block> SUGAR_BEETS = BLOCKS.register("sugar_beets", () -> new SugarBeetCropBlock(BlockBehaviour.Properties
                    .ofFullCopy(Blocks.BEETROOTS)));


    //Create Stuff
    public static final BlockEntry<BrassItemDrainBlock> BRASS_ITEM_DRAIN = REGISTRATE.block("brass_item_drain", BrassItemDrainBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate((c, p) -> p.simpleBlock(c.get(), AssetLookup.standardModel(c, p)))
            .simpleItem()
            .register();



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}