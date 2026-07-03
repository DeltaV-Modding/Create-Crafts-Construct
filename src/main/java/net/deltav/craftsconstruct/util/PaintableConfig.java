package net.deltav.craftsconstruct.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceLocation;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class PaintableConfig {

    private static final Set<ResourceLocation> paintableBlocks = new HashSet<>();

    static {
        try {
            InputStreamReader reader = new InputStreamReader(
                    PaintableConfig.class.getResourceAsStream("/config/paintgun/paintable.json")
            );

            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            if (json.has("paintable_blocks")) {
                Type type = new TypeToken<Set<String>>(){}.getType();
                Set<String> blockStrings = new Gson().fromJson(json.get("paintable_blocks"), type);

                for (String blockId : blockStrings) {
                    ResourceLocation rl = new ResourceLocation(blockId);
                    paintableBlocks.add(rl);
                }
            }

        } catch (Exception e){
        }
    }

    public static boolean isBlockPaintable(ResourceLocation blockId) {
        return paintableBlocks.contains(blockId) || isTempPaintVariant(blockId);
    }

    private static boolean isTempPaintVariant(ResourceLocation blockId) {
        if (!"crafts_construct".equals(blockId.getNamespace())) {
            return false;
        }
        String path = blockId.getPath();
        return path.startsWith("andesite_") || path.startsWith("brass_")
                || path.startsWith("copper_") || path.startsWith("sturdy_");
    }
}
