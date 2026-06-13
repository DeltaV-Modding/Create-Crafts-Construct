package net.deltav.data;

import net.deltav.block.SugarBeetCropBlock;
import net.deltav.registry.ModBlocks;
import net.deltav.registry.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.SUGAR_BEETS.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SugarBeetCropBlock.AGE, 3));

        this.add(ModBlocks.SUGAR_BEETS.get(), this.createCropDrops(ModBlocks.SUGAR_BEETS.get(),
                ModItems.SUGAR_BEET.get(), ModItems.SUGAR_BEET_SEEDS.get(), lootItemConditionBuilder));

    }
}