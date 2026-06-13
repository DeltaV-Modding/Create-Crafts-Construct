package net.deltav.block.create.kinetic;

import com.simibubi.create.content.logistics.depot.EjectorBlock;
import com.simibubi.create.content.logistics.depot.EjectorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedWeightedEjectorBlock extends EjectorBlock {
    private final Supplier<BlockEntityType<? extends EjectorBlockEntity>> blockEntityType;

    public PaintedWeightedEjectorBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends EjectorBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends EjectorBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
