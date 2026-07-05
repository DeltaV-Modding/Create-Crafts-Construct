package net.deltav.craftsconstruct.mixin;

import net.createmod.catnip.levelWrappers.SchematicLevel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.deltav.craftsconstruct.util.PonderSubjectHolder;
import net.deltav.craftsconstruct.util.PaintMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SchematicLevel.class, remap = false)
public class SchematicLevelMixin {

    @Inject(method = "processBlockStateForPrinting", at = @At("RETURN"), cancellable = true)
    private void onProcessBlockState(BlockState state, CallbackInfoReturnable<BlockState> cir) {
        BlockState original = cir.getReturnValue();
        ItemStack subject = PonderSubjectHolder.currentSubject;
        if (subject != null && !subject.isEmpty()) {
            ResourceLocation subjectId = BuiltInRegistries.ITEM.getKey(subject.getItem());
            if (subjectId.getNamespace().equals("crafts_construct")) {
                String path = subjectId.getPath();
                String material = null;
                for (PaintMaterial mat : PaintMaterial.values()) {
                    if (path.startsWith(mat.getSerializedName() + "_")) {
                        material = mat.getSerializedName();
                        break;
                    }
                }

                if (material != null) {
                    ResourceLocation originalId = BuiltInRegistries.BLOCK.getKey(original.getBlock());
                    if (originalId.getNamespace().equals("create")) {
                        String originalPath = originalId.getPath();
                        if (originalPath.equals("copper_valve_handle")) {
                            originalPath = "valve_handle";
                        }

                        ResourceLocation customId = new ResourceLocation("crafts_construct", material + "_" + originalPath);
                        if (BuiltInRegistries.BLOCK.containsKey(customId)) {
                            Block customBlock = BuiltInRegistries.BLOCK.get(customId);
                            BlockState customState = customBlock.defaultBlockState();
                            for (Property<?> property : original.getProperties()) {
                                customState = copyPropertyHelper(original, customState, property);
                            }
                            cir.setReturnValue(customState);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> BlockState copyPropertyHelper(BlockState source, BlockState target, Property<T> property) {
        if (target.hasProperty(property)) {
            return target.setValue(property, source.getValue(property));
        }
        return target;
    }
}
