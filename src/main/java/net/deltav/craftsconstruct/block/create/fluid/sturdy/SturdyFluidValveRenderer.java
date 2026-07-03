package net.deltav.craftsconstruct.block.create.fluid.sturdy;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;

import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class SturdyFluidValveRenderer extends KineticBlockEntityRenderer<FluidValveBlockEntity> {
    private static final Map<String, PartialModel> POINTERS = new HashMap<>();

    public SturdyFluidValveRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
    protected void renderSafe(FluidValveBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        BlockState state = be.getBlockState();
        Direction facing = state.getValue(FluidValveBlock.FACING);
        float pointerRotation = Mth.lerp(((net.deltav.craftsconstruct.mixin.FluidValveBlockEntityAccessor) be).getPointer().getValue(partialTicks), 0, -90);
        Direction.Axis pipeAxis = FluidValveBlock.getPipeAxis(state);
        Direction.Axis rotationAxis = getRotationAxisOf(be);
        int pointerRotationOffset = pipeAxis.isHorizontal() && rotationAxis == Direction.Axis.X || pipeAxis.isVertical() ? 90 : 0;

        CachedBuffers.partial(getPointer(state), state)
                .center()
                .rotateYDegrees(AngleHelper.horizontalAngle(facing))
                .rotateXDegrees(facing == Direction.UP ? 0 : facing == Direction.DOWN ? 180 : 90)
                .rotateYDegrees(pointerRotationOffset + pointerRotation)
                .uncenter()
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
    }
    protected BlockState getRenderedBlockState(FluidValveBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }

    static PartialModel getPointer(BlockState state) {
        String path = state.getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel pointer = net.deltav.craftsconstruct.registry.ModPartialModels.VALVE_POINTERS.get(path);
        if (pointer == null) {
            return com.simibubi.create.AllPartialModels.FLUID_VALVE_POINTER;
        }
        return pointer;
    }
}