package net.buildercraft.block.create.fluid;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedSpoutBlockEntity extends SpoutBlockEntity {
    public PaintedSpoutBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_SPOUT.get(), pos, state);
    }
}
