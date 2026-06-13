package net.deltav.block.create.fluid;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedHosePulleyBlock extends HosePulleyBlock {
    private final Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType;

    public PaintedHosePulleyBlock(BlockBehaviour.Properties properties,
                                  Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends HosePulleyBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
