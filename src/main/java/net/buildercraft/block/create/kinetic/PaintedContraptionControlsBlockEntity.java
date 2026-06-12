package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedContraptionControlsBlockEntity extends ContraptionControlsBlockEntity {
    public PaintedContraptionControlsBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_CONTRAPTION_CONTROLS.get(), pos, state);
    }
}
