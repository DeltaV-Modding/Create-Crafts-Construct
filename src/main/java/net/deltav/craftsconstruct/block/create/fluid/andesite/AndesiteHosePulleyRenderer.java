package net.deltav.craftsconstruct.block.create.fluid.andesite;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.contraptions.pulley.AbstractPulleyRenderer;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SuperByteBuffer;
import net.deltav.craftsconstruct.registry.ModPartialModels;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;

public class AndesiteHosePulleyRenderer extends AbstractPulleyRenderer<HosePulleyBlockEntity> {

    public AndesiteHosePulleyRenderer(BlockEntityRendererProvider.Context context) {
        super(context, AllPartialModels.HOSE_HALF, AllPartialModels.HOSE_HALF_MAGNET);
    }
    protected Direction.Axis getShaftAxis(HosePulleyBlockEntity be) {
        return be.getBlockState()
                .getValue(HosePulleyBlock.HORIZONTAL_FACING)
                .getClockWise()
                .getAxis();
    }
    protected PartialModel getCoil() {
        return AllPartialModels.HOSE_COIL;
    }
    protected SuperByteBuffer renderRope(HosePulleyBlockEntity be) {
        return CachedBuffers.partial(AllPartialModels.HOSE, be.getBlockState());
    }
    protected SuperByteBuffer renderMagnet(HosePulleyBlockEntity be) {
        return CachedBuffers.partial(ModPartialModels.ANDESITE_HOSE_PULLEY_MAGNET, be.getBlockState());
    }
    protected float getOffset(HosePulleyBlockEntity be, float partialTicks) {
        return be.getInterpolatedOffset(partialTicks);
    }
    protected SpriteShiftEntry getCoilShift() {
        return AllSpriteShifts.HOSE_PULLEY_COIL;
    }
    protected boolean isRunning(HosePulleyBlockEntity be) {
        return true;
    }

}
