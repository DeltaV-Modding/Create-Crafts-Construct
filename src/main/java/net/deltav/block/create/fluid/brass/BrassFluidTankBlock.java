package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassFluidTankBlock extends FluidTankBlock {
    private final Supplier<BlockEntityType<? extends FluidTankBlockEntity>> blockEntityType;

    public BrassFluidTankBlock(BlockBehaviour.Properties properties,
                                 Supplier<BlockEntityType<? extends FluidTankBlockEntity>> blockEntityType) {
        super(properties, false);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends FluidTankBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}