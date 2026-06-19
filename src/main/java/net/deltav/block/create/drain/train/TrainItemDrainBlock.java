package net.deltav.block.create.drain.train;

import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TrainItemDrainBlock extends ItemDrainBlock {
    public TrainItemDrainBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.TRAIN_ITEM_DRAIN.get().create(pos, state);
    }
}