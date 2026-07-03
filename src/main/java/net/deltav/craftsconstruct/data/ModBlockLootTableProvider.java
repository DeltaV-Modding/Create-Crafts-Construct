package net.deltav.craftsconstruct.data;

import net.deltav.craftsconstruct.block.SugarBeetCropBlock;
import net.deltav.craftsconstruct.registry.ModBlocks;
import net.deltav.craftsconstruct.registry.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.loot.CanToolPerformAction;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), Map.of());
    }

    protected void generate() {
        LootItemCondition.Builder ripeSugarBeets = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.SUGAR_BEETS.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SugarBeetCropBlock.AGE, 3));

        this.add(ModBlocks.SUGAR_BEETS.get(), this.createCropDrops(ModBlocks.SUGAR_BEETS.get(),
                ModItems.SUGAR_BEET.get(), ModItems.SUGAR_BEET_SEEDS.get(), ripeSugarBeets));

        this.add(ModBlocks.WILD_SUGAR_BEET_BLOCK.get(), this.wildSugarBeetLoot(ModBlocks.WILD_SUGAR_BEET_BLOCK.get(),
                ModItems.SUGAR_BEET.get()));
    }

    protected LootTable.Builder wildSugarBeetLoot(Block block, Item crop) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(block)
                                        .when(CanToolPerformAction.canToolPerformAction(ToolActions.SHEARS_HARVEST)),
                                LootItem.lootTableItem(crop)
                                        .apply(ApplyExplosionDecay.explosionDecay())
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))));
    }
}
