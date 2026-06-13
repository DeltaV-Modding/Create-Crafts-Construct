package net.deltav.util;

import net.minecraft.world.level.block.Block;

public class ModConnectivityHelper {
    public static final ThreadLocal<Block> CURRENT_SEARCH_BLOCK = new ThreadLocal<>();
}
