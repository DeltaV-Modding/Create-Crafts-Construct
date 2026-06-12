package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.gauge.StressGaugeBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedStressometerBlockEntity extends StressGaugeBlockEntity {
    public PaintedStressometerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_STRESSOMETER.get(), pos, state);
    }
}
