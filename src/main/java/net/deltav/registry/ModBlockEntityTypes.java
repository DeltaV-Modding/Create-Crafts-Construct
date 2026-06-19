package net.deltav.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.deltav.craftsconstruct;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// Material-specific imports
import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.fluid.copper.*;
import net.deltav.block.create.fluid.train.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.kinetic.train.*;
import net.deltav.block.create.pipe.andesite.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.pipe.train.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.portableInterface.train.*;
import net.deltav.block.create.drain.andesite.*;
import net.deltav.block.create.drain.brass.*;
import net.deltav.block.create.drain.train.*;

public class ModBlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = craftsconstruct.registrate();
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, craftsconstruct.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteFluidPipeBlockEntity>> ANDESITE_FLUID_PIPE =
            BLOCK_ENTITY_TYPES.register("andesite_fluid_pipe", () -> BlockEntityType.Builder.of(
                    AndesiteFluidPipeBlockEntity::new,
                    ModBlocks.ANDESITE_FLUID_PIPE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassFluidPipeBlockEntity>> BRASS_FLUID_PIPE =
            BLOCK_ENTITY_TYPES.register("brass_fluid_pipe", () -> BlockEntityType.Builder.of(
                    BrassFluidPipeBlockEntity::new,
                    ModBlocks.BRASS_FLUID_PIPE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainFluidPipeBlockEntity>> TRAIN_FLUID_PIPE =
            BLOCK_ENTITY_TYPES.register("train_fluid_pipe", () -> BlockEntityType.Builder.of(
                    TrainFluidPipeBlockEntity::new,
                    ModBlocks.TRAIN_FLUID_PIPE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteSmartFluidPipeBlockEntity>> ANDESITE_SMART_FLUID_PIPE =
            BLOCK_ENTITY_TYPES.register("andesite_smart_fluid_pipe", () -> BlockEntityType.Builder.of(
                    AndesiteSmartFluidPipeBlockEntity::new,
                    ModBlocks.ANDESITE_SMART_FLUID_PIPE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassSmartFluidPipeBlockEntity>> BRASS_SMART_FLUID_PIPE =
            BLOCK_ENTITY_TYPES.register("brass_smart_fluid_pipe", () -> BlockEntityType.Builder.of(
                    BrassSmartFluidPipeBlockEntity::new,
                    ModBlocks.BRASS_SMART_FLUID_PIPE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainSmartFluidPipeBlockEntity>> TRAIN_SMART_FLUID_PIPE =
            BLOCK_ENTITY_TYPES.register("train_smart_fluid_pipe", () -> BlockEntityType.Builder.of(
                    TrainSmartFluidPipeBlockEntity::new,
                    ModBlocks.TRAIN_SMART_FLUID_PIPE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesitePumpBlockEntity>> ANDESITE_MECHANICAL_PUMP =
            BLOCK_ENTITY_TYPES.register("andesite_mechanical_pump", () -> BlockEntityType.Builder.of(
                    AndesitePumpBlockEntity::new,
                    ModBlocks.ANDESITE_MECHANICAL_PUMP.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassPumpBlockEntity>> BRASS_MECHANICAL_PUMP =
            BLOCK_ENTITY_TYPES.register("brass_mechanical_pump", () -> BlockEntityType.Builder.of(
                    BrassPumpBlockEntity::new,
                    ModBlocks.BRASS_MECHANICAL_PUMP.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainPumpBlockEntity>> TRAIN_MECHANICAL_PUMP =
            BLOCK_ENTITY_TYPES.register("train_mechanical_pump", () -> BlockEntityType.Builder.of(
                    TrainPumpBlockEntity::new,
                    ModBlocks.TRAIN_MECHANICAL_PUMP.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteFluidValveBlockEntity>> ANDESITE_FLUID_VALVE =
            BLOCK_ENTITY_TYPES.register("andesite_fluid_valve", () -> BlockEntityType.Builder.of(
                    AndesiteFluidValveBlockEntity::new,
                    ModBlocks.ANDESITE_FLUID_VALVE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassFluidValveBlockEntity>> BRASS_FLUID_VALVE =
            BLOCK_ENTITY_TYPES.register("brass_fluid_valve", () -> BlockEntityType.Builder.of(
                    BrassFluidValveBlockEntity::new,
                    ModBlocks.BRASS_FLUID_VALVE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainFluidValveBlockEntity>> TRAIN_FLUID_VALVE =
            BLOCK_ENTITY_TYPES.register("train_fluid_valve", () -> BlockEntityType.Builder.of(
                    TrainFluidValveBlockEntity::new,
                    ModBlocks.TRAIN_FLUID_VALVE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteFluidTankBlockEntity>> ANDESITE_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("andesite_fluid_tank", () -> BlockEntityType.Builder.of(
                    AndesiteFluidTankBlockEntity::new,
                    ModBlocks.ANDESITE_FLUID_TANK.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassFluidTankBlockEntity>> BRASS_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("brass_fluid_tank", () -> BlockEntityType.Builder.of(
                    BrassFluidTankBlockEntity::new,
                    ModBlocks.BRASS_FLUID_TANK.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainFluidTankBlockEntity>> TRAIN_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("train_fluid_tank", () -> BlockEntityType.Builder.of(
                    TrainFluidTankBlockEntity::new,
                    ModBlocks.TRAIN_FLUID_TANK.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteHorizontalFluidTankBlockEntity>> ANDESITE_HORIZONTAL_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("andesite_horizontal_fluid_tank", () -> BlockEntityType.Builder.of(
                    AndesiteHorizontalFluidTankBlockEntity::new,
                    ModBlocks.ANDESITE_HORIZONTAL_FLUID_TANK.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassHorizontalFluidTankBlockEntity>> BRASS_HORIZONTAL_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("brass_horizontal_fluid_tank", () -> BlockEntityType.Builder.of(
                    BrassHorizontalFluidTankBlockEntity::new,
                    ModBlocks.BRASS_HORIZONTAL_FLUID_TANK.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CopperHorizontalFluidTankBlockEntity>> COPPER_HORIZONTAL_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("copper_horizontal_fluid_tank", () -> BlockEntityType.Builder.of(
                    CopperHorizontalFluidTankBlockEntity::new,
                    ModBlocks.COPPER_HORIZONTAL_FLUID_TANK.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainHorizontalFluidTankBlockEntity>> TRAIN_HORIZONTAL_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("train_horizontal_fluid_tank", () -> BlockEntityType.Builder.of(
                    TrainHorizontalFluidTankBlockEntity::new,
                    ModBlocks.TRAIN_HORIZONTAL_FLUID_TANK.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteSpoutBlockEntity>> ANDESITE_SPOUT =
            BLOCK_ENTITY_TYPES.register("andesite_spout", () -> BlockEntityType.Builder.of(
                    AndesiteSpoutBlockEntity::new,
                    ModBlocks.ANDESITE_SPOUT.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassSpoutBlockEntity>> BRASS_SPOUT =
            BLOCK_ENTITY_TYPES.register("brass_spout", () -> BlockEntityType.Builder.of(
                    BrassSpoutBlockEntity::new,
                    ModBlocks.BRASS_SPOUT.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainSpoutBlockEntity>> TRAIN_SPOUT =
            BLOCK_ENTITY_TYPES.register("train_spout", () -> BlockEntityType.Builder.of(
                    TrainSpoutBlockEntity::new,
                    ModBlocks.TRAIN_SPOUT.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteHosePulleyBlockEntity>> ANDESITE_HOSE_PULLEY =
            BLOCK_ENTITY_TYPES.register("andesite_hose_pulley", () -> BlockEntityType.Builder.of(
                    AndesiteHosePulleyBlockEntity::new,
                    ModBlocks.ANDESITE_HOSE_PULLEY.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassHosePulleyBlockEntity>> BRASS_HOSE_PULLEY =
            BLOCK_ENTITY_TYPES.register("brass_hose_pulley", () -> BlockEntityType.Builder.of(
                    BrassHosePulleyBlockEntity::new,
                    ModBlocks.BRASS_HOSE_PULLEY.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainHosePulleyBlockEntity>> TRAIN_HOSE_PULLEY =
            BLOCK_ENTITY_TYPES.register("train_hose_pulley", () -> BlockEntityType.Builder.of(
                    TrainHosePulleyBlockEntity::new,
                    ModBlocks.TRAIN_HOSE_PULLEY.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteItemDrainBlockEntity>> ANDESITE_ITEM_DRAIN =
            BLOCK_ENTITY_TYPES.register("andesite_item_drain", () -> BlockEntityType.Builder.of(
                    AndesiteItemDrainBlockEntity::new,
                    ModBlocks.ANDESITE_ITEM_DRAIN.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassItemDrainBlockEntity>> BRASS_ITEM_DRAIN =
            BLOCK_ENTITY_TYPES.register("brass_item_drain", () -> BlockEntityType.Builder.of(
                    BrassItemDrainBlockEntity::new,
                    ModBlocks.BRASS_ITEM_DRAIN.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainItemDrainBlockEntity>> TRAIN_ITEM_DRAIN =
            BLOCK_ENTITY_TYPES.register("train_item_drain", () -> BlockEntityType.Builder.of(
                    TrainItemDrainBlockEntity::new,
                    ModBlocks.TRAIN_ITEM_DRAIN.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesitePortableFluidInterfaceBlockEntity>> ANDESITE_PORTABLE_FLUID_INTERFACE =
            BLOCK_ENTITY_TYPES.register("andesite_portable_fluid_interface", () -> BlockEntityType.Builder.of(
                    AndesitePortableFluidInterfaceBlockEntity::new,
                    ModBlocks.ANDESITE_PORTABLE_FLUID_INTERFACE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassPortableFluidInterfaceBlockEntity>> BRASS_PORTABLE_FLUID_INTERFACE =
            BLOCK_ENTITY_TYPES.register("brass_portable_fluid_interface", () -> BlockEntityType.Builder.of(
                    BrassPortableFluidInterfaceBlockEntity::new,
                    ModBlocks.BRASS_PORTABLE_FLUID_INTERFACE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainPortableFluidInterfaceBlockEntity>> TRAIN_PORTABLE_FLUID_INTERFACE =
            BLOCK_ENTITY_TYPES.register("train_portable_fluid_interface", () -> BlockEntityType.Builder.of(
                    TrainPortableFluidInterfaceBlockEntity::new,
                    ModBlocks.TRAIN_PORTABLE_FLUID_INTERFACE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteSteamEngineBlockEntity>> ANDESITE_STEAM_ENGINE =
            BLOCK_ENTITY_TYPES.register("andesite_steam_engine", () -> BlockEntityType.Builder.of(
                    AndesiteSteamEngineBlockEntity::new,
                    ModBlocks.ANDESITE_STEAM_ENGINE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassSteamEngineBlockEntity>> BRASS_STEAM_ENGINE =
            BLOCK_ENTITY_TYPES.register("brass_steam_engine", () -> BlockEntityType.Builder.of(
                    BrassSteamEngineBlockEntity::new,
                    ModBlocks.BRASS_STEAM_ENGINE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainSteamEngineBlockEntity>> TRAIN_STEAM_ENGINE =
            BLOCK_ENTITY_TYPES.register("train_steam_engine", () -> BlockEntityType.Builder.of(
                    TrainSteamEngineBlockEntity::new,
                    ModBlocks.TRAIN_STEAM_ENGINE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteSteamWhistleBlockEntity>> ANDESITE_STEAM_WHISTLE =
            BLOCK_ENTITY_TYPES.register("andesite_steam_whistle", () -> BlockEntityType.Builder.of(
                    AndesiteSteamWhistleBlockEntity::new,
                    ModBlocks.ANDESITE_STEAM_WHISTLE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassSteamWhistleBlockEntity>> BRASS_STEAM_WHISTLE =
            BLOCK_ENTITY_TYPES.register("brass_steam_whistle", () -> BlockEntityType.Builder.of(
                    BrassSteamWhistleBlockEntity::new,
                    ModBlocks.BRASS_STEAM_WHISTLE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainSteamWhistleBlockEntity>> TRAIN_STEAM_WHISTLE =
            BLOCK_ENTITY_TYPES.register("train_steam_whistle", () -> BlockEntityType.Builder.of(
                    TrainSteamWhistleBlockEntity::new,
                    ModBlocks.TRAIN_STEAM_WHISTLE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AndesiteChainDriveBlockEntity>> ANDESITE_ENCASED_CHAIN_DRIVE =
            BLOCK_ENTITY_TYPES.register("andesite_encased_chain_drive", () -> BlockEntityType.Builder.of(
                    AndesiteChainDriveBlockEntity::new,
                    ModBlocks.ANDESITE_ENCASED_CHAIN_DRIVE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrassChainDriveBlockEntity>> BRASS_ENCASED_CHAIN_DRIVE =
            BLOCK_ENTITY_TYPES.register("brass_encased_chain_drive", () -> BlockEntityType.Builder.of(
                    BrassChainDriveBlockEntity::new,
                    ModBlocks.BRASS_ENCASED_CHAIN_DRIVE.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TrainChainDriveBlockEntity>> TRAIN_ENCASED_CHAIN_DRIVE =
            BLOCK_ENTITY_TYPES.register("train_encased_chain_drive", () -> BlockEntityType.Builder.of(
                    TrainChainDriveBlockEntity::new,
                    ModBlocks.TRAIN_ENCASED_CHAIN_DRIVE.get()
            ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
