package net.deltav.craftsconstruct.util;

public enum PaintMaterial {
    ANDESITE("andesite"),
    BRASS("brass"),
    COPPER("copper"),
    TRAIN("train");

    private final String serializedName;

    PaintMaterial(String serializedName) {
        this.serializedName = serializedName;
    }

    public String getSerializedName() {
        return serializedName;
    }
}
