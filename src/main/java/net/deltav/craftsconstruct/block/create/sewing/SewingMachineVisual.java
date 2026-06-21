package net.deltav.craftsconstruct.block.create.sewing;

import static com.simibubi.create.content.kinetics.base.DirectionalAxisKineticBlock.AXIS_ALONG_FIRST_COORDINATE;
import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;

import java.util.function.Consumer;

import org.joml.Quaternionf;

import com.mojang.math.Axis;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.ShaftVisual;

import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visual.DynamicVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.OrientedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;
import net.createmod.catnip.math.AngleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;

public class SewingMachineVisual extends ShaftVisual<SewingMachineBlockEntity> implements SimpleDynamicVisual {
    final Direction facing;
    final float yRot;
    final float xRot;
    final float zRot;
    protected final OrientedInstance pole;
    protected final OrientedInstance hand;
    float progress;

    public SewingMachineVisual(VisualizationContext context, SewingMachineBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick);
        facing = blockState.getValue(FACING);
        boolean rotatePole = blockState.getValue(AXIS_ALONG_FIRST_COORDINATE) ^ facing.getAxis() == Direction.Axis.Z;
        yRot = AngleHelper.horizontalAngle(facing);
        xRot = facing == Direction.UP ? 270 : facing == Direction.DOWN ? 90 : 0;
        zRot = rotatePole ? 90 : 0;
        pole = instancerProvider().instancer(InstanceTypes.ORIENTED, Models.partial(AllPartialModels.DEPLOYER_POLE)).createInstance();
        hand = instancerProvider().instancer(InstanceTypes.ORIENTED, Models.partial(blockEntity.getHandPose())).createInstance();
        progress = getProgress(partialTick);
        updateRotation(pole, hand, yRot, xRot, zRot);
        updatePosition();
    }

    @Override
    public void beginFrame(DynamicVisual.Context ctx) {
        float newProgress = getProgress(ctx.partialTick());
        if (Mth.equal(newProgress, progress))
            return;
        progress = newProgress;
        updatePosition();
    }

    @Override
    public void updateLight(float partialTick) {
        super.updateLight(partialTick);
        relight(hand, pole);
    }

    @Override
    protected void _delete() {
        super._delete();
        hand.delete();
        pole.delete();
    }

    private float getProgress(float partialTicks) {
        if (blockEntity.state == SewingMachineBlockEntity.State.EXPANDING)
            return 1 - (blockEntity.timer - partialTicks * blockEntity.getTimerSpeed()) / 1000f;
        if (blockEntity.state == SewingMachineBlockEntity.State.RETRACTING)
            return (blockEntity.timer - partialTicks * blockEntity.getTimerSpeed()) / 1000f;
        return 0;
    }

    private void updatePosition() {
        float distance = Math.min(Mth.clamp(progress, 0, 1) * (blockEntity.reach + 3 / 16f), 21 / 16f);
        Vec3i facingVec = facing.getNormal();
        BlockPos blockPos = getVisualPosition();
        float x = blockPos.getX() + facingVec.getX() * distance;
        float y = blockPos.getY() + facingVec.getY() * distance;
        float z = blockPos.getZ() + facingVec.getZ() * distance;
        pole.position(x, y, z).setChanged();
        hand.position(x, y, z).setChanged();
    }

    static void updateRotation(OrientedInstance pole, OrientedInstance hand, float yRot, float xRot, float zRot) {
        Quaternionf q = Axis.YP.rotationDegrees(yRot);
        q.mul(Axis.XP.rotationDegrees(xRot));
        hand.rotation(q).setChanged();
        q.mul(Axis.ZP.rotationDegrees(zRot));
        pole.rotation(q).setChanged();
    }

    @Override
    public void collectCrumblingInstances(Consumer<Instance> consumer) {
        super.collectCrumblingInstances(consumer);
        consumer.accept(pole);
        consumer.accept(hand);
    }
}
