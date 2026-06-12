package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.transmission.ClutchBlock;
import com.simibubi.create.content.kinetics.transmission.ClutchBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedClutchBlock extends ClutchBlock {
    private final Supplier<BlockEntityType<? extends ClutchBlockEntity>> blockEntityType;

    public PaintedClutchBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends ClutchBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends ClutchBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
