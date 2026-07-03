package net.deltav.craftsconstruct.block.create.fluid;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visual.DynamicVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.animation.LerpedFloat;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Based on Create's FluidValveVisual, with the pointer partial selected per painted material.
 */
public abstract class FluidValveVisualBase extends ShaftVisual<FluidValveBlockEntity> implements SimpleDynamicVisual {
    protected final TransformedInstance pointer;
    protected boolean settled;

    protected final double xRot;
    protected final double yRot;
    protected final int pointerRotationOffset;

    protected FluidValveVisualBase(VisualizationContext context, FluidValveBlockEntity blockEntity, float partialTick,
                                  Function<BlockState, PartialModel> pointerModel) {
        super(context, blockEntity, partialTick);

        Direction facing = blockState.getValue(FluidValveBlock.FACING);
        yRot = AngleHelper.horizontalAngle(facing);
        xRot = facing == Direction.UP ? 0 : facing == Direction.DOWN ? 180 : 90;

        Direction.Axis pipeAxis = FluidValveBlock.getPipeAxis(blockState);
        Direction.Axis shaftAxis = KineticBlockEntityRenderer.getRotationAxisOf(blockEntity);
        pointerRotationOffset = pipeAxis.isHorizontal() && shaftAxis == Direction.Axis.X || pipeAxis.isVertical() ? 90 : 0;
        settled = false;

        pointer = instancerProvider()
                .instancer(InstanceTypes.TRANSFORMED, Models.partial(pointerModel.apply(blockState)))
                .createInstance();

        transformPointer(partialTick);
    }

    @Override
    public void beginFrame(DynamicVisual.Context context) {
        LerpedFloat pointerState = FluidValvePointerAccess.get(blockEntity);
        if (pointerState.settled() && settled)
            return;

        transformPointer(context.partialTick());
    }

    private void transformPointer(float partialTick) {
        LerpedFloat pointerState = FluidValvePointerAccess.get(blockEntity);
        float value = pointerState.getValue(partialTick);
        float pointerRotation = Mth.lerp(value, 0, -90);
        settled = (value == 0 || value == 1) && pointerState.settled();

        pointer.setIdentityTransform()
                .translate(getVisualPosition())
                .center()
                .rotateYDegrees((float) yRot)
                .rotateXDegrees((float) xRot)
                .rotateYDegrees(pointerRotationOffset + pointerRotation)
                .uncenter()
                .setChanged();
    }

    @Override
    public void updateLight(float partialTick) {
        super.updateLight(partialTick);
        relight(pointer);
    }

    @Override
    protected void _delete() {
        super._delete();
        pointer.delete();
    }

    @Override
    public void collectCrumblingInstances(Consumer<Instance> consumer) {
        super.collectCrumblingInstances(consumer);
        consumer.accept(pointer);
    }
}
