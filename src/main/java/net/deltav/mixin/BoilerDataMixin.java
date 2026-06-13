package net.deltav.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.tank.BoilerData;

import net.deltav.block.create.kinetic.PaintedSteamEngineBlock;
import net.deltav.block.create.kinetic.PaintedSteamWhistleBlock;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoilerData.class)
public class BoilerDataMixin {
    @Redirect(
            method = "evaluate",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"),
            require = 2
    )
    private boolean craftsConstruct$recognizePaintedBoilerAttachments(
            com.tterrag.registrate.util.entry.BlockEntry<?> entry, BlockState state) {
        if (entry == AllBlocks.STEAM_ENGINE)
            return entry.has(state) || state.getBlock() instanceof PaintedSteamEngineBlock;
        if (entry == AllBlocks.STEAM_WHISTLE)
            return entry.has(state) || state.getBlock() instanceof PaintedSteamWhistleBlock;
        return entry.has(state);
    }

    @Redirect(
            method = "checkPipeOrganAdvancement",
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z")
    )
    private boolean craftsConstruct$recognizePaintedWhistlesForPipeOrgan(
            com.tterrag.registrate.util.entry.BlockEntry<?> entry, BlockState state) {
        return entry.has(state) || state.getBlock() instanceof PaintedSteamWhistleBlock;
    }
}
