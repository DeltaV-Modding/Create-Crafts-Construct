package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.deltav.craftsconstruct.registry.ModPartialModels;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PortableStorageInterfaceRenderer.class, remap = false)
public class PortableStorageInterfaceRendererMixin {

    @Redirect(
            method = "getMiddleForState(Lnet/minecraft/world/level/block/state/BlockState;Z)Ldev/engine_room/flywheel/lib/model/baked/PartialModel;",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z")
    )
    private static boolean onHasMiddle(BlockEntry<?> instance, BlockState state) {
        return instance.has(state) || BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().endsWith("portable_fluid_interface");
    }

    @Inject(
            method = "getTopForState(Lnet/minecraft/world/level/block/state/BlockState;)Ldev/engine_room/flywheel/lib/model/baked/PartialModel;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onGetTopForState(BlockState state, CallbackInfoReturnable<PartialModel> cir) {
        String path = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
        if (path.endsWith("portable_fluid_interface")) {
            PartialModel model = ModPartialModels.PORTABLE_FLUID_INTERFACE_TOP.get(path);
            if (model != null) {
                cir.setReturnValue(model);
            }
        }
    }
}
