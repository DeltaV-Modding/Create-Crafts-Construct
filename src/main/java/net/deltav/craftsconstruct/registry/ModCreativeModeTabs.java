package net.deltav.craftsconstruct.registry;

import net.deltav.craftsconstruct.craftsconstruct;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, craftsconstruct.MOD_ID);

    public static final Supplier<CreativeModeTab> CC_TAB = CREATIVE_MODE_TAB.register("cc_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAINBOW_GUMMY_BEAR.get()))
                    .title(Component.translatable("cc_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PAINT_GUN.get());

                        output.accept(ModItems.EMPTY_PAINT_CARTRIDGE.get());
                        output.accept(ModItems.BRASS_PAINT_CARTRIDGE.get());
                        output.accept(ModItems.COPPER_PAINT_CARTRIDGE.get());
                        output.accept(ModItems.ANDESITE_PAINT_CARTRIDGE.get());
                        output.accept(ModItems.STURDY_PAINT_CARTRIDGE.get());

                        output.accept(ModItems.RAW_AMETHYST.get());
                        //output.accept(ModItems.EMPTY_CHARGE);
                        //output.accept(ModItems.DRAGON_CHARGE);
                        //output.accept(ModItems.FIREBALL_CHARGE);
                        output.accept(ModItems.SUGAR_BEET_SEEDS.get());
                        output.accept(ModItems.SUGAR_BEET.get());

                        output.accept(ModItems.GREEN_GUMMY_BEAR.get());
                        output.accept(ModItems.YELLOW_GUMMY_BEAR.get());
                        output.accept(ModItems.RED_GUMMY_BEAR.get());
                        output.accept(ModItems.PINK_GUMMY_BEAR.get());
                        output.accept(ModItems.BLUE_GUMMY_BEAR.get());
                    }).build());

    public static final ResourceKey<CreativeModeTab> CC_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB, new ResourceLocation("crafts_construct:cc_tab")
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
