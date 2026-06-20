package net.deltav.craftsconstruct.mixin;

import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlock.Shape;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FluidTankBlockEntity.class, remap = false)
public abstract class FluidTankBlockEntityMixin {

    @Shadow
    protected boolean window;
    @Shadow
    protected int height;
    @Shadow
    protected int width;

    @Inject(method = "setWindows", at = @At("HEAD"), cancellable = true)
    private void onSetWindows(boolean window, CallbackInfo ci) {
        FluidTankBlockEntity tank = (FluidTankBlockEntity) (Object) this;
        BlockState blockState = tank.getBlockState();
        if (BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).getPath().endsWith("horizontal_fluid_tank")) {
            ci.cancel();
            this.window = window;
            if (tank.getLevel() == null) return;

            Direction.Axis axis = Direction.Axis.Y;
            if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                axis = blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            }

            for (int yOffset = 0; yOffset < height; yOffset++) {
                for (int xOffset = 0; xOffset < width; xOffset++) {
                    for (int zOffset = 0; zOffset < width; zOffset++) {

                        BlockPos pos = switch (axis) {
                            case X -> tank.getBlockPos().offset(yOffset, xOffset, zOffset);
                            case Y -> tank.getBlockPos().offset(xOffset, yOffset, zOffset);
                            case Z -> tank.getBlockPos().offset(xOffset, zOffset, yOffset);
                        };

                        BlockState state = tank.getLevel().getBlockState(pos);
                        if (!FluidTankBlock.isTank(state))
                            continue;

                        Shape shape = Shape.PLAIN;
                        if (window) {
                            if (width == 1)
                                shape = Shape.WINDOW;
                            if (width == 2)
                                shape = xOffset == 0 ? zOffset == 0 ? Shape.WINDOW_NW : Shape.WINDOW_SW
                                        : zOffset == 0 ? Shape.WINDOW_NE : Shape.WINDOW_SE;
                            if (width == 3 && Math.abs(Math.abs(xOffset) - Math.abs(zOffset)) == 1)
                                shape = Shape.WINDOW;
                        }

                        tank.getLevel().setBlock(pos, state.setValue(FluidTankBlock.SHAPE, shape), 22);
                    }
                }
            }
        }
    }
}
