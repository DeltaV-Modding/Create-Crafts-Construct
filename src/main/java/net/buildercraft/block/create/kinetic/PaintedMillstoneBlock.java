package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.millstone.MillstoneBlock;
import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedMillstoneBlock extends MillstoneBlock {
    private final Supplier<BlockEntityType<? extends MillstoneBlockEntity>> blockEntityType;

    public PaintedMillstoneBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends MillstoneBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends MillstoneBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
