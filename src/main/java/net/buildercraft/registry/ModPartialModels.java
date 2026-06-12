package net.buildercraft.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.buildercraft.craftsconstruct;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ModPartialModels {
    public static final Map<String, PartialModel> STEAM_ENGINE_GAUGES = new HashMap<>();
    public static final Map<String, PartialModel> STEAM_ENGINE_DIALS = new HashMap<>();
    public static final Map<String, PartialModel> STEAM_ENGINE_PISTONS = new HashMap<>();
    public static final Map<String, PartialModel> STEAM_ENGINE_LINKAGES = new HashMap<>();
    public static final Map<String, PartialModel> STEAM_ENGINE_CONNECTORS = new HashMap<>();

    public static final Map<String, PartialModel> VALVE_POINTERS = new HashMap<>();
    public static final Map<String, PartialModel> VALVE_HANDLES = new HashMap<>();

    public static void init() {
        String[] materials = {"andesite", "brass", "train"};
        for (String mat : materials) {
            String enginePath = mat + "_steam_engine";
            STEAM_ENGINE_GAUGES.put(enginePath, PartialModel.of(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + enginePath + "/gauge")));
            STEAM_ENGINE_DIALS.put(enginePath, PartialModel.of(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + enginePath + "/gauge_dial")));
            STEAM_ENGINE_PISTONS.put(enginePath, PartialModel.of(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + enginePath + "/piston")));
            STEAM_ENGINE_LINKAGES.put(enginePath, PartialModel.of(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + enginePath + "/linkage")));
            STEAM_ENGINE_CONNECTORS.put(enginePath, PartialModel.of(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + enginePath + "/shaft_connector")));

            String valvePath = mat + "_fluid_valve";
            VALVE_POINTERS.put(valvePath, PartialModel.of(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + valvePath + "/pointer")));

            String valveHandlePath = mat + "_valve_handle";
            VALVE_HANDLES.put(valveHandlePath, PartialModel.of(ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + valveHandlePath)));
        }
    }
}
