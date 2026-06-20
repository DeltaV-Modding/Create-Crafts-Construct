package net.deltav.block.create.fluid.andesite;

import com.simibubi.create.AllPartialModels;
import net.deltav.block.create.fluid.andesite.AndesitePumpBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class AndesitePumpRenderer extends KineticBlockEntityRenderer<AndesitePumpBlockEntity> {

    public AndesitePumpRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(AndesitePumpBlockEntity be, BlockState state) {
        return CachedBuffers.partialFacing(AllPartialModels.MECHANICAL_PUMP_COG, state);
    }

}
