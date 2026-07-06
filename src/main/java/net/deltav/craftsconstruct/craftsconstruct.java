package net.deltav.craftsconstruct;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.deltav.craftsconstruct.block.create.drain.ItemDrainClient;
import net.deltav.craftsconstruct.block.create.fluid.FluidClient;
import net.deltav.craftsconstruct.block.create.pipe.PipeClient;
import net.deltav.craftsconstruct.item.PaintGunItem;
import net.deltav.craftsconstruct.registry.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.deltav.craftsconstruct.registry.ModCreativeModeTabs;

@Mod(craftsconstruct.MOD_ID)
public class craftsconstruct {
    public static final String MOD_ID = "crafts_construct";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }


    public craftsconstruct() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRATE.defaultCreativeTab(ModCreativeModeTabs.CC_TAB_KEY);
        REGISTRATE.registerEventListeners(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        ModFluids.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register();
        ModCreativeModeTabs.register(modEventBus);
        ModFeatures.register(modEventBus);
        ModBlockEntityTypes.register();
        ModCapabilities.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!(event.getItemStack().getItem() instanceof PaintGunItem paintGun)) {
            return;
        }

        net.minecraft.world.item.context.UseOnContext context =
                new net.minecraft.world.item.context.UseOnContext(event.getEntity(), event.getHand(), event.getHitVec());
        net.minecraft.world.InteractionResult result = PaintGunItem.useOn(context, paintGun.isCreative());
        if (result.consumesAction()) {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                FluidClient.registerRenderLayers();
                PipeClient.registerRenderLayers();
                FluidClient.registerModelSwappers();
                PipeClient.registerModelSwappers();
            });
            ItemDrainClient.registerModelLayers();
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void onRegisterAdditional(net.minecraftforge.client.event.ModelEvent.RegisterAdditional event) {
            net.deltav.craftsconstruct.registry.ModPartialModels.init();
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            FluidClient.registerRenderers(event);
        }
    }
}
