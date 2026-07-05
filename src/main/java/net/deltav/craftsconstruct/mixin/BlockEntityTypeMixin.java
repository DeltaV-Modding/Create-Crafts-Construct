package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.AllBlockEntityTypes;
import net.deltav.craftsconstruct.registry.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void craftsConstruct$isValid(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this != AllBlockEntityTypes.VALVE_HANDLE.get()) {
            return;
        }

        if (state.is(ModBlocks.ANDESITE_VALVE_HANDLE.get())
                || state.is(ModBlocks.BRASS_VALVE_HANDLE.get())
                || state.is(ModBlocks.STURDY_VALVE_HANDLE.get())) {
            cir.setReturnValue(true);
        }
    }
}
