package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedMechanicalPressBlock extends MechanicalPressBlock {
    private final Supplier<BlockEntityType<? extends MechanicalPressBlockEntity>> blockEntityType;

    public PaintedMechanicalPressBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends MechanicalPressBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends MechanicalPressBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
