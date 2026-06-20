package net.deltav.craftsconstruct.mixin;

import dev.engine_room.flywheel.lib.visual.AbstractBlockEntityVisual;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = AbstractBlockEntityVisual.class, remap = false)
public interface AbstractBlockEntityVisualAccessor {
    @Accessor("blockEntity")
    BlockEntity crafts_construct$getBlockEntity();
}
