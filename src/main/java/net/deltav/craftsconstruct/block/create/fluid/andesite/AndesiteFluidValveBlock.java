package net.deltav.craftsconstruct.block.create.fluid.andesite;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class AndesiteFluidValveBlock extends FluidValveBlock {
    private final Supplier<BlockEntityType<? extends FluidValveBlockEntity>> blockEntityType;

    public AndesiteFluidValveBlock(BlockBehaviour.Properties properties,
                                  Supplier<BlockEntityType<? extends FluidValveBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends FluidValveBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}