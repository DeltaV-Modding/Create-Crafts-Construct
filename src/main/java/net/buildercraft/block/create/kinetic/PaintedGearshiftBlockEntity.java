package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.transmission.GearshiftBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedGearshiftBlockEntity extends GearshiftBlockEntity {
    public PaintedGearshiftBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_GEARSHIFT.get(), pos, state);
    }
}
