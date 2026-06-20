package net.deltav.craftsconstruct.block.create.fluid.train;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TrainHosePulleyBlockEntity extends HosePulleyBlockEntity {
    public TrainHosePulleyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected net.minecraft.world.level.block.Block getStressConfigKey() {
        return net.minecraft.core.registries.BuiltInRegistries.BLOCK.get(
            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("create", "hose_pulley")
        );
    }
}
