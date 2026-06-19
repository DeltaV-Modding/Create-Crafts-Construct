package net.deltav.block.create.kinetic.train;

import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TrainSteamWhistleBlockEntity extends WhistleBlockEntity {
    public TrainSteamWhistleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TRAIN_STEAM_WHISTLE.get(), pos, state);
    }
}