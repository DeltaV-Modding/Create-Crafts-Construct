package net.deltav.craftsconstruct.registry;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.TickEvent;
import net.deltav.craftsconstruct.util.PaintGunOutlineRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void onTickPost(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            onTick(false);
        }
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
