package net.deltav.craftsconstruct.item;

import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.utility.CreateLang;
import net.deltav.craftsconstruct.registry.ModItems;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.deltav.craftsconstruct.util.PaintMaterial;
import net.deltav.craftsconstruct.util.PaintTargetResolver;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.Optional;

public class PaintGunItem extends Item {
    private final boolean creative;

    public PaintGunItem(boolean creative, Properties properties) {
        super(properties.stacksTo(1).rarity(creative ? net.minecraft.world.item.Rarity.EPIC : net.minecraft.world.item.Rarity.UNCOMMON));
        this.creative = creative;
    }

    public boolean isCreative() {
        return creative;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return useOn(context, creative);
    }

    public static InteractionResult useOn(UseOnContext context, boolean creative) {
        Player player = context.getPlayer();
        if (player == null || !player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }

        Level level = context.getLevel();
        ItemStack cartridge = player.getOffhandItem();
        PaintMaterial heldMaterial = getMaterial(cartridge);
        PaintMaterial material = creative ? (heldMaterial == null ? PaintMaterial.BRASS : heldMaterial) : heldMaterial;
        if (material == null) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.translatable("message.crafts_construct.paint_gun.no_cartridge"), true);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        BlockPos pos = context.getClickedPos();
        BlockState sourceState = level.getBlockState(pos);
        if (PaintTargetResolver.isAlreadyMaterial(sourceState, material)) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.translatable("message.crafts_construct.paint_gun.same_material"), true);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        Optional<BlockState> targetState = PaintTargetResolver.resolve(sourceState, material);
        if (targetState.isEmpty()) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.translatable("message.crafts_construct.paint_gun.no_target"), true);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        if (!level.isClientSide) {
            BlockEntity oldBe = level.getBlockEntity(pos);
            CompoundTag nbt = null;
            if (oldBe != null) {
                nbt = oldBe.saveWithFullMetadata(level.registryAccess());
            }

            level.setBlock(pos, targetState.get(), 3);
            level.playSound(null, pos, SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0f, 1.0f);

            if (nbt != null) {
                BlockEntity newBe = level.getBlockEntity(pos);
                if (newBe != null) {
                    ResourceLocation newTypeId = BlockEntityType.getKey(newBe.getType());
                    if (newTypeId != null) {
                        nbt.putString("id", newTypeId.toString());
                    }
                    newBe.loadWithComponents(nbt, level.registryAccess());
                    newBe.setChanged();
                    level.sendBlockUpdated(pos, sourceState, targetState.get(), 3);
                }
            }

            if (!creative && !player.getAbilities().instabuild) {
                consumeCartridge(cartridge, player);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private static PaintMaterial getMaterial(ItemStack stack) {
        if (stack.getItem() instanceof PaintCartridgeItem cartridgeItem) {
            return cartridgeItem.getMaterial();
        }
        return null;
    }

    private static void consumeCartridge(ItemStack cartridge, Player player) {
        int nextDamage = cartridge.getDamageValue() + 1;
        if (nextDamage >= cartridge.getMaxDamage()) {
            player.setItemInHand(net.minecraft.world.InteractionHand.OFF_HAND, new ItemStack(ModItems.EMPTY_PAINT_CARTRIDGE.get()));
            return;
        }
        cartridge.setDamageValue(nextDamage);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        if (net.minecraft.client.gui.screens.Screen.hasShiftDown()) {
            List<Component> summary = TooltipHelper.cutTextComponent(
                    CreateLang.translate(creative ? "tooltip.cc.c_paint_gun.summary" : "tooltip.cc.paint_gun.summary").component(),
                    FontHelper.Palette.STANDARD_CREATE.primary(),
                    FontHelper.Palette.STANDARD_CREATE.highlight()
            );
            List<Component> condition = TooltipHelper.cutTextComponent(
                    CreateLang.translate("tooltip.cc.condition").component(),
                    FontHelper.Palette.STANDARD_CREATE.primary(),
                    FontHelper.Palette.STANDARD_CREATE.highlight()
            );
            List<Component> condition2 = TooltipHelper.cutTextComponent(
                    CreateLang.translate("tooltip.cc.condition2").component(),
                    FontHelper.Palette.STANDARD_CREATE.primary(),
                    FontHelper.Palette.STANDARD_CREATE.highlight()
            );
            tooltip.add(Component.translatable("create.tooltip.cc.keyShift_down").withStyle(ChatFormatting.DARK_GRAY));
            tooltip.add(Component.translatable("create.tooltip.cc.null"));
            tooltip.addAll(summary);
            tooltip.add(Component.translatable("create.tooltip.cc.null"));
            tooltip.add(Component.translatable("create.tooltip.cc.behaviour").withStyle(ChatFormatting.GRAY));
            tooltip.addAll(condition);
            if (!creative) {
                tooltip.add(Component.translatable("create.tooltip.cc.behaviour2").withStyle(ChatFormatting.GRAY));
                tooltip.addAll(condition2);
            }
        } else {
            tooltip.add(Component.translatable("create.tooltip.cc.keyShift").withStyle(ChatFormatting.DARK_GRAY));
        }
    }
}
