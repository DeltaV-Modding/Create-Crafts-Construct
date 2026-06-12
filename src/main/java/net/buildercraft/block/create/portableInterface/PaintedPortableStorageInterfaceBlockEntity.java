package net.buildercraft.block.create.portableInterface;

import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedPortableStorageInterfaceBlockEntity extends PortableStorageInterfaceBlockEntity {
    public PaintedPortableStorageInterfaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_PORTABLE_STORAGE_INTERFACE.get(), pos, state);
    }

    @Override
    protected void invalidateCapability() {
    }
}
