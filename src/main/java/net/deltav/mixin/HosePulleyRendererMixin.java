package net.deltav.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.render.SuperByteBuffer;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.deltav.registry.ModPartialModels;

@Mixin(value = HosePulleyRenderer.class, remap = false)
public class HosePulleyRendererMixin {

    @Inject(method = "getCoil()Ldev/engine_room/flywheel/lib/model/baked/PartialModel;", at = @At("HEAD"), cancellable = true)
    private void onGetCoil(CallbackInfoReturnable<PartialModel> cir) {
        BlockEntity be = ModPartialModels.CURRENT_BE.get();
        if (be instanceof HosePulleyBlockEntity) {
            String path = be.getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
            PartialModel coil = ModPartialModels.HOSE_PULLEY_COILS.get(path);
            if (coil != null) {
                cir.setReturnValue(coil);
            }
        }
    }

    @Inject(method = "renderRope(Lcom/simibubi/create/content/fluids/hosePulley/HosePulleyBlockEntity;)Lnet/createmod/catnip/render/SuperByteBuffer;", at = @At("HEAD"), cancellable = true)
    private void onRenderRope(HosePulleyBlockEntity be, CallbackInfoReturnable<SuperByteBuffer> cir) {
        String path = be.getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel rope = ModPartialModels.HOSE_PULLEY_ROPES.get(path);
        if (rope != null) {
            cir.setReturnValue(CachedBuffers.partial(rope, be.getBlockState()));
        }
    }

    @Inject(method = "renderMagnet(Lcom/simibubi/create/content/fluids/hosePulley/HosePulleyBlockEntity;)Lnet/createmod/catnip/render/SuperByteBuffer;", at = @At("HEAD"), cancellable = true)
    private void onRenderMagnet(HosePulleyBlockEntity be, CallbackInfoReturnable<SuperByteBuffer> cir) {
        String path = be.getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel magnet = ModPartialModels.HOSE_PULLEY_MAGNETS.get(path);
        if (magnet != null) {
            cir.setReturnValue(CachedBuffers.partial(magnet, be.getBlockState()));
        }
    }

    @Inject(method = "getCoilShift()Lnet/createmod/catnip/render/SpriteShiftEntry;", at = @At("HEAD"), cancellable = true)
    private void onGetCoilShift(CallbackInfoReturnable<SpriteShiftEntry> cir) {
        BlockEntity be = ModPartialModels.CURRENT_BE.get();
        if (be instanceof HosePulleyBlockEntity) {
            String path = be.getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
            SpriteShiftEntry shift = ModPartialModels.HOSE_PULLEY_COIL_SHIFTS.get(path);
            if (shift != null) {
                cir.setReturnValue(shift);
            }
        }
    }
}
