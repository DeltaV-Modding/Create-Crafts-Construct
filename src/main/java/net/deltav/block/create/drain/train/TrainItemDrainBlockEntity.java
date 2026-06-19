package net.deltav.block.create.drain.train;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TrainItemDrainBlockEntity extends ItemDrainBlockEntity {
    public TrainItemDrainBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TRAIN_ITEM_DRAIN.get(), pos, state);
    }
}