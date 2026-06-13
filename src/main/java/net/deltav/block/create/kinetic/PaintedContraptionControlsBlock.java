package net.deltav.block.create.kinetic;

import com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsBlock;
import com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class PaintedContraptionControlsBlock extends ContraptionControlsBlock {
    private final Supplier<BlockEntityType<? extends ContraptionControlsBlockEntity>> blockEntityType;

    public PaintedContraptionControlsBlock(BlockBehaviour.Properties properties, Supplier<BlockEntityType<? extends ContraptionControlsBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends ContraptionControlsBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
}
