package net.deltav.block.create.portableInterface.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.contraptions.actors.psi.PortableFluidInterfaceBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class AndesitePortableFluidInterfaceBlockEntity extends PortableFluidInterfaceBlockEntity {
    public AndesitePortableFluidInterfaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.ANDESITE_PORTABLE_FLUID_INTERFACE.get(), pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return capability;
    }
}