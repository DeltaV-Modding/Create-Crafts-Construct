package net.deltav.util;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllSpecialTextures;
import net.deltav.registry.ModItems;
import net.deltav.item.PaintCartridgeItem;
import net.createmod.catnip.outliner.Outliner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import java.util.Collections;
import java.util.function.Supplier;

public class PaintGunOutlineRenderer {

    private static Supplier<Iterable<BlockPos>> renderedPosition;

    public static void tick() {
        gatherBlockTarget();
        if (renderedPosition == null)
            return;

        Outliner outliner = Outliner.getInstance();
        BlockPos targetPos = renderedPosition.get().iterator().next();
        int color = 0xff0000;  //Red color

        if (isPaintableBlock(targetPos)) {
            color = 0x00ff00;  //Green color
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                ItemStack cartridge = player.getOffhandItem();
                boolean creative = player.getMainHandItem().is(ModItems.C_PAINT_GUN.get()) || player.getOffhandItem().is(ModItems.C_PAINT_GUN.get());
                PaintMaterial material = null;
                if (cartridge.getItem() instanceof PaintCartridgeItem cartridgeItem) {
                    material = cartridgeItem.getMaterial();
                }
                if (material == null && creative) {
                    material = PaintMaterial.BRASS;
                }
                if (material != null) {
                    BlockState state = player.level().getBlockState(targetPos);
                    if (PaintTargetResolver.isAlreadyMaterial(state, material)) {
                        color = 0xffd000;
                    }
                }
                if(material == null && !creative) {
                    color = 0xffd000;
                }
            }
        }

        outliner.showCluster("PaintGunOutline", renderedPosition.get())
                .colored(color)
                .disableLineNormals()
                .lineWidth(1 / 32f)
                .withFaceTexture(AllSpecialTextures.CHECKERED);
    }

    private static boolean isPaintableBlock(BlockPos pos) {
        BlockState blockState = Minecraft.getInstance().level.getBlockState(pos);
        Block block = blockState.getBlock();

        ResourceLocation regName = Minecraft.getInstance().level
                .registryAccess()
                .registryOrThrow(net.minecraft.core.registries.Registries.BLOCK)
                .getKey(block);

        if (regName == null) return false;
        return PaintableConfig.isBlockPaintable(regName);
    }


    protected static void gatherBlockTarget() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null)
            return;

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();

        boolean isHoldingPaintGun = main.is(ModItems.PAINT_GUN.get()) || off.is(ModItems.PAINT_GUN.get());
        boolean isHoldingCPaintGun = main.is(ModItems.C_PAINT_GUN.get()) || off.is(ModItems.C_PAINT_GUN.get());

        if (!isHoldingPaintGun && !isHoldingCPaintGun) {
            renderedPosition = null;
            return;
        }

        double maxDistance = hasExtendoGrip(player) ? 8 : 5;

        Vec3 eyePos = player.getEyePosition(1.0f);
        Vec3 lookVec = player.getLookAngle().scale(maxDistance);
        BlockHitResult hitResult = player.level().clip(new ClipContext(
                eyePos,
                eyePos.add(lookVec),
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
        ));

        if (hitResult == null || hitResult.getType() == HitResult.Type.MISS) {
            renderedPosition = null;
            return;
        }

        BlockPos targetBlock = hitResult.getBlockPos();
        renderedPosition = () -> Collections.singletonList(targetBlock);
    }

    private static boolean hasExtendoGrip(LocalPlayer player) {
        return player.getMainHandItem().is(AllItems.EXTENDO_GRIP.get()) ||
                player.getOffhandItem().is(AllItems.EXTENDO_GRIP.get());
    }
}
