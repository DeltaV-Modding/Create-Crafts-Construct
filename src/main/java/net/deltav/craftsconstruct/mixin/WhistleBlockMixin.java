package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WhistleBlock.class, remap = false)
public class WhistleBlockMixin {

    @Redirect(
            method = "use",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;isIn(Lnet/minecraft/world/item/ItemStack;)Z", remap = false),
            remap = true
    )
    private boolean craftsConstruct$allowCustomWhistlesToExtend(com.tterrag.registrate.util.entry.BlockEntry<?> entry, ItemStack stack) {
        if (entry.getId().equals(AllBlocks.STEAM_WHISTLE.getId())) {
            if (entry.isIn(stack)) {
                return true;
            }
            if (stack.getItem() instanceof BlockItem blockItem) {
                return blockItem.getBlock() instanceof WhistleBlock;
            }
        }
        return entry.isIn(stack);
    }
}
