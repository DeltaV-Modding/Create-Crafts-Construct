package net.deltav.craftsconstruct.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankRenderer;
import net.deltav.craftsconstruct.block.create.fluid.andesite.AndesiteFluidTankRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FluidTankRenderer.class, remap = false)
public class FluidTankRendererMixin {
    @Inject(
            method = "renderAsBoiler(Lcom/simibubi/create/content/fluids/tank/FluidTankBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onRenderAsBoiler(FluidTankBlockEntity tank, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                                  int light, int overlay, CallbackInfo ci) {
        if (AndesiteFluidTankRenderer.renderPaintedBoiler(tank, partialTicks, ms, buffer, light, overlay)) {
            ci.cancel();
        }
    }
}
