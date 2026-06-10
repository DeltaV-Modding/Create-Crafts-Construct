package net.buildercraft.block.create.portableInterface;

import com.simibubi.create.content.contraptions.actors.psi.PortableFluidInterfaceBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class PaintedPortableFluidInterfaceBlockEntity extends PortableFluidInterfaceBlockEntity {
    public PaintedPortableFluidInterfaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_PORTABLE_FLUID_INTERFACE.get(), pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return capability;
    }
}
