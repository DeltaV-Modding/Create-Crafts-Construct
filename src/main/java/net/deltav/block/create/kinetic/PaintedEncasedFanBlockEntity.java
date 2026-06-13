package net.deltav.block.create.kinetic;

import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PaintedEncasedFanBlockEntity extends EncasedFanBlockEntity {
    public PaintedEncasedFanBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PAINTED_ENCASED_FAN.get(), pos, state);
    }

    @Override
    protected Block getStressConfigKey() {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "encased_fan"));
    }
}
