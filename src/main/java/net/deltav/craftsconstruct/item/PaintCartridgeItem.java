package net.deltav.craftsconstruct.item;

import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.utility.CreateLang;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.deltav.craftsconstruct.util.PaintMaterial;

import java.util.List;

public class PaintCartridgeItem extends Item {
    public static final int USES = 8;

    private final PaintMaterial material;

    public PaintCartridgeItem(PaintMaterial material, Properties properties) {
        super(properties.durability(USES));
        this.material = material;
    }

    public PaintMaterial getMaterial() {
        return material;
    }
    public void appendHoverText(ItemStack stack, net.minecraft.world.level.Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        List<Component> materialTooltip = TooltipHelper.cutTextComponent(
                CreateLang.translate("tooltip.cc.paint." + material.getSerializedName()).component(),
                FontHelper.Palette.STANDARD_CREATE.primary(),
                FontHelper.Palette.STANDARD_CREATE.highlight()
        );
        tooltip.add(Component.translatable("create.tooltip.cc.behaviour3").withStyle(ChatFormatting.GRAY));
        tooltip.addAll(materialTooltip);
        tooltip.add(Component.translatable("create.tooltip.cc.paint_cartridge.uses",
                USES - stack.getDamageValue(), USES).withStyle(ChatFormatting.DARK_GRAY));
    }
}
