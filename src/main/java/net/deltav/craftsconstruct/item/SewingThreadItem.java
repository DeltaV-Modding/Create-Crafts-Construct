package net.deltav.craftsconstruct.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SewingThreadItem extends Item {
    public static final int MAX_USES = 8;

    public SewingThreadItem(Properties properties) {
        super(properties.durability(MAX_USES));
    }

    public static boolean isThread(ItemStack stack) {
        return stack.getItem() instanceof SewingThreadItem;
    }

    public static boolean consumeUse(ItemStack stack) {
        if (!isThread(stack))
            return false;
        int nextDamage = stack.getDamageValue() + 1;
        if (nextDamage >= stack.getMaxDamage()) {
            stack.shrink(1);
            if (!stack.isEmpty())
                stack.setDamageValue(0);
            return true;
        }
        stack.setDamageValue(nextDamage);
        return true;
    }

    public static int getUsesLeft(ItemStack stack) {
        if (!isThread(stack))
            return 0;
        return Math.max(0, stack.getMaxDamage() - stack.getDamageValue());
    }
}
