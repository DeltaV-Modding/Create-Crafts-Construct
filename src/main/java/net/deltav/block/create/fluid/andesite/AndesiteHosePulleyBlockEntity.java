package net.deltav.block.create.fluid.andesite;

import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.deltav.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class AndesiteHosePulleyBlockEntity extends HosePulleyBlockEntity {
    public AndesiteHosePulleyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.ANDESITE_HOSE_PULLEY.get(), pos, state);
    }

    @Override
    protected net.minecraft.world.level.block.Block getStressConfigKey() {
        return net.minecraft.core.registries.BuiltInRegistries.BLOCK.get(
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("create", "hose_pulley")
        );
    }
}