package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.tank.BoilerData;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = BoilerData.class, remap = false)
public class BoilerDataMixin {
    @Redirect(
            method = "evaluate",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"),
            require = 2
    )
    private boolean craftsConstruct$recognizePaintedBoilerAttachments(
            com.tterrag.registrate.util.entry.BlockEntry<?> entry, BlockState state) {
        if (entry.getId().equals(AllBlocks.STEAM_ENGINE.getId()))
            return entry.has(state) || state.getBlock() instanceof SteamEngineBlock;
        if (entry.getId().equals(AllBlocks.STEAM_WHISTLE.getId()))
            return entry.has(state) || state.getBlock() instanceof WhistleBlock;
        return entry.has(state);
    }

    @Redirect(
            method = "checkPipeOrganAdvancement",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z")
    )
    private boolean craftsConstruct$recognizePaintedWhistlesForPipeOrgan(
            com.tterrag.registrate.util.entry.BlockEntry<?> entry, BlockState state) {
        if (entry.getId().equals(AllBlocks.STEAM_WHISTLE.getId())) {
            return entry.has(state) || state.getBlock() instanceof WhistleBlock;
        }
        return entry.has(state);
    }
}
