package net.deltav.block.create.kinetic;

import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedDepotBlockEntity extends DepotBlockEntity {
    public PaintedDepotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_DEPOT.get(), pos, state);
    }
}
