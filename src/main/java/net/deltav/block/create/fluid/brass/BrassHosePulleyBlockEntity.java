package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BrassHosePulleyBlockEntity extends HosePulleyBlockEntity {
    public BrassHosePulleyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.BRASS_HOSE_PULLEY.get(), pos, state);
    }

    @Override
    protected net.minecraft.world.level.block.Block getStressConfigKey() {
        return net.minecraft.core.registries.BuiltInRegistries.BLOCK.get(
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("create", "hose_pulley")
        );
    }
}