package net.deltav.craftsconstruct.block.create.kinetic.brass;

import com.simibubi.create.content.kinetics.gauge.GaugeBlock;
import com.simibubi.create.content.kinetics.gauge.GaugeBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassGaugeBlock extends GaugeBlock {
    private final Supplier<BlockEntityType<? extends GaugeBlockEntity>> blockEntityType;

    public BrassGaugeBlock(boolean speed, BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends GaugeBlockEntity>> blockEntityType) {
        super(properties, speed ? Type.SPEED : Type.STRESS);
        this.blockEntityType = blockEntityType;
    }
    public BlockEntityType<? extends GaugeBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}