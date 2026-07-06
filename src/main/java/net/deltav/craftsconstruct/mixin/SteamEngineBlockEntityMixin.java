package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SteamEngineBlockEntity.class, remap = false)
public class SteamEngineBlockEntityMixin {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void craftsConstruct$recognizePaintedTank(CallbackInfoReturnable<Boolean> cir) {
        SteamEngineBlockEntity be = (SteamEngineBlockEntity) (Object) this;
        BlockState state = be.getBlockState();
        if (!(state.getBlock() instanceof SteamEngineBlock)) {
            return;
        }

        Level level = be.getLevel();
        if (level == null) {
            cir.setReturnValue(false);
            return;
        }

        Direction tankDirection = SteamEngineBlock.getConnectedDirection(state)
                .getOpposite();
        BlockState tankState = level.getBlockState(be.getBlockPos()
                .relative(tankDirection));
        if (FluidTankBlock.isTank(tankState)) {
            cir.setReturnValue(true);
        }
    }

    @Redirect(
            method = {"tick", "getTargetAngle"},
            at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z")
    )
    private boolean craftsConstruct$recognizePaintedSteamEngine(com.tterrag.registrate.util.entry.BlockEntry<?> entry, BlockState state) {
        if (entry.getId().equals(AllBlocks.STEAM_ENGINE.getId())) {
            return entry.has(state) || state.getBlock() instanceof SteamEngineBlock;
        }
        return entry.has(state);
    }
}
