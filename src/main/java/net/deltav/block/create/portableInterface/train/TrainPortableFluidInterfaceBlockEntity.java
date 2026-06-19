package net.deltav.block.create.portableInterface.train;

import com.simibubi.create.content.contraptions.actors.psi.PortableFluidInterfaceBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class TrainPortableFluidInterfaceBlockEntity extends PortableFluidInterfaceBlockEntity {
    public TrainPortableFluidInterfaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TRAIN_PORTABLE_FLUID_INTERFACE.get(), pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return capability;
    }
}