package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedGearboxBlock extends GearboxBlock {
    private final Supplier<BlockEntityType<? extends GearboxBlockEntity>> blockEntityType;

    public PaintedGearboxBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends GearboxBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends GearboxBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
