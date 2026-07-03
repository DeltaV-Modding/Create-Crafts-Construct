package net.deltav.craftsconstruct.registry;

import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.item.DragonCharge;
import net.deltav.craftsconstruct.item.FireballCharge;
import net.deltav.craftsconstruct.item.PaintCartridgeItem;
import net.deltav.craftsconstruct.item.PaintGunItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.deltav.craftsconstruct.util.PaintMaterial;

public class ModItems {

    // TODO: Change this to Registrate
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, craftsconstruct.MOD_ID);

    public static final RegistryObject<Item> RAW_AMETHYST = simpleItem("raw_amethyst");
    public static final RegistryObject<Item> EMPTY_CHARGE = simpleItem("empty_charge");

    public static final RegistryObject<Item> DRAGON_CHARGE = ITEMS.register("dragon_fireball", () -> new DragonCharge(chargeProperties()));
    public static final RegistryObject<Item> FIREBALL_CHARGE = ITEMS.register("fireball", () -> new FireballCharge(chargeProperties()));
    public static final RegistryObject<Item> SUGAR_BEET_SEEDS = ITEMS.register("sugar_beet_seeds", () -> new ItemNameBlockItem(ModBlocks.SUGAR_BEETS.get(), new Item.Properties()));
    public static final RegistryObject<Item> SUGAR_BEET = foodItem("sugar_beet", ModFood.SUGAR_BEET);

    public static final RegistryObject<Item> GREEN_GUMMY_BEAR = gummyBear("green_gummy_bear");
    public static final RegistryObject<Item> YELLOW_GUMMY_BEAR = gummyBear("yellow_gummy_bear");
    public static final RegistryObject<Item> RED_GUMMY_BEAR = gummyBear("red_gummy_bear");
    public static final RegistryObject<Item> PINK_GUMMY_BEAR = gummyBear("pink_gummy_bear");
    public static final RegistryObject<Item> BLUE_GUMMY_BEAR = gummyBear("blue_gummy_bear");
    public static final RegistryObject<Item> RAINBOW_GUMMY_BEAR = gummyBear("rainbow_gummy_bear");

    public static final RegistryObject<Item> EMPTY_PAINT_CARTRIDGE = simpleItem("empty_paint_cartridge");
    public static final RegistryObject<Item> BRASS_PAINT_CARTRIDGE = paintCartridge("brass_paint_cartridge", PaintMaterial.BRASS);
    public static final RegistryObject<Item> COPPER_PAINT_CARTRIDGE = paintCartridge("copper_paint_cartridge", PaintMaterial.COPPER);
    public static final RegistryObject<Item> ANDESITE_PAINT_CARTRIDGE = paintCartridge("andesite_paint_cartridge", PaintMaterial.ANDESITE);
    public static final RegistryObject<Item> STURDY_PAINT_CARTRIDGE = paintCartridge("sturdy_paint_cartridge", PaintMaterial.STURDY);

    public static final RegistryObject<Item> INCOMPLETE_ANDESITE_PAINT_CARTRIDGE = simpleItem("incomplete_andesite_paint_cartridge");
    public static final RegistryObject<Item> INCOMPLETE_BRASS_PAINT_CARTRIDGE = simpleItem("incomplete_brass_paint_cartridge");
    public static final RegistryObject<Item> INCOMPLETE_COPPER_PAINT_CARTRIDGE = simpleItem("incomplete_copper_paint_cartridge");
    public static final RegistryObject<Item> INCOMPLETE_STURDY_PAINT_CARTRIDGE = simpleItem("incomplete_sturdy_paint_cartridge");

    public static final RegistryObject<Item> PAINT_GUN = paintGun("paint_gun", false);
    public static final RegistryObject<Item> C_PAINT_GUN = paintGun("creative_paint_gun", true);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static RegistryObject<Item> simpleItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> foodItem(String name, net.minecraft.world.food.FoodProperties food) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(food)));
    }

    private static RegistryObject<Item> gummyBear(String name) {
        return foodItem(name, ModFood.GUMMY_BEAR);
    }

    private static RegistryObject<Item> paintCartridge(String name, PaintMaterial material) {
        return ITEMS.register(name, () -> new PaintCartridgeItem(material, new Item.Properties()));
    }

    private static RegistryObject<Item> paintGun(String name, boolean creative) {
        return ITEMS.register(name, () -> new PaintGunItem(creative, new Item.Properties()));
    }

    private static Item.Properties chargeProperties() {
        return new Item.Properties().stacksTo(16).fireResistant().rarity(Rarity.UNCOMMON);
    }
}
