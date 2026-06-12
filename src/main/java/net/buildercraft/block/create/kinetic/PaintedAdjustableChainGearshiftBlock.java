package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.chainDrive.ChainGearshiftBlock;
import com.simibubi.create.content.kinetics.chainDrive.ChainGearshiftBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedAdjustableChainGearshiftBlock extends ChainGearshiftBlock {
    private final Supplier<BlockEntityType<? extends ChainGearshiftBlockEntity>> blockEntityType;

    public PaintedAdjustableChainGearshiftBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends ChainGearshiftBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends ChainGearshiftBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
