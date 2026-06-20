package net.deltav.craftsconstruct.block.create.kinetic.andesite;

import com.simibubi.create.content.kinetics.gauge.GaugeBlock;
import com.simibubi.create.content.kinetics.gauge.GaugeBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class AndesiteGaugeBlock extends GaugeBlock {
    private final Supplier<BlockEntityType<? extends GaugeBlockEntity>> blockEntityType;

    public AndesiteGaugeBlock(boolean speed, BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends GaugeBlockEntity>> blockEntityType) {
        super(properties, speed ? Type.SPEED : Type.STRESS);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends GaugeBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}