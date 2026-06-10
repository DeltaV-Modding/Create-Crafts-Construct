package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.saw.SawBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedMechanicalSawBlockEntity extends SawBlockEntity {
    public PaintedMechanicalSawBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_MECHANICAL_SAW.get(), pos, state);
    }
}
