package net.deltav.mixin;

import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.deltav.block.create.portableInterface.PaintedPortableFluidInterfaceBlock;
import net.deltav.registry.ModPartialModels;
import net.minecraft.core.registries.BuiltInRegistries;
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
            String path = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
            PartialModel model = lit ? ModPartialModels.PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED.get(path)
                    : ModPartialModels.PORTABLE_FLUID_INTERFACE_MIDDLE.get(path);
            if (model != null) {
                cir.setReturnValue(model);
            }
        }
    }

    @Inject(
            method = "getTopForState(Lnet/minecraft/world/level/block/state/BlockState;)Ldev/engine_room/flywheel/lib/model/baked/PartialModel;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onGetTopForState(BlockState state, CallbackInfoReturnable<PartialModel> cir) {
        if (state.getBlock() instanceof PaintedPortableFluidInterfaceBlock) {
            String path = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
            PartialModel model = ModPartialModels.PORTABLE_FLUID_INTERFACE_TOP.get(path);
            if (model != null) {
                cir.setReturnValue(model);
            }
        }
    }
}
