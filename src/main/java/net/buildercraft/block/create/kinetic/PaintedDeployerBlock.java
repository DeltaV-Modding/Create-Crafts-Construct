package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.deployer.DeployerBlock;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedDeployerBlock extends DeployerBlock {
    private final Supplier<BlockEntityType<? extends DeployerBlockEntity>> blockEntityType;

    public PaintedDeployerBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends DeployerBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends DeployerBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
