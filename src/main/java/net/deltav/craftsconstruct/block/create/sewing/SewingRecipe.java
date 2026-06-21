package net.deltav.craftsconstruct.block.create.sewing;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import net.deltav.craftsconstruct.registry.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public record SewingRecipe(ItemStack input, ItemStack output) {
    private static final Map<net.minecraft.world.item.Item, SewingRecipe> RECIPES = new HashMap<>();

    static {
        register(new ItemStack(Items.WHITE_WOOL), new ItemStack(ModBlocks.WHITE_TEXTILE.get()));
        register(new ItemStack(Items.LIGHT_GRAY_WOOL), new ItemStack(ModBlocks.LIGHT_GRAY_TEXTILE.get()));
        register(new ItemStack(Items.GRAY_WOOL), new ItemStack(ModBlocks.GRAY_TEXTILE.get()));
        register(new ItemStack(Items.BLACK_WOOL), new ItemStack(ModBlocks.BLACK_TEXTILE.get()));
        register(new ItemStack(Items.BROWN_WOOL), new ItemStack(ModBlocks.BROWN_TEXTILE.get()));
        register(new ItemStack(Items.RED_WOOL), new ItemStack(ModBlocks.RED_TEXTILE.get()));
        register(new ItemStack(Items.ORANGE_WOOL), new ItemStack(ModBlocks.ORANGE_TEXTILE.get()));
        register(new ItemStack(Items.YELLOW_WOOL), new ItemStack(ModBlocks.YELLOW_TEXTILE.get()));
        register(new ItemStack(Items.LIME_WOOL), new ItemStack(ModBlocks.LIME_TEXTILE.get()));
        register(new ItemStack(Items.GREEN_WOOL), new ItemStack(ModBlocks.GREEN_TEXTILE.get()));
        register(new ItemStack(Items.CYAN_WOOL), new ItemStack(ModBlocks.CYAN_TEXTILE.get()));
        register(new ItemStack(Items.LIGHT_BLUE_WOOL), new ItemStack(ModBlocks.LIGHT_BLUE_TEXTILE.get()));
        register(new ItemStack(Items.BLUE_WOOL), new ItemStack(ModBlocks.BLUE_TEXTILE.get()));
        register(new ItemStack(Items.PURPLE_WOOL), new ItemStack(ModBlocks.PURPLE_TEXTILE.get()));
        register(new ItemStack(Items.MAGENTA_WOOL), new ItemStack(ModBlocks.MAGENTA_TEXTILE.get()));
        register(new ItemStack(Items.PINK_WOOL), new ItemStack(ModBlocks.PINK_TEXTILE.get()));
    }

    private static void register(ItemStack input, ItemStack output) {
        RECIPES.put(input.getItem(), new SewingRecipe(input, output));
    }

    public static Optional<SewingRecipe> find(ItemStack stack) {
        if (stack.isEmpty())
            return Optional.empty();
        SewingRecipe recipe = RECIPES.get(stack.getItem());
        if (recipe == null)
            return Optional.empty();
        return Optional.of(recipe);
    }

    public boolean matches(ItemStack stack) {
        return ItemStack.isSameItemSameComponents(input, stack);
    }
}
