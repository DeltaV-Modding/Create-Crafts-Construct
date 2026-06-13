package net.deltav.mixin;

import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.content.fluids.FluidTransportBehaviour.AttachmentTypes;
import net.deltav.block.create.pipe.PaintedFluidPipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PipeAttachmentModel.class, remap = false)
public class PipeAttachmentModelMixin {
    @Redirect(
        method = "gatherModelData",
        at = @At(
            value = "INVOKE",
            target = "Lcom/simibubi/create/content/fluids/FluidTransportBehaviour;getRenderedRimAttachment(Lnet/minecraft/world/level/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Lcom/simibubi/create/content/fluids/FluidTransportBehaviour$AttachmentTypes;"
        )
    )
    private AttachmentTypes redirectGetRenderedRimAttachment(com.simibubi.create.content.fluids.FluidTransportBehaviour instance,
                                                            BlockAndTintGetter world, BlockPos pos, BlockState state, Direction direction) {
        AttachmentTypes attachment = instance.getRenderedRimAttachment(world, pos, state, direction);
        if (state.hasProperty(PaintedFluidPipeBlock.GLASS) && state.getValue(PaintedFluidPipeBlock.GLASS)) {
            Direction.Axis axis = state.getValue(PaintedFluidPipeBlock.AXIS);
            if (direction.getAxis() != axis) {
                return AttachmentTypes.NONE;
            } else {
                BlockState otherState = world.getBlockState(pos.relative(direction));
                if (com.simibubi.create.content.fluids.pipes.FluidPipeBlock.canConnectTo(world, pos.relative(direction), otherState, direction)) {
                    return AttachmentTypes.NONE;
                } else {
                    return attachment.withoutConnector();
                }
            }
        }
        return attachment;
    }
}
