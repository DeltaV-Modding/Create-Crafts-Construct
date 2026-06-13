package net.deltav.mixin;

import net.createmod.ponder.foundation.ui.PonderUI;
import net.createmod.ponder.foundation.PonderTag;
import net.minecraft.world.item.ItemStack;
import net.deltav.util.PonderSubjectHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PonderUI.class, remap = false)
public class PonderUIMixin {

    @Inject(method = "of(Lnet/minecraft/world/item/ItemStack;)Lnet/createmod/ponder/foundation/ui/PonderUI;", at = @At("HEAD"))
    private static void onOf(ItemStack item, CallbackInfoReturnable<?> cir) {
        PonderSubjectHolder.currentSubject = item;
    }

    @Inject(method = "of(Lnet/minecraft/world/item/ItemStack;Lnet/createmod/ponder/foundation/PonderTag;)Lnet/createmod/ponder/foundation/ui/PonderUI;", at = @At("HEAD"))
    private static void onOfWithTag(ItemStack item, PonderTag tag, CallbackInfoReturnable<?> cir) {
        PonderSubjectHolder.currentSubject = item;
    }
}
