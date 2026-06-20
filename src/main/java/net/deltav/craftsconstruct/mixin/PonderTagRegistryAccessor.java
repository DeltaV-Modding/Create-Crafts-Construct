package net.deltav.craftsconstruct.mixin;

import com.google.common.collect.Multimap;
import net.createmod.ponder.foundation.registration.PonderTagRegistry;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = PonderTagRegistry.class, remap = false)
public interface PonderTagRegistryAccessor {
    @Accessor("componentTagMap")
    Multimap<ResourceLocation, ResourceLocation> getComponentTagMap();
}
