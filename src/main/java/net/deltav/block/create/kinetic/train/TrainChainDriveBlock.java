package net.deltav.block.create.kinetic.train;

import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class TrainChainDriveBlock extends ChainDriveBlock {
    private final Supplier<BlockEntityType<? extends KineticBlockEntity>> blockEntityType;

    public TrainChainDriveBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends KineticBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}