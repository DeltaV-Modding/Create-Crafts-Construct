package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BrassHosePulleyBlock extends HosePulleyBlock {
    private final Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType;

    public BrassHosePulleyBlock(BlockBehaviour.Properties properties,
                                  Supplier<BlockEntityType<? extends HosePulleyBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends HosePulleyBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}