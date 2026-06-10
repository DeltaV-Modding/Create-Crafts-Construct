package net.buildercraft.registry;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.buildercraft.block.create.fluid.PaintedFluidTankBlockEntity;
import net.buildercraft.block.create.fluid.PaintedHosePulleyBlockEntity;
import net.buildercraft.block.create.fluid.PaintedSpoutBlockEntity;
import net.buildercraft.block.create.portableInterface.PaintedPortableFluidInterfaceBlockEntity;
import net.minecraft.core.Direction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

import java.lang.reflect.Field;

public class ModCapabilities {
    private static final Field ITEM_HANDLERS_FIELD = field("itemHandlers");
    private static final Field INTERNAL_TANK_FIELD = field("internalTank");
    private static final Field SPOUT_TANK_FIELD = field(SpoutBlockEntity.class, "tank");
    private static final Field HOSE_PULLEY_HANDLER_FIELD = field(HosePulleyBlockEntity.class, "handler");

    private ModCapabilities() {
    }

    public static void register(IEventBus eventBus) {
        eventBus.addListener(ModCapabilities::registerCapabilities);
    }

    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntityTypes.BRASS_ITEM_DRAIN.get(),
                (be, context) -> {
                    if (context != null && context.getAxis().isHorizontal()) {
                        return ReflectionAccess.itemHandler(be, context);
                    }
                    return null;
                }
        );

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntityTypes.BRASS_ITEM_DRAIN.get(),
                (be, context) -> {
                    if (context != Direction.UP) {
                        return ReflectionAccess.fluidHandler(be);
                    }
                    return null;
                }
        );

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntityTypes.PAINTED_PORTABLE_FLUID_INTERFACE.get(),
                (PaintedPortableFluidInterfaceBlockEntity be, Direction context) -> be.getFluidHandler()
        );

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntityTypes.PAINTED_FLUID_TANK.get(),
                (PaintedFluidTankBlockEntity be, Direction context) -> {
                    IFluidHandler handler = be.getFluidHandler();
                    if (handler == null) {
                        be.initialize();
                        handler = be.getFluidHandler();
                    }
                    return handler;
                }
        );

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntityTypes.PAINTED_SPOUT.get(),
                (PaintedSpoutBlockEntity be, Direction context) -> ReflectionAccess.spoutFluidHandler(be)
        );

        event.registerBlockEntity(
                Capabilities.FluidHandler.BLOCK,
                ModBlockEntityTypes.PAINTED_HOSE_PULLEY.get(),
                (PaintedHosePulleyBlockEntity be, Direction context) -> {
                    if (context == null || HosePulleyBlock.hasPipeTowards(be.getLevel(), be.getBlockPos(), be.getBlockState(), context)) {
                        return ReflectionAccess.hosePulleyFluidHandler(be);
                    }
                    return null;
                }
        );
    }

    private static Field field(String name) {
        return field(ItemDrainBlockEntity.class, name);
    }

    private static Field field(Class<?> owner, String name) {
        try {
            Field field = owner.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Create internals changed: " + owner.getSimpleName() + "." + name, e);
        }
    }

    private static class ReflectionAccess {
        @SuppressWarnings("unchecked")
        private static net.neoforged.neoforge.items.IItemHandler itemHandler(ItemDrainBlockEntity be, Direction direction) {
            try {
                java.util.Map<Direction, net.neoforged.neoforge.items.IItemHandler> handlers =
                        (java.util.Map<Direction, net.neoforged.neoforge.items.IItemHandler>) ITEM_HANDLERS_FIELD.get(be);
                return handlers.get(direction);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Unable to access Create Item Drain item handlers", e);
            }
        }

        private static net.neoforged.neoforge.fluids.capability.IFluidHandler fluidHandler(ItemDrainBlockEntity be) {
            try {
                com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour tank =
                        (com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour) INTERNAL_TANK_FIELD.get(be);
                return tank.getCapability();
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Unable to access Create Item Drain fluid tank", e);
            }
        }

        private static IFluidHandler spoutFluidHandler(SpoutBlockEntity be) {
            try {
                SmartFluidTankBehaviour tank = (SmartFluidTankBehaviour) SPOUT_TANK_FIELD.get(be);
                return tank == null ? null : tank.getCapability();
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Unable to access Create Spout fluid tank", e);
            }
        }

        private static IFluidHandler hosePulleyFluidHandler(HosePulleyBlockEntity be) {
            try {
                return (IFluidHandler) HOSE_PULLEY_HANDLER_FIELD.get(be);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Unable to access Create Hose Pulley fluid handler", e);
            }
        }
    }
}
