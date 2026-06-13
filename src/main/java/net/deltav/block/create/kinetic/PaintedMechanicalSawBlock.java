package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.saw.SawBlock;
import com.simibubi.create.content.kinetics.saw.SawBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedMechanicalSawBlock extends SawBlock {
    private final Supplier<BlockEntityType<? extends SawBlockEntity>> blockEntityType;

    public PaintedMechanicalSawBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends SawBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends SawBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
