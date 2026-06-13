package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedChainDriveBlock extends ChainDriveBlock {
    private final Supplier<BlockEntityType<? extends KineticBlockEntity>> blockEntityType;

    public PaintedChainDriveBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends KineticBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
