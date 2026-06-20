package net.deltav.craftsconstruct.block.create.fluid.brass;

import com.simibubi.create.content.fluids.spout.SpoutBlock;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassSpoutBlock extends SpoutBlock {
    private final Supplier<BlockEntityType<? extends SpoutBlockEntity>> blockEntityType;

    public BrassSpoutBlock(BlockBehaviour.Properties properties,
                             Supplier<BlockEntityType<? extends SpoutBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends SpoutBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}