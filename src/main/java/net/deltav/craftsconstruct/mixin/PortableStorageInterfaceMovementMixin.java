package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceMovement;
import net.minecraft.core.registries.BuiltInRegistries;
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
            target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;",
            remap = true
        ),
        remap = false
    )
    private Block onGetBlockCompare(BlockState instance) {
        Block block = instance.getBlock();
        String path = BuiltInRegistries.BLOCK.getKey(block).getPath();
        if (block == AllBlocks.PORTABLE_FLUID_INTERFACE.get() || path.endsWith("portable_fluid_interface")) {
            return AllBlocks.PORTABLE_FLUID_INTERFACE.get();
        }
        return block;
    }
}
