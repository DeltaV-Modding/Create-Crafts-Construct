package net.deltav.block.create.drain.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.drain.ItemDrainRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class BrassItemDrainRenderer extends ItemDrainRenderer {
    public BrassItemDrainRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }
}