package net.deltav;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.deltav.block.create.drain.ItemDrainClient;
import net.deltav.block.create.fluid.FluidClient;
import net.deltav.block.create.kinetic.KineticClient;
import net.deltav.block.create.pipe.PipeClient;
import net.deltav.block.create.portableInterface.PortableFluidInterfaceClient;
import net.deltav.item.PaintGunItem;
import net.deltav.registry.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.deltav.util.ModCreativeModeTabs;

@Mod(craftsconstruct.MOD_ID)
public class craftsconstruct {
    public static final String MOD_ID = "crafts_construct";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }


    public craftsconstruct(IEventBus modEventBus, ModContainer modContainer) {
        REGISTRATE.registerEventListeners(modEventBus);

        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        ModFluids.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);
        ModCapabilities.register(modEventBus);
        modEventBus.addListener(this::onAddBlocksToBE);


        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void onAddBlocksToBE(net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent event) {
        for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_FLUID_TANKS) {
            event.modify(com.simibubi.create.AllBlockEntityTypes.FLUID_TANK.get(), block.get());
        }
        for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_VALVE_HANDLES) {
            event.modify(com.simibubi.create.AllBlockEntityTypes.VALVE_HANDLE.get(), block.get());
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_STEAM_ENGINES) {
                com.simibubi.create.api.stress.BlockStressValues.CAPACITIES.register(block.get(), () -> com.simibubi.create.api.stress.BlockStressValues.getCapacity(com.simibubi.create.AllBlocks.STEAM_ENGINE.get()));
                com.simibubi.create.api.stress.BlockStressValues.RPM.register(block.get(), com.simibubi.create.api.stress.BlockStressValues.RPM.get(com.simibubi.create.AllBlocks.STEAM_ENGINE.get()));
            }

            // Register Portable Storage Interfaces
            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_PORTABLE_STORAGE_INTERFACES) {
                com.simibubi.create.api.behaviour.movement.MovementBehaviour.REGISTRY.register(block.get(), new com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceMovement());
            }

            // Register Portable Fluid Interfaces
            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_PORTABLE_FLUID_INTERFACES) {
                com.simibubi.create.api.behaviour.movement.MovementBehaviour.REGISTRY.register(block.get(), new com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceMovement());
            }

            // Register Contraption Controls
            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_CONTRAPTION_CONTROLS) {
                com.simibubi.create.api.behaviour.movement.MovementBehaviour.REGISTRY.register(block.get(), new com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsMovement());
                com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour.REGISTRY.register(block.get(), new com.simibubi.create.content.contraptions.actors.contraptionControls.ContraptionControlsMovingInteraction());
            }

            // Register Fluid Tanks
            for (net.neoforged.neoforge.registries.DeferredBlock<net.minecraft.world.level.block.Block> block : ModBlocks.PAINTED_FLUID_TANKS) {
                com.simibubi.create.api.behaviour.movement.MovementBehaviour.REGISTRY.register(block.get(), new com.simibubi.create.content.fluids.tank.FluidTankMovementBehavior());
                com.simibubi.create.api.contraption.storage.fluid.MountedFluidStorageType.REGISTRY.register(block.get(), com.simibubi.create.AllMountedStorageTypes.FLUID_TANK.get());
            }
        });
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
            });
            KineticClient.registerVisualizers();
            FluidClient.registerVisualizers();
            PortableFluidInterfaceClient.registerVisualizers();
            ItemDrainClient.registerModelLayers();
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onModifyBakingResult(net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult event) {
            FluidClient.registerModelSwappers();
            PipeClient.registerModelSwappers();
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public static void onRegisterAdditional(net.neoforged.neoforge.client.event.ModelEvent.RegisterAdditional event) {
            net.deltav.registry.ModPartialModels.init();
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            KineticClient.registerRenderers(event);
            FluidClient.registerRenderers(event);
            PipeClient.registerRenderers(event);
            PortableFluidInterfaceClient.registerRenderers(event);
            ItemDrainClient.registerRenderers(event);
        }
    }
}
