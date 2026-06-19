package net.deltav.block.create.kinetic.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.kinetics.chainDrive.ChainDriveBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class AndesiteChainDriveBlock extends ChainDriveBlock {
    private final Supplier<BlockEntityType<? extends KineticBlockEntity>> blockEntityType;

    public AndesiteChainDriveBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends KineticBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}