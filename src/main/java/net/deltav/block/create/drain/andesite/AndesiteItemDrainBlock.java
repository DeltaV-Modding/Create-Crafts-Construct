package net.deltav.block.create.drain.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AndesiteItemDrainBlock extends ItemDrainBlock {
    public AndesiteItemDrainBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.ANDESITE_ITEM_DRAIN.get().create(pos, state);
    }
}