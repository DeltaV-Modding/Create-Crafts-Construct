package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlock;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedMechanicalMixerBlock extends MechanicalMixerBlock {
    private final Supplier<BlockEntityType<? extends MechanicalMixerBlockEntity>> blockEntityType;

    public PaintedMechanicalMixerBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends MechanicalMixerBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends MechanicalMixerBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
