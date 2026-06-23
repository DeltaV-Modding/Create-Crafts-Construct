package net.deltav.craftsconstruct.registry;

import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.item.DragonCharge;
import net.deltav.craftsconstruct.item.FireballCharge;
import net.deltav.craftsconstruct.item.PaintCartridgeItem;
import net.deltav.craftsconstruct.item.PaintGunItem;
import net.deltav.craftsconstruct.item.SewingThreadItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.deltav.craftsconstruct.util.PaintMaterial;
import com.tterrag.registrate.util.entry.ItemEntry;

import static net.deltav.craftsconstruct.craftsconstruct.REGISTRATE;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(craftsconstruct.MOD_ID);

    public static final DeferredItem<Item> RAW_AMETHYST = simpleItem("raw_amethyst");
    public static final DeferredItem<Item> EMPTY_CHARGE = simpleItem("empty_charge");

    public static final DeferredItem<Item> DRAGON_CHARGE = ITEMS.register("dragon_fireball", () -> new DragonCharge(chargeProperties()));
    public static final DeferredItem<Item> FIREBALL_CHARGE = ITEMS.register("fireball", () -> new FireballCharge(chargeProperties()));
    public static final DeferredItem<Item> SUGAR_BEET_SEEDS = ITEMS.register("sugar_beet_seeds", () -> new ItemNameBlockItem(ModBlocks.SUGAR_BEETS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SUGAR_BEET = foodItem("sugar_beet", ModFood.SUGAR_BEET);

    public static final DeferredItem<Item> GREEN_GUMMY_BEAR = gummyBear("green_gummy_bear");
    public static final DeferredItem<Item> YELLOW_GUMMY_BEAR = gummyBear("yellow_gummy_bear");
    public static final DeferredItem<Item> RED_GUMMY_BEAR = gummyBear("red_gummy_bear");
    public static final DeferredItem<Item> PINK_GUMMY_BEAR = gummyBear("pink_gummy_bear");
    public static final DeferredItem<Item> BLUE_GUMMY_BEAR = gummyBear("blue_gummy_bear");
    public static final DeferredItem<Item> RAINBOW_GUMMY_BEAR = gummyBear("rainbow_gummy_bear");

    public static final DeferredItem<Item> EMPTY_PAINT_CARTRIDGE = simpleItem("empty_paint_cartridge");
    public static final DeferredItem<Item> BRASS_PAINT_CARTRIDGE = paintCartridge("brass_paint_cartridge", PaintMaterial.BRASS);
    public static final DeferredItem<Item> COPPER_PAINT_CARTRIDGE = paintCartridge("copper_paint_cartridge", PaintMaterial.COPPER);
    public static final DeferredItem<Item> ANDESITE_PAINT_CARTRIDGE = paintCartridge("andesite_paint_cartridge", PaintMaterial.ANDESITE);
    public static final DeferredItem<Item> STURDY_PAINT_CARTRIDGE = paintCartridge("sturdy_paint_cartridge", PaintMaterial.STURDY);

    public static final DeferredItem<Item> INCOMPLETE_ANDESITE_PAINT_CARTRIDGE = simpleItem("incomplete_andesite_paint_cartridge");
    public static final DeferredItem<Item> INCOMPLETE_BRASS_PAINT_CARTRIDGE = simpleItem("incomplete_brass_paint_cartridge");
    public static final DeferredItem<Item> INCOMPLETE_COPPER_PAINT_CARTRIDGE = simpleItem("incomplete_copper_paint_cartridge");
    public static final DeferredItem<Item> INCOMPLETE_STURDY_PAINT_CARTRIDGE = simpleItem("incomplete_sturdy_paint_cartridge");

    public static final DeferredItem<Item> PAINT_GUN = paintGun("paint_gun", false);
    public static final DeferredItem<Item> C_PAINT_GUN = paintGun("creative_paint_gun", true);

    @Deprecated(forRemoval = true)
    public static final ItemEntry<SewingThreadItem> SEWING_THREAD = REGISTRATE.item("sewing_thread", SewingThreadItem::new).register();

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static DeferredItem<Item> simpleItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    private static DeferredItem<Item> foodItem(String name, net.minecraft.world.food.FoodProperties food) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(food)));
    }

    private static DeferredItem<Item> gummyBear(String name) {
        return foodItem(name, ModFood.GUMMY_BEAR);
    }

    private static DeferredItem<Item> paintCartridge(String name, PaintMaterial material) {
        return ITEMS.register(name, () -> new PaintCartridgeItem(material, new Item.Properties()));
    }

    private static DeferredItem<Item> paintGun(String name, boolean creative) {
        return ITEMS.register(name, () -> new PaintGunItem(creative, new Item.Properties()));
    }

    private static Item.Properties chargeProperties() {
        return new Item.Properties().stacksTo(16).fireResistant().rarity(Rarity.UNCOMMON);
    }
}
