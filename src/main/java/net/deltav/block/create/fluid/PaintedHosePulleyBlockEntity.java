package net.deltav.block.create.fluid;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedHosePulleyBlockEntity extends HosePulleyBlockEntity {
    public PaintedHosePulleyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get(), pos, state);
    }

    @Override
    protected net.minecraft.world.level.block.Block getStressConfigKey() {
        return net.minecraft.core.registries.BuiltInRegistries.BLOCK.get(
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("create", "hose_pulley")
        );
    }
}
