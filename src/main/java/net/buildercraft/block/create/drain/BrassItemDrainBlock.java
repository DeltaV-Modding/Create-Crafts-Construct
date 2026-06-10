package net.buildercraft.block.create.drain;

import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BrassItemDrainBlock extends ItemDrainBlock {
    public BrassItemDrainBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.BRASS_ITEM_DRAIN.get().create(pos, state);
    }
}