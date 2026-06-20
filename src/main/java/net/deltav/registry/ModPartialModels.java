package net.deltav.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.deltav.craftsconstruct;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SpriteShifter;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ModPartialModels {
    public static final ThreadLocal<BlockEntity> CURRENT_BE = new ThreadLocal<>();

    public static final Map<String, PartialModel> HOSE_PULLEY_MAGNETS = new HashMap<>();
    public static final Map<String, PartialModel> HOSE_PULLEY_ROPES = new HashMap<>();
    public static final Map<String, PartialModel> HOSE_PULLEY_ROPES_HALF = new HashMap<>();
    public static final Map<String, PartialModel> HOSE_PULLEY_ROPES_HALF_MAGNET = new HashMap<>();
    public static final Map<String, PartialModel> HOSE_PULLEY_COILS = new HashMap<>();
    public static final Map<String, SpriteShiftEntry> HOSE_PULLEY_COIL_SHIFTS = new HashMap<>();

    public static final Map<String, PartialModel> STEAM_ENGINE_GAUGES = new HashMap<>();
    public static final Map<String, PartialModel> STEAM_ENGINE_DIALS = new HashMap<>();
    public static final Map<String, PartialModel> STEAM_ENGINE_PISTONS = new HashMap<>();
    public static final Map<String, PartialModel> STEAM_ENGINE_LINKAGES = new HashMap<>();
    public static final Map<String, PartialModel> STEAM_ENGINE_CONNECTORS = new HashMap<>();

    public static final Map<String, PartialModel> VALVE_POINTERS = new HashMap<>();
    public static final Map<String, PartialModel> VALVE_HANDLES = new HashMap<>();

    public static final Map<String, PartialModel> PORTABLE_STORAGE_INTERFACE_TOP = new HashMap<>();
    public static final Map<String, PartialModel> PORTABLE_STORAGE_INTERFACE_MIDDLE = new HashMap<>();
    public static final Map<String, PartialModel> PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED = new HashMap<>();

    public static final Map<String, PartialModel> PORTABLE_FLUID_INTERFACE_TOP = new HashMap<>();
    public static final Map<String, PartialModel> PORTABLE_FLUID_INTERFACE_MIDDLE = new HashMap<>();
    public static final Map<String, PartialModel> PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED = new HashMap<>();

    public static final Map<String, PartialModel> SPOUT_TOPS = new HashMap<>();
    public static final Map<String, PartialModel> SPOUT_MIDDLES = new HashMap<>();
    public static final Map<String, PartialModel> SPOUT_BOTTOMS = new HashMap<>();

    public static void init() {
        String[] materials = {"andesite", "brass", "train"};
        for (String mat : materials) {
            String enginePath = mat + "_steam_engine";
            STEAM_ENGINE_GAUGES.put(enginePath, partial(enginePath, "gauge"));
            STEAM_ENGINE_DIALS.put(enginePath, partial(enginePath, "gauge_dial"));
            STEAM_ENGINE_PISTONS.put(enginePath, partial(enginePath, "piston"));
            STEAM_ENGINE_LINKAGES.put(enginePath, partial(enginePath, "linkage"));
            STEAM_ENGINE_CONNECTORS.put(enginePath, partial(enginePath, "shaft_connector"));

            String valvePath = mat + "_fluid_valve";
            VALVE_POINTERS.put(valvePath, partial(valvePath, "pointer"));

            String valveHandlePath = mat + "_valve_handle";
            VALVE_HANDLES.put(valveHandlePath, partial(valveHandlePath));

            String spoutPath = mat + "_spout";
            SPOUT_TOPS.put(spoutPath, partial(spoutPath, "top"));
            SPOUT_MIDDLES.put(spoutPath, partial(spoutPath, "middle"));
            SPOUT_BOTTOMS.put(spoutPath, partial(spoutPath, "bottom"));
        }

        String[] storageMaterials = {"brass", "copper", "train"};
        for (String mat : storageMaterials) {
            String path = mat + "_portable_storage_interface";
            PORTABLE_STORAGE_INTERFACE_TOP.put(path, partial(path, "block_top"));
            PORTABLE_STORAGE_INTERFACE_MIDDLE.put(path, partial(path, "block_middle"));
            PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED.put(path, partial(path, "block_middle_powered"));
        }

        String[] fluidMaterials = {"andesite", "brass", "train"};
        for (String mat : fluidMaterials) {
            String path = mat + "_portable_fluid_interface";
            PORTABLE_FLUID_INTERFACE_TOP.put(path, partial(path, "block_top"));
            PORTABLE_FLUID_INTERFACE_MIDDLE.put(path, partial(path, "block_middle"));
            PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED.put(path, partial(path, "block_middle_powered"));
        }

        for (String mat : fluidMaterials) {
            String hosePath = mat + "_hose_pulley";
            HOSE_PULLEY_MAGNETS.put(hosePath, partial(hosePath, "pulley_magnet"));
            HOSE_PULLEY_ROPES.put(hosePath, partial(hosePath, "rope"));
            HOSE_PULLEY_ROPES_HALF.put(hosePath, partial(hosePath, "rope_half"));
            HOSE_PULLEY_ROPES_HALF_MAGNET.put(hosePath, partial(hosePath, "rope_half_magnet"));
            HOSE_PULLEY_COILS.put(hosePath, partial(hosePath, "hose_coil"));
            HOSE_PULLEY_COIL_SHIFTS.put(hosePath, SpriteShifter.get(
                    blockResource(hosePath + "/" + mat + "_hose_pulley_coil"),
                    blockResource(hosePath + "/" + mat + "_hose_pulley_coil_scroll")
            ));
        }
    }

    private static PartialModel partial(String blockPath) {
        return PartialModel.of(blockResource(blockPath));
    }

    private static PartialModel partial(String blockPath, String modelName) {
        return PartialModel.of(blockResource(blockPath + "/" + modelName));
    }

    private static ResourceLocation blockResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + path);
    }
}
