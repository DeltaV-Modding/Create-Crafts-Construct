package net.buildercraft.registry;

import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.content.fluids.pipes.SmartFluidPipeBlockEntity;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterBlockEntity;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.content.kinetics.drill.DrillBlockEntity;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlockEntity;
import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.kinetics.saw.SawBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import com.simibubi.create.content.contraptions.actors.psi.PortableFluidInterfaceBlockEntity;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.buildercraft.block.create.drain.BrassItemDrainBlockEntity;
import net.buildercraft.block.create.drain.BrassItemDrainRenderer;
import net.buildercraft.block.create.fluid.PaintedFluidTankBlockEntity;
import net.buildercraft.block.create.fluid.PaintedFluidValveBlockEntity;
import net.buildercraft.block.create.fluid.PaintedHosePulleyBlockEntity;
import net.buildercraft.block.create.fluid.PaintedPumpBlockEntity;
import net.buildercraft.block.create.fluid.PaintedSpoutBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedChainDriveBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedDeployerBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedEncasedFanBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedGearboxBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedMechanicalCrafterBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedMechanicalDrillBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedMechanicalMixerBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedMechanicalPressBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedMechanicalSawBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedMillstoneBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedSteamEngineBlockEntity;
import net.buildercraft.block.create.kinetic.PaintedSteamWhistleBlockEntity;
import net.buildercraft.block.create.pipe.PaintedFluidPipeBlockEntity;
import net.buildercraft.block.create.pipe.PaintedSmartFluidPipeBlockEntity;
import net.buildercraft.block.create.portableInterface.PaintedPortableFluidInterfaceBlockEntity;
import net.buildercraft.craftsconstruct;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModBlockEntityTypes {
    private static final CreateRegistrate REGISTRATE = craftsconstruct.registrate();
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, craftsconstruct.MOD_ID);

public static final BlockEntityEntry<BrassItemDrainBlockEntity> BRASS_ITEM_DRAIN = REGISTRATE
        .blockEntity("brass_item_drain", BrassItemDrainBlockEntity::new)
        .validBlocks(ModBlocks.BRASS_ITEM_DRAIN)
        .renderer(() -> BrassItemDrainRenderer::new)
        .register();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedFluidPipeBlockEntity>> PAINTED_FLUID_PIPE =
            BLOCK_ENTITY_TYPES.register("painted_fluid_pipe", () -> BlockEntityType.Builder.of(
                    PaintedFluidPipeBlockEntity::new,
                    blocks(ModBlocks.PAINTED_FLUID_PIPES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedSmartFluidPipeBlockEntity>> PAINTED_SMART_FLUID_PIPE =
            BLOCK_ENTITY_TYPES.register("painted_smart_fluid_pipe", () -> BlockEntityType.Builder.of(
                    PaintedSmartFluidPipeBlockEntity::new,
                    blocks(ModBlocks.PAINTED_SMART_FLUID_PIPES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedPumpBlockEntity>> PAINTED_MECHANICAL_PUMP =
            BLOCK_ENTITY_TYPES.register("painted_mechanical_pump", () -> BlockEntityType.Builder.of(
                    PaintedPumpBlockEntity::new,
                    blocks(ModBlocks.PAINTED_MECHANICAL_PUMPS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedFluidValveBlockEntity>> PAINTED_FLUID_VALVE =
            BLOCK_ENTITY_TYPES.register("painted_fluid_valve", () -> BlockEntityType.Builder.of(
                    PaintedFluidValveBlockEntity::new,
                    blocks(ModBlocks.PAINTED_FLUID_VALVES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedFluidTankBlockEntity>> PAINTED_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("painted_fluid_tank", () -> BlockEntityType.Builder.of(
                    PaintedFluidTankBlockEntity::new,
                    blocks(ModBlocks.PAINTED_FLUID_TANKS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedSpoutBlockEntity>> PAINTED_SPOUT =
            BLOCK_ENTITY_TYPES.register("painted_spout", () -> BlockEntityType.Builder.of(
                    PaintedSpoutBlockEntity::new,
                    blocks(ModBlocks.PAINTED_SPOUTS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedHosePulleyBlockEntity>> PAINTED_HOSE_PULLEY =
            BLOCK_ENTITY_TYPES.register("painted_hose_pulley", () -> BlockEntityType.Builder.of(
                    PaintedHosePulleyBlockEntity::new,
                    blocks(ModBlocks.PAINTED_HOSE_PULLEYS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedGearboxBlockEntity>> PAINTED_GEARBOX =
            BLOCK_ENTITY_TYPES.register("painted_gearbox", () -> BlockEntityType.Builder.of(
                    PaintedGearboxBlockEntity::new,
                    blocks(ModBlocks.PAINTED_GEARBOXES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedChainDriveBlockEntity>> PAINTED_ENCASED_CHAIN_DRIVE =
            BLOCK_ENTITY_TYPES.register("painted_encased_chain_drive", () -> BlockEntityType.Builder.of(
                    PaintedChainDriveBlockEntity::new,
                    blocks(ModBlocks.PAINTED_ENCASED_CHAIN_DRIVES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedEncasedFanBlockEntity>> PAINTED_ENCASED_FAN =
            BLOCK_ENTITY_TYPES.register("painted_encased_fan", () -> BlockEntityType.Builder.of(
                    PaintedEncasedFanBlockEntity::new,
                    blocks(ModBlocks.PAINTED_ENCASED_FANS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedMillstoneBlockEntity>> PAINTED_MILLSTONE =
            BLOCK_ENTITY_TYPES.register("painted_millstone", () -> BlockEntityType.Builder.of(
                    PaintedMillstoneBlockEntity::new,
                    blocks(ModBlocks.PAINTED_MILLSTONES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedMechanicalSawBlockEntity>> PAINTED_MECHANICAL_SAW =
            BLOCK_ENTITY_TYPES.register("painted_mechanical_saw", () -> BlockEntityType.Builder.of(
                    PaintedMechanicalSawBlockEntity::new,
                    blocks(ModBlocks.PAINTED_MECHANICAL_SAWS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedMechanicalPressBlockEntity>> PAINTED_MECHANICAL_PRESS =
            BLOCK_ENTITY_TYPES.register("painted_mechanical_press", () -> BlockEntityType.Builder.of(
                    PaintedMechanicalPressBlockEntity::new,
                    blocks(ModBlocks.PAINTED_MECHANICAL_PRESSES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedMechanicalMixerBlockEntity>> PAINTED_MECHANICAL_MIXER =
            BLOCK_ENTITY_TYPES.register("painted_mechanical_mixer", () -> BlockEntityType.Builder.of(
                    PaintedMechanicalMixerBlockEntity::new,
                    blocks(ModBlocks.PAINTED_MECHANICAL_MIXERS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedDeployerBlockEntity>> PAINTED_DEPLOYER =
            BLOCK_ENTITY_TYPES.register("painted_deployer", () -> BlockEntityType.Builder.of(
                    PaintedDeployerBlockEntity::new,
                    blocks(ModBlocks.PAINTED_DEPLOYERS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedMechanicalDrillBlockEntity>> PAINTED_MECHANICAL_DRILL =
            BLOCK_ENTITY_TYPES.register("painted_mechanical_drill", () -> BlockEntityType.Builder.of(
                    PaintedMechanicalDrillBlockEntity::new,
                    blocks(ModBlocks.PAINTED_MECHANICAL_DRILLS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedMechanicalCrafterBlockEntity>> PAINTED_MECHANICAL_CRAFTER =
            BLOCK_ENTITY_TYPES.register("painted_mechanical_crafter", () -> BlockEntityType.Builder.of(
                    PaintedMechanicalCrafterBlockEntity::new,
                    blocks(ModBlocks.PAINTED_MECHANICAL_CRAFTERS)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedSteamEngineBlockEntity>> PAINTED_STEAM_ENGINE =
            BLOCK_ENTITY_TYPES.register("painted_steam_engine", () -> BlockEntityType.Builder.of(
                    PaintedSteamEngineBlockEntity::new,
                    blocks(ModBlocks.PAINTED_STEAM_ENGINES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedSteamWhistleBlockEntity>> PAINTED_STEAM_WHISTLE =
            BLOCK_ENTITY_TYPES.register("painted_steam_whistle", () -> BlockEntityType.Builder.of(
                    PaintedSteamWhistleBlockEntity::new,
                    blocks(ModBlocks.PAINTED_STEAM_WHISTLES)
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedPortableFluidInterfaceBlockEntity>> PAINTED_PORTABLE_FLUID_INTERFACE =
            BLOCK_ENTITY_TYPES.register("painted_portable_fluid_interface", () -> BlockEntityType.Builder.of(
                    PaintedPortableFluidInterfaceBlockEntity::new,
                    blocks(ModBlocks.PAINTED_PORTABLE_FLUID_INTERFACES)
            ).build(null));

    private static Block[] blocks(List<DeferredBlock<Block>> blocks) {
        return blocks.stream()
                .map(DeferredHolder::get)
                .toArray(Block[]::new);
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
