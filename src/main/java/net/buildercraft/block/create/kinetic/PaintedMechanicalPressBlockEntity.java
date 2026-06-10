package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedMechanicalPressBlockEntity extends MechanicalPressBlockEntity {
    public PaintedMechanicalPressBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_MECHANICAL_PRESS.get(), pos, state);
    }
}
