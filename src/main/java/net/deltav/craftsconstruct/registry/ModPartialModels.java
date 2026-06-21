package net.deltav.craftsconstruct.registry;

import java.util.HashMap;
import java.util.Map;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SpriteShifter;
import net.deltav.craftsconstruct.craftsconstruct;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ModPartialModels {
    public static final ThreadLocal<BlockEntity> CURRENT_BE = new ThreadLocal<>();

    public static final PartialModel
            ANDESITE_HOSE_PULLEY_MAGNET = block("andesite_hose_pulley/pulley_magnet"),
            ANDESITE_HOSE_PULLEY_ROPE = block("andesite_hose_pulley/rope"),
            ANDESITE_HOSE_PULLEY_ROPE_HALF = block("andesite_hose_pulley/rope_half"),
            ANDESITE_HOSE_PULLEY_ROPE_HALF_MAGNET = block("andesite_hose_pulley/rope_half_magnet"),
            ANDESITE_HOSE_PULLEY_COIL = block("andesite_hose_pulley/hose_coil"),

            BRASS_HOSE_PULLEY_MAGNET = block("brass_hose_pulley/pulley_magnet"),
            BRASS_HOSE_PULLEY_ROPE = block("brass_hose_pulley/rope"),
            BRASS_HOSE_PULLEY_ROPE_HALF = block("brass_hose_pulley/rope_half"),
            BRASS_HOSE_PULLEY_ROPE_HALF_MAGNET = block("brass_hose_pulley/rope_half_magnet"),
            BRASS_HOSE_PULLEY_COIL = block("brass_hose_pulley/hose_coil"),

            STURDY_HOSE_PULLEY_MAGNET = block("sturdy_hose_pulley/pulley_magnet"),
            STURDY_HOSE_PULLEY_ROPE = block("sturdy_hose_pulley/rope"),
            STURDY_HOSE_PULLEY_ROPE_HALF = block("sturdy_hose_pulley/rope_half"),
            STURDY_HOSE_PULLEY_ROPE_HALF_MAGNET = block("sturdy_hose_pulley/rope_half_magnet"),
            STURDY_HOSE_PULLEY_COIL = block("sturdy_hose_pulley/hose_coil"),

            ANDESITE_STEAM_ENGINE_GAUGE = block("andesite_steam_engine/gauge"),
            ANDESITE_STEAM_ENGINE_GAUGE_DIAL = block("andesite_steam_engine/gauge_dial"),
            ANDESITE_STEAM_ENGINE_PISTON = block("andesite_steam_engine/piston"),
            ANDESITE_STEAM_ENGINE_LINKAGE = block("andesite_steam_engine/linkage"),
            ANDESITE_STEAM_ENGINE_CONNECTOR = block("andesite_steam_engine/shaft_connector"),

            BRASS_STEAM_ENGINE_GAUGE = block("brass_steam_engine/gauge"),
            BRASS_STEAM_ENGINE_GAUGE_DIAL = block("brass_steam_engine/gauge_dial"),
            BRASS_STEAM_ENGINE_PISTON = block("brass_steam_engine/piston"),
            BRASS_STEAM_ENGINE_LINKAGE = block("brass_steam_engine/linkage"),
            BRASS_STEAM_ENGINE_CONNECTOR = block("brass_steam_engine/shaft_connector"),

            STURDY_STEAM_ENGINE_GAUGE = block("sturdy_steam_engine/gauge"),
            STURDY_STEAM_ENGINE_GAUGE_DIAL = block("sturdy_steam_engine/gauge_dial"),
            STURDY_STEAM_ENGINE_PISTON = block("sturdy_steam_engine/piston"),
            STURDY_STEAM_ENGINE_LINKAGE = block("sturdy_steam_engine/linkage"),
            STURDY_STEAM_ENGINE_CONNECTOR = block("sturdy_steam_engine/shaft_connector"),

            ANDESITE_FLUID_VALVE_POINTER = block("andesite_fluid_valve/pointer"),
            BRASS_FLUID_VALVE_POINTER = block("brass_fluid_valve/pointer"),
            STURDY_FLUID_VALVE_POINTER = block("sturdy_fluid_valve/pointer"),

            ANDESITE_VALVE_HANDLE = block("andesite_valve_handle"),
            BRASS_VALVE_HANDLE = block("brass_valve_handle"),
            STURDY_VALVE_HANDLE = block("sturdy_valve_handle"),

            ANDESITE_SPOUT_TOP = block("andesite_spout/top"),
            ANDESITE_SPOUT_MIDDLE = block("andesite_spout/middle"),
            ANDESITE_SPOUT_BOTTOM = block("andesite_spout/bottom"),
            BRASS_SPOUT_TOP = block("brass_spout/top"),
            BRASS_SPOUT_MIDDLE = block("brass_spout/middle"),
            BRASS_SPOUT_BOTTOM = block("brass_spout/bottom"),
            STURDY_SPOUT_TOP = block("sturdy_spout/top"),
            STURDY_SPOUT_MIDDLE = block("sturdy_spout/middle"),
            STURDY_SPOUT_BOTTOM = block("sturdy_spout/bottom"),

            BRASS_PORTABLE_STORAGE_INTERFACE_TOP = block("brass_portable_storage_interface/block_top"),
            BRASS_PORTABLE_STORAGE_INTERFACE_MIDDLE = block("brass_portable_storage_interface/block_middle"),
            BRASS_PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED = block("brass_portable_storage_interface/block_middle_powered"),
            COPPER_PORTABLE_STORAGE_INTERFACE_TOP = block("copper_portable_storage_interface/block_top"),
            COPPER_PORTABLE_STORAGE_INTERFACE_MIDDLE = block("copper_portable_storage_interface/block_middle"),
            COPPER_PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED = block("copper_portable_storage_interface/block_middle_powered"),
            STURDY_PORTABLE_STORAGE_INTERFACE_TOP = block("sturdy_portable_storage_interface/block_top"),
            STURDY_PORTABLE_STORAGE_INTERFACE_MIDDLE = block("sturdy_portable_storage_interface/block_middle"),
            STURDY_PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED = block("sturdy_portable_storage_interface/block_middle_powered"),

            ANDESITE_PORTABLE_FLUID_INTERFACE_TOP = block("andesite_portable_fluid_interface/block_top"),
            ANDESITE_PORTABLE_FLUID_INTERFACE_MIDDLE = block("andesite_portable_fluid_interface/block_middle"),
            ANDESITE_PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED = block("andesite_portable_fluid_interface/block_middle_powered"),
            BRASS_PORTABLE_FLUID_INTERFACE_TOP = block("brass_portable_fluid_interface/block_top"),
            BRASS_PORTABLE_FLUID_INTERFACE_MIDDLE = block("brass_portable_fluid_interface/block_middle"),
            BRASS_PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED = block("brass_portable_fluid_interface/block_middle_powered"),
            STURDY_PORTABLE_FLUID_INTERFACE_TOP = block("sturdy_portable_fluid_interface/block_top"),
            STURDY_PORTABLE_FLUID_INTERFACE_MIDDLE = block("sturdy_portable_fluid_interface/block_middle"),
            STURDY_PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED = block("sturdy_portable_fluid_interface/block_middle_powered");

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

    static {
        putHosePulley("andesite_hose_pulley", ANDESITE_HOSE_PULLEY_MAGNET, ANDESITE_HOSE_PULLEY_ROPE,
                ANDESITE_HOSE_PULLEY_ROPE_HALF, ANDESITE_HOSE_PULLEY_ROPE_HALF_MAGNET, ANDESITE_HOSE_PULLEY_COIL);
        putHosePulley("brass_hose_pulley", BRASS_HOSE_PULLEY_MAGNET, BRASS_HOSE_PULLEY_ROPE,
                BRASS_HOSE_PULLEY_ROPE_HALF, BRASS_HOSE_PULLEY_ROPE_HALF_MAGNET, BRASS_HOSE_PULLEY_COIL);
        putHosePulley("sturdy_hose_pulley", STURDY_HOSE_PULLEY_MAGNET, STURDY_HOSE_PULLEY_ROPE,
                STURDY_HOSE_PULLEY_ROPE_HALF, STURDY_HOSE_PULLEY_ROPE_HALF_MAGNET, STURDY_HOSE_PULLEY_COIL);

        putSteamEngine("andesite_steam_engine", ANDESITE_STEAM_ENGINE_GAUGE, ANDESITE_STEAM_ENGINE_GAUGE_DIAL,
                ANDESITE_STEAM_ENGINE_PISTON, ANDESITE_STEAM_ENGINE_LINKAGE, ANDESITE_STEAM_ENGINE_CONNECTOR);
        putSteamEngine("brass_steam_engine", BRASS_STEAM_ENGINE_GAUGE, BRASS_STEAM_ENGINE_GAUGE_DIAL,
                BRASS_STEAM_ENGINE_PISTON, BRASS_STEAM_ENGINE_LINKAGE, BRASS_STEAM_ENGINE_CONNECTOR);
        putSteamEngine("sturdy_steam_engine", STURDY_STEAM_ENGINE_GAUGE, STURDY_STEAM_ENGINE_GAUGE_DIAL,
                STURDY_STEAM_ENGINE_PISTON, STURDY_STEAM_ENGINE_LINKAGE, STURDY_STEAM_ENGINE_CONNECTOR);

        VALVE_POINTERS.put("andesite_fluid_valve", ANDESITE_FLUID_VALVE_POINTER);
        VALVE_POINTERS.put("brass_fluid_valve", BRASS_FLUID_VALVE_POINTER);
        VALVE_POINTERS.put("sturdy_fluid_valve", STURDY_FLUID_VALVE_POINTER);

        VALVE_HANDLES.put("andesite_valve_handle", ANDESITE_VALVE_HANDLE);
        VALVE_HANDLES.put("brass_valve_handle", BRASS_VALVE_HANDLE);
        VALVE_HANDLES.put("sturdy_valve_handle", STURDY_VALVE_HANDLE);

        putSpout("andesite_spout", ANDESITE_SPOUT_TOP, ANDESITE_SPOUT_MIDDLE, ANDESITE_SPOUT_BOTTOM);
        putSpout("brass_spout", BRASS_SPOUT_TOP, BRASS_SPOUT_MIDDLE, BRASS_SPOUT_BOTTOM);
        putSpout("sturdy_spout", STURDY_SPOUT_TOP, STURDY_SPOUT_MIDDLE, STURDY_SPOUT_BOTTOM);

        putPortableStorageInterface("brass_portable_storage_interface", BRASS_PORTABLE_STORAGE_INTERFACE_TOP,
                BRASS_PORTABLE_STORAGE_INTERFACE_MIDDLE, BRASS_PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED);
        putPortableStorageInterface("copper_portable_storage_interface", COPPER_PORTABLE_STORAGE_INTERFACE_TOP,
                COPPER_PORTABLE_STORAGE_INTERFACE_MIDDLE, COPPER_PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED);
        putPortableStorageInterface("sturdy_portable_storage_interface", STURDY_PORTABLE_STORAGE_INTERFACE_TOP,
                STURDY_PORTABLE_STORAGE_INTERFACE_MIDDLE, STURDY_PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED);

        putPortableFluidInterface("andesite_portable_fluid_interface", ANDESITE_PORTABLE_FLUID_INTERFACE_TOP,
                ANDESITE_PORTABLE_FLUID_INTERFACE_MIDDLE, ANDESITE_PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED);
        putPortableFluidInterface("brass_portable_fluid_interface", BRASS_PORTABLE_FLUID_INTERFACE_TOP,
                BRASS_PORTABLE_FLUID_INTERFACE_MIDDLE, BRASS_PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED);
        putPortableFluidInterface("sturdy_portable_fluid_interface", STURDY_PORTABLE_FLUID_INTERFACE_TOP,
                STURDY_PORTABLE_FLUID_INTERFACE_MIDDLE, STURDY_PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED);
    }

    private static void putHosePulley(String path, PartialModel magnet, PartialModel rope, PartialModel ropeHalf,
                                      PartialModel ropeHalfMagnet, PartialModel coil) {
        HOSE_PULLEY_MAGNETS.put(path, magnet);
        HOSE_PULLEY_ROPES.put(path, rope);
        HOSE_PULLEY_ROPES_HALF.put(path, ropeHalf);
        HOSE_PULLEY_ROPES_HALF_MAGNET.put(path, ropeHalfMagnet);
        HOSE_PULLEY_COILS.put(path, coil);

        String material = path.substring(0, path.indexOf("_hose_pulley"));
        HOSE_PULLEY_COIL_SHIFTS.put(path, SpriteShifter.get(
                blockResource(path + "/" + material + "_hose_pulley_coil"),
                blockResource(path + "/" + material + "_hose_pulley_coil_scroll")
        ));
    }

    private static void putSteamEngine(String path, PartialModel gauge, PartialModel dial, PartialModel piston,
                                       PartialModel linkage, PartialModel connector) {
        STEAM_ENGINE_GAUGES.put(path, gauge);
        STEAM_ENGINE_DIALS.put(path, dial);
        STEAM_ENGINE_PISTONS.put(path, piston);
        STEAM_ENGINE_LINKAGES.put(path, linkage);
        STEAM_ENGINE_CONNECTORS.put(path, connector);
    }

    private static void putSpout(String path, PartialModel top, PartialModel middle, PartialModel bottom) {
        SPOUT_TOPS.put(path, top);
        SPOUT_MIDDLES.put(path, middle);
        SPOUT_BOTTOMS.put(path, bottom);
    }

    private static void putPortableStorageInterface(String path, PartialModel top, PartialModel middle, PartialModel middlePowered) {
        PORTABLE_STORAGE_INTERFACE_TOP.put(path, top);
        PORTABLE_STORAGE_INTERFACE_MIDDLE.put(path, middle);
        PORTABLE_STORAGE_INTERFACE_MIDDLE_POWERED.put(path, middlePowered);
    }

    private static void putPortableFluidInterface(String path, PartialModel top, PartialModel middle, PartialModel middlePowered) {
        PORTABLE_FLUID_INTERFACE_TOP.put(path, top);
        PORTABLE_FLUID_INTERFACE_MIDDLE.put(path, middle);
        PORTABLE_FLUID_INTERFACE_MIDDLE_POWERED.put(path, middlePowered);
    }

    private static PartialModel block(String path) {
        return PartialModel.of(blockResource(path));
    }

    private static ResourceLocation blockResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + path);
    }

    public static void init() {
        // init static fields
    }
}
