package net.deltav.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleExtenderBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WhistleExtenderBlock.class, remap = false)
public class WhistleExtenderBlockMixin {

    @Redirect(
            method = "useItemOn",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;isIn(Lnet/minecraft/world/item/ItemStack;)Z")
    )
    private boolean craftsConstruct$allowCustomWhistlesToExtendOnExtender(com.tterrag.registrate.util.entry.BlockEntry<?> entry, ItemStack stack) {
        if (entry == AllBlocks.STEAM_WHISTLE) {
            if (entry.isIn(stack)) {
                return true;
            }
            if (stack.getItem() instanceof BlockItem blockItem) {
                return blockItem.getBlock() instanceof WhistleBlock;
            }
        }
        return entry.isIn(stack);
    }

    @Redirect(
            method = "canSurvive",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z")
    )
    private boolean craftsConstruct$allowExtenderToSurviveOnCustomWhistles(com.tterrag.registrate.util.entry.BlockEntry<?> entry, BlockState state) {
        if (entry == AllBlocks.STEAM_WHISTLE) {
            return entry.has(state) || state.getBlock() instanceof WhistleBlock;
        }
        return entry.has(state);
    }

    @Redirect(
            method = "hidesNeighborFace",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z")
    )
    private boolean craftsConstruct$allowHidingNeighborFaceOnCustomWhistles(com.tterrag.registrate.util.entry.BlockEntry<?> entry, BlockState state) {
        if (entry == AllBlocks.STEAM_WHISTLE) {
            return entry.has(state) || state.getBlock() instanceof WhistleBlock;
        }
        return entry.has(state);
    }
}
