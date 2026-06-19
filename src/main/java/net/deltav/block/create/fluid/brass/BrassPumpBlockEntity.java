package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BrassPumpBlockEntity extends PumpBlockEntity {
    public BrassPumpBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BRASS_MECHANICAL_PUMP.get(), pos, state);
    }

    @Override
    protected Block getStressConfigKey() {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "mechanical_pump"));
    }
}