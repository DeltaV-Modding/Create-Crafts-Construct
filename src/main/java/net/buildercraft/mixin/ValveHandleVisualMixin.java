package net.buildercraft.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.crank.HandCrankBlockEntity;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import com.simibubi.create.content.kinetics.crank.ValveHandleVisual;
import net.buildercraft.registry.ModPartialModels;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.core.registries.BuiltInRegistries;

@Mixin(value = ValveHandleVisual.class, remap = false)
public class ValveHandleVisualMixin {
    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Ldev/engine_room/flywheel/lib/model/Models;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;)Ldev/engine_room/flywheel/api/model/Model;")
    )
    private dev.engine_room.flywheel.api.model.Model craftsConstruct$redirectCrankModel(
            dev.engine_room.flywheel.lib.model.baked.PartialModel partialModel,
            dev.engine_room.flywheel.api.visualization.VisualizationContext modelManager,
            HandCrankBlockEntity blockEntity,
            float partialTick
    ) {
        BlockState state = blockEntity.getBlockState();
        if (state.getBlock() instanceof ValveHandleBlock vhb && vhb.color == null && !state.is(AllBlocks.COPPER_VALVE_HANDLE.get())) {
            String path = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
            dev.engine_room.flywheel.lib.model.baked.PartialModel model = ModPartialModels.VALVE_HANDLES.get(path);
            if (model != null) {
                return dev.engine_room.flywheel.lib.model.Models.partial(model);
            }
        }
        return dev.engine_room.flywheel.lib.model.Models.partial(partialModel);
    }
}
