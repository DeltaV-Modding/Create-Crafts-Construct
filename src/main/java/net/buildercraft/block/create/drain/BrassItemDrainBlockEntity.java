package net.buildercraft.block.create.drain;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.buildercraft.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import java.util.List;

public class BrassItemDrainBlockEntity extends ItemDrainBlockEntity {

    private SmartFluidTankBehaviour internalTank;

    public BrassItemDrainBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.BRASS_ITEM_DRAIN.get();
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);

        internalTank = new SmartFluidTankBehaviour(
                SmartFluidTankBehaviour.INPUT,
                this,
                1,
                1500,
                true
        )
                .forbidInsertion()
                .allowExtraction();

        behaviours.add(internalTank);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        boolean added = super.addToGoggleTooltip(tooltip, isPlayerSneaking);
        if (internalTank != null) {
            added |= containedFluidTooltip(
                    tooltip,
                    isPlayerSneaking,
                    internalTank.getCapability()
            );
        }
        return added;
    }
}