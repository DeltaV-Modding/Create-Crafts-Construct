package net.buildercraft.data;

import net.buildercraft.block.SugarBeetCropBlock;
import net.buildercraft.craftsconstruct;
import net.buildercraft.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, craftsconstruct.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeCrop(((CropBlock) ModBlocks.SUGAR_BEETS.get()), "sugar_beets_stage", "sugar_beets_stage");
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((SugarBeetCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/sugar_beets/" + textureName + state.getValue(((SugarBeetCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}
