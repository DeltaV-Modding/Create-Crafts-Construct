package net.buildercraft.block.create.kinetic;

import com.simibubi.create.content.logistics.depot.EjectorBlockEntity;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedWeightedEjectorBlockEntity extends EjectorBlockEntity {
    public PaintedWeightedEjectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_WEIGHTED_EJECTOR.get(), pos, state);
    }

    @Override
    protected Block getStressConfigKey() {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "weighted_ejector"));
    }
}
