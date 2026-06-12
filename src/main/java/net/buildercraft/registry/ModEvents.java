package net.buildercraft.registry;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.buildercraft.util.PaintGunOutlineRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void onTickPost(ClientTickEvent.Post event) {
        onTick(false);
    }

    protected static boolean isGameActive() {
        return !(Minecraft.getInstance().level == null);
    }

    public static void onTick(boolean isPreEvent) {
        if (!isGameActive())
            return;
        PaintGunOutlineRenderer.tick();
    }
}