package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.transmission.GearshiftBlock;
import com.simibubi.create.content.kinetics.transmission.GearshiftBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedGearshiftBlock extends GearshiftBlock {
    private final Supplier<BlockEntityType<? extends GearshiftBlockEntity>> blockEntityType;

    public PaintedGearshiftBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends GearshiftBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends GearshiftBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
