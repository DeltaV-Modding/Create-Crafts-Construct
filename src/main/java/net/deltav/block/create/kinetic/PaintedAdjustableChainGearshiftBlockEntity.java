package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.chainDrive.ChainGearshiftBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedAdjustableChainGearshiftBlockEntity extends ChainGearshiftBlockEntity {
    public PaintedAdjustableChainGearshiftBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_ADJUSTABLE_CHAIN_GEARSHIFT.get(), pos, state);
    }

    @Override
    protected Block getStressConfigKey() {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "adjustable_chain_gearshift"));
    }
}
