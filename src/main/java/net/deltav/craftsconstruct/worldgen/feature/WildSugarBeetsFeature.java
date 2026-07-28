package net.deltav.craftsconstruct.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

// Credit: Farmer's Delight
public class WildSugarBeetsFeature extends Feature<WildSugarBeetsFeatureConfigurator> {
    public WildSugarBeetsFeature(Codec<WildSugarBeetsFeatureConfigurator> codec) {
        super(codec);
    }
    public boolean place(FeaturePlaceContext<WildSugarBeetsFeatureConfigurator> context) {
        WildSugarBeetsFeatureConfigurator config = context.config();
        BlockPos origin = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();

        int i = 0;
        int tries = config.tries();
        int xzSpread = config.xzSpread() + 1;
        int ySpread = config.ySpread() + 1;

        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        Holder<PlacedFeature> floorFeature = config.floorFeature();
        if (floorFeature != null) {
            for (int j = 0; j < tries; ++j) {
                mutablePos.setWithOffset(origin, random.nextInt(xzSpread) - random.nextInt(xzSpread), random.nextInt(ySpread) - random.nextInt(ySpread), random.nextInt(xzSpread) - random.nextInt(xzSpread));
                if (config.floorFeature().value().place(level, context.chunkGenerator(), random, mutablePos)) {
                    ++i;
                }
            }
        }

        int primaryXZSpread = Math.max(1, xzSpread - 2);
        for (int k = 0; k < tries; ++k) {
            mutablePos.setWithOffset(origin, random.nextInt(primaryXZSpread) - random.nextInt(primaryXZSpread), random.nextInt(ySpread) - random.nextInt(ySpread), random.nextInt(primaryXZSpread) - random.nextInt(primaryXZSpread));
            if (config.primaryFeature().value().place(level, context.chunkGenerator(), random, mutablePos)) {
                ++i;
            }
        }

        return i > 0;
    }
}
