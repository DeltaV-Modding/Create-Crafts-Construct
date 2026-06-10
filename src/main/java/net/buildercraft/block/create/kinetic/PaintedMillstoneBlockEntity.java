package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedMillstoneBlockEntity extends MillstoneBlockEntity {
    public PaintedMillstoneBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_MILLSTONE.get(), pos, state);
    }
}
