package net.deltav.util;

import net.deltav.CraftsConstruct;
import net.deltav.registry.ModItems;
import net.deltav.registry.ModBlocks;
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
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CraftsConstruct.MOD_ID);

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
                        output.accept(ModItems.INCOMPLETE_ANDESITE_PAINT_CARTRIDGE);
                        output.accept(ModItems.INCOMPLETE_BRASS_PAINT_CARTRIDGE);
                        output.accept(ModItems.INCOMPLETE_COPPER_PAINT_CARTRIDGE);
                        output.accept(ModItems.INCOMPLETE_STURDY_PAINT_CARTRIDGE);

                        output.accept(ModItems.RAW_DIAMOND);
                        output.accept(ModItems.RAW_AMETHYST);
                        output.accept(ModItems.EMPTY_CHARGE);
                        output.accept(ModItems.DRAGON_CHARGE);
                        output.accept(ModItems.FIREBALL_CHARGE);
                        output.accept(ModItems.SUGAR_BEET_SEEDS);
                        output.accept(ModItems.SUGAR_BEET);

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
