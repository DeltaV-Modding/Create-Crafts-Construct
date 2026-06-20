/*

package net.deltav.mixin;

import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FluidPipeBlock.class, remap = false)
public class FluidPipeBlockMixin {
    @Inject(method = "shouldDrawRim", at = @At("HEAD"), cancellable = true)
    private static void onShouldDrawRim(BlockAndTintGetter world, BlockPos pos, BlockState state, Direction direction,
                                        CallbackInfoReturnable<Boolean> cir) {
        BlockPos offsetPos = pos.relative(direction);
        BlockState facingState = world.getBlockState(offsetPos);

        if ((craftsconstruct$isGlassPipe(state) && craftsconstruct$isNormalPipe(facingState)) ||
            (craftsconstruct$isNormalPipe(state) && craftsconstruct$isGlassPipe(facingState))) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canConnectTo", at = @At("HEAD"), cancellable = true)
    private static void onCanConnectTo(BlockAndTintGetter world, BlockPos neighbourPos, BlockState neighbour,
                                       Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (craftsconstruct$isGlassPipe(neighbour)) {
            Direction.Axis glassAxis = neighbour.getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS);
            if (direction.getAxis() != glassAxis) {
                cir.setReturnValue(false);
            }
        }
    }

    @Unique
    private static boolean craftsconstruct$isGlassPipe(BlockState state) {
        if (state.getBlock() instanceof com.simibubi.create.content.fluids.pipes.GlassFluidPipeBlock) {
            return true;
        }
        for (var prop : state.getProperties()) {
            if (prop.getName().equals("glass") && prop instanceof net.minecraft.world.level.block.state.properties.BooleanProperty bp) {
                return state.getValue(bp);
            }
        }
        return false;
    }

    @Unique
    private static boolean craftsconstruct$isNormalPipe(BlockState state) {
        if (state.getBlock() instanceof FluidPipeBlock) {
            for (var prop : state.getProperties()) {
                if (prop.getName().equals("glass") && prop instanceof net.minecraft.world.level.block.state.properties.BooleanProperty bp) {
                    return !state.getValue(bp);
                }
            }
            return true;
        }
        return false;
    }
}


 */