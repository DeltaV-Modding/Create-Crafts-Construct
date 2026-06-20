package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SteamEngineBlockEntity.class, remap = false)
public class SteamEngineBlockEntityMixin {
    @Redirect(
            method = {"tick", "getTargetAngle"},
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z")
    )
    private boolean craftsConstruct$recognizePaintedSteamEngine(com.tterrag.registrate.util.entry.BlockEntry<?> entry, BlockState state) {
        if (entry == AllBlocks.STEAM_ENGINE) {
            return entry.has(state) || state.getBlock() instanceof SteamEngineBlock;
        }
        return entry.has(state);
    }
}
