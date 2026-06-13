package net.deltav.registry;

import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import net.deltav.block.SugarBeetCropBlock;
import net.deltav.block.create.drain.PaintedItemDrainBlock;
import com.simibubi.create.content.fluids.tank.FluidTankItem;
import net.deltav.block.create.fluid.PaintedFluidTankBlock;
import net.deltav.block.create.fluid.HorizontalFluidTankBlock;
import net.deltav.block.create.fluid.PaintedFluidValveBlock;
import net.deltav.block.create.fluid.PaintedHosePulleyBlock;
import net.deltav.block.create.fluid.PaintedPumpBlock;
import net.deltav.block.create.fluid.PaintedSpoutBlock;
import net.deltav.block.create.kinetic.PaintedChainDriveBlock;
import net.deltav.block.create.kinetic.PaintedDeployerBlock;
import net.deltav.block.create.kinetic.PaintedEncasedFanBlock;
import net.deltav.block.create.kinetic.PaintedGearboxBlock;
import net.deltav.block.create.kinetic.PaintedMechanicalCrafterBlock;
import net.deltav.block.create.kinetic.PaintedMechanicalDrillBlock;
import net.deltav.block.create.kinetic.PaintedMechanicalMixerBlock;
import net.deltav.block.create.kinetic.PaintedMechanicalPressBlock;
import net.deltav.block.create.kinetic.PaintedMechanicalSawBlock;
import net.deltav.block.create.kinetic.PaintedMillstoneBlock;
import net.deltav.block.create.kinetic.PaintedSteamEngineBlock;
import net.deltav.block.create.kinetic.PaintedSteamWhistleBlock;
import net.deltav.block.create.pipe.PaintedFluidPipeBlock;
import net.deltav.block.create.pipe.PaintedSmartFluidPipeBlock;
import net.deltav.block.create.portableInterface.PaintedPortableFluidInterfaceBlock;
import net.deltav.block.create.portableInterface.PaintedPortableStorageInterfaceBlock;
import net.deltav.block.create.kinetic.PaintedDepotBlock;
import net.deltav.block.create.kinetic.PaintedWeightedEjectorBlock;
import net.deltav.block.create.kinetic.PaintedClutchBlock;
import net.deltav.block.create.kinetic.PaintedGearshiftBlock;
import net.deltav.block.create.kinetic.PaintedGaugeBlock;
import net.deltav.block.create.kinetic.PaintedAdjustableChainGearshiftBlock;
import net.deltav.block.create.kinetic.PaintedContraptionControlsBlock;
import net.deltav.craftsconstruct;
import net.minecraft.client.renderer.RenderType;
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static net.deltav.craftsconstruct.REGISTRATE;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(craftsconstruct.MOD_ID);
    private static final String[] TEMP_PAINT_MATERIALS = {"andesite", "brass", "copper", "train"};
    private static final String[] TEMP_PAINT_TARGETS = {
            "fluid_pipe",
            "mechanical_pump",
            "smart_fluid_pipe",
            "fluid_valve",
            "valve_handle",
            "fluid_tank",
            "horizontal_fluid_tank",
            "hose_pulley",
            "spout",
            "portable_fluid_interface",
            "steam_engine",
            "steam_whistle",
            "gearbox",
            "encased_chain_drive",
            "encased_fan",
            "millstone",
            "mechanical_saw",
            "mechanical_press",
            "mechanical_mixer",
            "deployer",
            "mechanical_drill",
            "mechanical_crafter",
            "item_drain",
            "depot",
            "weighted_ejector",
            "clutch",
            "gearshift",
            "speedometer",
            "stressometer",
            "adjustable_chain_gearshift",
            "portable_storage_interface",
            "contraption_controls"
    };
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


    //Create Stuff
    public static final BlockEntry<PaintedItemDrainBlock> BRASS_ITEM_DRAIN = REGISTRATE.block("brass_item_drain", PaintedItemDrainBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate((c, p) -> p.simpleBlock(c.get(), AssetLookup.standardModel(c, p)))
            .simpleItem()
            .register();

    static {
        for (String material : TEMP_PAINT_MATERIALS) {
            for (String target : TEMP_PAINT_TARGETS) {
                if (shouldRegisterPaintVariant(material, target)) {
                    registerTempPaintBlock(material + "_" + target);
                }
            }
        }
    }



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> {
            Block b = block.get();
            if (b instanceof PaintedFluidTankBlock || b instanceof HorizontalFluidTankBlock) {
                return new FluidTankItem(b, new Item.Properties());
            }
            return new BlockItem(b, new Item.Properties());
        });
    }

    private static void registerTempPaintBlock(String name) {
        DeferredBlock<Block> block = registerBlock(name, () -> createTempPaintBlock(name));
        TEMP_PAINT_BLOCKS.add(block);
        switch (stripMaterialPrefix(name)) {
            case "fluid_pipe" -> PAINTED_FLUID_PIPES.add(block);
            case "smart_fluid_pipe" -> PAINTED_SMART_FLUID_PIPES.add(block);
            case "mechanical_pump" -> PAINTED_MECHANICAL_PUMPS.add(block);
            case "fluid_valve" -> PAINTED_FLUID_VALVES.add(block);
            case "valve_handle" -> PAINTED_VALVE_HANDLES.add(block);
            case "fluid_tank" -> PAINTED_FLUID_TANKS.add(block);
            case "horizontal_fluid_tank" -> PAINTED_HORIZONTAL_FLUID_TANKS.add(block);
            case "spout" -> PAINTED_SPOUTS.add(block);
            case "hose_pulley" -> PAINTED_HOSE_PULLEYS.add(block);
            case "gearbox" -> PAINTED_GEARBOXES.add(block);
            case "encased_chain_drive" -> PAINTED_ENCASED_CHAIN_DRIVES.add(block);
            case "encased_fan" -> PAINTED_ENCASED_FANS.add(block);
            case "millstone" -> PAINTED_MILLSTONES.add(block);
            case "mechanical_saw" -> PAINTED_MECHANICAL_SAWS.add(block);
            case "mechanical_press" -> PAINTED_MECHANICAL_PRESSES.add(block);
            case "mechanical_mixer" -> PAINTED_MECHANICAL_MIXERS.add(block);
            case "deployer" -> PAINTED_DEPLOYERS.add(block);
            case "mechanical_drill" -> PAINTED_MECHANICAL_DRILLS.add(block);
            case "mechanical_crafter" -> PAINTED_MECHANICAL_CRAFTERS.add(block);
            case "steam_engine" -> PAINTED_STEAM_ENGINES.add(block);
            case "steam_whistle" -> PAINTED_STEAM_WHISTLES.add(block);
            case "portable_fluid_interface" -> PAINTED_PORTABLE_FLUID_INTERFACES.add(block);
            case "item_drain" -> PAINTED_ITEM_DRAINS.add(block);
            case "depot" -> PAINTED_DEPOTS.add(block);
            case "weighted_ejector" -> PAINTED_WEIGHTED_EJECTORS.add(block);
            case "clutch" -> PAINTED_CLUTCHES.add(block);
            case "gearshift" -> PAINTED_GEARSHIFTS.add(block);
            case "speedometer" -> PAINTED_SPEEDOMETERS.add(block);
            case "stressometer" -> PAINTED_STRESSOMETERS.add(block);
            case "adjustable_chain_gearshift" -> PAINTED_ADJUSTABLE_CHAIN_GEARSHIFTS.add(block);
            case "portable_storage_interface" -> PAINTED_PORTABLE_STORAGE_INTERFACES.add(block);
            case "contraption_controls" -> PAINTED_CONTRAPTION_CONTROLS.add(block);
            default -> {
            }
        }
    }

    private static Block createTempPaintBlock(String name) {
        String target = stripMaterialPrefix(name);
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(createBlock(target));
        return switch (target) {
            case "fluid_pipe" -> new PaintedFluidPipeBlock(properties, () -> ModBlockEntityTypes.PAINTED_FLUID_PIPE.get());
            case "smart_fluid_pipe" -> new PaintedSmartFluidPipeBlock(properties, () -> ModBlockEntityTypes.PAINTED_SMART_FLUID_PIPE.get());
            case "mechanical_pump" -> new PaintedPumpBlock(properties, () -> ModBlockEntityTypes.PAINTED_MECHANICAL_PUMP.get());
            case "fluid_valve" -> new PaintedFluidValveBlock(properties, () -> ModBlockEntityTypes.PAINTED_FLUID_VALVE.get());
            case "valve_handle" -> ValveHandleBlock.copper(properties);
            case "fluid_tank" -> new PaintedFluidTankBlock(properties, com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get);
            case "horizontal_fluid_tank" -> new HorizontalFluidTankBlock(properties, () -> ModBlockEntityTypes.HORIZONTAL_FLUID_TANK.get());
            case "spout" -> new PaintedSpoutBlock(properties, () -> ModBlockEntityTypes.PAINTED_SPOUT.get());
            case "hose_pulley" -> new PaintedHosePulleyBlock(properties, () -> ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get());
            case "gearbox" -> new PaintedGearboxBlock(properties, () -> ModBlockEntityTypes.PAINTED_GEARBOX.get());
            case "encased_chain_drive" -> new PaintedChainDriveBlock(properties, () -> ModBlockEntityTypes.PAINTED_ENCASED_CHAIN_DRIVE.get());
            case "encased_fan" -> new PaintedEncasedFanBlock(properties, () -> ModBlockEntityTypes.PAINTED_ENCASED_FAN.get());
            case "millstone" -> new PaintedMillstoneBlock(properties, () -> ModBlockEntityTypes.PAINTED_MILLSTONE.get());
            case "mechanical_saw" -> new PaintedMechanicalSawBlock(properties, () -> ModBlockEntityTypes.PAINTED_MECHANICAL_SAW.get());
            case "mechanical_press" -> new PaintedMechanicalPressBlock(properties, () -> ModBlockEntityTypes.PAINTED_MECHANICAL_PRESS.get());
            case "mechanical_mixer" -> new PaintedMechanicalMixerBlock(properties, () -> ModBlockEntityTypes.PAINTED_MECHANICAL_MIXER.get());
            case "deployer" -> new PaintedDeployerBlock(properties, () -> ModBlockEntityTypes.PAINTED_DEPLOYER.get());
            case "mechanical_drill" -> new PaintedMechanicalDrillBlock(properties, () -> ModBlockEntityTypes.PAINTED_MECHANICAL_DRILL.get());
            case "mechanical_crafter" -> new PaintedMechanicalCrafterBlock(properties, () -> ModBlockEntityTypes.PAINTED_MECHANICAL_CRAFTER.get());
            case "steam_engine" -> new PaintedSteamEngineBlock(properties, () -> ModBlockEntityTypes.PAINTED_STEAM_ENGINE.get());
            case "steam_whistle" -> new PaintedSteamWhistleBlock(properties, () -> ModBlockEntityTypes.PAINTED_STEAM_WHISTLE.get());
            case "portable_fluid_interface" -> new PaintedPortableFluidInterfaceBlock(properties);
            case "item_drain" -> new PaintedItemDrainBlock(properties);
            case "depot" -> new PaintedDepotBlock(properties, () -> ModBlockEntityTypes.PAINTED_DEPOT.get());
            case "weighted_ejector" -> new PaintedWeightedEjectorBlock(properties, () -> ModBlockEntityTypes.PAINTED_WEIGHTED_EJECTOR.get());
            case "clutch" -> new PaintedClutchBlock(properties, () -> ModBlockEntityTypes.PAINTED_CLUTCH.get());
            case "gearshift" -> new PaintedGearshiftBlock(properties, () -> ModBlockEntityTypes.PAINTED_GEARSHIFT.get());
            case "speedometer" -> new PaintedGaugeBlock(true, properties, () -> ModBlockEntityTypes.PAINTED_SPEEDOMETER.get());
            case "stressometer" -> new PaintedGaugeBlock(false, properties, () -> ModBlockEntityTypes.PAINTED_STRESSOMETER.get());
            case "adjustable_chain_gearshift" -> new PaintedAdjustableChainGearshiftBlock(properties, () -> ModBlockEntityTypes.PAINTED_ADJUSTABLE_CHAIN_GEARSHIFT.get());
            case "portable_storage_interface" -> new PaintedPortableStorageInterfaceBlock(properties, () -> ModBlockEntityTypes.PAINTED_PORTABLE_STORAGE_INTERFACE.get());
            case "contraption_controls" -> new PaintedContraptionControlsBlock(properties, () -> ModBlockEntityTypes.PAINTED_CONTRAPTION_CONTROLS.get());
            default -> new Block(properties);
        };
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

    private static boolean shouldRegisterPaintVariant(String material, String target) {
        if ("encased_chain_drive".equals(target) && "copper".equals(material)) {
            return true;
        }
        if ("item_drain".equals(target) && "brass".equals(material)) {
            return false;
        }
        String baseMaterial = baseMaterialFor(target);
        return baseMaterial == null || !baseMaterial.equals(material);
    }

    private static String baseMaterialFor(String target) {
        return switch (target) {
            case "horizontal_fluid_tank" -> null;
            case "fluid_pipe",
                 "smart_fluid_pipe",
                 "mechanical_pump",
                 "fluid_valve",
                 "valve_handle",
                 "fluid_tank",
                 "spout",
                 "hose_pulley",
                 "portable_fluid_interface",
                 "steam_engine",
                 "steam_whistle",
                 "item_drain" -> "copper";
            case "mechanical_crafter" -> "brass";
            case "gearbox",
                 "encased_chain_drive",
                 "encased_fan",
                 "millstone",
                 "mechanical_saw",
                 "mechanical_press",
                 "mechanical_mixer",
                 "deployer",
                 "mechanical_drill",
                 "clutch",
                 "gearshift",
                 "speedometer",
                 "stressometer",
                 "adjustable_chain_gearshift",
                 "contraption_controls",
                 "depot",
                 "weighted_ejector",
                 "portable_storage_interface" -> "andesite";
            default -> null;
        };
    }

    private static String stripMaterialPrefix(String name) {
        for (String material : TEMP_PAINT_MATERIALS) {
            String prefix = material + "_";
            if (name.startsWith(prefix)) {
                return name.substring(prefix.length());
            }
        }
        return name;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
