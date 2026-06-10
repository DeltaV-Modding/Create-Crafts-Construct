package util;

import net.buildercraft.craftsconstruct;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Map;
import java.util.Optional;

public class PaintTargetResolver {
    private static final Map<ResourceLocation, Map<PaintMaterial, ResourceLocation>> SPECIAL_TARGETS = Map.of(
            id("create", "item_drain"), Map.of(
                    PaintMaterial.COPPER, id("create", "item_drain"),
                    PaintMaterial.BRASS, id(craftsconstruct.MOD_ID, "brass_item_drain")
            ),
            id(craftsconstruct.MOD_ID, "brass_item_drain"), Map.of(
                    PaintMaterial.COPPER, id("create", "item_drain"),
                    PaintMaterial.BRASS, id(craftsconstruct.MOD_ID, "brass_item_drain")
            )
    );

    private PaintTargetResolver() {
    }

    public static Optional<BlockState> resolve(BlockState sourceState, PaintMaterial material) {
        ResourceLocation sourceId = BuiltInRegistries.BLOCK.getKey(sourceState.getBlock());
        if (!PaintableConfig.isBlockPaintable(sourceId)) {
            return Optional.empty();
        }

        ResourceLocation targetId = resolveTargetId(sourceId, material);
        if (targetId == null || targetId.equals(sourceId)) {
            return Optional.empty();
        }

        Block targetBlock = BuiltInRegistries.BLOCK.get(targetId);
        if (targetBlock == Blocks.AIR) {
            return Optional.empty();
        }

        return Optional.of(copySharedProperties(sourceState, targetBlock.defaultBlockState()));
    }

    private static ResourceLocation resolveTargetId(ResourceLocation sourceId, PaintMaterial material) {
        Map<PaintMaterial, ResourceLocation> specialTargets = SPECIAL_TARGETS.get(sourceId);
        if (specialTargets != null) {
            return specialTargets.get(material);
        }

        if (!"create".equals(sourceId.getNamespace())) {
            return null;
        }

        String sourcePath = sourceId.getPath();
        String strippedPath = stripKnownMaterialPrefix(sourcePath);
        if (strippedPath == null) {
            return null;
        }

        return id("create", material.getSerializedName() + "_" + strippedPath);
    }

    private static String stripKnownMaterialPrefix(String path) {
        for (PaintMaterial material : PaintMaterial.values()) {
            String prefix = material.getSerializedName() + "_";
            if (path.startsWith(prefix)) {
                return path.substring(prefix.length());
            }
        }
        return null;
    }

    private static BlockState copySharedProperties(BlockState sourceState, BlockState targetState) {
        BlockState result = targetState;
        for (Property<?> property : sourceState.getProperties()) {
            if (result.hasProperty(property)) {
                result = copyProperty(sourceState, result, property);
            }
        }
        return result;
    }

    private static <T extends Comparable<T>> BlockState copyProperty(BlockState sourceState, BlockState targetState, Property<T> property) {
        return targetState.setValue(property, sourceState.getValue(property));
    }

    private static ResourceLocation id(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}
