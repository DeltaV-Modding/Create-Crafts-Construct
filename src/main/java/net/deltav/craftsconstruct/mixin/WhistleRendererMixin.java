package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock.WhistleSize;
import com.simibubi.create.content.decoration.steamWhistle.WhistleRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.render.CachedBuffers;
import net.deltav.craftsconstruct.registry.ModPartialModels;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WhistleRenderer.class, remap = false)
public class WhistleRendererMixin {
    @Redirect(
            method = "renderSafe",
            at = @At(value = "INVOKE", target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;")
    )
    private net.createmod.catnip.render.SuperByteBuffer craftsConstruct$renderCustomWhistleMouth(PartialModel model, BlockState state) {
        String path = state.getBlock().builtInRegistryHolder().key().location().getPath();
        WhistleSize size = state.getValue(WhistleBlock.SIZE);
        PartialModel custom = switch (size) {
            case LARGE -> ModPartialModels.WHISTLE_MOUTHS_LARGE.get(path);
            case MEDIUM -> ModPartialModels.WHISTLE_MOUTHS_MEDIUM.get(path);
            case SMALL -> ModPartialModels.WHISTLE_MOUTHS_SMALL.get(path);
        };
        return CachedBuffers.partial(custom != null ? custom : model, state);
    }
}
