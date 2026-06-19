package net.deltav.block.create.kinetic.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassChainDriveBlock extends ChainDriveBlock {
    private final Supplier<BlockEntityType<? extends KineticBlockEntity>> blockEntityType;

    public BrassChainDriveBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends KineticBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}