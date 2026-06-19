package net.deltav.block.create.kinetic.train;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TrainChainDriveBlockEntity extends KineticBlockEntity {
    public TrainChainDriveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TRAIN_ENCASED_CHAIN_DRIVE.get(), pos, state);
    }
}