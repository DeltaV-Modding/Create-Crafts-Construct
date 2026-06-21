package net.deltav.craftsconstruct.block.create.sewing;

import java.util.List;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.item.SmartInventory;

import net.deltav.craftsconstruct.item.SewingThreadItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SpoolBlockEntity extends SmartBlockEntity implements Clearable {
    private final SmartInventory inventory;

    public SpoolBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = new SmartInventory(1, this, 1, false, (slot, stack) -> SewingThreadItem.isThread(stack));
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
    }

    public SmartInventory getInventory() {
        return inventory;
    }

    public boolean hasUsableThread() {
        ItemStack stack = inventory.getStackInSlot(0);
        return SewingThreadItem.isThread(stack) && SewingThreadItem.getUsesLeft(stack) > 0;
    }

    public boolean consumeThread() {
        ItemStack stack = inventory.getStackInSlot(0);
        if (!hasUsableThread())
            return false;
        SewingThreadItem.consumeUse(stack);
        if (stack.isEmpty())
            inventory.setStackInSlot(0, ItemStack.EMPTY);
        notifyUpdate();
        return true;
    }

    public void interact(Player player, InteractionHand hand, ItemStack held) {
        ItemStack stored = inventory.getStackInSlot(0);
        if (stored.isEmpty()) {
            if (!SewingThreadItem.isThread(held))
                return;
            ItemStack inserted = held.copyWithCount(1);
            inventory.setStackInSlot(0, inserted);
            if (!player.isCreative())
                held.shrink(1);
            return;
        }

        if (held.isEmpty()) {
            player.setItemInHand(hand, stored.copy());
            inventory.setStackInSlot(0, ItemStack.EMPTY);
            return;
        }

        if (!player.getInventory().add(stored.copy()))
            player.drop(stored.copy(), false);
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }

    @Override
    protected void write(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        tag.put("Inventory", inventory.serializeNBT(registries));
        super.write(tag, registries, clientPacket);
    }

    @Override
    protected void read(CompoundTag tag, HolderLookup.Provider registries, boolean clientPacket) {
        inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        super.read(tag, registries, clientPacket);
    }

    @Override
    public void clearContent() {
        inventory.setStackInSlot(0, ItemStack.EMPTY);
    }
}
