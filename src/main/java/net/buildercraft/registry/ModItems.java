package net.buildercraft.registry;

import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.utility.CreateLang;
import net.buildercraft.craftsconstruct;
import net.buildercraft.item.DragonCharge;
import net.buildercraft.item.FireballCharge;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import util.ModFood;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(craftsconstruct.MOD_ID);

    public static final DeferredItem<Item> RAW_DIAMOND = ITEMS.register("raw_diamond", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_AMETHYST = ITEMS.register("raw_amethyst", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EMPTY_CHARGE = ITEMS.register("empty_charge", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRAGON_CHARGE = ITEMS.register("dragon_fireball", () -> new DragonCharge(new Item.Properties().stacksTo(16).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> FIREBALL_CHARGE = ITEMS.register("fireball", () -> new FireballCharge(new Item.Properties().stacksTo(16).fireResistant().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> SUGAR_BEET_SEEDS = ITEMS.register("sugar_beet_seeds", () -> new ItemNameBlockItem(ModBlocks.SUGAR_BEETS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SUGAR_BEET = ITEMS.register("sugar_beet", () -> new Item(new Item.Properties().food(ModFood.SUGAR_BEET)));

    public static final DeferredItem<Item> GREEN_GUMMY_BEAR = ITEMS.register("green_gummy_bear", () -> new Item(new Item.Properties().food(ModFood.GUMMY_BEAR)));
    public static final DeferredItem<Item> YELLOW_GUMMY_BEAR = ITEMS.register("yellow_gummy_bear", () -> new Item(new Item.Properties().food(ModFood.GUMMY_BEAR)));
    public static final DeferredItem<Item> RED_GUMMY_BEAR = ITEMS.register("red_gummy_bear", () -> new Item(new Item.Properties().food(ModFood.GUMMY_BEAR)));
    public static final DeferredItem<Item> PINK_GUMMY_BEAR = ITEMS.register("pink_gummy_bear", () -> new Item(new Item.Properties().food(ModFood.GUMMY_BEAR)));
    public static final DeferredItem<Item> BLUE_GUMMY_BEAR = ITEMS.register("blue_gummy_bear", () -> new Item(new Item.Properties().food(ModFood.GUMMY_BEAR)));
    public static final DeferredItem<Item> RAINBOW_GUMMY_BEAR = ITEMS.register("rainbow_gummy_bear", () -> new Item(new Item.Properties().food(ModFood.GUMMY_BEAR)));



    //Paint Cartridge
    public static final DeferredItem<Item> EMPTY_PAINT_CARTRIDGE = ITEMS.register("empty_paint_cartridge", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRASS_PAINT_CARTRIDGE = ITEMS.register("brass_paint_cartridge", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, context, tooltip, flag);
                List<Component> material = TooltipHelper.cutTextComponent(
                        CreateLang.translate("tooltip.cc.paint.brass").component(),
                        FontHelper.Palette.STANDARD_CREATE.primary(),
                        FontHelper.Palette.STANDARD_CREATE.highlight()
                );
                tooltip.add(Component.translatable("create.tooltip.cc.behaviour3")
                        .withStyle(ChatFormatting.GRAY));
                tooltip.addAll(material);
        }

    });
    public static final DeferredItem<Item> COPPER_PAINT_CARTRIDGE = ITEMS.register("copper_paint_cartridge", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, context, tooltip, flag);
            List<Component> material = TooltipHelper.cutTextComponent(
                    CreateLang.translate("tooltip.cc.paint.copper").component(),
                    FontHelper.Palette.STANDARD_CREATE.primary(),
                    FontHelper.Palette.STANDARD_CREATE.highlight()
            );
            tooltip.add(Component.translatable("create.tooltip.cc.behaviour3")
                    .withStyle(ChatFormatting.GRAY));
            tooltip.addAll(material);
        }

    });
    public static final DeferredItem<Item> ANDESITE_PAINT_CARTRIDGE = ITEMS.register("andesite_paint_cartridge", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, context, tooltip, flag);
            List<Component> material = TooltipHelper.cutTextComponent(
                    CreateLang.translate("tooltip.cc.paint.andesite").component(),
                    FontHelper.Palette.STANDARD_CREATE.primary(),
                    FontHelper.Palette.STANDARD_CREATE.highlight()
            );
            tooltip.add(Component.translatable("create.tooltip.cc.behaviour3")
                    .withStyle(ChatFormatting.GRAY));
            tooltip.addAll(material);
        }

    });
    public static final DeferredItem<Item> TRAIN_PAINT_CARTRIDGE = ITEMS.register("train_paint_cartridge", () -> new Item(new Item.Properties()){
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, context, tooltip, flag);
            List<Component> material = TooltipHelper.cutTextComponent(
                    CreateLang.translate("tooltip.cc.paint.train").component(),
                    FontHelper.Palette.STANDARD_CREATE.primary(),
                    FontHelper.Palette.STANDARD_CREATE.highlight()
            );
            tooltip.add(Component.translatable("create.tooltip.cc.behaviour3")
                    .withStyle(ChatFormatting.GRAY));
            tooltip.addAll(material);
        }

    });


    //Paint Gun
    public static final DeferredItem<Item> PAINT_GUN = ITEMS.register("paint_gun", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)){
        //adds the tooltips to the Item
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, context, tooltip, flag);
            if (Screen.hasShiftDown()) {
                List<Component> summary = TooltipHelper.cutTextComponent(
                        CreateLang.translate("tooltip.cc.paint_gun.summary").component(),
                        FontHelper.Palette.STANDARD_CREATE.primary(),
                        FontHelper.Palette.STANDARD_CREATE.highlight()
                );
                List<Component> condition = TooltipHelper.cutTextComponent(
                        CreateLang.translate("tooltip.cc.condition").component(),
                        FontHelper.Palette.STANDARD_CREATE.primary(),
                        FontHelper.Palette.STANDARD_CREATE.highlight()
                );
                List<Component> condition2 = TooltipHelper.cutTextComponent(
                        CreateLang.translate("tooltip.cc.condition2").component(),
                        FontHelper.Palette.STANDARD_CREATE.primary(),
                        FontHelper.Palette.STANDARD_CREATE.highlight()
                );
                tooltip.add(Component.translatable("create.tooltip.cc.keyShift_down")
                        .withStyle(ChatFormatting.DARK_GRAY));
                tooltip.add(Component.translatable("create.tooltip.cc.null"));
                tooltip.addAll(summary);
                tooltip.add(Component.translatable("create.tooltip.cc.null"));
                tooltip.add(Component.translatable("create.tooltip.cc.behaviour")
                        .withStyle(ChatFormatting.GRAY));
                tooltip.addAll(condition);
                tooltip.add(Component.translatable("create.tooltip.cc.behaviour2")
                        .withStyle(ChatFormatting.GRAY));
                tooltip.addAll(condition2);

            } else {
                tooltip.add(Component.translatable("create.tooltip.cc.keyShift")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
        }

    });

    public static final DeferredItem<Item> C_PAINT_GUN = ITEMS.register("creative_paint_gun", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)){
        //adds the tooltips to the Item
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(stack, context, tooltip, flag);
            if (Screen.hasShiftDown()) {
                List<Component> summary = TooltipHelper.cutTextComponent(
                        CreateLang.translate("tooltip.cc.c_paint_gun.summary").component(),
                        FontHelper.Palette.STANDARD_CREATE.primary(),
                        FontHelper.Palette.STANDARD_CREATE.highlight()
                );
                List<Component> condition = TooltipHelper.cutTextComponent(
                        CreateLang.translate("tooltip.cc.condition").component(),
                        FontHelper.Palette.STANDARD_CREATE.primary(),
                        FontHelper.Palette.STANDARD_CREATE.highlight()
                );
                List<Component> condition2 = TooltipHelper.cutTextComponent(
                        CreateLang.translate("tooltip.cc.condition2").component(),
                        FontHelper.Palette.STANDARD_CREATE.primary(),
                        FontHelper.Palette.STANDARD_CREATE.highlight()
                );
                tooltip.add(Component.translatable("create.tooltip.cc.keyShift_down")
                        .withStyle(ChatFormatting.DARK_GRAY));
                tooltip.add(Component.translatable("create.tooltip.cc.null"));
                tooltip.addAll(summary);
                tooltip.add(Component.translatable("create.tooltip.cc.null"));
                tooltip.add(Component.translatable("create.tooltip.cc.behaviour")
                        .withStyle(ChatFormatting.GRAY));
                tooltip.addAll(condition);
                tooltip.add(Component.translatable("create.tooltip.cc.behaviour2")
                        .withStyle(ChatFormatting.GRAY));
                tooltip.addAll(condition2);

            } else {
                tooltip.add(Component.translatable("create.tooltip.cc.keyShift")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
        }

    });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}