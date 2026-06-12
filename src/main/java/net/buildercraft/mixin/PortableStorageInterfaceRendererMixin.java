package net.buildercraft.mixin;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.buildercraft.block.create.portableInterface.PaintedPortableFluidInterfaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PortableStorageInterfaceRenderer.class, remap = false)
public class PortableStorageInterfaceRendererMixin {

    @Inject(
            method = "getMiddleForState(Lnet/minecraft/world/level/block/state/BlockState;Z)Ldev/engine_room/flywheel/lib/model/baked/PartialModel;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onGetMiddleForState(BlockState state, boolean lit, CallbackInfoReturnable<PartialModel> cir) {
        if (state.getBlock() instanceof PaintedPortableFluidInterfaceBlock) {
            cir.setReturnValue(lit ? AllPartialModels.PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED
                    : AllPartialModels.PORTABLE_FLUID_INTERFACE_MIDDLE);
        }
    }

    @Inject(
            method = "getTopForState(Lnet/minecraft/world/level/block/state/BlockState;)Ldev/engine_room/flywheel/lib/model/baked/PartialModel;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onGetTopForState(BlockState state, CallbackInfoReturnable<PartialModel> cir) {
        if (state.getBlock() instanceof PaintedPortableFluidInterfaceBlock) {
            cir.setReturnValue(AllPartialModels.PORTABLE_FLUID_INTERFACE_TOP);
        }
    }
}
