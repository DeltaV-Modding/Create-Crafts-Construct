package net.deltav.registry;

import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import net.deltav.block.SugarBeetCropBlock;
import net.deltav.craftsconstruct;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.simibubi.create.content.fluids.tank.FluidTankItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(craftsconstruct.MOD_ID);


    public static final List<DeferredBlock<Block>> TEMP_PAINT_BLOCKS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_FLUID_PIPES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_SMART_FLUID_PIPES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_MECHANICAL_PUMPS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_FLUID_VALVES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_VALVE_HANDLES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_FLUID_TANKS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_HORIZONTAL_FLUID_TANKS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_SPOUTS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_HOSE_PULLEYS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_GEARBOXES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_ENCASED_CHAIN_DRIVES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_ENCASED_FANS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_MILLSTONES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_MECHANICAL_SAWS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_MECHANICAL_PRESSES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_MECHANICAL_MIXERS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_DEPLOYERS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_MECHANICAL_DRILLS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_MECHANICAL_CRAFTERS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_STEAM_ENGINES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_STEAM_WHISTLES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_PORTABLE_FLUID_INTERFACES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_ITEM_DRAINS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_DEPOTS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_WEIGHTED_EJECTORS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_CLUTCHES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_GEARSHIFTS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_SPEEDOMETERS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_STRESSOMETERS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_ADJUSTABLE_CHAIN_GEARSHIFTS = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_PORTABLE_STORAGE_INTERFACES = new ArrayList<>();
    public static final List<DeferredBlock<Block>> PAINTED_CONTRAPTION_CONTROLS = new ArrayList<>();

    public static final DeferredBlock<Block> ANDESITE_FLUID_PIPE = registerBlock("andesite_fluid_pipe", () -> new AndesiteFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_pipe")), () -> ModBlockEntityTypes.ANDESITE_FLUID_PIPE.get()));
    public static final DeferredBlock<Block> BRASS_FLUID_PIPE = registerBlock("brass_fluid_pipe", () -> new BrassFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_pipe")), () -> ModBlockEntityTypes.BRASS_FLUID_PIPE.get()));
    public static final DeferredBlock<Block> TRAIN_FLUID_PIPE = registerBlock("train_fluid_pipe", () -> new TrainFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_pipe")), () -> ModBlockEntityTypes.TRAIN_FLUID_PIPE.get()));
    public static final DeferredBlock<Block> ANDESITE_SMART_FLUID_PIPE = registerBlock("andesite_smart_fluid_pipe", () -> new AndesiteSmartFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("smart_fluid_pipe")), () -> ModBlockEntityTypes.ANDESITE_SMART_FLUID_PIPE.get()));
    public static final DeferredBlock<Block> BRASS_SMART_FLUID_PIPE = registerBlock("brass_smart_fluid_pipe", () -> new BrassSmartFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("smart_fluid_pipe")), () -> ModBlockEntityTypes.BRASS_SMART_FLUID_PIPE.get()));
    public static final DeferredBlock<Block> TRAIN_SMART_FLUID_PIPE = registerBlock("train_smart_fluid_pipe", () -> new TrainSmartFluidPipeBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("smart_fluid_pipe")), () -> ModBlockEntityTypes.TRAIN_SMART_FLUID_PIPE.get()));
    public static final DeferredBlock<Block> ANDESITE_MECHANICAL_PUMP = registerBlock("andesite_mechanical_pump", () -> new AndesitePumpBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("mechanical_pump")), () -> ModBlockEntityTypes.ANDESITE_MECHANICAL_PUMP.get()));
    public static final DeferredBlock<Block> BRASS_MECHANICAL_PUMP = registerBlock("brass_mechanical_pump", () -> new BrassPumpBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("mechanical_pump")), () -> ModBlockEntityTypes.BRASS_MECHANICAL_PUMP.get()));
    public static final DeferredBlock<Block> TRAIN_MECHANICAL_PUMP = registerBlock("train_mechanical_pump", () -> new TrainPumpBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("mechanical_pump")), () -> ModBlockEntityTypes.TRAIN_MECHANICAL_PUMP.get()));
    public static final DeferredBlock<Block> ANDESITE_FLUID_VALVE = registerBlock("andesite_fluid_valve", () -> new AndesiteFluidValveBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_valve")), () -> ModBlockEntityTypes.ANDESITE_FLUID_VALVE.get()));
    public static final DeferredBlock<Block> BRASS_FLUID_VALVE = registerBlock("brass_fluid_valve", () -> new BrassFluidValveBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_valve")), () -> ModBlockEntityTypes.BRASS_FLUID_VALVE.get()));
    public static final DeferredBlock<Block> TRAIN_FLUID_VALVE = registerBlock("train_fluid_valve", () -> new TrainFluidValveBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_valve")), () -> ModBlockEntityTypes.TRAIN_FLUID_VALVE.get()));
    public static final DeferredBlock<Block> ANDESITE_VALVE_HANDLE = registerBlock("andesite_valve_handle", () -> ValveHandleBlock.copper(BlockBehaviour.Properties.ofFullCopy(createBlock("valve_handle"))));
    public static final DeferredBlock<Block> BRASS_VALVE_HANDLE = registerBlock("brass_valve_handle", () -> ValveHandleBlock.copper(BlockBehaviour.Properties.ofFullCopy(createBlock("valve_handle"))));
    public static final DeferredBlock<Block> TRAIN_VALVE_HANDLE = registerBlock("train_valve_handle", () -> ValveHandleBlock.copper(BlockBehaviour.Properties.ofFullCopy(createBlock("valve_handle"))));
    public static final DeferredBlock<Block> ANDESITE_FLUID_TANK = registerBlock("andesite_fluid_tank", () -> new AndesiteFluidTankBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_tank")), com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get));
    public static final DeferredBlock<Block> BRASS_FLUID_TANK = registerBlock("brass_fluid_tank", () -> new BrassFluidTankBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_tank")), com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get));
    public static final DeferredBlock<Block> TRAIN_FLUID_TANK = registerBlock("train_fluid_tank", () -> new TrainFluidTankBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("fluid_tank")), com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get));
    public static final DeferredBlock<Block> ANDESITE_HORIZONTAL_FLUID_TANK = registerBlock("andesite_horizontal_fluid_tank", () -> new AndesiteHorizontalFluidTankBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("horizontal_fluid_tank")), () -> ModBlockEntityTypes.ANDESITE_HORIZONTAL_FLUID_TANK.get()));
    public static final DeferredBlock<Block> BRASS_HORIZONTAL_FLUID_TANK = registerBlock("brass_horizontal_fluid_tank", () -> new BrassHorizontalFluidTankBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("horizontal_fluid_tank")), () -> ModBlockEntityTypes.BRASS_HORIZONTAL_FLUID_TANK.get()));
    public static final DeferredBlock<Block> COPPER_HORIZONTAL_FLUID_TANK = registerBlock("copper_horizontal_fluid_tank", () -> new CopperHorizontalFluidTankBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("horizontal_fluid_tank")), () -> ModBlockEntityTypes.COPPER_HORIZONTAL_FLUID_TANK.get()));
    public static final DeferredBlock<Block> TRAIN_HORIZONTAL_FLUID_TANK = registerBlock("train_horizontal_fluid_tank", () -> new TrainHorizontalFluidTankBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("horizontal_fluid_tank")), () -> ModBlockEntityTypes.TRAIN_HORIZONTAL_FLUID_TANK.get()));
    public static final DeferredBlock<Block> ANDESITE_SPOUT = registerBlock("andesite_spout", () -> new AndesiteSpoutBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("spout")), () -> ModBlockEntityTypes.ANDESITE_SPOUT.get()));
    public static final DeferredBlock<Block> BRASS_SPOUT = registerBlock("brass_spout", () -> new BrassSpoutBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("spout")), () -> ModBlockEntityTypes.BRASS_SPOUT.get()));
    public static final DeferredBlock<Block> TRAIN_SPOUT = registerBlock("train_spout", () -> new TrainSpoutBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("spout")), () -> ModBlockEntityTypes.TRAIN_SPOUT.get()));
    public static final DeferredBlock<Block> ANDESITE_HOSE_PULLEY = registerBlock("andesite_hose_pulley", () -> new AndesiteHosePulleyBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("hose_pulley")), () -> ModBlockEntityTypes.ANDESITE_HOSE_PULLEY.get()));
    public static final DeferredBlock<Block> BRASS_HOSE_PULLEY = registerBlock("brass_hose_pulley", () -> new BrassHosePulleyBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("hose_pulley")), () -> ModBlockEntityTypes.BRASS_HOSE_PULLEY.get()));
    public static final DeferredBlock<Block> TRAIN_HOSE_PULLEY = registerBlock("train_hose_pulley", () -> new TrainHosePulleyBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("hose_pulley")), () -> ModBlockEntityTypes.TRAIN_HOSE_PULLEY.get()));
    public static final DeferredBlock<Block> ANDESITE_ITEM_DRAIN = registerBlock("andesite_item_drain", () -> new AndesiteItemDrainBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("item_drain"))));
    public static final DeferredBlock<Block> BRASS_ITEM_DRAIN = registerBlock("brass_item_drain", () -> new BrassItemDrainBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("item_drain"))));
    public static final DeferredBlock<Block> TRAIN_ITEM_DRAIN = registerBlock("train_item_drain", () -> new TrainItemDrainBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("item_drain"))));
    public static final DeferredBlock<Block> ANDESITE_PORTABLE_FLUID_INTERFACE = registerBlock("andesite_portable_fluid_interface", () -> new AndesitePortableFluidInterfaceBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("portable_fluid_interface"))));
    public static final DeferredBlock<Block> BRASS_PORTABLE_FLUID_INTERFACE = registerBlock("brass_portable_fluid_interface", () -> new BrassPortableFluidInterfaceBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("portable_fluid_interface"))));
    public static final DeferredBlock<Block> TRAIN_PORTABLE_FLUID_INTERFACE = registerBlock("train_portable_fluid_interface", () -> new TrainPortableFluidInterfaceBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("portable_fluid_interface"))));
    public static final DeferredBlock<Block> ANDESITE_STEAM_ENGINE = registerBlock("andesite_steam_engine", () -> new AndesiteSteamEngineBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("steam_engine")), () -> ModBlockEntityTypes.ANDESITE_STEAM_ENGINE.get()));
    public static final DeferredBlock<Block> BRASS_STEAM_ENGINE = registerBlock("brass_steam_engine", () -> new BrassSteamEngineBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("steam_engine")), () -> ModBlockEntityTypes.BRASS_STEAM_ENGINE.get()));
    public static final DeferredBlock<Block> TRAIN_STEAM_ENGINE = registerBlock("train_steam_engine", () -> new TrainSteamEngineBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("steam_engine")), () -> ModBlockEntityTypes.TRAIN_STEAM_ENGINE.get()));
    public static final DeferredBlock<Block> ANDESITE_STEAM_WHISTLE = registerBlock("andesite_steam_whistle", () -> new AndesiteSteamWhistleBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("steam_whistle")), () -> ModBlockEntityTypes.ANDESITE_STEAM_WHISTLE.get()));
    public static final DeferredBlock<Block> BRASS_STEAM_WHISTLE = registerBlock("brass_steam_whistle", () -> new BrassSteamWhistleBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("steam_whistle")), () -> ModBlockEntityTypes.BRASS_STEAM_WHISTLE.get()));
    public static final DeferredBlock<Block> TRAIN_STEAM_WHISTLE = registerBlock("train_steam_whistle", () -> new TrainSteamWhistleBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("steam_whistle")), () -> ModBlockEntityTypes.TRAIN_STEAM_WHISTLE.get()));
    public static final DeferredBlock<Block> ANDESITE_ENCASED_CHAIN_DRIVE = registerBlock("andesite_encased_chain_drive", () -> new AndesiteChainDriveBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("encased_chain_drive")), () -> ModBlockEntityTypes.ANDESITE_ENCASED_CHAIN_DRIVE.get()));
    public static final DeferredBlock<Block> BRASS_ENCASED_CHAIN_DRIVE = registerBlock("brass_encased_chain_drive", () -> new BrassChainDriveBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("encased_chain_drive")), () -> ModBlockEntityTypes.BRASS_ENCASED_CHAIN_DRIVE.get()));
    public static final DeferredBlock<Block> TRAIN_ENCASED_CHAIN_DRIVE = registerBlock("train_encased_chain_drive", () -> new TrainChainDriveBlock(BlockBehaviour.Properties.ofFullCopy(createBlock("encased_chain_drive")), () -> ModBlockEntityTypes.TRAIN_ENCASED_CHAIN_DRIVE.get()));

    public static final DeferredBlock<Block> WHITE_TEXTILE = registerBlock("white_textile_block", () -> new Block(BlockBehaviour.Properties.of()
                    .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIGHT_GRAY_TEXTILE = registerBlock("light_gray_textile_block", () -> new Block(BlockBehaviour.Properties.of()
                    .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> GRAY_TEXTILE = registerBlock("gray_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BLACK_TEXTILE = registerBlock("black_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BROWN_TEXTILE = registerBlock("brown_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> RED_TEXTILE = registerBlock("red_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> ORANGE_TEXTILE = registerBlock("orange_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> YELLOW_TEXTILE = registerBlock("yellow_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIME_TEXTILE = registerBlock("lime_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> GREEN_TEXTILE = registerBlock("green_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> CYAN_TEXTILE = registerBlock("cyan_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIGHT_BLUE_TEXTILE = registerBlock("light_blue_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BLUE_TEXTILE = registerBlock("blue_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> PURPLE_TEXTILE = registerBlock("purple_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> MAGENTA_TEXTILE = registerBlock("magenta_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> PINK_TEXTILE = registerBlock("pink_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));


    public static final DeferredBlock<Block> WHITE_CHECKERED_TEXTILE = registerBlock("white_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIGHT_GRAY_CHECKERED_TEXTILE = registerBlock("light_gray_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> GRAY_CHECKERED_TEXTILE = registerBlock("gray_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BLACK_CHECKERED_TEXTILE = registerBlock("black_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BROWN_CHECKERED_TEXTILE = registerBlock("brown_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> RED_CHECKERED_TEXTILE = registerBlock("red_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> ORANGE_CHECKERED_TEXTILE = registerBlock("orange_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> YELLOW_CHECKERED_TEXTILE = registerBlock("yellow_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIME_CHECKERED_TEXTILE = registerBlock("lime_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> GREEN_CHECKERED_TEXTILE = registerBlock("green_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> CYAN_CHECKERED_TEXTILE = registerBlock("cyan_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> LIGHT_CHECKERED_BLUE_TEXTILE = registerBlock("light_blue_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> BLUE_CHECKERED_TEXTILE = registerBlock("blue_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> PURPLE_CHECKERED_TEXTILE = registerBlock("purple_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> MAGENTA_CHECKERED_TEXTILE = registerBlock("magenta_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));
    public static final DeferredBlock<Block> PINK_CHECKERED_TEXTILE = registerBlock("pink_checkered_textile_block", () -> new Block(BlockBehaviour.Properties.of()
            .ignitedByLava().sound(SoundType.WOOL).strength(0.8f)));


    public static final DeferredBlock<Block> SUGAR_BEETS = BLOCKS.register("sugar_beets", () -> new SugarBeetCropBlock(BlockBehaviour.Properties
                    .ofFullCopy(Blocks.BEETROOTS)));


    static {
        PAINTED_FLUID_PIPES.add(ANDESITE_FLUID_PIPE);
        PAINTED_FLUID_PIPES.add(BRASS_FLUID_PIPE);
        PAINTED_FLUID_PIPES.add(TRAIN_FLUID_PIPE);
        PAINTED_SMART_FLUID_PIPES.add(ANDESITE_SMART_FLUID_PIPE);
        PAINTED_SMART_FLUID_PIPES.add(BRASS_SMART_FLUID_PIPE);
        PAINTED_SMART_FLUID_PIPES.add(TRAIN_SMART_FLUID_PIPE);
        PAINTED_MECHANICAL_PUMPS.add(ANDESITE_MECHANICAL_PUMP);
        PAINTED_MECHANICAL_PUMPS.add(BRASS_MECHANICAL_PUMP);
        PAINTED_MECHANICAL_PUMPS.add(TRAIN_MECHANICAL_PUMP);
        PAINTED_FLUID_VALVES.add(ANDESITE_FLUID_VALVE);
        PAINTED_FLUID_VALVES.add(BRASS_FLUID_VALVE);
        PAINTED_FLUID_VALVES.add(TRAIN_FLUID_VALVE);
        PAINTED_VALVE_HANDLES.add(ANDESITE_VALVE_HANDLE);
        PAINTED_VALVE_HANDLES.add(BRASS_VALVE_HANDLE);
        PAINTED_VALVE_HANDLES.add(TRAIN_VALVE_HANDLE);
        PAINTED_FLUID_TANKS.add(ANDESITE_FLUID_TANK);
        PAINTED_FLUID_TANKS.add(BRASS_FLUID_TANK);
        PAINTED_FLUID_TANKS.add(TRAIN_FLUID_TANK);
        PAINTED_HORIZONTAL_FLUID_TANKS.add(ANDESITE_HORIZONTAL_FLUID_TANK);
        PAINTED_HORIZONTAL_FLUID_TANKS.add(BRASS_HORIZONTAL_FLUID_TANK);
        PAINTED_HORIZONTAL_FLUID_TANKS.add(COPPER_HORIZONTAL_FLUID_TANK);
        PAINTED_HORIZONTAL_FLUID_TANKS.add(TRAIN_HORIZONTAL_FLUID_TANK);
        PAINTED_SPOUTS.add(ANDESITE_SPOUT);
        PAINTED_SPOUTS.add(BRASS_SPOUT);
        PAINTED_SPOUTS.add(TRAIN_SPOUT);
        PAINTED_HOSE_PULLEYS.add(ANDESITE_HOSE_PULLEY);
        PAINTED_HOSE_PULLEYS.add(BRASS_HOSE_PULLEY);
        PAINTED_HOSE_PULLEYS.add(TRAIN_HOSE_PULLEY);
        PAINTED_ITEM_DRAINS.add(ANDESITE_ITEM_DRAIN);
        PAINTED_ITEM_DRAINS.add(BRASS_ITEM_DRAIN);
        PAINTED_ITEM_DRAINS.add(TRAIN_ITEM_DRAIN);
        PAINTED_PORTABLE_FLUID_INTERFACES.add(ANDESITE_PORTABLE_FLUID_INTERFACE);
        PAINTED_PORTABLE_FLUID_INTERFACES.add(BRASS_PORTABLE_FLUID_INTERFACE);
        PAINTED_PORTABLE_FLUID_INTERFACES.add(TRAIN_PORTABLE_FLUID_INTERFACE);
        PAINTED_STEAM_ENGINES.add(ANDESITE_STEAM_ENGINE);
        PAINTED_STEAM_ENGINES.add(BRASS_STEAM_ENGINE);
        PAINTED_STEAM_ENGINES.add(TRAIN_STEAM_ENGINE);
        PAINTED_STEAM_WHISTLES.add(ANDESITE_STEAM_WHISTLE);
        PAINTED_STEAM_WHISTLES.add(BRASS_STEAM_WHISTLE);
        PAINTED_STEAM_WHISTLES.add(TRAIN_STEAM_WHISTLE);
        PAINTED_ENCASED_CHAIN_DRIVES.add(ANDESITE_ENCASED_CHAIN_DRIVE);
        PAINTED_ENCASED_CHAIN_DRIVES.add(BRASS_ENCASED_CHAIN_DRIVE);
        PAINTED_ENCASED_CHAIN_DRIVES.add(TRAIN_ENCASED_CHAIN_DRIVE);
    }


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> {
            Block b = block.get();
            if (b instanceof com.simibubi.create.content.fluids.tank.FluidTankBlock) {
                return new FluidTankItem(b, new Item.Properties());
            }
            return new BlockItem(b, new Item.Properties());
        });
    }

    private static Block createBlock(String target) {
        if ("valve_handle".equals(target)) {
            return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "copper_valve_handle"));
        }
        if ("horizontal_fluid_tank".equals(target)) {
            return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", "fluid_tank"));
        }
        Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("create", target));
        return block == Blocks.AIR ? Blocks.IRON_BLOCK : block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
