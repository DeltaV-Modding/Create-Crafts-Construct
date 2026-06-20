package net.deltav.craftsconstruct.block.create.fluid.andesite;

import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class AndesiteFluidTankBlock extends FluidTankBlock {
    private final Supplier<BlockEntityType<? extends FluidTankBlockEntity>> blockEntityType;

    public AndesiteFluidTankBlock(BlockBehaviour.Properties properties,
                                 Supplier<BlockEntityType<? extends FluidTankBlockEntity>> blockEntityType) {
        super(properties, false);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends FluidTankBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}