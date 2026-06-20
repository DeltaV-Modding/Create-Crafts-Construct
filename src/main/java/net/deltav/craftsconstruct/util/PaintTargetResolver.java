package net.deltav.craftsconstruct.util;

import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.registry.ModBlocks;
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
    private static final ResourceLocation CREATE_ITEM_DRAIN = id("create", "item_drain");
    private static final ResourceLocation BRASS_ITEM_DRAIN = id(craftsconstruct.MOD_ID, "brass_item_drain");
    private static final ResourceLocation ANDESITE_ITEM_DRAIN = id(craftsconstruct.MOD_ID, "andesite_item_drain");
    private static final ResourceLocation TRAIN_ITEM_DRAIN = id(craftsconstruct.MOD_ID, "train_item_drain");
    private static final ResourceLocation CREATE_GLASS_FLUID_PIPE = id("create", "glass_fluid_pipe");
    private static final ResourceLocation CREATE_ENCASED_FLUID_PIPE = id("create", "encased_fluid_pipe");

    private static final Map<PaintMaterial, ResourceLocation> ITEM_DRAIN_TARGETS = Map.of(
            PaintMaterial.COPPER, CREATE_ITEM_DRAIN,
            PaintMaterial.BRASS, BRASS_ITEM_DRAIN,
            PaintMaterial.ANDESITE, ANDESITE_ITEM_DRAIN,
            PaintMaterial.TRAIN, TRAIN_ITEM_DRAIN
    );
    private static final Map<PaintMaterial, ResourceLocation> GLASS_FLUID_PIPE_TARGETS = Map.of(
            PaintMaterial.COPPER, CREATE_GLASS_FLUID_PIPE,
            PaintMaterial.BRASS, ModBlocks.BRASS_GLASS_PIPE.getId(),
            PaintMaterial.ANDESITE, ModBlocks.ANDESITE_GLASS_PIPE.getId(),
            PaintMaterial.TRAIN, ModBlocks.TRAIN_GLASS_PIPE.getId()
    );
    private static final Map<PaintMaterial, ResourceLocation> ENCASED_FLUID_PIPE_TARGETS = Map.of(
            PaintMaterial.COPPER, CREATE_ENCASED_FLUID_PIPE,
            PaintMaterial.BRASS, ModBlocks.BRASS_ENCASED_PIPE.getId(),
            PaintMaterial.ANDESITE, ModBlocks.ANDESITE_ENCASED_PIPE.getId(),
            PaintMaterial.TRAIN, ModBlocks.TRAIN_ENCASED_PIPE.getId()
    );
    private static final Set<PaintMaterial> CUSTOM_CREATE_MATERIALS =
            Set.of(PaintMaterial.ANDESITE, PaintMaterial.BRASS, PaintMaterial.TRAIN);
    private static final Set<String> FUNCTIONAL_CRAFTS_CONSTRUCT_VARIANTS = Set.of(
            "fluid_pipe",
            "smart_fluid_pipe",
            "mechanical_pump",
            "fluid_valve",
            "valve_handle",
            "fluid_tank",
            "spout",
            "hose_pulley",
            "portable_fluid_interface",
            "steam_engine",
            "steam_whistle"
    );

    private static final Map<ResourceLocation, Map<PaintMaterial, ResourceLocation>> SPECIAL_TARGETS = Map.ofEntries(
            Map.entry(CREATE_ITEM_DRAIN, ITEM_DRAIN_TARGETS),
            Map.entry(BRASS_ITEM_DRAIN, ITEM_DRAIN_TARGETS),
            Map.entry(ANDESITE_ITEM_DRAIN, ITEM_DRAIN_TARGETS),
            Map.entry(TRAIN_ITEM_DRAIN, ITEM_DRAIN_TARGETS),
            Map.entry(CREATE_GLASS_FLUID_PIPE, GLASS_FLUID_PIPE_TARGETS),
            Map.entry(ModBlocks.BRASS_GLASS_PIPE.getId(), GLASS_FLUID_PIPE_TARGETS),
            Map.entry(ModBlocks.ANDESITE_GLASS_PIPE.getId(), GLASS_FLUID_PIPE_TARGETS),
            Map.entry(ModBlocks.TRAIN_GLASS_PIPE.getId(), GLASS_FLUID_PIPE_TARGETS),
            Map.entry(CREATE_ENCASED_FLUID_PIPE, ENCASED_FLUID_PIPE_TARGETS),
            Map.entry(ModBlocks.BRASS_ENCASED_PIPE.getId(), ENCASED_FLUID_PIPE_TARGETS),
            Map.entry(ModBlocks.ANDESITE_ENCASED_PIPE.getId(), ENCASED_FLUID_PIPE_TARGETS),
            Map.entry(ModBlocks.TRAIN_ENCASED_PIPE.getId(), ENCASED_FLUID_PIPE_TARGETS),
            Map.entry(id("create", "shaft"), Map.of(
                    PaintMaterial.ANDESITE, id("create", "andesite_encased_shaft"),
                    PaintMaterial.BRASS, id("create", "brass_encased_shaft")
            )),
            Map.entry(id("create", "cogwheel"), Map.of(
                    PaintMaterial.ANDESITE, id("create", "andesite_encased_cogwheel"),
                    PaintMaterial.BRASS, id("create", "brass_encased_cogwheel")
            )),
            Map.entry(id("create", "large_cogwheel"), Map.of(
                    PaintMaterial.ANDESITE, id("create", "andesite_encased_large_cogwheel"),
                    PaintMaterial.BRASS, id("create", "brass_encased_large_cogwheel")
            ))
    );
    private static final Map<String, PaintMaterial> CREATE_BASE_MATERIALS = Map.ofEntries(
            Map.entry("fluid_pipe", PaintMaterial.COPPER),
            Map.entry("glass_fluid_pipe", PaintMaterial.COPPER),
            Map.entry("encased_fluid_pipe", PaintMaterial.COPPER),
            Map.entry("mechanical_pump", PaintMaterial.COPPER),
            Map.entry("fluid_valve", PaintMaterial.COPPER),
            Map.entry("valve_handle", PaintMaterial.COPPER),
            Map.entry("fluid_tank", PaintMaterial.COPPER),
            Map.entry("spout", PaintMaterial.COPPER),
            Map.entry("hose_pulley", PaintMaterial.COPPER),
            Map.entry("portable_fluid_interface", PaintMaterial.COPPER),
            Map.entry("steam_engine", PaintMaterial.COPPER),
            Map.entry("steam_whistle", PaintMaterial.COPPER),
            Map.entry("smart_fluid_pipe", PaintMaterial.COPPER)
    );
    private static final Map<String, Set<PaintMaterial>> CUSTOM_MATERIALS = Map.ofEntries(
            Map.entry("fluid_pipe", CUSTOM_CREATE_MATERIALS),
            Map.entry("glass_fluid_pipe", CUSTOM_CREATE_MATERIALS),
            Map.entry("mechanical_pump", CUSTOM_CREATE_MATERIALS),
            Map.entry("fluid_valve", CUSTOM_CREATE_MATERIALS),
            Map.entry("valve_handle", CUSTOM_CREATE_MATERIALS),
            Map.entry("fluid_tank", CUSTOM_CREATE_MATERIALS),
            Map.entry("spout", CUSTOM_CREATE_MATERIALS),
            Map.entry("hose_pulley", CUSTOM_CREATE_MATERIALS),
            Map.entry("portable_fluid_interface", CUSTOM_CREATE_MATERIALS),
            Map.entry("steam_engine", CUSTOM_CREATE_MATERIALS),
            Map.entry("steam_whistle", CUSTOM_CREATE_MATERIALS),
            Map.entry("smart_fluid_pipe", CUSTOM_CREATE_MATERIALS)
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

    public static Optional<PaintMaterial> currentMaterial(ResourceLocation sourceId) {
        if (sourceId.equals(CREATE_ITEM_DRAIN)) {
            return Optional.of(PaintMaterial.COPPER);
        }
        if (sourceId.equals(BRASS_ITEM_DRAIN)) {
            return Optional.of(PaintMaterial.BRASS);
        }
        if (sourceId.equals(ANDESITE_ITEM_DRAIN)) {
            return Optional.of(PaintMaterial.ANDESITE);
        }
        if (sourceId.equals(TRAIN_ITEM_DRAIN)) {
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
        ResourceLocation id = id("create", baseCreatePath(strippedPath));
        return BuiltInRegistries.BLOCK.get(id) == Blocks.AIR ? Optional.empty() : Optional.of(id);
    }

    private static String baseCreatePath(String strippedPath) {
        return "valve_handle".equals(strippedPath) ? "copper_valve_handle" : strippedPath;
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
        return FUNCTIONAL_CRAFTS_CONSTRUCT_VARIANTS.contains(strippedPath);
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
