package net.deltav.block.create.kinetic;

import com.simibubi.create.content.logistics.depot.DepotBlock;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedDepotBlock extends DepotBlock {
    private final Supplier<BlockEntityType<? extends DepotBlockEntity>> blockEntityType;

    public PaintedDepotBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends DepotBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends DepotBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
