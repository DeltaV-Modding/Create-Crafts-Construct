package net.deltav.block.create.fluid.train;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TrainSpoutBlockEntity extends SpoutBlockEntity {
    public TrainSpoutBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TRAIN_SPOUT.get(), pos, state);
    }
}