package net.deltav.block.create.pipe.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BrassSmartFluidPipeBlockEntity extends SmartFluidPipeBlockEntity {
    public BrassSmartFluidPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BRASS_SMART_FLUID_PIPE.get(), pos, state);
    }
}