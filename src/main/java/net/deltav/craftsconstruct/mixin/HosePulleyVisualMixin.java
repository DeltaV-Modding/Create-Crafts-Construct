package net.deltav.craftsconstruct.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import dev.engine_room.flywheel.api.instance.Instancer;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import com.simibubi.create.content.processing.burner.ScrollInstance;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.deltav.craftsconstruct.registry.ModPartialModels;

@Mixin(value = HosePulleyVisual.class, remap = false)
public abstract class HosePulleyVisualMixin {

    private dev.engine_room.flywheel.api.visualization.VisualizationContext crafts_construct$getVisualizationContext() {
        return ((AbstractVisualAccessor) (Object) this).crafts_construct$getVisualizationContext();
    }

    private HosePulleyBlockEntity crafts_construct$getBlockEntity() {
        return (HosePulleyBlockEntity) ((AbstractBlockEntityVisualAccessor) (Object) this).crafts_construct$getBlockEntity();
    }

    @Inject(method = "getRopeModel()Ldev/engine_room/flywheel/api/instance/Instancer;", at = @At("HEAD"), cancellable = true)
    private void onGetRopeModel(CallbackInfoReturnable<Instancer<TransformedInstance>> cir) {
        String path = this.crafts_construct$getBlockEntity().getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel rope = ModPartialModels.HOSE_PULLEY_ROPES.get(path);
        if (rope != null) {
            cir.setReturnValue(this.crafts_construct$getVisualizationContext().instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(rope)));
        }
    }

    @Inject(method = "getMagnetModel()Ldev/engine_room/flywheel/api/instance/Instancer;", at = @At("HEAD"), cancellable = true)
    private void onGetMagnetModel(CallbackInfoReturnable<Instancer<TransformedInstance>> cir) {
        String path = this.crafts_construct$getBlockEntity().getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel magnet = ModPartialModels.HOSE_PULLEY_MAGNETS.get(path);
        if (magnet != null) {
            cir.setReturnValue(this.crafts_construct$getVisualizationContext().instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(magnet)));
        }
    }

    @Inject(method = "getHalfMagnetModel()Ldev/engine_room/flywheel/api/instance/Instancer;", at = @At("HEAD"), cancellable = true)
    private void onGetHalfMagnetModel(CallbackInfoReturnable<Instancer<TransformedInstance>> cir) {
        String path = this.crafts_construct$getBlockEntity().getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel halfMagnet = ModPartialModels.HOSE_PULLEY_ROPES_HALF_MAGNET.get(path);
        if (halfMagnet != null) {
            cir.setReturnValue(this.crafts_construct$getVisualizationContext().instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(halfMagnet)));
        }
    }

    @Inject(method = "getHalfRopeModel()Ldev/engine_room/flywheel/api/instance/Instancer;", at = @At("HEAD"), cancellable = true)
    private void onGetHalfRopeModel(CallbackInfoReturnable<Instancer<TransformedInstance>> cir) {
        String path = this.crafts_construct$getBlockEntity().getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel halfRope = ModPartialModels.HOSE_PULLEY_ROPES_HALF.get(path);
        if (halfRope != null) {
            cir.setReturnValue(this.crafts_construct$getVisualizationContext().instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(halfRope)));
        }
    }

    @Inject(method = "getCoilModel()Ldev/engine_room/flywheel/api/instance/Instancer;", at = @At("HEAD"), cancellable = true)
    private void onGetCoilModel(CallbackInfoReturnable<Instancer<ScrollInstance>> cir) {
        String path = this.crafts_construct$getBlockEntity().getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel coil = ModPartialModels.HOSE_PULLEY_COILS.get(path);
        if (coil != null) {
            cir.setReturnValue(this.crafts_construct$getVisualizationContext().instancerProvider().instancer(AllInstanceTypes.SCROLLING, Models.partial(coil)));
        }
    }

    @Inject(method = "getCoilAnimation()Lnet/createmod/catnip/render/SpriteShiftEntry;", at = @At("HEAD"), cancellable = true)
    private void onGetCoilAnimation(CallbackInfoReturnable<SpriteShiftEntry> cir) {
        String path = this.crafts_construct$getBlockEntity().getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        SpriteShiftEntry shift = ModPartialModels.HOSE_PULLEY_COIL_SHIFTS.get(path);
        if (shift != null) {
            cir.setReturnValue(shift);
        }
    }
}
