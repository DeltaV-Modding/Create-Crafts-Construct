package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassPumpBlock extends PumpBlock {
    private final Supplier<BlockEntityType<? extends PumpBlockEntity>> blockEntityType;

    public BrassPumpBlock(BlockBehaviour.Properties properties,
                            Supplier<BlockEntityType<? extends PumpBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends PumpBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}