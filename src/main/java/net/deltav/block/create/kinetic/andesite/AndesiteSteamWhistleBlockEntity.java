package net.deltav.block.create.kinetic.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class AndesiteSteamWhistleBlockEntity extends WhistleBlockEntity {
    public AndesiteSteamWhistleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.ANDESITE_STEAM_WHISTLE.get(), pos, state);
    }
}