package net.deltav.block.create.kinetic;

import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedSteamWhistleBlockEntity extends WhistleBlockEntity {
    public PaintedSteamWhistleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_STEAM_WHISTLE.get(), pos, state);
    }
}
