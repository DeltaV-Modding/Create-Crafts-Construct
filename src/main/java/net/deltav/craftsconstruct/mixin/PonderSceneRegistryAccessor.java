package net.deltav.craftsconstruct.mixin;

import com.google.common.collect.Multimap;
import net.createmod.ponder.api.registration.StoryBoardEntry;
import net.createmod.ponder.foundation.registration.PonderSceneRegistry;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = PonderSceneRegistry.class, remap = false)
public interface PonderSceneRegistryAccessor {
    @Accessor("scenes")
    Multimap<ResourceLocation, StoryBoardEntry> getScenes();
}
