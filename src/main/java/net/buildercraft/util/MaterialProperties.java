package net.buildercraft.util;

import net.minecraft.world.level.block.state.properties.EnumProperty;

public class MaterialProperties {
    public static final EnumProperty<MaterialType> MATERIAL =
            EnumProperty.create("material", MaterialType.class);
}
