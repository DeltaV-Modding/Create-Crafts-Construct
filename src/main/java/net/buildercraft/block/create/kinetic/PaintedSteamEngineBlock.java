package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedSteamEngineBlock extends SteamEngineBlock {
    private final Supplier<BlockEntityType<? extends SteamEngineBlockEntity>> blockEntityType;

    public PaintedSteamEngineBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends SteamEngineBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends SteamEngineBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
