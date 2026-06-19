package net.deltav.block.create.fluid.train;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class TrainHosePulleyBlock extends HosePulleyBlock {
    private final Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType;

    public TrainHosePulleyBlock(BlockBehaviour.Properties properties,
                                  Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends HosePulleyBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}