package net.deltav.block.create.kinetic.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassSteamWhistleBlock extends WhistleBlock {
    private final Supplier<BlockEntityType<? extends WhistleBlockEntity>> blockEntityType;

    public BrassSteamWhistleBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends WhistleBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends WhistleBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}