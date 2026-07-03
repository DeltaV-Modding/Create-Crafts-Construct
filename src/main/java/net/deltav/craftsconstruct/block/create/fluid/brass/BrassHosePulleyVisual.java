package net.deltav.craftsconstruct.block.create.fluid.brass;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.contraptions.pulley.AbstractPulleyVisual;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.processing.burner.ScrollInstance;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instancer;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.deltav.craftsconstruct.registry.ModPartialModels;

public class BrassHosePulleyVisual extends AbstractPulleyVisual<HosePulleyBlockEntity> {
    public BrassHosePulleyVisual(VisualizationContext dispatcher, HosePulleyBlockEntity blockEntity, float partialTick) {
        super(dispatcher, blockEntity, partialTick);
    }
    protected Instancer<TransformedInstance> getRopeModel() {
        return instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.HOSE));
    }
    protected Instancer<TransformedInstance> getMagnetModel() {
        return instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(ModPartialModels.BRASS_HOSE_PULLEY_MAGNET));
    }
    protected Instancer<TransformedInstance> getHalfMagnetModel() {
        return instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(ModPartialModels.BRASS_HOSE_PULLEY_ROPE_HALF_MAGNET));
    }
    protected Instancer<ScrollInstance> getCoilModel() {
        return instancerProvider().instancer(AllInstanceTypes.SCROLLING, Models.partial(AllPartialModels.HOSE_COIL));
    }
    protected Instancer<TransformedInstance> getHalfRopeModel() {
        return instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.HOSE_HALF));
    }
    protected float getOffset(float pt) {
        return blockEntity.getInterpolatedOffset(pt);
    }
    protected boolean isRunning() {
        return true;
    }
    protected SpriteShiftEntry getCoilAnimation() {
        return AllSpriteShifts.HOSE_PULLEY_COIL;
    }

}
