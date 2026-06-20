package net.deltav.craftsconstruct.block.create.kinetic.train;

import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TrainSteamWhistleBlockEntity extends WhistleBlockEntity {
    public TrainSteamWhistleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
