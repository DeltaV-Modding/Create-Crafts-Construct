package net.deltav.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceMovement;
import net.deltav.block.create.portableInterface.PaintedPortableFluidInterfaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PortableStorageInterfaceMovement.class, remap = false)
public class PortableStorageInterfaceMovementMixin {

    @Redirect(
        method = "getStationaryInterfaceAt",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;"
        )
    )
    private Block onGetBlockCompare(BlockState instance) {
        Block block = instance.getBlock();
        if (block == AllBlocks.PORTABLE_FLUID_INTERFACE.get() || block instanceof PaintedPortableFluidInterfaceBlock) {
            return AllBlocks.PORTABLE_FLUID_INTERFACE.get();
        }
        return block;
    }
}
