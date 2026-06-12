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
import java.util.Set;

public class PaintTargetResolver {
    private static final Map<ResourceLocation, Map<PaintMaterial, ResourceLocation>> SPECIAL_TARGETS = Map.of(
            id("create", "item_drain"), Map.of(
                    PaintMaterial.COPPER, id("create", "item_drain"),
                    PaintMaterial.BRASS, id(craftsconstruct.MOD_ID, "brass_item_drain"),
                    PaintMaterial.ANDESITE, id(craftsconstruct.MOD_ID, "andesite_item_drain"),
                    PaintMaterial.TRAIN, id(craftsconstruct.MOD_ID, "train_item_drain")
            ),
            id(craftsconstruct.MOD_ID, "brass_item_drain"), Map.of(
                    PaintMaterial.COPPER, id("create", "item_drain"),
                    PaintMaterial.BRASS, id(craftsconstruct.MOD_ID, "brass_item_drain"),
                    PaintMaterial.ANDESITE, id(craftsconstruct.MOD_ID, "andesite_item_drain"),
                    PaintMaterial.TRAIN, id(craftsconstruct.MOD_ID, "train_item_drain")
            ),
            id(craftsconstruct.MOD_ID, "andesite_item_drain"), Map.of(
                    PaintMaterial.COPPER, id("create", "item_drain"),
                    PaintMaterial.BRASS, id(craftsconstruct.MOD_ID, "brass_item_drain"),
                    PaintMaterial.ANDESITE, id(craftsconstruct.MOD_ID, "andesite_item_drain"),
                    PaintMaterial.TRAIN, id(craftsconstruct.MOD_ID, "train_item_drain")
            ),
            id(craftsconstruct.MOD_ID, "train_item_drain"), Map.of(
                    PaintMaterial.COPPER, id("create", "item_drain"),
                    PaintMaterial.BRASS, id(craftsconstruct.MOD_ID, "brass_item_drain"),
                    PaintMaterial.ANDESITE, id(craftsconstruct.MOD_ID, "andesite_item_drain"),
                    PaintMaterial.TRAIN, id(craftsconstruct.MOD_ID, "train_item_drain")
            ),
            id("create", "shaft"), Map.of(
                    PaintMaterial.ANDESITE, id("create", "andesite_encased_shaft"),
                    PaintMaterial.BRASS, id("create", "brass_encased_shaft")
            ),
            id("create", "cogwheel"), Map.of(
                    PaintMaterial.ANDESITE, id("create", "andesite_encased_cogwheel"),
                    PaintMaterial.BRASS, id("create", "brass_encased_cogwheel")
            ),
            id("create", "large_cogwheel"), Map.of(
                    PaintMaterial.ANDESITE, id("create", "andesite_encased_large_cogwheel"),
                    PaintMaterial.BRASS, id("create", "brass_encased_large_cogwheel")
            )
    );
    private static final Map<String, PaintMaterial> CREATE_BASE_MATERIALS = Map.ofEntries(
            Map.entry("fluid_pipe", PaintMaterial.COPPER),
            Map.entry("mechanical_pump", PaintMaterial.COPPER),
            Map.entry("fluid_valve", PaintMaterial.COPPER),
            Map.entry("fluid_tank", PaintMaterial.COPPER),
            Map.entry("spout", PaintMaterial.COPPER),
            Map.entry("hose_pulley", PaintMaterial.COPPER),
            Map.entry("portable_fluid_interface", PaintMaterial.COPPER),
            Map.entry("steam_engine", PaintMaterial.COPPER),
            Map.entry("steam_whistle", PaintMaterial.COPPER),
            Map.entry("smart_fluid_pipe", PaintMaterial.COPPER),
            Map.entry("mechanical_crafter", PaintMaterial.BRASS),
            Map.entry("gearbox", PaintMaterial.ANDESITE),
            Map.entry("encased_chain_drive", PaintMaterial.ANDESITE),
            Map.entry("encased_fan", PaintMaterial.ANDESITE),
            Map.entry("millstone", PaintMaterial.ANDESITE),
            Map.entry("mechanical_saw", PaintMaterial.ANDESITE),
            Map.entry("mechanical_press", PaintMaterial.ANDESITE),
            Map.entry("mechanical_mixer", PaintMaterial.ANDESITE),
            Map.entry("deployer", PaintMaterial.ANDESITE),
            Map.entry("mechanical_drill", PaintMaterial.ANDESITE)
    );
    private static final Map<String, Set<PaintMaterial>> CUSTOM_MATERIALS = Map.ofEntries(
            Map.entry("fluid_pipe", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("mechanical_pump", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("fluid_valve", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("fluid_tank", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("spout", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("hose_pulley", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("portable_fluid_interface", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("steam_engine", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("steam_whistle", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("smart_fluid_pipe", Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("mechanical_crafter", Set.of(PaintMaterial.ANDESITE, PaintMaterial.COPPER, PaintMaterial.TRAIN)),
            Map.entry("gearbox", Set.of(PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("encased_chain_drive", Set.of(PaintMaterial.BRASS, PaintMaterial.COPPER, PaintMaterial.TRAIN)),
            Map.entry("encased_fan", Set.of(PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("millstone", Set.of(PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("mechanical_saw", Set.of(PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("mechanical_press", Set.of(PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("mechanical_mixer", Set.of(PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("deployer", Set.of(PaintMaterial.BRASS, PaintMaterial.TRAIN)),
            Map.entry("mechanical_drill", Set.of(PaintMaterial.BRASS, PaintMaterial.TRAIN))
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

    public static boolean isAlreadyMaterial(BlockState sourceState, PaintMaterial material) {
        ResourceLocation sourceId = BuiltInRegistries.BLOCK.getKey(sourceState.getBlock());
        return currentMaterial(sourceId).filter(material::equals).isPresent();
    }

    private static Optional<PaintMaterial> currentMaterial(ResourceLocation sourceId) {
        if (sourceId.equals(id("create", "item_drain"))) {
            return Optional.of(PaintMaterial.COPPER);
        }
        if (sourceId.equals(id(craftsconstruct.MOD_ID, "brass_item_drain"))) {
            return Optional.of(PaintMaterial.BRASS);
        }
        if (sourceId.equals(id(craftsconstruct.MOD_ID, "andesite_item_drain"))) {
            return Optional.of(PaintMaterial.ANDESITE);
        }
        if (sourceId.equals(id(craftsconstruct.MOD_ID, "train_item_drain"))) {
            return Optional.of(PaintMaterial.TRAIN);
        }
        if ("create".equals(sourceId.getNamespace())) {
            String strippedPath = stripKnownMaterialPrefix(sourceId.getPath());
            String path = strippedPath == null ? sourceId.getPath() : strippedPath;
            PaintMaterial baseMaterial = CREATE_BASE_MATERIALS.get(path);
            if (baseMaterial != null && sourceId.equals(id("create", path))) {
                return Optional.of(baseMaterial);
            }
        }

        String path = sourceId.getPath();
        for (PaintMaterial material : PaintMaterial.values()) {
            if (path.startsWith(material.getSerializedName() + "_")) {
                return Optional.of(material);
            }
        }

        return Optional.empty();
    }

    private static ResourceLocation resolveTargetId(ResourceLocation sourceId, PaintMaterial material) {
        Map<PaintMaterial, ResourceLocation> specialTargets = SPECIAL_TARGETS.get(sourceId);
        if (specialTargets != null) {
            return specialTargets.get(material);
        }

        if (craftsconstruct.MOD_ID.equals(sourceId.getNamespace())) {
            String strippedPath = stripKnownMaterialPrefix(sourceId.getPath());
            if (strippedPath == null) {
                return null;
            }
            Optional<ResourceLocation> baseVariant = baseCreateId(material, strippedPath);
            if (baseVariant.isPresent()) {
                return baseVariant.get();
            }
            Optional<ResourceLocation> createVariant = existingCreateId(material.getSerializedName() + "_" + strippedPath);
            return createVariant.orElse(functionalCraftsConstructVariant(material, strippedPath).orElse(null));
        }

        if (!"create".equals(sourceId.getNamespace())) {
            return null;
        }

        String sourcePath = sourceId.getPath();
        String strippedPath = stripKnownMaterialPrefix(sourcePath);
        if (strippedPath == null) {
            strippedPath = sourcePath;
        }

        Optional<ResourceLocation> baseVariant = baseCreateId(material, strippedPath);
        if (baseVariant.isPresent()) {
            return baseVariant.get();
        }
        Optional<ResourceLocation> createVariant = existingCreateId(material.getSerializedName() + "_" + strippedPath);
        return createVariant.orElse(functionalCraftsConstructVariant(material, strippedPath).orElse(null));
    }

    private static Optional<ResourceLocation> baseCreateId(PaintMaterial material, String strippedPath) {
        if (CREATE_BASE_MATERIALS.get(strippedPath) != material) {
            return Optional.empty();
        }
        ResourceLocation id = id("create", strippedPath);
        return BuiltInRegistries.BLOCK.get(id) == Blocks.AIR ? Optional.empty() : Optional.of(id);
    }

    private static Optional<ResourceLocation> existingCreateId(String path) {
        ResourceLocation id = id("create", path);
        return BuiltInRegistries.BLOCK.get(id) == Blocks.AIR ? Optional.empty() : Optional.of(id);
    }

    private static Optional<ResourceLocation> functionalCraftsConstructVariant(PaintMaterial material, String strippedPath) {
        if (!isFunctionalCraftsConstructVariant(strippedPath)) {
            return Optional.empty();
        }
        if (!CUSTOM_MATERIALS.getOrDefault(strippedPath, Set.of()).contains(material)) {
            return Optional.empty();
        }

        ResourceLocation id = id(craftsconstruct.MOD_ID, material.getSerializedName() + "_" + strippedPath);
        return BuiltInRegistries.BLOCK.get(id) == Blocks.AIR ? Optional.empty() : Optional.of(id);
    }

    private static boolean isFunctionalCraftsConstructVariant(String strippedPath) {
        return switch (strippedPath) {
            case "fluid_pipe",
                 "smart_fluid_pipe",
                 "mechanical_pump",
                 "fluid_valve",
                 "fluid_tank",
                 "spout",
                 "hose_pulley",
                 "portable_fluid_interface",
                 "steam_engine",
                 "steam_whistle",
                 "gearbox",
                 "encased_chain_drive",
                 "encased_fan",
                 "millstone",
                 "mechanical_saw",
                 "mechanical_press",
                 "mechanical_mixer",
                 "deployer",
                 "mechanical_drill",
                 "mechanical_crafter" -> true;
            default -> false;
        };
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
