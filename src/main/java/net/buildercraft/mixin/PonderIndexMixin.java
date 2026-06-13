package net.buildercraft.mixin;

import com.google.common.collect.Multimap;
import net.createmod.ponder.foundation.PonderIndex;
import net.createmod.ponder.foundation.registration.PonderSceneRegistry;
import net.createmod.ponder.foundation.registration.PonderTagRegistry;
import net.createmod.ponder.api.registration.StoryBoardEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.buildercraft.util.PaintMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(value = PonderIndex.class, remap = false)
public class PonderIndexMixin {

    @Shadow
    private static PonderSceneRegistry SCENES;

    @Shadow
    private static PonderTagRegistry TAGS;

    @Inject(method = "registerAll", at = @At("RETURN"))
    private static void onRegisterAll(CallbackInfo ci) {
        Multimap<ResourceLocation, StoryBoardEntry> scenesMap = ((PonderSceneRegistryAccessor) SCENES).getScenes();
        List<Map.Entry<ResourceLocation, StoryBoardEntry>> scenesToCopy = new ArrayList<>(scenesMap.entries());

        for (Map.Entry<ResourceLocation, StoryBoardEntry> entry : scenesToCopy) {
            ResourceLocation originalId = entry.getKey();
            if (originalId.getNamespace().equals("create")) {
                for (PaintMaterial material : PaintMaterial.values()) {
                    ResourceLocation customId = ResourceLocation.fromNamespaceAndPath("crafts_construct", material.getSerializedName() + "_" + originalId.getPath());
                    if (BuiltInRegistries.ITEM.containsKey(customId) || BuiltInRegistries.BLOCK.containsKey(customId)) {
                        scenesMap.put(customId, entry.getValue());
                    }
                }
            }
        }

        Multimap<ResourceLocation, ResourceLocation> tagsMap = ((PonderTagRegistryAccessor) TAGS).getComponentTagMap();
        List<Map.Entry<ResourceLocation, ResourceLocation>> tagsToCopy = new ArrayList<>(tagsMap.entries());

        for (Map.Entry<ResourceLocation, ResourceLocation> entry : tagsToCopy) {
            ResourceLocation originalId = entry.getKey();
            if (originalId.getNamespace().equals("create")) {
                for (PaintMaterial material : PaintMaterial.values()) {
                    ResourceLocation customId = ResourceLocation.fromNamespaceAndPath("crafts_construct", material.getSerializedName() + "_" + originalId.getPath());
                    if (BuiltInRegistries.ITEM.containsKey(customId) || BuiltInRegistries.BLOCK.containsKey(customId)) {
                        tagsMap.put(customId, entry.getValue());
                    }
                }
            }
        }
    }
}
