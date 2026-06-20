package net.deltav.craftsconstruct.util;

import net.minecraft.util.StringRepresentable;

public enum MaterialType implements StringRepresentable {
    COPPER("copper"),
    BRASS("brass");

    private final String name;

    MaterialType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
