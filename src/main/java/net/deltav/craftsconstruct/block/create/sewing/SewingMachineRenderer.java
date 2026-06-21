package net.deltav.craftsconstruct.block.create.sewing;

import static com.simibubi.create.content.kinetics.base.DirectionalAxisKineticBlock.AXIS_ALONG_FIRST_COORDINATE;
import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringRenderer;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;

import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SewingMachineRenderer extends SafeBlockEntityRenderer<SewingMachineBlockEntity> {
    public SewingMachineRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    protected void renderSafe(SewingMachineBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        FilteringRenderer.renderOnBlockEntity(be, partialTicks, ms, buffer, light, overlay);
        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;
        VertexConsumer vb = buffer.getBuffer(RenderType.solid());
        KineticBlockEntityRenderer.renderRotatingKineticBlock(be, getRenderedBlockState(be), ms, vb, light);

        BlockState blockState = be.getBlockState();
        Vec3 offset = Vec3.atLowerCornerOf(blockState.getValue(FACING).getNormal()).scale(be.getHandOffset(partialTicks));
        SuperByteBuffer pole = CachedBuffers.partial(AllPartialModels.DEPLOYER_POLE, blockState);
        SuperByteBuffer hand = CachedBuffers.partial(be.getHandPose(), blockState);

        transform(pole.translate(offset.x, offset.y, offset.z), blockState, true).light(light).renderInto(ms, vb);
        transform(hand.translate(offset.x, offset.y, offset.z), blockState, false).light(light).renderInto(ms, vb);
    }

    protected BlockState getRenderedBlockState(KineticBlockEntity be) {
        return KineticBlockEntityRenderer.shaft(KineticBlockEntityRenderer.getRotationAxisOf(be));
    }

    private static SuperByteBuffer transform(SuperByteBuffer buffer, BlockState state, boolean axisDirectionMatters) {
        Direction facing = state.getValue(FACING);
        float yRot = AngleHelper.horizontalAngle(facing);
        float xRot = facing == Direction.UP ? 270 : facing == Direction.DOWN ? 90 : 0;
        float zRot = axisDirectionMatters && (state.getValue(AXIS_ALONG_FIRST_COORDINATE) ^ facing.getAxis() == Direction.Axis.Z) ? 90 : 0;

        buffer.rotateCentered((float) (yRot / 180 * Math.PI), Direction.UP);
        buffer.rotateCentered((float) (xRot / 180 * Math.PI), Direction.EAST);
        buffer.rotateCentered((float) (zRot / 180 * Math.PI), Direction.SOUTH);
        return buffer;
    }
}
