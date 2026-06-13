package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterBlock;
import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedMechanicalCrafterBlock extends MechanicalCrafterBlock {
    private final Supplier<BlockEntityType<? extends MechanicalCrafterBlockEntity>> blockEntityType;

    public PaintedMechanicalCrafterBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends MechanicalCrafterBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends MechanicalCrafterBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
