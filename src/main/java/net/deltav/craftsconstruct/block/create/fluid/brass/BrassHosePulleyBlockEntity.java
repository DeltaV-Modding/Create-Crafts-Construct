package net.deltav.craftsconstruct.block.create.fluid.brass;

import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BrassHosePulleyBlockEntity extends HosePulleyBlockEntity {
    public BrassHosePulleyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    protected net.minecraft.world.level.block.Block getStressConfigKey() {
        return net.minecraft.core.registries.BuiltInRegistries.BLOCK.get(
            new net.minecraft.resources.ResourceLocation("create", "hose_pulley")
        );
    }
}
