package net.deltav.craftsconstruct.block.create.fluid.andesite;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class AndesiteHosePulleyBlock extends HosePulleyBlock {
    private final Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType;

    public AndesiteHosePulleyBlock(BlockBehaviour.Properties properties,
                                  Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends HosePulleyBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}