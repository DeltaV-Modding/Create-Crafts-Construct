package net.deltav.craftsconstruct.block.create.drain.brass;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BrassItemDrainBlockEntity extends ItemDrainBlockEntity {
    public BrassItemDrainBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
