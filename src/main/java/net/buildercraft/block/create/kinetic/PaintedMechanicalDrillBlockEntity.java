package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.drill.DrillBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedMechanicalDrillBlockEntity extends DrillBlockEntity {
    public PaintedMechanicalDrillBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_MECHANICAL_DRILL.get(), pos, state);
    }
}
