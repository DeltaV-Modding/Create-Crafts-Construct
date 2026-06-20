package net.deltav.craftsconstruct.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.simibubi.create.content.contraptions.pulley.AbstractPulleyRenderer;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.world.level.block.state.BlockState;
import net.deltav.craftsconstruct.registry.ModPartialModels;

@Mixin(value = AbstractPulleyRenderer.class, remap = false)
public abstract class AbstractPulleyRendererMixin<T extends KineticBlockEntity> {
    @Shadow private PartialModel halfRope;
    @Shadow private PartialModel halfMagnet;

    @Unique
    private PartialModel originalHalfRope;
    @Unique
    private PartialModel originalHalfMagnet;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        this.originalHalfRope = this.halfRope;
        this.originalHalfMagnet = this.halfMagnet;
    }

    @Inject(method = "renderSafe(Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("HEAD"))
    private void beforeRenderSafe(T be, float partialTicks, com.mojang.blaze3d.vertex.PoseStack ms, net.minecraft.client.renderer.MultiBufferSource buffer, int light, int overlay, CallbackInfo ci) {
        ModPartialModels.CURRENT_BE.set(be);
        BlockState state = be.getBlockState();
        String path = state.getBlock().builtInRegistryHolder().key().location().getPath();
        
        PartialModel customHalfRope = ModPartialModels.HOSE_PULLEY_ROPES_HALF.get(path);
        this.halfRope = (customHalfRope != null) ? customHalfRope : originalHalfRope;
        
        PartialModel customHalfMagnet = ModPartialModels.HOSE_PULLEY_ROPES_HALF_MAGNET.get(path);
        this.halfMagnet = (customHalfMagnet != null) ? customHalfMagnet : originalHalfMagnet;
    }

    @Inject(method = "renderSafe(Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("RETURN"))
    private void afterRenderSafe(T be, float partialTicks, com.mojang.blaze3d.vertex.PoseStack ms, net.minecraft.client.renderer.MultiBufferSource buffer, int light, int overlay, CallbackInfo ci) {
        ModPartialModels.CURRENT_BE.remove();
    }
}
