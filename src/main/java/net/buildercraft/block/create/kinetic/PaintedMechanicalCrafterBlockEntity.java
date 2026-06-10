package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedMechanicalCrafterBlockEntity extends MechanicalCrafterBlockEntity {
    public PaintedMechanicalCrafterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_MECHANICAL_CRAFTER.get(), pos, state);
    }
}
