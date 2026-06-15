package net.deltav.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.deltav.block.create.drain.PaintedItemDrainBlockEntity;
import net.deltav.block.create.fluid.PaintedFluidTankBlockEntity;
import net.deltav.block.create.fluid.HorizontalFluidTankBlockEntity;
import net.deltav.block.create.fluid.PaintedFluidValveBlockEntity;
import net.deltav.block.create.fluid.PaintedHosePulleyBlockEntity;
import net.deltav.block.create.fluid.PaintedPumpBlockEntity;
import net.deltav.block.create.fluid.PaintedSpoutBlockEntity;
import net.deltav.block.create.kinetic.PaintedChainDriveBlockEntity;
import net.deltav.block.create.kinetic.PaintedSteamEngineBlockEntity;
import net.deltav.block.create.kinetic.PaintedSteamWhistleBlockEntity;
import net.deltav.block.create.pipe.PaintedFluidPipeBlockEntity;
import net.deltav.block.create.pipe.PaintedSmartFluidPipeBlockEntity;
import net.deltav.block.create.portableInterface.PaintedPortableFluidInterfaceBlockEntity;
import net.deltav.craftsconstruct;
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

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedItemDrainBlockEntity>> PAINTED_ITEM_DRAIN =
            BLOCK_ENTITY_TYPES.register("painted_item_drain", () -> BlockEntityType.Builder.of(
                    PaintedItemDrainBlockEntity::new,
                    getValidItemDrains()
            ).build(null));

    private static Block[] getValidItemDrains() {
        java.util.List<Block> list = new java.util.ArrayList<>();
    //    list.add(ModBlocks.BRASS_ITEM_DRAIN.get());
        for (DeferredBlock<Block> block : ModBlocks.PAINTED_ITEM_DRAINS) {
            list.add(block.get());
        }
        return list.toArray(Block[]::new);
    }

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

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HorizontalFluidTankBlockEntity>> HORIZONTAL_FLUID_TANK =
            BLOCK_ENTITY_TYPES.register("horizontal_fluid_tank", () -> BlockEntityType.Builder.of(
                    HorizontalFluidTankBlockEntity::new,
                    blocks(ModBlocks.PAINTED_HORIZONTAL_FLUID_TANKS)
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

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PaintedChainDriveBlockEntity>> PAINTED_ENCASED_CHAIN_DRIVE =
            BLOCK_ENTITY_TYPES.register("painted_encased_chain_drive", () -> BlockEntityType.Builder.of(
                    PaintedChainDriveBlockEntity::new,
                    blocks(ModBlocks.PAINTED_ENCASED_CHAIN_DRIVES)
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
