package net.deltav.craftsconstruct.block.create.fluid.brass;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BrassFluidValveBlockEntity extends FluidValveBlockEntity {
    public BrassFluidValveBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
