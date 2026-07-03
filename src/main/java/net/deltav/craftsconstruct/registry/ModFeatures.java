package net.deltav.craftsconstruct.registry;

import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.worldgen.feature.WildSugarBeetsFeature;
import net.deltav.craftsconstruct.worldgen.feature.WildSugarBeetsFeatureConfigurator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    private static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, craftsconstruct.MOD_ID);

    public static final RegistryObject<WildSugarBeetsFeature> WILD_SUGAR_BEETS = REGISTER.register("wild_sugar_beets", () -> new WildSugarBeetsFeature(WildSugarBeetsFeatureConfigurator.CODEC));

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

}
