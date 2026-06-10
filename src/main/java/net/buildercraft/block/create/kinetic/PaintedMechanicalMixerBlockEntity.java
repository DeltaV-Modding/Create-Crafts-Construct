package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedMechanicalMixerBlockEntity extends MechanicalMixerBlockEntity {
    public PaintedMechanicalMixerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_MECHANICAL_MIXER.get(), pos, state);
    }
}
