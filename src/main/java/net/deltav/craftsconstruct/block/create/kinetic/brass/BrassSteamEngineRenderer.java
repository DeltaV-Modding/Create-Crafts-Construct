package net.deltav.craftsconstruct.block.create.kinetic.brass;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.deltav.craftsconstruct.registry.ModPartialModels;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

public class BrassSteamEngineRenderer extends SafeBlockEntityRenderer<SteamEngineBlockEntity> {
    public BrassSteamEngineRenderer(BlockEntityRendererProvider.Context context) {
        super();
    }
    protected void renderSafe(SteamEngineBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        Float angle = be.getTargetAngle();
        if (angle == null)
            return;

        BlockState state = be.getBlockState();
        Direction facing = SteamEngineBlock.getFacing(state);
        Direction.Axis facingAxis = facing.getAxis();
        Direction.Axis rotationAxis = Direction.Axis.Y;
        PoweredShaftBlockEntity shaft = be.getShaft();
        if (shaft != null) {
            rotationAxis = KineticBlockEntityRenderer.getRotationAxisOf(shaft);
        }

        boolean roll90 = facingAxis.isHorizontal() && rotationAxis == Direction.Axis.Y || facingAxis.isVertical() && rotationAxis == Direction.Axis.Z;

        float sin = Mth.sin(angle);
        float sinShift = Mth.sin(angle - 1.5707964f);
        float pistonTranslation = (1.0f - sin) / 4.0f * 24.0f / 16.0f;

        String path = state.getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel pistonModel = ModPartialModels.STEAM_ENGINE_PISTONS.getOrDefault(path, com.simibubi.create.AllPartialModels.ENGINE_PISTON);
        PartialModel linkageModel = ModPartialModels.STEAM_ENGINE_LINKAGES.getOrDefault(path, com.simibubi.create.AllPartialModels.ENGINE_LINKAGE);
        PartialModel connectorModel = ModPartialModels.STEAM_ENGINE_CONNECTORS.getOrDefault(path, com.simibubi.create.AllPartialModels.ENGINE_CONNECTOR);

        VertexConsumer vc = buffer.getBuffer(RenderType.solid());

        transformed(pistonModel, state, facing, roll90)
                .translate(0, pistonTranslation, 0)
                .light(light)
                .renderInto(ms, vc);

        transformed(linkageModel, state, facing, roll90)
                .center()
                .translate(0, 1.0f, 0)
                .uncenter()
                .translate(0, pistonTranslation, 0)
                .translate(0, 0.25f, 0.5f)
                .rotateXDegrees(sinShift * 23.0f)
                .translate(0, -0.25f, -0.5f)
                .light(light)
                .renderInto(ms, vc);

        transformed(connectorModel, state, facing, roll90)
                .translate(0, 2.0f, 0)
                .center()
                .rotateX(-angle + 1.5707964f)
                .uncenter()
                .light(light)
                .renderInto(ms, vc);
    }

    protected SuperByteBuffer transformed(PartialModel model, BlockState state, Direction facing, boolean roll90) {
        return CachedBuffers.partial(model, state)
                .center()
                .rotateYDegrees(AngleHelper.horizontalAngle(facing))
                .rotateXDegrees(AngleHelper.verticalAngle(facing) + 90.0f)
                .rotateYDegrees(roll90 ? -90.0f : 0.0f)
                .uncenter();
    }
}