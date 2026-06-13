package net.deltav.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import net.neoforged.neoforge.capabilities.BlockCapabilityCache;

@Mixin(targets = "com.simibubi.create.foundation.ICapabilityProvider$BlockCapabilityCacheProvider", remap = false)
public class BlockCapabilityCacheProviderMixin {

    @Shadow
    private BlockCapabilityCache<?, ?> inner;

    /**
     * @author Malioanro
     * @reason Prevent crash when getCapability is called on an invalid cache during block painting/updates
     */
    @Overwrite
    public Object getCapability() {
        try {
            return this.inner.getCapability();
        } catch (IllegalStateException e) {
            return null;
        }
    }
}
