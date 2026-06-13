package net.deltav.registry;

import net.deltav.craftsconstruct;
import net.deltav.item.DragonCharge;
import net.deltav.item.FireballCharge;
import net.deltav.item.PaintCartridgeItem;
import net.deltav.item.PaintGunItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.deltav.util.ModFood;
import net.deltav.util.PaintMaterial;

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
    public static final DeferredItem<Item> BRASS_PAINT_CARTRIDGE = ITEMS.register("brass_paint_cartridge",
            () -> new PaintCartridgeItem(PaintMaterial.BRASS, new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PAINT_CARTRIDGE = ITEMS.register("copper_paint_cartridge",
            () -> new PaintCartridgeItem(PaintMaterial.COPPER, new Item.Properties()));
    public static final DeferredItem<Item> ANDESITE_PAINT_CARTRIDGE = ITEMS.register("andesite_paint_cartridge",
            () -> new PaintCartridgeItem(PaintMaterial.ANDESITE, new Item.Properties()));
    public static final DeferredItem<Item> TRAIN_PAINT_CARTRIDGE = ITEMS.register("train_paint_cartridge",
            () -> new PaintCartridgeItem(PaintMaterial.TRAIN, new Item.Properties()));

    public static final DeferredItem<Item> INCOMPLETE_ANDESITE_PAINT_CARTRIDGE = ITEMS.register("incomplete_andesite_paint_cartridge",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_BRASS_PAINT_CARTRIDGE = ITEMS.register("incomplete_brass_paint_cartridge",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_COPPER_PAINT_CARTRIDGE = ITEMS.register("incomplete_copper_paint_cartridge",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INCOMPLETE_STURDY_PAINT_CARTRIDGE = ITEMS.register("incomplete_sturdy_paint_cartridge",
            () -> new Item(new Item.Properties()));


    //Paint Gun
    public static final DeferredItem<Item> PAINT_GUN = ITEMS.register("paint_gun",
            () -> new PaintGunItem(false, new Item.Properties()));

    public static final DeferredItem<Item> C_PAINT_GUN = ITEMS.register("creative_paint_gun",
            () -> new PaintGunItem(true, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
