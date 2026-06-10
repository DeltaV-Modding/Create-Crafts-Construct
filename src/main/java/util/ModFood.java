package util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFood {
    public static final FoodProperties GUMMY_BEAR = new FoodProperties.Builder().nutrition(5).saturationModifier(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0, true, true), 1f).fast().alwaysEdible().build();
    public static final FoodProperties SUGAR_BEET = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f)
            .build();
}