package net.deltav.block.create.fluid;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutRenderer;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour.TankSegment;
import com.simibubi.create.foundation.fluid.FluidRenderer;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.fluids.FluidStack;
import net.deltav.registry.ModPartialModels;

public class PaintedSpoutRenderer extends SpoutRenderer {
    private static final java.lang.reflect.Field SPOUT_TANK_FIELD;
    static {
        try {
            SPOUT_TANK_FIELD = SpoutBlockEntity.class.getDeclaredField("tank");
            SPOUT_TANK_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public PaintedSpoutRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(SpoutBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        SmartFluidTankBehaviour tank;
        try {
            tank = (SmartFluidTankBehaviour) SPOUT_TANK_FIELD.get(be);
        } catch (IllegalAccessException e) {
            return;
        }

        if (tank == null)
            return;

        TankSegment primaryTank = tank.getPrimaryTank();
        FluidStack fluidStack = primaryTank.getRenderedFluid();
        float level = primaryTank.getFluidLevel()
                .getValue(partialTicks);

        if (!fluidStack.isEmpty() && level != 0) {
            boolean top = fluidStack.getFluid()
                    .getFluidType()
                    .isLighterThanAir();

            level = Math.max(level, 0.175f);
            float min = 2.5f / 16f;
            float max = min + (11 / 16f);
            float yOffset = (11 / 16f) * level;

            ms.pushPose();
            if (!top) ms.translate(0, yOffset, 0);
            else ms.translate(0, max - min, 0);

            FluidRenderer.renderFluidBox(fluidStack.getFluid(), fluidStack.getAmount(),
                    min, min - yOffset, min, max, min, max,
                    buffer, ms, light, false, true, fluidStack.getComponentsPatch());

            ms.popPose();
        }

        int processingTicks = be.processingTicks;
        float processingPT = processingTicks - partialTicks;
        float processingProgress = 1 - (processingPT - 5) / 10;
        processingProgress = Mth.clamp(processingProgress, 0, 1);
        float radius = 0;

        if (!fluidStack.isEmpty() && processingTicks != -1) {
            radius = (float) (Math.pow(((2 * processingProgress) - 1), 2) - 1);
            AABB bb = new AABB(0.5, 0.0, 0.5, 0.5, -1.2, 0.5).inflate(radius / 32f);
            FluidRenderer.renderFluidBox(fluidStack.getFluid(), fluidStack.getAmount(),
                    (float) bb.minX, (float) bb.minY, (float) bb.minZ,
                    (float) bb.maxX, (float) bb.maxY, (float) bb.maxZ,
                    buffer, ms, light, true, true, fluidStack.getComponentsPatch());
        }

        float squeeze = radius;
        if (processingPT < 0)
            squeeze = 0;
        else if (processingPT < 2)
            squeeze = Mth.lerp(processingPT / 2f, 0, -1);
        else if (processingPT < 10)
            squeeze = -1;

        String path = be.getBlockState().getBlock().builtInRegistryHolder().key().location().getPath();
        PartialModel topModel = ModPartialModels.SPOUT_TOPS.getOrDefault(path, com.simibubi.create.AllPartialModels.SPOUT_TOP);
        PartialModel middleModel = ModPartialModels.SPOUT_MIDDLES.getOrDefault(path, com.simibubi.create.AllPartialModels.SPOUT_MIDDLE);
        PartialModel bottomModel = ModPartialModels.SPOUT_BOTTOMS.getOrDefault(path, com.simibubi.create.AllPartialModels.SPOUT_BOTTOM);

        ms.pushPose();

        CachedBuffers.partial(topModel, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
        ms.translate(0, -3 * squeeze / 32f, 0);

        CachedBuffers.partial(middleModel, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
        ms.translate(0, -3 * squeeze / 32f, 0);

        CachedBuffers.partial(bottomModel, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
        ms.translate(0, -3 * squeeze / 32f, 0);

        ms.popPose();
    }
}
