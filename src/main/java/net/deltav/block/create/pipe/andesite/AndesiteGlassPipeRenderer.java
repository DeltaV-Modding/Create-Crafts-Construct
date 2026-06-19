package net.deltav.block.create.pipe.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.PipeConnection.Flow;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import net.createmod.catnip.animation.LerpedFloat;
import net.createmod.catnip.data.Iterate;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;

/**
 * Renders the fluid stream inside painted fluid pipes when they are in glass mode.
 */
public class AndesiteGlassPipeRenderer extends SafeBlockEntityRenderer<AndesiteFluidPipeBlockEntity> {

    public AndesiteGlassPipeRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    protected void renderSafe(AndesiteFluidPipeBlockEntity be, float partialTicks, PoseStack ms,
                              MultiBufferSource buffer, int light, int overlay) {
        BlockState state = be.getBlockState();
        if (!state.hasProperty(AndesiteFluidPipeBlock.GLASS) || !state.getValue(AndesiteFluidPipeBlock.GLASS))
            return;

        FluidTransportBehaviour pipe = be.getBehaviour(FluidTransportBehaviour.TYPE);
        if (pipe == null)
            return;

        for (Direction side : Iterate.directions) {
            Flow flow = pipe.getFlow(side);
            if (flow == null) continue;
            FluidStack fluidStack = flow.fluid;
            if (fluidStack.isEmpty()) continue;
            LerpedFloat progress = flow.progress;
            if (progress == null) continue;

            float value = progress.getValue(partialTicks);
            boolean inbound = flow.inbound;
            if (value == 1) {
                if (inbound) {
                    Flow opposite = pipe.getFlow(side.getOpposite());
                    if (opposite == null)
                        value -= 1e-6f;
                } else {
                    FluidTransportBehaviour adjacent = BlockEntityBehaviour.get(be.getLevel(),
                            be.getBlockPos().relative(side), FluidTransportBehaviour.TYPE);
                    if (adjacent == null)
                        value -= 1e-6f;
                    else {
                        Flow other = adjacent.getFlow(side.getOpposite());
                        if (other == null || !other.inbound && !other.complete)
                            value -= 1e-6f;
                    }
                }
            }

            FluidRenderer.renderFluidStream(fluidStack, side, 3 / 16f, value, inbound, buffer, ms, light);
        }
    }
}