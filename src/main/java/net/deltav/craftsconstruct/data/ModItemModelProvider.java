package net.deltav.craftsconstruct.data;

import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, craftsconstruct.MOD_ID, existingFileHelper);
    }
    protected void registerModels() {
        basicItem(ModItems.SUGAR_BEET.get());
        basicItem(ModItems.SUGAR_BEET_SEEDS.get());
    }
    private ItemModelBuilder handheldItem(RegistryObject<?> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID,"item/" + item.getId().getPath()));
    }
}
