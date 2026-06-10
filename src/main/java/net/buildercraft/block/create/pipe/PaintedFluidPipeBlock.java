package net.buildercraft.block.create.pipe;

import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedFluidPipeBlock extends FluidPipeBlock {
    private final Supplier<BlockEntityType<? extends FluidPipeBlockEntity>> blockEntityType;

    public PaintedFluidPipeBlock(BlockBehaviour.Properties properties,
                                 Supplier<BlockEntityType<? extends FluidPipeBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends FluidPipeBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
