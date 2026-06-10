package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedDeployerBlockEntity extends DeployerBlockEntity {
    public PaintedDeployerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_DEPLOYER.get(), pos, state);
    }
}
