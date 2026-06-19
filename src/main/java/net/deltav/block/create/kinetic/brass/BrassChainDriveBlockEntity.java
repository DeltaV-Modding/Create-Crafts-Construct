package net.deltav.block.create.kinetic.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BrassChainDriveBlockEntity extends KineticBlockEntity {
    public BrassChainDriveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BRASS_ENCASED_CHAIN_DRIVE.get(), pos, state);
    }
}