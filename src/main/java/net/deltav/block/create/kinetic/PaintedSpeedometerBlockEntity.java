package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.gauge.SpeedGaugeBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedSpeedometerBlockEntity extends SpeedGaugeBlockEntity {
    public PaintedSpeedometerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_SPEEDOMETER.get(), pos, state);
    }
}
