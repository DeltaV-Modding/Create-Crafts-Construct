package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedChainDriveBlockEntity extends KineticBlockEntity {
    public PaintedChainDriveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_ENCASED_CHAIN_DRIVE.get(), pos, state);
    }
}
