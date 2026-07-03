package net.deltav.craftsconstruct.data;

import net.deltav.craftsconstruct.block.SugarBeetCropBlock;
import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, craftsconstruct.MOD_ID, exFileHelper);
    }
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
                new ResourceLocation(craftsconstruct.MOD_ID, "block/sugar_beets/" + textureName + state.getValue(((SugarBeetCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}
