package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.transmission.ClutchBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedClutchBlockEntity extends ClutchBlockEntity {
    public PaintedClutchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_CLUTCH.get(), pos, state);
    }
}
