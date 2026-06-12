package net.buildercraft.block.create.drain;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedItemDrainBlockEntity extends ItemDrainBlockEntity {
    public PaintedItemDrainBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_ITEM_DRAIN.get(), pos, state);
    }
}
