package net.deltav.block.create.fluid.train;

import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TrainPumpBlockEntity extends PumpBlockEntity {
    public TrainPumpBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.TRAIN_MECHANICAL_PUMP.get(), pos, state);
    }

    @Override
    protected Block getStressConfigKey() {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "mechanical_pump"));
    }
}