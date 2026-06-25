package net.deltav.craftsconstruct.registry;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlock;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.deltav.craftsconstruct.block.create.fluid.andesite.*;
import net.deltav.craftsconstruct.block.create.fluid.brass.*;
import net.deltav.craftsconstruct.block.create.fluid.copper.*;
import net.deltav.craftsconstruct.block.create.fluid.sturdy.*;
import net.deltav.craftsconstruct.block.create.portableInterface.andesite.*;
import net.deltav.craftsconstruct.block.create.portableInterface.brass.*;
import net.deltav.craftsconstruct.block.create.portableInterface.sturdy.*;
import net.minecraft.core.Direction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

import java.lang.reflect.Field;
import java.util.List;

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
        for (var beType : List.of(
                ModBlockEntityTypes.ANDESITE_ITEM_DRAIN,
                ModBlockEntityTypes.BRASS_ITEM_DRAIN,
                ModBlockEntityTypes.STURDY_ITEM_DRAIN
        )) {
            event.registerBlockEntity(
                    Capabilities.ItemHandler.BLOCK,
                    beType.get(),
                    (be, context) -> {
                        if (context != null && context.getAxis().isHorizontal()) {
                            return ReflectionAccess.itemHandler(be, context);
                        }
                        return null;
                    }
            );

            event.registerBlockEntity(
                    Capabilities.FluidHandler.BLOCK,
                    beType.get(),
                    (be, context) -> {
                        if (context != Direction.UP) {
                            return ReflectionAccess.fluidHandler(be);
                        }
                        return null;
                    }
            );
        }

        for (var beType : List.of(
                ModBlockEntityTypes.ANDESITE_PORTABLE_FLUID_INTERFACE,
                ModBlockEntityTypes.BRASS_PORTABLE_FLUID_INTERFACE,
                ModBlockEntityTypes.STURDY_PORTABLE_FLUID_INTERFACE
        )) {
            event.registerBlockEntity(
                    Capabilities.FluidHandler.BLOCK,
                    beType.get(),
                    (be, context) -> {
                        if (be instanceof AndesitePortableFluidInterfaceBlockEntity andesiteBe) {
                            return andesiteBe.getFluidHandler();
                        } else if (be instanceof BrassPortableFluidInterfaceBlockEntity brassBe) {
                            return brassBe.getFluidHandler();
                        } else if (be instanceof SturdyPortableFluidInterfaceBlockEntity sturdyBe) {
                            return sturdyBe.getFluidHandler();
                        }
                        return null;
                    }
            );
        }

        for (var beType : List.of(
                ModBlockEntityTypes.ANDESITE_FLUID_TANK,
                ModBlockEntityTypes.BRASS_FLUID_TANK,
                ModBlockEntityTypes.STURDY_FLUID_TANK
        )) {
            event.registerBlockEntity(
                    Capabilities.FluidHandler.BLOCK,
                    beType.get(),
                    (be, context) -> {
                        IFluidHandler handler = null;
                        if (be instanceof AndesiteFluidTankBlockEntity andesiteBe) {
                            handler = andesiteBe.getFluidHandler();
                        } else if (be instanceof BrassFluidTankBlockEntity brassBe) {
                            handler = brassBe.getFluidHandler();
                        } else if (be instanceof SturdyFluidTankBlockEntity sturdyBe) {
                            handler = sturdyBe.getFluidHandler();
                        }
                        if (handler == null && be instanceof com.simibubi.create.content.fluids.tank.FluidTankBlockEntity ftbe) {
                            ftbe.initialize();
                            if (be instanceof AndesiteFluidTankBlockEntity andesiteBe) {
                                handler = andesiteBe.getFluidHandler();
                            } else if (be instanceof BrassFluidTankBlockEntity brassBe) {
                                handler = brassBe.getFluidHandler();
                            } else if (be instanceof SturdyFluidTankBlockEntity sturdyBe) {
                                handler = sturdyBe.getFluidHandler();
                            }
                        }
                        return handler;
                    }
            );
        }

        for (var beType : List.of(
                ModBlockEntityTypes.ANDESITE_HORIZONTAL_FLUID_TANK,
                ModBlockEntityTypes.BRASS_HORIZONTAL_FLUID_TANK,
                ModBlockEntityTypes.COPPER_HORIZONTAL_FLUID_TANK,
                ModBlockEntityTypes.STURDY_HORIZONTAL_FLUID_TANK
        )) {
            event.registerBlockEntity(
                    Capabilities.FluidHandler.BLOCK,
                    beType.get(),
                    (be, context) -> {
                        IFluidHandler handler = null;
                        if (be instanceof AndesiteHorizontalFluidTankBlockEntity andesiteBe) {
                            handler = andesiteBe.getFluidHandler();
                        } else if (be instanceof BrassHorizontalFluidTankBlockEntity brassBe) {
                            handler = brassBe.getFluidHandler();
                        } else if (be instanceof CopperHorizontalFluidTankBlockEntity copperBe) {
                            handler = copperBe.getFluidHandler();
                        } else if (be instanceof SturdyHorizontalFluidTankBlockEntity sturdyBe) {
                            handler = sturdyBe.getFluidHandler();
                        }
                        if (handler == null && be instanceof com.simibubi.create.content.fluids.tank.FluidTankBlockEntity ftbe) {
                            ftbe.initialize();
                            if (be instanceof AndesiteHorizontalFluidTankBlockEntity andesiteBe) {
                                handler = andesiteBe.getFluidHandler();
                            } else if (be instanceof BrassHorizontalFluidTankBlockEntity brassBe) {
                                handler = brassBe.getFluidHandler();
                            } else if (be instanceof CopperHorizontalFluidTankBlockEntity copperBe) {
                                handler = copperBe.getFluidHandler();
                            } else if (be instanceof SturdyHorizontalFluidTankBlockEntity sturdyBe) {
                                handler = sturdyBe.getFluidHandler();
                            }
                        }
                        return handler;
                    }
            );
        }

        for (var beType : List.of(
                ModBlockEntityTypes.ANDESITE_SPOUT,
                ModBlockEntityTypes.BRASS_SPOUT,
                ModBlockEntityTypes.STURDY_SPOUT
        )) {
            event.registerBlockEntity(
                    Capabilities.FluidHandler.BLOCK,
                    beType.get(),
                    (be, context) -> ReflectionAccess.spoutFluidHandler(be)
            );
        }

        for (var beType : List.of(
                ModBlockEntityTypes.ANDESITE_HOSE_PULLEY,
                ModBlockEntityTypes.BRASS_HOSE_PULLEY,
                ModBlockEntityTypes.STURDY_HOSE_PULLEY
        )) {
            event.registerBlockEntity(
                    Capabilities.FluidHandler.BLOCK,
                    beType.get(),
                    (be, context) -> {
                        if (context == null || HosePulleyBlock.hasPipeTowards(be.getLevel(), be.getBlockPos(), be.getBlockState(), context)) {
                            return ReflectionAccess.hosePulleyFluidHandler(be);
                        }
                        return null;
                }
            );
        }
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
