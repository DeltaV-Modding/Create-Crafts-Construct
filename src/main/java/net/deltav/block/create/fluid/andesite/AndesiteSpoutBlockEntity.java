package net.deltav.block.create.fluid.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class AndesiteSpoutBlockEntity extends SpoutBlockEntity {
    public AndesiteSpoutBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.ANDESITE_SPOUT.get(), pos, state);
    }
}