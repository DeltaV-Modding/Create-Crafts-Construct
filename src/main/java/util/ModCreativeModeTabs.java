package util;

import net.buildercraft.craftsconstruct;
import net.buildercraft.registry.ModItems;
import net.buildercraft.registry.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, craftsconstruct.MOD_ID);

    public static final Supplier<CreativeModeTab> CC_TAB = CREATIVE_MODE_TAB.register("cc_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAINBOW_GUMMY_BEAR.get()))
                    .title(Component.translatable("cc_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.C_PAINT_GUN);
                        output.accept(ModItems.PAINT_GUN);

                        output.accept(ModItems.EMPTY_PAINT_CARTRIDGE);
                        output.accept(ModItems.BRASS_PAINT_CARTRIDGE);
                        output.accept(ModItems.COPPER_PAINT_CARTRIDGE);
                        output.accept(ModItems.ANDESITE_PAINT_CARTRIDGE);
                        output.accept(ModItems.TRAIN_PAINT_CARTRIDGE);

                        output.accept(ModItems.RAW_DIAMOND);
                        output.accept(ModItems.RAW_AMETHYST);
                        output.accept(ModItems.EMPTY_CHARGE);
                        output.accept(ModItems.DRAGON_CHARGE);
                        output.accept(ModItems.FIREBALL_CHARGE);
                        output.accept(ModItems.SUGAR_BEET_SEEDS);
                        output.accept(ModItems.SUGAR_BEET);

                        output.accept(ModBlocks.WHITE_TEXTILE);
                        output.accept(ModBlocks.LIGHT_GRAY_TEXTILE);
                        output.accept(ModBlocks.GRAY_TEXTILE);
                        output.accept(ModBlocks.BLACK_TEXTILE);
                        output.accept(ModBlocks.BROWN_TEXTILE);
                        output.accept(ModBlocks.RED_TEXTILE);
                        output.accept(ModBlocks.ORANGE_TEXTILE);
                        output.accept(ModBlocks.YELLOW_TEXTILE);
                        output.accept(ModBlocks.LIME_TEXTILE);
                        output.accept(ModBlocks.GREEN_TEXTILE);
                        output.accept(ModBlocks.CYAN_TEXTILE);
                        output.accept(ModBlocks.LIGHT_BLUE_TEXTILE);
                        output.accept(ModBlocks.BLUE_TEXTILE);
                        output.accept(ModBlocks.PURPLE_TEXTILE);
                        output.accept(ModBlocks.MAGENTA_TEXTILE);
                        output.accept(ModBlocks.PINK_TEXTILE);

                        output.accept(ModBlocks.WHITE_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.LIGHT_GRAY_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.GRAY_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.BLACK_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.BROWN_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.RED_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.ORANGE_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.YELLOW_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.LIME_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.GREEN_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.CYAN_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.LIGHT_CHECKERED_BLUE_TEXTILE);
                        output.accept(ModBlocks.BLUE_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.PURPLE_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.MAGENTA_CHECKERED_TEXTILE);
                        output.accept(ModBlocks.PINK_CHECKERED_TEXTILE);

                        for (var tempPaintBlock : ModBlocks.TEMP_PAINT_BLOCKS) {
                            output.accept(tempPaintBlock);
                        }

                        output.accept(ModItems.GREEN_GUMMY_BEAR);
                        output.accept(ModItems.RED_GUMMY_BEAR);
                        output.accept(ModItems.YELLOW_GUMMY_BEAR);
                        output.accept(ModItems.PINK_GUMMY_BEAR);
                        output.accept(ModItems.BLUE_GUMMY_BEAR);
                    }).build());

    public static final ResourceKey<CreativeModeTab> CC_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB, ResourceLocation.parse("crafts_construct:cc_tab")
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
