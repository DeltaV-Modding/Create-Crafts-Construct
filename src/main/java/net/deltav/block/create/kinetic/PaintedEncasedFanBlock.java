package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.fan.EncasedFanBlock;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedEncasedFanBlock extends EncasedFanBlock {
    private final Supplier<BlockEntityType<? extends EncasedFanBlockEntity>> blockEntityType;

    public PaintedEncasedFanBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends EncasedFanBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends EncasedFanBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
