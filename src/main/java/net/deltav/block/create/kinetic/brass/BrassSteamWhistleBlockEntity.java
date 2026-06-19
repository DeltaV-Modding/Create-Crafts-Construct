package net.deltav.block.create.kinetic.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BrassSteamWhistleBlockEntity extends WhistleBlockEntity {
    public BrassSteamWhistleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BRASS_STEAM_WHISTLE.get(), pos, state);
    }
}