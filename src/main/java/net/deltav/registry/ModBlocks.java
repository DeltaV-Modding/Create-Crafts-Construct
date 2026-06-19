package net.deltav.registry;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import net.deltav.block.SugarBeetCropBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import com.simibubi.create.content.fluids.tank.FluidTankItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static net.deltav.CraftsConstruct.REGISTRATE;

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
    public static final List<BlockEntry<? extends Block>> TEMP_PAINT_BLOCKS = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_FLUID_PIPES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_SMART_FLUID_PIPES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_MECHANICAL_PUMPS = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_FLUID_VALVES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_VALVE_HANDLES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_FLUID_TANKS = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_HORIZONTAL_FLUID_TANKS = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_SPOUTS = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_HOSE_PULLEYS = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_STEAM_ENGINES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_STEAM_WHISTLES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_PORTABLE_FLUID_INTERFACES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_ITEM_DRAINS = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_PORTABLE_STORAGE_INTERFACES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_CONTRAPTION_CONTROLS = new ArrayList<>();

    public static final BlockEntry<AndesiteFluidPipeBlock> ANDESITE_FLUID_PIPE = REGISTRATE.block("andesite_fluid_pipe", p -> new AndesiteFluidPipeBlock(p, () -> ModBlockEntityTypes.ANDESITE_FLUID_PIPE.get()))
            .initialProperties(() -> createBlock("fluid_pipe"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassFluidPipeBlock> BRASS_FLUID_PIPE = REGISTRATE.block("brass_fluid_pipe", p -> new BrassFluidPipeBlock(p, () -> ModBlockEntityTypes.BRASS_FLUID_PIPE.get()))
            .initialProperties(() -> createBlock("fluid_pipe"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainFluidPipeBlock> TRAIN_FLUID_PIPE = REGISTRATE.block("train_fluid_pipe", p -> new TrainFluidPipeBlock(p, () -> ModBlockEntityTypes.TRAIN_FLUID_PIPE.get()))
            .initialProperties(() -> createBlock("fluid_pipe"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesiteSmartFluidPipeBlock> ANDESITE_SMART_FLUID_PIPE = REGISTRATE.block("andesite_smart_fluid_pipe", p -> new AndesiteSmartFluidPipeBlock(p, () -> ModBlockEntityTypes.ANDESITE_SMART_FLUID_PIPE.get()))
            .initialProperties(() -> createBlock("smart_fluid_pipe"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassSmartFluidPipeBlock> BRASS_SMART_FLUID_PIPE = REGISTRATE.block("brass_smart_fluid_pipe", p -> new BrassSmartFluidPipeBlock(p, () -> ModBlockEntityTypes.BRASS_SMART_FLUID_PIPE.get()))
            .initialProperties(() -> createBlock("smart_fluid_pipe"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainSmartFluidPipeBlock> TRAIN_SMART_FLUID_PIPE = REGISTRATE.block("train_smart_fluid_pipe", p -> new TrainSmartFluidPipeBlock(p, () -> ModBlockEntityTypes.TRAIN_SMART_FLUID_PIPE.get()))
            .initialProperties(() -> createBlock("smart_fluid_pipe"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesitePumpBlock> ANDESITE_MECHANICAL_PUMP = REGISTRATE.block("andesite_mechanical_pump", p -> new AndesitePumpBlock(p, () -> ModBlockEntityTypes.ANDESITE_MECHANICAL_PUMP.get()))
            .initialProperties(() -> createBlock("mechanical_pump"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassPumpBlock> BRASS_MECHANICAL_PUMP = REGISTRATE.block("brass_mechanical_pump", p -> new BrassPumpBlock(p, () -> ModBlockEntityTypes.BRASS_MECHANICAL_PUMP.get()))
            .initialProperties(() -> createBlock("mechanical_pump"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainPumpBlock> TRAIN_MECHANICAL_PUMP = REGISTRATE.block("train_mechanical_pump", p -> new TrainPumpBlock(p, () -> ModBlockEntityTypes.TRAIN_MECHANICAL_PUMP.get()))
            .initialProperties(() -> createBlock("mechanical_pump"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesiteFluidValveBlock> ANDESITE_FLUID_VALVE = REGISTRATE.block("andesite_fluid_valve", p -> new AndesiteFluidValveBlock(p, () -> ModBlockEntityTypes.ANDESITE_FLUID_VALVE.get()))
            .initialProperties(() -> createBlock("fluid_valve"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassFluidValveBlock> BRASS_FLUID_VALVE = REGISTRATE.block("brass_fluid_valve", p -> new BrassFluidValveBlock(p, () -> ModBlockEntityTypes.BRASS_FLUID_VALVE.get()))
            .initialProperties(() -> createBlock("fluid_valve"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainFluidValveBlock> TRAIN_FLUID_VALVE = REGISTRATE.block("train_fluid_valve", p -> new TrainFluidValveBlock(p, () -> ModBlockEntityTypes.TRAIN_FLUID_VALVE.get()))
            .initialProperties(() -> createBlock("fluid_valve"))
            .item()
            .build()
            .register();
    public static final BlockEntry<ValveHandleBlock> ANDESITE_VALVE_HANDLE = REGISTRATE.block("andesite_valve_handle", p -> ValveHandleBlock.copper(p))
            .initialProperties(() -> createBlock("valve_handle"))
            .item()
            .build()
            .register();
    public static final BlockEntry<ValveHandleBlock> BRASS_VALVE_HANDLE = REGISTRATE.block("brass_valve_handle", p -> ValveHandleBlock.copper(p))
            .initialProperties(() -> createBlock("valve_handle"))
            .item()
            .build()
            .register();
    public static final BlockEntry<ValveHandleBlock> TRAIN_VALVE_HANDLE = REGISTRATE.block("train_valve_handle", p -> ValveHandleBlock.copper(p))
            .initialProperties(() -> createBlock("valve_handle"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesiteFluidTankBlock> ANDESITE_FLUID_TANK = REGISTRATE.block("andesite_fluid_tank", p -> new AndesiteFluidTankBlock(p, com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get))
            .initialProperties(() -> createBlock("fluid_tank"))
            .item(FluidTankItem::new)
            .build()
            .register();
    public static final BlockEntry<BrassFluidTankBlock> BRASS_FLUID_TANK = REGISTRATE.block("brass_fluid_tank", p -> new BrassFluidTankBlock(p, com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get))
            .initialProperties(() -> createBlock("fluid_tank"))
            .item(FluidTankItem::new)
            .build()
            .register();
    public static final BlockEntry<TrainFluidTankBlock> TRAIN_FLUID_TANK = REGISTRATE.block("train_fluid_tank", p -> new TrainFluidTankBlock(p, com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get))
            .initialProperties(() -> createBlock("fluid_tank"))
            .item(FluidTankItem::new)
            .build()
            .register();
    public static final BlockEntry<AndesiteHorizontalFluidTankBlock> ANDESITE_HORIZONTAL_FLUID_TANK = REGISTRATE.block("andesite_horizontal_fluid_tank", p -> new AndesiteHorizontalFluidTankBlock(p, () -> ModBlockEntityTypes.ANDESITE_HORIZONTAL_FLUID_TANK.get()))
            .initialProperties(() -> createBlock("horizontal_fluid_tank"))
            .item(FluidTankItem::new)
            .build()
            .register();
    public static final BlockEntry<BrassHorizontalFluidTankBlock> BRASS_HORIZONTAL_FLUID_TANK = REGISTRATE.block("brass_horizontal_fluid_tank", p -> new BrassHorizontalFluidTankBlock(p, () -> ModBlockEntityTypes.BRASS_HORIZONTAL_FLUID_TANK.get()))
            .initialProperties(() -> createBlock("horizontal_fluid_tank"))
            .item(FluidTankItem::new)
            .build()
            .register();
    public static final BlockEntry<CopperHorizontalFluidTankBlock> COPPER_HORIZONTAL_FLUID_TANK = REGISTRATE.block("copper_horizontal_fluid_tank", p -> new CopperHorizontalFluidTankBlock(p, () -> ModBlockEntityTypes.COPPER_HORIZONTAL_FLUID_TANK.get()))
            .initialProperties(() -> createBlock("horizontal_fluid_tank"))
            .item(FluidTankItem::new)
            .build()
            .register();
    public static final BlockEntry<TrainHorizontalFluidTankBlock> TRAIN_HORIZONTAL_FLUID_TANK = REGISTRATE.block("train_horizontal_fluid_tank", p -> new TrainHorizontalFluidTankBlock(p, () -> ModBlockEntityTypes.TRAIN_HORIZONTAL_FLUID_TANK.get()))
            .initialProperties(() -> createBlock("horizontal_fluid_tank"))
            .item(FluidTankItem::new)
            .build()
            .register();
    public static final BlockEntry<AndesiteSpoutBlock> ANDESITE_SPOUT = REGISTRATE.block("andesite_spout", p -> new AndesiteSpoutBlock(p, () -> ModBlockEntityTypes.ANDESITE_SPOUT.get()))
            .initialProperties(() -> createBlock("spout"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassSpoutBlock> BRASS_SPOUT = REGISTRATE.block("brass_spout", p -> new BrassSpoutBlock(p, () -> ModBlockEntityTypes.BRASS_SPOUT.get()))
            .initialProperties(() -> createBlock("spout"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainSpoutBlock> TRAIN_SPOUT = REGISTRATE.block("train_spout", p -> new TrainSpoutBlock(p, () -> ModBlockEntityTypes.TRAIN_SPOUT.get()))
            .initialProperties(() -> createBlock("spout"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesiteHosePulleyBlock> ANDESITE_HOSE_PULLEY = REGISTRATE.block("andesite_hose_pulley", p -> new AndesiteHosePulleyBlock(p, () -> ModBlockEntityTypes.ANDESITE_HOSE_PULLEY.get()))
            .initialProperties(() -> createBlock("hose_pulley"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassHosePulleyBlock> BRASS_HOSE_PULLEY = REGISTRATE.block("brass_hose_pulley", p -> new BrassHosePulleyBlock(p, () -> ModBlockEntityTypes.BRASS_HOSE_PULLEY.get()))
            .initialProperties(() -> createBlock("hose_pulley"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainHosePulleyBlock> TRAIN_HOSE_PULLEY = REGISTRATE.block("train_hose_pulley", p -> new TrainHosePulleyBlock(p, () -> ModBlockEntityTypes.TRAIN_HOSE_PULLEY.get()))
            .initialProperties(() -> createBlock("hose_pulley"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesiteItemDrainBlock> ANDESITE_ITEM_DRAIN = REGISTRATE.block("andesite_item_drain", p -> new AndesiteItemDrainBlock(p))
            .initialProperties(() -> createBlock("item_drain"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassItemDrainBlock> BRASS_ITEM_DRAIN = REGISTRATE.block("brass_item_drain", p -> new BrassItemDrainBlock(p))
            .initialProperties(() -> createBlock("item_drain"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainItemDrainBlock> TRAIN_ITEM_DRAIN = REGISTRATE.block("train_item_drain", p -> new TrainItemDrainBlock(p))
            .initialProperties(() -> createBlock("item_drain"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesitePortableFluidInterfaceBlock> ANDESITE_PORTABLE_FLUID_INTERFACE = REGISTRATE.block("andesite_portable_fluid_interface", p -> new AndesitePortableFluidInterfaceBlock(p))
            .initialProperties(() -> createBlock("portable_fluid_interface"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassPortableFluidInterfaceBlock> BRASS_PORTABLE_FLUID_INTERFACE = REGISTRATE.block("brass_portable_fluid_interface", p -> new BrassPortableFluidInterfaceBlock(p))
            .initialProperties(() -> createBlock("portable_fluid_interface"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainPortableFluidInterfaceBlock> TRAIN_PORTABLE_FLUID_INTERFACE = REGISTRATE.block("train_portable_fluid_interface", p -> new TrainPortableFluidInterfaceBlock(p))
            .initialProperties(() -> createBlock("portable_fluid_interface"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesiteSteamEngineBlock> ANDESITE_STEAM_ENGINE = REGISTRATE.block("andesite_steam_engine", p -> new AndesiteSteamEngineBlock(p, () -> ModBlockEntityTypes.ANDESITE_STEAM_ENGINE.get()))
            .initialProperties(() -> createBlock("steam_engine"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassSteamEngineBlock> BRASS_STEAM_ENGINE = REGISTRATE.block("brass_steam_engine", p -> new BrassSteamEngineBlock(p, () -> ModBlockEntityTypes.BRASS_STEAM_ENGINE.get()))
            .initialProperties(() -> createBlock("steam_engine"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainSteamEngineBlock> TRAIN_STEAM_ENGINE = REGISTRATE.block("train_steam_engine", p -> new TrainSteamEngineBlock(p, () -> ModBlockEntityTypes.TRAIN_STEAM_ENGINE.get()))
            .initialProperties(() -> createBlock("steam_engine"))
            .item()
            .build()
            .register();
    public static final BlockEntry<AndesiteSteamWhistleBlock> ANDESITE_STEAM_WHISTLE = REGISTRATE.block("andesite_steam_whistle", p -> new AndesiteSteamWhistleBlock(p, () -> ModBlockEntityTypes.ANDESITE_STEAM_WHISTLE.get()))
            .initialProperties(() -> createBlock("steam_whistle"))
            .item()
            .build()
            .register();
    public static final BlockEntry<BrassSteamWhistleBlock> BRASS_STEAM_WHISTLE = REGISTRATE.block("brass_steam_whistle", p -> new BrassSteamWhistleBlock(p, () -> ModBlockEntityTypes.BRASS_STEAM_WHISTLE.get()))
            .initialProperties(() -> createBlock("steam_whistle"))
            .item()
            .build()
            .register();
    public static final BlockEntry<TrainSteamWhistleBlock> TRAIN_STEAM_WHISTLE = REGISTRATE.block("train_steam_whistle", p -> new TrainSteamWhistleBlock(p, () -> ModBlockEntityTypes.TRAIN_STEAM_WHISTLE.get()))
            .initialProperties(() -> createBlock("steam_whistle"))
            .item()
            .build()
            .register();

    public static final BlockEntry<SugarBeetCropBlock> SUGAR_BEETS = REGISTRATE.block("sugar_beets", SugarBeetCropBlock::new)
            .initialProperties(() -> Blocks.BEETROOTS)
            .register();

    public static void register() {
        // This method is called from CraftsConstruct to ensure the class is loaded
    }

    public static final BlockEntry<Block> WHITE_TEXTILE = textile("white_textile_block");
    public static final BlockEntry<Block> WHITE_CHECKERED_TEXTILE = textile("white_checkered_textile_block");
    public static final BlockEntry<Block> LIGHT_GRAY_TEXTILE = textile("light_gray_textile_block");
    public static final BlockEntry<Block> LIGHT_GRAY_CHECKERED_TEXTILE = textile("light_gray_checkered_textile_block");
    public static final BlockEntry<Block> GRAY_TEXTILE = textile("gray_textile_block");
    public static final BlockEntry<Block> GRAY_CHECKERED_TEXTILE = textile("gray_checkered_textile_block");
    public static final BlockEntry<Block> BLACK_TEXTILE = textile("black_textile_block");
    public static final BlockEntry<Block> BLACK_CHECKERED_TEXTILE = textile("black_checkered_textile_block");
    public static final BlockEntry<Block> BROWN_TEXTILE = textile("brown_textile_block");
    public static final BlockEntry<Block> BROWN_CHECKERED_TEXTILE = textile("brown_checkered_textile_block");
    public static final BlockEntry<Block> RED_TEXTILE = textile("red_textile_block");
    public static final BlockEntry<Block> RED_CHECKERED_TEXTILE = textile("red_checkered_textile_block");
    public static final BlockEntry<Block> ORANGE_TEXTILE = textile("orange_textile_block");
    public static final BlockEntry<Block> ORANGE_CHECKERED_TEXTILE = textile("orange_checkered_textile_block");
    public static final BlockEntry<Block> YELLOW_TEXTILE = textile("yellow_textile_block");
    public static final BlockEntry<Block> YELLOW_CHECKERED_TEXTILE = textile("yellow_checkered_textile_block");
    public static final BlockEntry<Block> LIME_TEXTILE = textile("lime_textile_block");
    public static final BlockEntry<Block> LIME_CHECKERED_TEXTILE = textile("lime_checkered_textile_block");
    public static final BlockEntry<Block> GREEN_TEXTILE = textile("green_textile_block");
    public static final BlockEntry<Block> GREEN_CHECKERED_TEXTILE = textile("green_checkered_textile_block");
    public static final BlockEntry<Block> CYAN_TEXTILE = textile("cyan_textile_block");
    public static final BlockEntry<Block> CYAN_CHECKERED_TEXTILE = textile("cyan_checkered_textile_block");
    public static final BlockEntry<Block> LIGHT_BLUE_TEXTILE = textile("light_blue_textile_block");
    public static final BlockEntry<Block> LIGHT_BLUE_CHECKERED_TEXTILE = textile("light_blue_checkered_textile_block");
    public static final BlockEntry<Block> BLUE_TEXTILE = textile("blue_textile_block");
    public static final BlockEntry<Block> BLUE_CHECKERED_TEXTILE = textile("blue_checkered_textile_block");
    public static final BlockEntry<Block> PURPLE_TEXTILE = textile("purple_textile_block");
    public static final BlockEntry<Block> PURPLE_CHECKERED_TEXTILE = textile("purple_checkered_textile_block");
    public static final BlockEntry<Block> MAGENTA_TEXTILE = textile("magenta_textile_block");
    public static final BlockEntry<Block> MAGENTA_CHECKERED_TEXTILE = textile("magenta_checkered_textile_block");
    public static final BlockEntry<Block> PINK_TEXTILE = textile("pink_textile_block");
    public static final BlockEntry<Block> PINK_CHECKERED_TEXTILE = textile("pink_checkered_textile_block");

    static {
        addAll(PAINTED_FLUID_PIPES, ANDESITE_FLUID_PIPE, BRASS_FLUID_PIPE, TRAIN_FLUID_PIPE);
        addAll(PAINTED_SMART_FLUID_PIPES, ANDESITE_SMART_FLUID_PIPE, BRASS_SMART_FLUID_PIPE, TRAIN_SMART_FLUID_PIPE);
        addAll(PAINTED_MECHANICAL_PUMPS, ANDESITE_MECHANICAL_PUMP, BRASS_MECHANICAL_PUMP, TRAIN_MECHANICAL_PUMP);
        addAll(PAINTED_FLUID_VALVES, ANDESITE_FLUID_VALVE, BRASS_FLUID_VALVE, TRAIN_FLUID_VALVE);
        addAll(PAINTED_VALVE_HANDLES, ANDESITE_VALVE_HANDLE, BRASS_VALVE_HANDLE, TRAIN_VALVE_HANDLE);
        addAll(PAINTED_FLUID_TANKS, ANDESITE_FLUID_TANK, BRASS_FLUID_TANK, TRAIN_FLUID_TANK);
        addAll(PAINTED_HORIZONTAL_FLUID_TANKS, ANDESITE_HORIZONTAL_FLUID_TANK, BRASS_HORIZONTAL_FLUID_TANK, COPPER_HORIZONTAL_FLUID_TANK, TRAIN_HORIZONTAL_FLUID_TANK);
        addAll(PAINTED_SPOUTS, ANDESITE_SPOUT, BRASS_SPOUT, TRAIN_SPOUT);
        addAll(PAINTED_HOSE_PULLEYS, ANDESITE_HOSE_PULLEY, BRASS_HOSE_PULLEY, TRAIN_HOSE_PULLEY);
        addAll(PAINTED_ITEM_DRAINS, ANDESITE_ITEM_DRAIN, BRASS_ITEM_DRAIN, TRAIN_ITEM_DRAIN);
        addAll(PAINTED_PORTABLE_FLUID_INTERFACES, ANDESITE_PORTABLE_FLUID_INTERFACE, BRASS_PORTABLE_FLUID_INTERFACE, TRAIN_PORTABLE_FLUID_INTERFACE);
        addAll(PAINTED_STEAM_ENGINES, ANDESITE_STEAM_ENGINE, BRASS_STEAM_ENGINE, TRAIN_STEAM_ENGINE);
        addAll(PAINTED_STEAM_WHISTLES, ANDESITE_STEAM_WHISTLE, BRASS_STEAM_WHISTLE, TRAIN_STEAM_WHISTLE);
    }

    private static BlockEntry<Block> textile(String name) {
        return REGISTRATE.block(name, Block::new)
                .initialProperties(() -> Blocks.WHITE_WOOL)
                .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
                .item()
                .build()
                .register();
    }

    @SafeVarargs
    private static void addAll(List<BlockEntry<? extends Block>> target, BlockEntry<? extends Block>... blocks) {
        target.addAll(List.of(blocks));
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
}
