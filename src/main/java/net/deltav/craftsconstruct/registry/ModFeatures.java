package net.deltav.craftsconstruct.registry;

import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.worldgen.feature.WildSugarBeetsFeature;
import net.deltav.craftsconstruct.worldgen.feature.WildSugarBeetsFeatureConfigurator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {

    private static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(BuiltInRegistries.FEATURE, craftsconstruct.MOD_ID);

    public static final DeferredHolder<Feature<?>, WildSugarBeetsFeature> WILD_SUGAR_BEETS = REGISTER.register("wild_sugar_beets", () -> new WildSugarBeetsFeature(WildSugarBeetsFeatureConfigurator.CODEC));

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

}
