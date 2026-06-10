package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedSteamEngineBlockEntity extends SteamEngineBlockEntity {
    public PaintedSteamEngineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_STEAM_ENGINE.get(), pos, state);
    }
}
