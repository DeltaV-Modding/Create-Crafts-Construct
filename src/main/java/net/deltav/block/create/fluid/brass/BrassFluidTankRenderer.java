package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankRenderer;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.engine_room.flywheel.lib.transform.TransformStack;

import net.deltav.craftsconstruct;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class BrassFluidTankRenderer extends FluidTankRenderer {
    private static final Map<String, Partials> PARTIALS = new HashMap<>();

    public BrassFluidTankRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderAsBoiler(FluidTankBlockEntity tank, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                                  int light, int overlay) {
        if (!renderPaintedBoiler(tank, partialTicks, ms, buffer, light, overlay)) {
            super.renderAsBoiler(tank, partialTicks, ms, buffer, light, overlay);
        }
    }

    public static boolean renderPaintedBoiler(FluidTankBlockEntity tank, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                                              int light, int overlay) {
        BlockState blockState = tank.getBlockState();
        if (!isPaintedTank(blockState)) {
            return false;
        }

        VertexConsumer vb = buffer.getBuffer(RenderType.cutout());

        ms.pushPose();
        int width = tank.getWidth();
        TransformStack.of(ms)
                .translate(width / 2f, .5, width / 2f);

        float dialPivotY = .375f;
        float dialPivotZ = .5f;
        float gaugeValue = tank.boiler.gauge.getValue(partialTicks);
        Partials partials = getPartials(blockState);

        for (Direction direction : Iterate.horizontalDirections) {
            if (tank.boiler.occludedDirections[direction.get2DDataValue()])
                continue;

            ms.pushPose();
            float yRot = -direction.toYRot() - 90;

            CachedBuffers.partial(partials.gauge, blockState)
                    .rotateYDegrees(yRot)
                    .uncenter()
                    .translate(width / 2f - .375f, 0, 0)
                    .light(light)
                    .renderInto(ms, vb);

            CachedBuffers.partial(partials.dial, blockState)
                    .rotateYDegrees(yRot)
                    .uncenter()
                    .translate(width / 2f - .375f, 0, 0)
                    .translate(0, dialPivotY, dialPivotZ)
                    .rotateXDegrees(-145 * gaugeValue + 90)
                    .translate(0, -dialPivotY, -dialPivotZ)
                    .light(light)
                    .renderInto(ms, vb);

            ms.popPose();
        }

        ms.popPose();
        return true;
    }

    private static boolean isPaintedTank(BlockState state) {
        ResourceLocation id = state.getBlock().builtInRegistryHolder().key().location();
        return craftsconstruct.MOD_ID.equals(id.getNamespace()) && id.getPath().endsWith("_fluid_tank");
    }

    private static Partials getPartials(BlockState state) {
        String path = state.getBlock().builtInRegistryHolder().key().location().getPath();
        String material = path.substring(0, path.indexOf("_fluid_tank"));
        String steamEnginePath = material + "_steam_engine";

        PartialModel gauge = net.deltav.registry.ModPartialModels.STEAM_ENGINE_GAUGES.get(steamEnginePath);
        PartialModel dial = net.deltav.registry.ModPartialModels.STEAM_ENGINE_DIALS.get(steamEnginePath);
        if (gauge == null || dial == null) {
            return new Partials(com.simibubi.create.AllPartialModels.BOILER_GAUGE, com.simibubi.create.AllPartialModels.BOILER_GAUGE_DIAL);
        }
        return new Partials(gauge, dial);
    }

    private record Partials(PartialModel gauge, PartialModel dial) {
    }
}