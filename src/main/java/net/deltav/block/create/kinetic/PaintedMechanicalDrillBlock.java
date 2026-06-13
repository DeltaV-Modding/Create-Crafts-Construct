package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.drill.DrillBlock;
import com.simibubi.create.content.kinetics.drill.DrillBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedMechanicalDrillBlock extends DrillBlock {
    private final Supplier<BlockEntityType<? extends DrillBlockEntity>> blockEntityType;

    public PaintedMechanicalDrillBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends DrillBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends DrillBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
