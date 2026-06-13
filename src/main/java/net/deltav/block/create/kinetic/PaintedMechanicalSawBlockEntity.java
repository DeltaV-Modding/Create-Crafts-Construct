package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.saw.SawBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedMechanicalSawBlockEntity extends SawBlockEntity {
    public PaintedMechanicalSawBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_MECHANICAL_SAW.get(), pos, state);
    }

    @Override
    protected Block getStressConfigKey() {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "mechanical_saw"));
    }
}
