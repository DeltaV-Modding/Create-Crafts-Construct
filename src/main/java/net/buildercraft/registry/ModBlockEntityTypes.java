package net.buildercraft.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.buildercraft.block.create.drain.BrassItemDrainBlockEntity;
import net.buildercraft.block.create.drain.BrassItemDrainRenderer;
import net.buildercraft.craftsconstruct;

public class ModBlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = craftsconstruct.registrate();

public static final BlockEntityEntry<BrassItemDrainBlockEntity> BRASS_ITEM_DRAIN = REGISTRATE
        .blockEntity("brass_item_drain", BrassItemDrainBlockEntity::new)
        .validBlocks(ModBlocks.BRASS_ITEM_DRAIN)
        .renderer(() -> BrassItemDrainRenderer::new)
        .register();
    public static void register() {
    }
}