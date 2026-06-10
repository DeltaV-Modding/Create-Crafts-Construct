package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedGearboxBlockEntity extends GearboxBlockEntity {
    public PaintedGearboxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_GEARBOX.get(), pos, state);
    }
}
