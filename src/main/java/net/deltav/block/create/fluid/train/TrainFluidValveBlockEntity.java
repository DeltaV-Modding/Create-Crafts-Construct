package net.deltav.block.create.fluid.train;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TrainFluidValveBlockEntity extends FluidValveBlockEntity {
    public TrainFluidValveBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TRAIN_FLUID_VALVE.get(), pos, state);
    }
}