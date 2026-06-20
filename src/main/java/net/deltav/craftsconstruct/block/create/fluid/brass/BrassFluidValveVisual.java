package net.deltav.craftsconstruct.block.create.fluid.brass;

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
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;

import net.createmod.catnip.math.AngleHelper;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class BrassFluidValveVisual extends ShaftVisual<FluidValveBlockEntity> implements SimpleDynamicVisual {
    private final TransformedInstance pointer;
    private final float xRot;
    private final float yRot;
    private final int pointerRotationOffset;
    private boolean settled;

    public BrassFluidValveVisual(VisualizationContext context, FluidValveBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick);

        BlockState state = blockState;
        Direction facing = state.getValue(FluidValveBlock.FACING);
        yRot = AngleHelper.horizontalAngle(facing);
        xRot = facing == Direction.UP ? 0 : facing == Direction.DOWN ? 180 : 90;

        Direction.Axis pipeAxis = FluidValveBlock.getPipeAxis(state);
        Direction.Axis rotationAxis = KineticBlockEntityRenderer.getRotationAxisOf(blockEntity);
        pointerRotationOffset = pipeAxis.isHorizontal() && rotationAxis == Direction.Axis.X || pipeAxis.isVertical() ? 90 : 0;

        pointer = instancerProvider()
                .instancer(InstanceTypes.TRANSFORMED, Models.partial(BrassFluidValveRenderer.getPointer(state)))
                .createInstance();
        transformPointer(partialTick);
    }

    @Override
    public void beginFrame(DynamicVisual.Context context) {
        if (((net.deltav.craftsconstruct.mixin.FluidValveBlockEntityAccessor) blockEntity).getPointer().settled() && settled)
            return;
        transformPointer(context.partialTick());
    }

    private void transformPointer(float partialTick) {
        float value = ((net.deltav.craftsconstruct.mixin.FluidValveBlockEntityAccessor) blockEntity).getPointer().getValue(partialTick);
        float pointerRotation = Mth.lerp(value, 0, -90);
        settled = (value == 0 || value == 1) && ((net.deltav.craftsconstruct.mixin.FluidValveBlockEntityAccessor) blockEntity).getPointer().settled();

        pointer.setIdentityTransform()
                .translate(getVisualPosition())
                .center()
                .rotateYDegrees(yRot)
                .rotateXDegrees(xRot)
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