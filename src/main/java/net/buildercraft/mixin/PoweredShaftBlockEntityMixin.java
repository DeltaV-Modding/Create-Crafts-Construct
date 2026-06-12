package net.buildercraft.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedSteamEngineBlock;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PoweredShaftBlockEntity.class, remap = false)
public class PoweredShaftBlockEntityMixin {
    @Redirect(
            method = "getCombinedCapacity",
            at = @At(value = "INVOKE", target = "Lcom/simibubi/create/api/stress/BlockStressValues;getCapacity(Lnet/minecraft/world/level/block/Block;)D")
    )
    private double craftsConstruct$redirectGetCapacity(Block block) {
        if (block instanceof PaintedSteamEngineBlock) {
            return BlockStressValues.getCapacity(AllBlocks.STEAM_ENGINE.get());
        }
        return BlockStressValues.getCapacity(block);
    }
}
