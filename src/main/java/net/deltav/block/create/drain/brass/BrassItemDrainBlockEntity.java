package net.deltav.block.create.drain.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BrassItemDrainBlockEntity extends ItemDrainBlockEntity {
    public BrassItemDrainBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BRASS_ITEM_DRAIN.get(), pos, state);
    }
}