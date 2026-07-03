package net.deltav.craftsconstruct.block.create.portableInterface.sturdy;

import com.simibubi.create.content.contraptions.actors.psi.PortableFluidInterfaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class SturdyPortableFluidInterfaceBlockEntity extends PortableFluidInterfaceBlockEntity {
    public SturdyPortableFluidInterfaceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public IFluidHandler getFluidHandler() {
        return capability.orElse(null);
    }
}
