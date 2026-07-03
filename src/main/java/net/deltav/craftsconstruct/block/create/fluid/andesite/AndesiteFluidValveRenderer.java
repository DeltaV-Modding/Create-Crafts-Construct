package net.deltav.craftsconstruct.block.create.fluid.andesite;

import com.simibubi.create.AllPartialModels;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.deltav.craftsconstruct.block.create.fluid.FluidValveRendererBase;
import net.deltav.craftsconstruct.registry.ModPartialModels;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class AndesiteFluidValveRenderer extends FluidValveRendererBase {
    public AndesiteFluidValveRenderer(BlockEntityRendererProvider.Context context) {
        super(context, AndesiteFluidValveRenderer::getPointer);
    }

    static PartialModel getPointer(BlockState state) {
        String path = state.getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel pointer = ModPartialModels.VALVE_POINTERS.get(path);
        return pointer != null ? pointer : AllPartialModels.FLUID_VALVE_POINTER;
    }
}
