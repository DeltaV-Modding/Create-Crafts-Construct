package net.deltav.craftsconstruct.registry;

import com.simibubi.create.AllDisplaySources;
import com.simibubi.create.AllMountedStorageTypes;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceMovement;
import com.simibubi.create.content.decoration.steamWhistle.WhistleGenerator;
import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeGenerator;
import com.simibubi.create.content.fluids.pipes.valve.FluidValveBlock;
import com.simibubi.create.content.fluids.tank.FluidTankGenerator;
import com.simibubi.create.content.fluids.tank.FluidTankModel;
import com.simibubi.create.content.fluids.tank.FluidTankMovementBehavior;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import net.deltav.craftsconstruct.block.SugarBeetCropBlock;
import net.deltav.craftsconstruct.data.CCBlockStateGen;
import net.deltav.craftsconstruct.util.CCStress;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import com.simibubi.create.content.fluids.tank.FluidTankItem;
import java.util.ArrayList;
import java.util.List;

import static com.simibubi.create.api.behaviour.display.DisplaySource.displaySource;
import static com.simibubi.create.api.behaviour.movement.MovementBehaviour.movementBehaviour;
import static com.simibubi.create.api.contraption.storage.fluid.MountedFluidStorageType.mountedFluidStorage;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static net.deltav.craftsconstruct.craftsconstruct.REGISTRATE;
import net.deltav.craftsconstruct.block.create.fluid.andesite.*;
import net.deltav.craftsconstruct.block.create.fluid.brass.*;
import net.deltav.craftsconstruct.block.create.fluid.copper.*;
import net.deltav.craftsconstruct.block.create.fluid.train.*;
import net.deltav.craftsconstruct.block.create.kinetic.andesite.*;
import net.deltav.craftsconstruct.block.create.kinetic.brass.*;
import net.deltav.craftsconstruct.block.create.kinetic.train.*;
import net.deltav.craftsconstruct.block.create.pipe.andesite.*;
import net.deltav.craftsconstruct.block.create.pipe.brass.*;
import net.deltav.craftsconstruct.block.create.pipe.train.*;
import net.deltav.craftsconstruct.block.create.portableInterface.andesite.*;
import net.deltav.craftsconstruct.block.create.portableInterface.brass.*;
import net.deltav.craftsconstruct.block.create.portableInterface.train.*;
import net.deltav.craftsconstruct.block.create.drain.andesite.*;
import net.deltav.craftsconstruct.block.create.drain.brass.*;
import net.deltav.craftsconstruct.block.create.drain.train.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;

public class ModBlocks {
    public static final List<BlockEntry<? extends Block>> TEMP_PAINT_BLOCKS = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_FLUID_PIPES = new ArrayList<>();
    public static final List<BlockEntry<? extends Block>> PAINTED_GLASS_PIPES = new ArrayList<>();
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
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate(CCBlockStateGen.pipeAndesite())
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassFluidPipeBlock> BRASS_FLUID_PIPE = REGISTRATE.block("brass_fluid_pipe", p -> new BrassFluidPipeBlock(p, () -> ModBlockEntityTypes.BRASS_FLUID_PIPE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate(CCBlockStateGen.pipeBrass())
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<TrainFluidPipeBlock> TRAIN_FLUID_PIPE = REGISTRATE.block("train_fluid_pipe", p -> new TrainFluidPipeBlock(p, () -> ModBlockEntityTypes.TRAIN_FLUID_PIPE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate(CCBlockStateGen.pipeTrain())
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<AndesiteGlassPipeBlock> ANDESITE_GLASS_PIPE =
            REGISTRATE.block("andesite_glass_pipe", p -> new AndesiteGlassPipeBlock(p))
                    .initialProperties(SharedProperties::copperMetal)
                    .properties(p -> p.noOcclusion())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(pickaxeOnly())
                    .blockstate((c, p) -> {
                        p.getVariantBuilder(c.getEntry())
                                .forAllStatesExcept(state -> {
                                    Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
                                    return ConfiguredModel.builder()
                                            .modelFile(p.models()
                                                    .getExistingFile(p.modLoc("block/andesite_fluid_pipe/window")))
                                            .uvLock(false)
                                            .rotationX(axis == Direction.Axis.Y ? 0 : 90)
                                            .rotationY(axis == Direction.Axis.X ? 90 : 0)
                                            .build();
                                }, BlockStateProperties.WATERLOGGED);
                    })
                    .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
                    .loot((p, b) -> p.dropOther(b, ANDESITE_FLUID_PIPE.get()))
                    .register();
    public static final BlockEntry<BrassGlassPipeBlock> BRASS_GLASS_PIPE =
            REGISTRATE.block("brass_glass_pipe", p -> new BrassGlassPipeBlock(p))
                    .initialProperties(SharedProperties::copperMetal)
                    .properties(p -> p.noOcclusion())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(pickaxeOnly())
                    .blockstate((c, p) -> {
                        p.getVariantBuilder(c.getEntry())
                                .forAllStatesExcept(state -> {
                                    Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
                                    return ConfiguredModel.builder()
                                            .modelFile(p.models()
                                                    .getExistingFile(p.modLoc("block/andesite_fluid_pipe/window")))
                                            .uvLock(false)
                                            .rotationX(axis == Direction.Axis.Y ? 0 : 90)
                                            .rotationY(axis == Direction.Axis.X ? 90 : 0)
                                            .build();
                                }, BlockStateProperties.WATERLOGGED);
                    })
                    .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
                    .loot((p, b) -> p.dropOther(b, BRASS_FLUID_PIPE.get()))
                    .register();
    public static final BlockEntry<TrainGlassPipeBlock> TRAIN_GLASS_PIPE =
            REGISTRATE.block("train_glass_pipe", p -> new TrainGlassPipeBlock(p))
                    .initialProperties(SharedProperties::copperMetal)
                    .properties(p -> p.noOcclusion())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(pickaxeOnly())
                    .blockstate((c, p) -> {
                        p.getVariantBuilder(c.getEntry())
                                .forAllStatesExcept(state -> {
                                    Direction.Axis axis = state.getValue(BlockStateProperties.AXIS);
                                    return ConfiguredModel.builder()
                                            .modelFile(p.models()
                                                    .getExistingFile(p.modLoc("block/andesite_fluid_pipe/window")))
                                            .uvLock(false)
                                            .rotationX(axis == Direction.Axis.Y ? 0 : 90)
                                            .rotationY(axis == Direction.Axis.X ? 90 : 0)
                                            .build();
                                }, BlockStateProperties.WATERLOGGED);
                    })
                    .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
                    .loot((p, b) -> p.dropOther(b, BRASS_FLUID_PIPE.get()))
                    .register();
    public static final BlockEntry<AndesiteSmartFluidPipeBlock> ANDESITE_SMART_FLUID_PIPE = REGISTRATE.block("andesite_smart_fluid_pipe", p -> new AndesiteSmartFluidPipeBlock(p, () -> ModBlockEntityTypes.ANDESITE_SMART_FLUID_PIPE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW))
            .transform(pickaxeOnly())
            .blockstate(new SmartFluidPipeGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassSmartFluidPipeBlock> BRASS_SMART_FLUID_PIPE = REGISTRATE.block("brass_smart_fluid_pipe", p -> new BrassSmartFluidPipeBlock(p, () -> ModBlockEntityTypes.BRASS_SMART_FLUID_PIPE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW))
            .transform(pickaxeOnly())
            .blockstate(new SmartFluidPipeGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<TrainSmartFluidPipeBlock> TRAIN_SMART_FLUID_PIPE = REGISTRATE.block("train_smart_fluid_pipe", p -> new TrainSmartFluidPipeBlock(p, () -> ModBlockEntityTypes.TRAIN_SMART_FLUID_PIPE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW))
            .transform(pickaxeOnly())
            .blockstate(new SmartFluidPipeGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<AndesitePumpBlock> ANDESITE_MECHANICAL_PUMP = REGISTRATE.block("andesite_mechanical_pump", p -> new AndesitePumpBlock(p, () -> ModBlockEntityTypes.ANDESITE_MECHANICAL_PUMP.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(CCStress.setImpact(4.0))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassPumpBlock> BRASS_MECHANICAL_PUMP = REGISTRATE.block("brass_mechanical_pump", p -> new BrassPumpBlock(p, () -> ModBlockEntityTypes.BRASS_MECHANICAL_PUMP.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(CCStress.setImpact(4.0))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<TrainPumpBlock> TRAIN_MECHANICAL_PUMP = REGISTRATE.block("train_mechanical_pump", p -> new TrainPumpBlock(p, () -> ModBlockEntityTypes.TRAIN_MECHANICAL_PUMP.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(CCStress.setImpact(4.0))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<AndesiteFluidValveBlock> ANDESITE_FLUID_VALVE = REGISTRATE.block("andesite_fluid_valve", p -> new AndesiteFluidValveBlock(p, () -> ModBlockEntityTypes.ANDESITE_FLUID_VALVE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate((c, p) -> BlockStateGen.directionalAxisBlock(c, p,
                    (state, vertical) -> AssetLookup.partialBaseModel(c, p, vertical ? "vertical" : "horizontal",
                            state.getValue(FluidValveBlock.ENABLED) ? "open" : "closed")))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassFluidValveBlock> BRASS_FLUID_VALVE = REGISTRATE.block("brass_fluid_valve", p -> new BrassFluidValveBlock(p, () -> ModBlockEntityTypes.BRASS_FLUID_VALVE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate((c, p) -> BlockStateGen.directionalAxisBlock(c, p,
                    (state, vertical) -> AssetLookup.partialBaseModel(c, p, vertical ? "vertical" : "horizontal",
                            state.getValue(FluidValveBlock.ENABLED) ? "open" : "closed")))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<TrainFluidValveBlock> TRAIN_FLUID_VALVE = REGISTRATE.block("train_fluid_valve", p -> new TrainFluidValveBlock(p, () -> ModBlockEntityTypes.TRAIN_FLUID_VALVE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate((c, p) -> BlockStateGen.directionalAxisBlock(c, p,
                    (state, vertical) -> AssetLookup.partialBaseModel(c, p, vertical ? "vertical" : "horizontal",
                            state.getValue(FluidValveBlock.ENABLED) ? "open" : "closed")))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<ValveHandleBlock> ANDESITE_VALVE_HANDLE = REGISTRATE.block("andesite_valve_handle", p -> ValveHandleBlock.copper(p))
            .transform(pickaxeOnly())
            .transform(BuilderTransformers.valveHandle(null))
            .transform(CCStress.setCapacity(8.0))
            .register();
    public static final BlockEntry<ValveHandleBlock> BRASS_VALVE_HANDLE = REGISTRATE.block("brass_valve_handle", p -> ValveHandleBlock.copper(p))
            .transform(pickaxeOnly())
            .transform(BuilderTransformers.valveHandle(null))
            .transform(CCStress.setCapacity(8.0))
            .register();
    public static final BlockEntry<ValveHandleBlock> TRAIN_VALVE_HANDLE = REGISTRATE.block("train_valve_handle", p -> ValveHandleBlock.copper(p))
            .transform(pickaxeOnly())
            .transform(BuilderTransformers.valveHandle(null))
            .transform(CCStress.setCapacity(8.0))
            .register();
    public static final BlockEntry<AndesiteFluidTankBlock> ANDESITE_FLUID_TANK = REGISTRATE.block("andesite_fluid_tank", p -> new AndesiteFluidTankBlock(p, com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion()
                    .isRedstoneConductor((p1, p2, p3) -> true))
            .transform(pickaxeOnly())
            .blockstate(new FluidTankGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> FluidTankModel::standard))
            .transform(displaySource(AllDisplaySources.BOILER))
            .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
            .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(FluidTankItem::new)
            .model(AssetLookup.customBlockItemModel("_", "block_single_window"))
            .build()
            .register();
    public static final BlockEntry<BrassFluidTankBlock> BRASS_FLUID_TANK = REGISTRATE.block("brass_fluid_tank", p -> new BrassFluidTankBlock(p, com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion()
                    .isRedstoneConductor((p1, p2, p3) -> true))
            .transform(pickaxeOnly())
            .blockstate(new FluidTankGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> FluidTankModel::standard))
            .transform(displaySource(AllDisplaySources.BOILER))
            .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
            .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(FluidTankItem::new)
            .model(AssetLookup.customBlockItemModel("_", "block_single_window"))
            .build()
            .register();
    public static final BlockEntry<TrainFluidTankBlock> TRAIN_FLUID_TANK = REGISTRATE.block("train_fluid_tank", p -> new TrainFluidTankBlock(p, com.simibubi.create.AllBlockEntityTypes.FLUID_TANK::get))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion()
                    .isRedstoneConductor((p1, p2, p3) -> true))
            .transform(pickaxeOnly())
            .blockstate(new FluidTankGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> FluidTankModel::standard))
            .transform(displaySource(AllDisplaySources.BOILER))
            .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
            .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(FluidTankItem::new)
            .model(AssetLookup.customBlockItemModel("_", "block_single_window"))
            .build()
            .register();
    public static final BlockEntry<AndesiteHorizontalFluidTankBlock> ANDESITE_HORIZONTAL_FLUID_TANK = REGISTRATE.block("andesite_horizontal_fluid_tank", p -> new AndesiteHorizontalFluidTankBlock(p, () -> ModBlockEntityTypes.ANDESITE_HORIZONTAL_FLUID_TANK.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion()
                    .isRedstoneConductor((p1, p2, p3) -> true))
            .transform(pickaxeOnly())
            .blockstate(new FluidTankGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> FluidTankModel::standard))
            .transform(displaySource(AllDisplaySources.BOILER))
            .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
            .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(FluidTankItem::new)
            .model(AssetLookup.customBlockItemModel("_", "block_single_window"))
            .build()
            .register();
    public static final BlockEntry<BrassHorizontalFluidTankBlock> BRASS_HORIZONTAL_FLUID_TANK = REGISTRATE.block("brass_horizontal_fluid_tank", p -> new BrassHorizontalFluidTankBlock(p, () -> ModBlockEntityTypes.BRASS_HORIZONTAL_FLUID_TANK.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion()
                    .isRedstoneConductor((p1, p2, p3) -> true))
            .transform(pickaxeOnly())
            .blockstate(new FluidTankGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> FluidTankModel::standard))
            .transform(displaySource(AllDisplaySources.BOILER))
            .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
            .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(FluidTankItem::new)
            .model(AssetLookup.customBlockItemModel("_", "block_single_window"))
            .build()
            .register();
    public static final BlockEntry<CopperHorizontalFluidTankBlock> COPPER_HORIZONTAL_FLUID_TANK = REGISTRATE.block("copper_horizontal_fluid_tank", p -> new CopperHorizontalFluidTankBlock(p, () -> ModBlockEntityTypes.COPPER_HORIZONTAL_FLUID_TANK.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion()
                    .isRedstoneConductor((p1, p2, p3) -> true))
            .transform(pickaxeOnly())
            .blockstate(new FluidTankGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> FluidTankModel::standard))
            .transform(displaySource(AllDisplaySources.BOILER))
            .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
            .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(FluidTankItem::new)
            .model(AssetLookup.customBlockItemModel("_", "block_single_window"))
            .build()
            .register();
    public static final BlockEntry<TrainHorizontalFluidTankBlock> TRAIN_HORIZONTAL_FLUID_TANK = REGISTRATE.block("train_horizontal_fluid_tank", p -> new TrainHorizontalFluidTankBlock(p, () -> ModBlockEntityTypes.TRAIN_HORIZONTAL_FLUID_TANK.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion()
                    .isRedstoneConductor((p1, p2, p3) -> true))
            .transform(pickaxeOnly())
            .blockstate(new FluidTankGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> FluidTankModel::standard))
            .transform(displaySource(AllDisplaySources.BOILER))
            .transform(mountedFluidStorage(AllMountedStorageTypes.FLUID_TANK))
            .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(FluidTankItem::new)
            .model(AssetLookup.customBlockItemModel("_", "block_single_window"))
            .build()
            .register();
    public static final BlockEntry<AndesiteSpoutBlock> ANDESITE_SPOUT = REGISTRATE.block("andesite_spout", p -> new AndesiteSpoutBlock(p, () -> ModBlockEntityTypes.ANDESITE_SPOUT.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), AssetLookup.partialBaseModel(ctx, prov)))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassSpoutBlock> BRASS_SPOUT = REGISTRATE.block("brass_spout", p -> new BrassSpoutBlock(p, () -> ModBlockEntityTypes.BRASS_SPOUT.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), AssetLookup.partialBaseModel(ctx, prov)))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();
    public static final BlockEntry<TrainSpoutBlock> TRAIN_SPOUT = REGISTRATE.block("train_spout", p -> new TrainSpoutBlock(p, () -> ModBlockEntityTypes.TRAIN_SPOUT.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), AssetLookup.partialBaseModel(ctx, prov)))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();
    public static final BlockEntry<AndesiteHosePulleyBlock> ANDESITE_HOSE_PULLEY = REGISTRATE.block("andesite_hose_pulley", p -> new AndesiteHosePulleyBlock(p, () -> ModBlockEntityTypes.ANDESITE_HOSE_PULLEY.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .transform(CCStress.setImpact(4.0))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassHosePulleyBlock> BRASS_HOSE_PULLEY = REGISTRATE.block("brass_hose_pulley", p -> new BrassHosePulleyBlock(p, () -> ModBlockEntityTypes.BRASS_HOSE_PULLEY.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .transform(CCStress.setImpact(4.0))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<TrainHosePulleyBlock> TRAIN_HOSE_PULLEY = REGISTRATE.block("train_hose_pulley", p -> new TrainHosePulleyBlock(p, () -> ModBlockEntityTypes.TRAIN_HOSE_PULLEY.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .transform(CCStress.setImpact(4.0))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<AndesiteItemDrainBlock> ANDESITE_ITEM_DRAIN = REGISTRATE.block("andesite_item_drain", p -> new AndesiteItemDrainBlock(p))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate((c, p) -> p.simpleBlock(c.get(), AssetLookup.standardModel(c, p)))
            .simpleItem()
            .register();
    public static final BlockEntry<BrassItemDrainBlock> BRASS_ITEM_DRAIN = REGISTRATE.block("brass_item_drain", p -> new BrassItemDrainBlock(p))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate((c, p) -> p.simpleBlock(c.get(), AssetLookup.standardModel(c, p)))
            .simpleItem()
            .register();
    public static final BlockEntry<TrainItemDrainBlock> TRAIN_ITEM_DRAIN = REGISTRATE.block("train_item_drain", p -> new TrainItemDrainBlock(p))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate((c, p) -> p.simpleBlock(c.get(), AssetLookup.standardModel(c, p)))
            .simpleItem()
            .register();
    public static final BlockEntry<AndesitePortableFluidInterfaceBlock> ANDESITE_PORTABLE_FLUID_INTERFACE = REGISTRATE.block("andesite_portable_fluid_interface", p -> new AndesitePortableFluidInterfaceBlock(p))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), AssetLookup.partialBaseModel(c, p)))
            .onRegister(movementBehaviour(new PortableStorageInterfaceMovement()))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassPortableFluidInterfaceBlock> BRASS_PORTABLE_FLUID_INTERFACE = REGISTRATE.block("brass_portable_fluid_interface", p -> new BrassPortableFluidInterfaceBlock(p))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), AssetLookup.partialBaseModel(c, p)))
            .onRegister(movementBehaviour(new PortableStorageInterfaceMovement()))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .transform(customItemModel())
            .register();
    public static final BlockEntry<TrainPortableFluidInterfaceBlock> TRAIN_PORTABLE_FLUID_INTERFACE = REGISTRATE.block("train_portable_fluid_interface", p -> new TrainPortableFluidInterfaceBlock(p))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_LIGHT_GRAY))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), AssetLookup.partialBaseModel(c, p)))
            .onRegister(movementBehaviour(new PortableStorageInterfaceMovement()))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .transform(customItemModel())
            .register();
    public static final BlockEntry<AndesiteSteamEngineBlock> ANDESITE_STEAM_ENGINE = REGISTRATE.block("andesite_steam_engine", p -> new AndesiteSteamEngineBlock(p, () -> ModBlockEntityTypes.ANDESITE_STEAM_ENGINE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate((c, p) -> p.horizontalFaceBlock(c.get(), AssetLookup.partialBaseModel(c, p)))
            .transform(CCStress.setCapacity(1024.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(64, true))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassSteamEngineBlock> BRASS_STEAM_ENGINE = REGISTRATE.block("brass_steam_engine", p -> new BrassSteamEngineBlock(p, () -> ModBlockEntityTypes.BRASS_STEAM_ENGINE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate((c, p) -> p.horizontalFaceBlock(c.get(), AssetLookup.partialBaseModel(c, p)))
            .transform(CCStress.setCapacity(1024.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(64, true))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<TrainSteamEngineBlock> TRAIN_STEAM_ENGINE = REGISTRATE.block("train_steam_engine", p -> new TrainSteamEngineBlock(p, () -> ModBlockEntityTypes.TRAIN_STEAM_ENGINE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate((c, p) -> p.horizontalFaceBlock(c.get(), AssetLookup.partialBaseModel(c, p)))
            .transform(CCStress.setCapacity(1024.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(64, true))
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<AndesiteSteamWhistleBlock> ANDESITE_STEAM_WHISTLE = REGISTRATE.block("andesite_steam_whistle", p -> new AndesiteSteamWhistleBlock(p, () -> ModBlockEntityTypes.ANDESITE_STEAM_WHISTLE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.GOLD))
            .transform(pickaxeOnly())
            .blockstate(new WhistleGenerator()::generate)
            .item()
            .transform(customItemModel())
            .register();
    public static final BlockEntry<BrassSteamWhistleBlock> BRASS_STEAM_WHISTLE = REGISTRATE.block("brass_steam_whistle", p -> new BrassSteamWhistleBlock(p, () -> ModBlockEntityTypes.BRASS_STEAM_WHISTLE.get()))
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.GOLD))
            .transform(pickaxeOnly())
            .blockstate(new WhistleGenerator()::generate)
            .item()
            .transform(customItemModel())
            .register();
     public static final BlockEntry<TrainSteamWhistleBlock> TRAIN_STEAM_WHISTLE = REGISTRATE.block("train_steam_whistle", p -> new TrainSteamWhistleBlock(p, () -> ModBlockEntityTypes.TRAIN_STEAM_WHISTLE.get()))
             .initialProperties(SharedProperties::copperMetal)
             .properties(p -> p.mapColor(MapColor.GOLD))
             .transform(pickaxeOnly())
             .blockstate(new WhistleGenerator()::generate)
             .item()
             .transform(customItemModel())
             .register();

    public static final BlockEntry<SugarBeetCropBlock> SUGAR_BEETS = REGISTRATE.block("sugar_beets", SugarBeetCropBlock::new)
            .initialProperties(() -> Blocks.BEETROOTS)
            .register();

    public static void register() {
        // This method is called from craftsconstruct to ensure the class is loaded
    }

    public static final BlockEntry<Block> WHITE_TEXTILE = REGISTRATE.block("white_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> WHITE_CHECKERED_TEXTILE = REGISTRATE.block("white_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> LIGHT_GRAY_TEXTILE = REGISTRATE.block("light_gray_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> LIGHT_GRAY_CHECKERED_TEXTILE = REGISTRATE.block("light_gray_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> GRAY_TEXTILE = REGISTRATE.block("gray_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> GRAY_CHECKERED_TEXTILE = REGISTRATE.block("gray_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> BLACK_TEXTILE = REGISTRATE.block("black_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> BLACK_CHECKERED_TEXTILE = REGISTRATE.block("black_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> BROWN_TEXTILE = REGISTRATE.block("brown_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> BROWN_CHECKERED_TEXTILE = REGISTRATE.block("brown_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> RED_TEXTILE = REGISTRATE.block("red_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> RED_CHECKERED_TEXTILE = REGISTRATE.block("red_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> ORANGE_TEXTILE = REGISTRATE.block("orange_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> ORANGE_CHECKERED_TEXTILE = REGISTRATE.block("orange_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> YELLOW_TEXTILE = REGISTRATE.block("yellow_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> YELLOW_CHECKERED_TEXTILE = REGISTRATE.block("yellow_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> LIME_TEXTILE = REGISTRATE.block("lime_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> LIME_CHECKERED_TEXTILE = REGISTRATE.block("lime_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> GREEN_TEXTILE = REGISTRATE.block("green_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> GREEN_CHECKERED_TEXTILE = REGISTRATE.block("green_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> CYAN_TEXTILE = REGISTRATE.block("cyan_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> CYAN_CHECKERED_TEXTILE = REGISTRATE.block("cyan_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> LIGHT_BLUE_TEXTILE = REGISTRATE.block("light_blue_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> LIGHT_BLUE_CHECKERED_TEXTILE = REGISTRATE.block("light_blue_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> BLUE_TEXTILE = REGISTRATE.block("blue_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> BLUE_CHECKERED_TEXTILE = REGISTRATE.block("blue_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> PURPLE_TEXTILE = REGISTRATE.block("purple_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> PURPLE_CHECKERED_TEXTILE = REGISTRATE.block("purple_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> MAGENTA_TEXTILE = REGISTRATE.block("magenta_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> MAGENTA_CHECKERED_TEXTILE = REGISTRATE.block("magenta_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> PINK_TEXTILE = REGISTRATE.block("pink_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();
    public static final BlockEntry<Block> PINK_CHECKERED_TEXTILE = REGISTRATE.block("pink_checkered_textile_block", Block::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.ignitedByLava().sound(SoundType.WOOL).strength(0.8f))
            .item()
            .build()
            .register();

    static {
        PAINTED_FLUID_PIPES.add(ANDESITE_FLUID_PIPE);
        PAINTED_FLUID_PIPES.add(BRASS_FLUID_PIPE);
        PAINTED_FLUID_PIPES.add(TRAIN_FLUID_PIPE);
        PAINTED_GLASS_PIPES.add(ANDESITE_GLASS_PIPE);
        PAINTED_GLASS_PIPES.add(BRASS_GLASS_PIPE);
        PAINTED_GLASS_PIPES.add(TRAIN_GLASS_PIPE);
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
