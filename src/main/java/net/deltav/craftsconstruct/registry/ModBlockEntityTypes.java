package net.deltav.craftsconstruct.registry;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.contraptions.actors.psi.PSIVisual;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;
import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.content.decoration.steamWhistle.WhistleRenderer;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyRenderer;
import com.simibubi.create.content.fluids.pipes.GlassPipeVisual;
import com.simibubi.create.content.fluids.pipes.StraightPipeBlockEntity;
import com.simibubi.create.content.fluids.pipes.TransparentStraightPipeRenderer;
import com.simibubi.create.content.fluids.pump.PumpRenderer;
import com.simibubi.create.content.fluids.tank.FluidTankRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.deltav.craftsconstruct.block.create.drain.andesite.AndesiteItemDrainBlockEntity;
import net.deltav.craftsconstruct.block.create.drain.andesite.AndesiteItemDrainRenderer;
import net.deltav.craftsconstruct.block.create.drain.brass.BrassItemDrainBlockEntity;
import net.deltav.craftsconstruct.block.create.drain.brass.BrassItemDrainRenderer;
import net.deltav.craftsconstruct.block.create.drain.train.TrainItemDrainBlockEntity;
import net.deltav.craftsconstruct.block.create.drain.train.TrainItemDrainRenderer;
import net.deltav.craftsconstruct.block.create.fluid.andesite.*;
import net.deltav.craftsconstruct.block.create.fluid.brass.*;
import net.deltav.craftsconstruct.block.create.fluid.copper.CopperHorizontalFluidTankBlockEntity;
import net.deltav.craftsconstruct.block.create.fluid.train.*;
import net.deltav.craftsconstruct.block.create.kinetic.andesite.*;
import net.deltav.craftsconstruct.block.create.kinetic.brass.*;
import net.deltav.craftsconstruct.block.create.kinetic.train.*;
import net.deltav.craftsconstruct.block.create.pipe.andesite.*;
import net.deltav.craftsconstruct.block.create.pipe.brass.*;
import net.deltav.craftsconstruct.block.create.pipe.train.*;
import net.deltav.craftsconstruct.block.create.portableInterface.andesite.AndesitePortableFluidInterfaceBlockEntity;
import net.deltav.craftsconstruct.block.create.portableInterface.brass.BrassPortableFluidInterfaceBlockEntity;
import net.deltav.craftsconstruct.block.create.portableInterface.train.TrainPortableFluidInterfaceBlockEntity;

import static net.deltav.craftsconstruct.craftsconstruct.REGISTRATE;

public class ModBlockEntityTypes {
    public static final BlockEntityEntry<AndesiteFluidPipeBlockEntity> ANDESITE_FLUID_PIPE = REGISTRATE
            .blockEntity("andesite_fluid_pipe", AndesiteFluidPipeBlockEntity::new)
            .validBlocks(ModBlocks.ANDESITE_FLUID_PIPE)
            .register();
    public static final BlockEntityEntry<BrassFluidPipeBlockEntity> BRASS_FLUID_PIPE = REGISTRATE
            .blockEntity("brass_fluid_pipe", BrassFluidPipeBlockEntity::new)
            .validBlocks(ModBlocks.BRASS_FLUID_PIPE)
            .register();
    public static final BlockEntityEntry<TrainFluidPipeBlockEntity> TRAIN_FLUID_PIPE = REGISTRATE
            .blockEntity("train_fluid_pipe", TrainFluidPipeBlockEntity::new)
            .validBlocks(ModBlocks.TRAIN_FLUID_PIPE)
            .register();
    public static final BlockEntityEntry<StraightPipeBlockEntity> ANDESITE_GLASS_PIPE = REGISTRATE
            .blockEntity("andesite_glass_pipe", StraightPipeBlockEntity::new)
            .visual(() -> GlassPipeVisual::new, false)
            .validBlocks(ModBlocks.ANDESITE_GLASS_PIPE)
            .renderer(() -> TransparentStraightPipeRenderer::new)
            .register();
    public static final BlockEntityEntry<StraightPipeBlockEntity> BRASS_GLASS_PIPE = REGISTRATE
            .blockEntity("brass_glass_pipe", StraightPipeBlockEntity::new)
            .visual(() -> GlassPipeVisual::new, false)
            .validBlocks(ModBlocks.BRASS_GLASS_PIPE)
            .renderer(() -> TransparentStraightPipeRenderer::new)
            .register();
    public static final BlockEntityEntry<StraightPipeBlockEntity> TRAIN_GLASS_PIPE = REGISTRATE
            .blockEntity("train_glass_pipe", StraightPipeBlockEntity::new)
            .visual(() -> GlassPipeVisual::new, false)
            .validBlocks(ModBlocks.TRAIN_GLASS_PIPE)
            .renderer(() -> TransparentStraightPipeRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesiteSmartFluidPipeBlockEntity> ANDESITE_SMART_FLUID_PIPE = REGISTRATE
            .blockEntity("andesite_smart_fluid_pipe", AndesiteSmartFluidPipeBlockEntity::new)
            .validBlocks(ModBlocks.ANDESITE_SMART_FLUID_PIPE)
            .renderer(() -> SmartBlockEntityRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassSmartFluidPipeBlockEntity> BRASS_SMART_FLUID_PIPE = REGISTRATE
            .blockEntity("brass_smart_fluid_pipe", BrassSmartFluidPipeBlockEntity::new)
            .validBlocks(ModBlocks.BRASS_SMART_FLUID_PIPE)
            .renderer(() -> SmartBlockEntityRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainSmartFluidPipeBlockEntity> TRAIN_SMART_FLUID_PIPE = REGISTRATE
            .blockEntity("train_smart_fluid_pipe", TrainSmartFluidPipeBlockEntity::new)
            .validBlocks(ModBlocks.TRAIN_SMART_FLUID_PIPE)
            .renderer(() -> SmartBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<AndesitePumpBlockEntity> ANDESITE_MECHANICAL_PUMP = REGISTRATE
            .blockEntity("andesite_mechanical_pump", AndesitePumpBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .validBlocks(ModBlocks.ANDESITE_MECHANICAL_PUMP)
            .renderer(() -> PumpRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassPumpBlockEntity> BRASS_MECHANICAL_PUMP = REGISTRATE
            .blockEntity("brass_mechanical_pump", BrassPumpBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .validBlocks(ModBlocks.BRASS_MECHANICAL_PUMP)
            .renderer(() -> PumpRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainPumpBlockEntity> TRAIN_MECHANICAL_PUMP = REGISTRATE
            .blockEntity("train_mechanical_pump", TrainPumpBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .validBlocks(ModBlocks.TRAIN_MECHANICAL_PUMP)
            .renderer(() -> PumpRenderer::new)
            .register();

    public static final BlockEntityEntry<AndesiteFluidValveBlockEntity> ANDESITE_FLUID_VALVE = REGISTRATE
            .blockEntity("andesite_fluid_valve", AndesiteFluidValveBlockEntity::new)
            .visual(() -> AndesiteFluidValveVisual::new)
            .validBlocks(ModBlocks.ANDESITE_FLUID_VALVE)
            .renderer(() -> AndesiteFluidValveRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassFluidValveBlockEntity> BRASS_FLUID_VALVE = REGISTRATE
            .blockEntity("brass_fluid_valve", BrassFluidValveBlockEntity::new)
            .visual(() -> BrassFluidValveVisual::new)
            .validBlocks(ModBlocks.BRASS_FLUID_VALVE)
            .renderer(() -> BrassFluidValveRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainFluidValveBlockEntity> TRAIN_FLUID_VALVE = REGISTRATE
            .blockEntity("train_fluid_valve", TrainFluidValveBlockEntity::new)
            .visual(() -> TrainFluidValveVisual::new)
            .validBlocks(ModBlocks.TRAIN_FLUID_VALVE)
            .renderer(() -> TrainFluidValveRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesiteFluidTankBlockEntity> ANDESITE_FLUID_TANK = REGISTRATE
            .blockEntity("andesite_fluid_tank", AndesiteFluidTankBlockEntity::new)
            .validBlocks(ModBlocks.ANDESITE_FLUID_TANK)
            .renderer(() -> AndesiteFluidTankRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassFluidTankBlockEntity> BRASS_FLUID_TANK = REGISTRATE
            .blockEntity("brass_fluid_tank", BrassFluidTankBlockEntity::new)
            .validBlocks(ModBlocks.BRASS_FLUID_TANK)
            .renderer(() -> BrassFluidTankRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainFluidTankBlockEntity> TRAIN_FLUID_TANK = REGISTRATE
            .blockEntity("train_fluid_tank", TrainFluidTankBlockEntity::new)
            .validBlocks(ModBlocks.TRAIN_FLUID_TANK)
            .renderer(() -> TrainFluidTankRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesiteHorizontalFluidTankBlockEntity> ANDESITE_HORIZONTAL_FLUID_TANK = REGISTRATE
            .blockEntity("andesite_horizontal_fluid_tank", AndesiteHorizontalFluidTankBlockEntity::new)
            .validBlocks(ModBlocks.ANDESITE_HORIZONTAL_FLUID_TANK)
            .renderer(() -> AndesiteFluidTankRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassHorizontalFluidTankBlockEntity> BRASS_HORIZONTAL_FLUID_TANK = REGISTRATE
            .blockEntity("brass_horizontal_fluid_tank", BrassHorizontalFluidTankBlockEntity::new)
            .validBlocks(ModBlocks.BRASS_HORIZONTAL_FLUID_TANK)
            .renderer(() -> BrassFluidTankRenderer::new)
            .register();
    public static final BlockEntityEntry<CopperHorizontalFluidTankBlockEntity> COPPER_HORIZONTAL_FLUID_TANK = REGISTRATE
            .blockEntity("copper_horizontal_fluid_tank", CopperHorizontalFluidTankBlockEntity::new)
            .validBlocks(ModBlocks.COPPER_HORIZONTAL_FLUID_TANK)
            .renderer(() -> FluidTankRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainHorizontalFluidTankBlockEntity> TRAIN_HORIZONTAL_FLUID_TANK = REGISTRATE
            .blockEntity("train_horizontal_fluid_tank", TrainHorizontalFluidTankBlockEntity::new)
            .validBlocks(ModBlocks.TRAIN_HORIZONTAL_FLUID_TANK)
            .renderer(() -> TrainFluidTankRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesiteSpoutBlockEntity> ANDESITE_SPOUT = REGISTRATE
            .blockEntity("andesite_spout", AndesiteSpoutBlockEntity::new)
            .validBlocks(ModBlocks.ANDESITE_SPOUT)
            .renderer(() -> AndesiteSpoutRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassSpoutBlockEntity> BRASS_SPOUT = REGISTRATE
            .blockEntity("brass_spout", BrassSpoutBlockEntity::new)
            .validBlocks(ModBlocks.BRASS_SPOUT)
            .renderer(() -> BrassSpoutRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainSpoutBlockEntity> TRAIN_SPOUT = REGISTRATE
            .blockEntity("train_spout", TrainSpoutBlockEntity::new)
            .validBlocks(ModBlocks.TRAIN_SPOUT)
            .renderer(() -> TrainSpoutRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesiteHosePulleyBlockEntity> ANDESITE_HOSE_PULLEY = REGISTRATE
            .blockEntity("andesite_hose_pulley", AndesiteHosePulleyBlockEntity::new)
            .visual(() -> HosePulleyVisual::new)
            .validBlocks(ModBlocks.ANDESITE_HOSE_PULLEY)
            .renderer(() -> HosePulleyRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassHosePulleyBlockEntity> BRASS_HOSE_PULLEY = REGISTRATE
            .blockEntity("brass_hose_pulley", BrassHosePulleyBlockEntity::new)
            .visual(() -> HosePulleyVisual::new)
            .validBlocks(ModBlocks.BRASS_HOSE_PULLEY)
            .renderer(() -> HosePulleyRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainHosePulleyBlockEntity> TRAIN_HOSE_PULLEY = REGISTRATE
            .blockEntity("train_hose_pulley", TrainHosePulleyBlockEntity::new)
            .visual(() -> HosePulleyVisual::new)
            .validBlocks(ModBlocks.TRAIN_HOSE_PULLEY)
            .renderer(() -> HosePulleyRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesiteItemDrainBlockEntity> ANDESITE_ITEM_DRAIN = REGISTRATE
            .blockEntity("andesite_item_drain", AndesiteItemDrainBlockEntity::new)
            .validBlocks(ModBlocks.ANDESITE_ITEM_DRAIN)
            .renderer(() -> AndesiteItemDrainRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassItemDrainBlockEntity> BRASS_ITEM_DRAIN = REGISTRATE
            .blockEntity("brass_item_drain", BrassItemDrainBlockEntity::new)
            .validBlocks(ModBlocks.BRASS_ITEM_DRAIN)
            .renderer(() -> BrassItemDrainRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainItemDrainBlockEntity> TRAIN_ITEM_DRAIN = REGISTRATE
            .blockEntity("train_item_drain", TrainItemDrainBlockEntity::new)
            .validBlocks(ModBlocks.TRAIN_ITEM_DRAIN)
            .renderer(() -> TrainItemDrainRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesitePortableFluidInterfaceBlockEntity> ANDESITE_PORTABLE_FLUID_INTERFACE = REGISTRATE
            .blockEntity("andesite_portable_fluid_interface", AndesitePortableFluidInterfaceBlockEntity::new)
            .visual(() -> PSIVisual::new)
            .validBlocks(ModBlocks.ANDESITE_PORTABLE_FLUID_INTERFACE)
            .renderer(() -> PortableStorageInterfaceRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassPortableFluidInterfaceBlockEntity> BRASS_PORTABLE_FLUID_INTERFACE = REGISTRATE
            .blockEntity("brass_portable_fluid_interface", BrassPortableFluidInterfaceBlockEntity::new)
            .visual(() -> PSIVisual::new)
            .validBlocks(ModBlocks.BRASS_PORTABLE_FLUID_INTERFACE)
            .renderer(() -> PortableStorageInterfaceRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainPortableFluidInterfaceBlockEntity> TRAIN_PORTABLE_FLUID_INTERFACE = REGISTRATE
            .blockEntity("train_portable_fluid_interface", TrainPortableFluidInterfaceBlockEntity::new)
            .visual(() -> PSIVisual::new)
            .validBlocks(ModBlocks.TRAIN_PORTABLE_FLUID_INTERFACE)
            .renderer(() -> PortableStorageInterfaceRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesiteSteamEngineBlockEntity> ANDESITE_STEAM_ENGINE = REGISTRATE
            .blockEntity("andesite_steam_engine", AndesiteSteamEngineBlockEntity::new)
            .visual(() -> AndesiteSteamEngineVisual::new, false)
            .validBlocks(ModBlocks.ANDESITE_STEAM_ENGINE)
            .renderer(() -> AndesiteSteamEngineRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassSteamEngineBlockEntity> BRASS_STEAM_ENGINE = REGISTRATE
            .blockEntity("brass_steam_engine", BrassSteamEngineBlockEntity::new)
            .visual(() -> BrassSteamEngineVisual::new, false)
            .validBlocks(ModBlocks.BRASS_STEAM_ENGINE)
            .renderer(() -> BrassSteamEngineRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainSteamEngineBlockEntity> TRAIN_STEAM_ENGINE = REGISTRATE
            .blockEntity("train_steam_engine", TrainSteamEngineBlockEntity::new)
            .visual(() -> TrainSteamEngineVisual::new, false)
            .validBlocks(ModBlocks.TRAIN_STEAM_ENGINE)
            .renderer(() -> TrainSteamEngineRenderer::new)
            .register();
    public static final BlockEntityEntry<AndesiteSteamWhistleBlockEntity> ANDESITE_STEAM_WHISTLE = REGISTRATE
            .blockEntity("andesite_steam_whistle", AndesiteSteamWhistleBlockEntity::new)
            .validBlocks(ModBlocks.ANDESITE_STEAM_WHISTLE)
            .renderer(() -> WhistleRenderer::new)
            .register();
    public static final BlockEntityEntry<BrassSteamWhistleBlockEntity> BRASS_STEAM_WHISTLE = REGISTRATE
            .blockEntity("brass_steam_whistle", BrassSteamWhistleBlockEntity::new)
            .validBlocks(ModBlocks.BRASS_STEAM_WHISTLE)
            .renderer(() -> WhistleRenderer::new)
            .register();
    public static final BlockEntityEntry<TrainSteamWhistleBlockEntity> TRAIN_STEAM_WHISTLE = REGISTRATE
            .blockEntity("train_steam_whistle", TrainSteamWhistleBlockEntity::new)
            .validBlocks(ModBlocks.TRAIN_STEAM_WHISTLE)
            .renderer(() -> WhistleRenderer::new)
            .register();

    private ModBlockEntityTypes() {
    }

    public static void register() {
    }
}