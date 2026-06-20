package net.deltav.craftsconstruct.block.create.kinetic.brass;

import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassSteamEngineBlock extends SteamEngineBlock {
    private final Supplier<BlockEntityType<? extends SteamEngineBlockEntity>> blockEntityType;

    public BrassSteamEngineBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends SteamEngineBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends SteamEngineBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}