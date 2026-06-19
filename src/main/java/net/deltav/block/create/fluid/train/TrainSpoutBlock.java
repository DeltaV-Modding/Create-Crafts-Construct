package net.deltav.block.create.fluid.train;

import com.simibubi.create.content.fluids.spout.SpoutBlock;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class TrainSpoutBlock extends SpoutBlock {
    private final Supplier<BlockEntityType<? extends SpoutBlockEntity>> blockEntityType;

    public TrainSpoutBlock(BlockBehaviour.Properties properties,
                             Supplier<BlockEntityType<? extends SpoutBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends SpoutBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}