package net.buildercraft.block.create.drain;

import com.simibubi.create.content.fluids.transfer.GenericItemEmptying;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public class PaintedItemDrainItemHandler implements IItemHandler {

    private final PaintedItemDrainBlockEntity blockEntity;
    private final Direction side;

    public PaintedItemDrainItemHandler(PaintedItemDrainBlockEntity be, Direction side) {
        this.blockEntity = be;
        this.side = side;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return blockEntity.getHeldItemStack();
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (!blockEntity.getHeldItemStack().isEmpty())
            return stack;

        ItemStack returned = ItemStack.EMPTY;
        if (stack.getCount() > 1 && GenericItemEmptying.canItemBeEmptied(blockEntity.getLevel(), stack)) {
            returned = stack.copyWithCount(stack.getCount() - 1);
            stack = stack.copyWithCount(1);
        } else {
            returned = ItemHelper.limitCountToMaxStackSize(stack, simulate);
        }

        if (!simulate) {
            TransportedItemStack heldItem = new TransportedItemStack(stack);
            heldItem.prevBeltPosition = 0;
            blockEntity.setHeldItem(heldItem, side.getOpposite());
            blockEntity.notifyUpdate();
        }

        return returned;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack heldStack = blockEntity.getHeldItemStack();
        if (heldStack.isEmpty())
            return ItemStack.EMPTY;

        ItemStack stack = heldStack.copy();
        ItemStack extracted = stack.split(amount);

        if (!simulate) {
            if (stack.isEmpty()) {
                blockEntity.setHeldItem(null, side.getOpposite());
            } else {
                TransportedItemStack newHeld = new TransportedItemStack(stack);
                newHeld.insertedFrom = side.getOpposite();
                blockEntity.setHeldItem(newHeld, side.getOpposite());
            }
            blockEntity.notifyUpdate();
        }

        return extracted;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return true;
    }
}
