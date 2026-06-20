package net.deltav.craftsconstruct.block.create.drain.train;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TrainItemDrainBlockEntity extends ItemDrainBlockEntity {
    public TrainItemDrainBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
