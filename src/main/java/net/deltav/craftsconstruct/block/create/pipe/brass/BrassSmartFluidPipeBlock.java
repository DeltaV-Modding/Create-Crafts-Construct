package net.deltav.craftsconstruct.block.create.pipe.brass;

import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassSmartFluidPipeBlock extends SmartFluidPipeBlock {
    private final Supplier<BlockEntityType<? extends SmartFluidPipeBlockEntity>> blockEntityType;

    public BrassSmartFluidPipeBlock(BlockBehaviour.Properties properties,
                                      Supplier<BlockEntityType<? extends SmartFluidPipeBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends SmartFluidPipeBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}