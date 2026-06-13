package net.buildercraft.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.buildercraft.registry.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FluidPropagator.class, remap = false)
public class FluidPropagatorMixin {

    @Redirect(
        method = "propagateChangedPipe",
        at = @At(
            value = "INVOKE",
            target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"
        )
    )
    private static boolean onHas(BlockEntry<?> instance, BlockState state) {
        if (instance == AllBlocks.MECHANICAL_PUMP) {
            return instance.has(state) || ModBlocks.PAINTED_MECHANICAL_PUMPS.stream().anyMatch(block -> state.is(block.get()));
        }
        return instance.has(state);
    }
}
