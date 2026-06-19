package net.deltav.block.create.kinetic.train;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import dev.engine_room.flywheel.api.visual.DynamicVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.engine_room.flywheel.lib.visual.AbstractBlockEntityVisual;
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;
import net.deltav.registry.ModPartialModels;
import net.createmod.catnip.math.AngleHelper;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Objects;
import java.util.function.Consumer;

public class TrainSteamEngineVisual extends AbstractBlockEntityVisual<SteamEngineBlockEntity> implements SimpleDynamicVisual {
    protected final TransformedInstance piston;
    protected final TransformedInstance linkage;
    protected final TransformedInstance connector;
    private Float lastAngle = Float.NaN;
    private Axis lastAxis = null;

    public TrainSteamEngineVisual(VisualizationContext context, SteamEngineBlockEntity blockEntity, float partialTicks) {
        super(context, blockEntity, partialTicks);

        BlockState state = blockEntity.getBlockState();
        String path = state.getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel pistonModel = ModPartialModels.STEAM_ENGINE_PISTONS.getOrDefault(path, AllPartialModels.ENGINE_PISTON);
        PartialModel linkageModel = ModPartialModels.STEAM_ENGINE_LINKAGES.getOrDefault(path, AllPartialModels.ENGINE_LINKAGE);
        PartialModel connectorModel = ModPartialModels.STEAM_ENGINE_CONNECTORS.getOrDefault(path, AllPartialModels.ENGINE_CONNECTOR);

        this.piston = instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(pistonModel)).createInstance();
        this.linkage = instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(linkageModel)).createInstance();
        this.connector = instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(connectorModel)).createInstance();

        animate();
    }

    @Override
    public void beginFrame(DynamicVisual.Context context) {
        animate();
    }

    private void animate() {
        Float angle = blockEntity.getTargetAngle();
        Axis axis = Axis.Y;
        PoweredShaftBlockEntity shaft = blockEntity.getShaft();
        if (shaft != null) {
            axis = KineticBlockEntityRenderer.getRotationAxisOf(shaft);
        }

        if (Objects.equals(angle, lastAngle) && axis == lastAxis) {
            return;
        }

        lastAngle = angle;
        lastAxis = axis;

        if (angle == null) {
            piston.setVisible(false);
            linkage.setVisible(false);
            connector.setVisible(false);
            return;
        }

        piston.setVisible(true);
        linkage.setVisible(true);
        connector.setVisible(true);

        BlockState state = blockEntity.getBlockState();
        Direction facing = SteamEngineBlock.getFacing(state);
        Axis facingAxis = facing.getAxis();
        boolean roll90 = (facingAxis.isHorizontal() && axis == Axis.Y) || (facingAxis.isVertical() && axis == Axis.Z);

        float sin = Mth.sin(angle);
        float sinShift = Mth.sin(angle - 1.5707964f);
        float pistonTranslation = (1.0f - sin) / 4.0f * 24.0f / 16.0f;

        transformed(piston, facing, roll90)
                .translate(0.0f, pistonTranslation, 0.0f)
                .setChanged();

        transformed(linkage, facing, roll90)
                .center()
                .translate(0.0f, 1.0f, 0.0f)
                .uncenter()
                .translate(0.0f, pistonTranslation, 0.0f)
                .translate(0.0f, 0.25f, 0.5f)
                .rotateXDegrees(sinShift * 23.0f)
                .translate(0.0f, -0.25f, -0.5f)
                .setChanged();

        transformed(connector, facing, roll90)
                .translate(0.0f, 2.0f, 0.0f)
                .center()
                .rotateX(-angle + 1.5707964f)
                .uncenter()
                .setChanged();
    }

    protected TransformedInstance transformed(TransformedInstance instance, Direction facing, boolean roll90) {
        return instance.setIdentityTransform()
                .translate(getVisualPosition())
                .center()
                .rotateYDegrees(AngleHelper.horizontalAngle(facing))
                .rotateXDegrees(AngleHelper.verticalAngle(facing) + 90.0f)
                .rotateYDegrees(roll90 ? -90.0f : 0.0f)
                .uncenter();
    }

    @Override
    protected void _delete() {
        piston.delete();
        linkage.delete();
        connector.delete();
    }

    @Override
    public void collectCrumblingInstances(Consumer<dev.engine_room.flywheel.api.instance.Instance> consumer) {
        consumer.accept(piston);
        consumer.accept(linkage);
        consumer.accept(connector);
    }

    @Override
    public void updateLight(float partialTicks) {
        relight(piston, linkage, connector);
    }
}