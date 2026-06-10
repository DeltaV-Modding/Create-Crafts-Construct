package net.buildercraft.block.create.fluid;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedHosePulleyBlockEntity extends HosePulleyBlockEntity {
    public PaintedHosePulleyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get(), pos, state);
    }
}
