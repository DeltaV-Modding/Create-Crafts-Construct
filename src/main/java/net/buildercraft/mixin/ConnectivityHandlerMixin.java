package net.buildercraft.mixin;

import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.foundation.blockEntity.IMultiBlockEntityContainer;
import net.buildercraft.util.ModConnectivityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ConnectivityHandler.class, remap = false)
public class ConnectivityHandlerMixin {

    @Inject(method = "formMulti", at = @At("HEAD"))
    private static <T extends BlockEntity & IMultiBlockEntityContainer> void onFormMultiHead(T be, CallbackInfo ci) {
        if (be != null) {
            ModConnectivityHelper.CURRENT_SEARCH_BLOCK.set(be.getBlockState().getBlock());
        }
    }

    @Inject(method = "formMulti", at = @At("RETURN"))
    private static <T extends BlockEntity & IMultiBlockEntityContainer> void onFormMultiReturn(T be, CallbackInfo ci) {
        ModConnectivityHelper.CURRENT_SEARCH_BLOCK.remove();
    }

    @Inject(method = "partAt", at = @At("RETURN"), cancellable = true)
    private static <T extends BlockEntity & IMultiBlockEntityContainer> void onPartAt(
            BlockEntityType<?> type, BlockGetter level, BlockPos pos, CallbackInfoReturnable<T> cir) {
        T result = cir.getReturnValue();
        if (result != null) {
            Block searchBlock = ModConnectivityHelper.CURRENT_SEARCH_BLOCK.get();
            if (searchBlock != null) {
                Block neighborBlock = result.getBlockState().getBlock();
                if (neighborBlock != searchBlock) {
                    cir.setReturnValue(null);
                }
            }
        }
    }
}
