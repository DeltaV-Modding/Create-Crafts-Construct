package net.deltav.craftsconstruct.block.create.drain.sturdy;

import com.simibubi.create.content.fluids.transfer.GenericItemEmptying;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class SturdyItemDrainItemHandler implements IItemHandler {

    private final SturdyItemDrainBlockEntity blockEntity;
    private final Direction side;

    public SturdyItemDrainItemHandler(SturdyItemDrainBlockEntity be, Direction side) {
        this.blockEntity = be;
        this.side = side;
    }
    public int getSlots() {
        return 1;
    }
    public ItemStack getStackInSlot(int slot) {
        return blockEntity.getHeldItemStack();
    }
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
    public int getSlotLimit(int slot) {
        return 64;
    }
    public boolean isItemValid(int slot, ItemStack stack) {
        return true;
    }
}