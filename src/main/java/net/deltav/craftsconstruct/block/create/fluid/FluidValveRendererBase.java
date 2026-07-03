package net.deltav.craftsconstruct.block.create.fluid;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.animation.LerpedFloat;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

/**
 * Based on Create's FluidValveRenderer, with the pointer partial selected per painted material.
 */
public abstract class FluidValveRendererBase extends KineticBlockEntityRenderer<FluidValveBlockEntity> {
    private final Function<BlockState, PartialModel> pointerModel;

    protected FluidValveRendererBase(BlockEntityRendererProvider.Context context, Function<BlockState, PartialModel> pointerModel) {
        super(context);
        this.pointerModel = pointerModel;
    }

    @Override
    protected void renderSafe(FluidValveBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        BlockState blockState = be.getBlockState();
        SuperByteBuffer pointer = CachedBuffers.partial(pointerModel.apply(blockState), blockState);
        Direction facing = blockState.getValue(FluidValveBlock.FACING);

        LerpedFloat pointerState = FluidValvePointerAccess.get(be);
        float pointerRotation = Mth.lerp(pointerState.getValue(partialTicks), 0, -90);
        Axis pipeAxis = FluidValveBlock.getPipeAxis(blockState);
        Axis shaftAxis = getRotationAxisOf(be);

        int pointerRotationOffset = 0;
        if (pipeAxis.isHorizontal() && shaftAxis == Axis.X || pipeAxis.isVertical())
            pointerRotationOffset = 90;

        pointer.center()
                .rotateYDegrees(AngleHelper.horizontalAngle(facing))
                .rotateXDegrees(facing == Direction.UP ? 0 : facing == Direction.DOWN ? 180 : 90)
                .rotateYDegrees(pointerRotationOffset + pointerRotation)
                .uncenter()
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
    }

    @Override
    protected BlockState getRenderedBlockState(FluidValveBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }
}
