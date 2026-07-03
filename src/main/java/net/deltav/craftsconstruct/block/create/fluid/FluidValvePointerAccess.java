package net.deltav.craftsconstruct.block.create.fluid;

import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlockEntity;
import net.createmod.catnip.animation.LerpedFloat;

import java.lang.reflect.Field;

/**
 * Create keeps the fluid valve pointer package-private. Forge access transformers cannot
 * reliably widen dependency-mod fields here, so keep the workaround isolated from the
 * Create-derived renderer and visual logic.
 */
final class FluidValvePointerAccess {
    private static final Field POINTER_FIELD = findPointerField();

    private FluidValvePointerAccess() {
    }

    static LerpedFloat get(FluidValveBlockEntity blockEntity) {
        try {
            return (LerpedFloat) POINTER_FIELD.get(blockEntity);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to access FluidValveBlockEntity pointer", e);
        }
    }

    private static Field findPointerField() {
        try {
            Field field = FluidValveBlockEntity.class.getDeclaredField("pointer");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("Unable to find FluidValveBlockEntity pointer field", e);
        }
    }
}
