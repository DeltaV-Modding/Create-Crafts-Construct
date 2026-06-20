package net.deltav.craftsconstruct.block.create.kinetic.andesite;

import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class AndesiteSteamWhistleBlock extends WhistleBlock {
    private final Supplier<BlockEntityType<? extends WhistleBlockEntity>> blockEntityType;

    public AndesiteSteamWhistleBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends WhistleBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends WhistleBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}