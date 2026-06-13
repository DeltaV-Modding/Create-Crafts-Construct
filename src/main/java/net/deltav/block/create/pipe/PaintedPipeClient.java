package net.deltav.block.create.pipe;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;

import net.deltav.craftsconstruct;
import net.deltav.registry.ModBlockEntityTypes;
import net.deltav.registry.ModBlocks;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SpriteShifter;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.List;

public final class PaintedPipeClient {
    private PaintedPipeClient() {
    }

    public static void registerModelSwappers() {
        registerPipeModels(ModBlocks.PAINTED_FLUID_PIPES);
        registerPipeModels(ModBlocks.PAINTED_SMART_FLUID_PIPES);
    }

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_SMART_FLUID_PIPE.get(), SmartBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.PAINTED_FLUID_PIPE.get(), PaintedGlassPipeRenderer::new);
    }

    private static void registerPipeModels(List<DeferredBlock<Block>> blocks) {
        for (DeferredBlock<Block> block : blocks) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());

            String path = block.getId().getPath();
            String material = path.split("_")[0];
            String targetPrefix = "block/" + material + "_fluid_pipe/" + material + "_fluid_pipe";

            SpriteShiftEntry pipeShift = SpriteShifter.get(
                    ResourceLocation.fromNamespaceAndPath("create", "block/pipes"),
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, targetPrefix)
            );
            SpriteShiftEntry pipeConnectedShift = SpriteShifter.get(
                    ResourceLocation.fromNamespaceAndPath("create", "block/pipes_connected"),
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, targetPrefix + "_connected")
            );

            SpriteShiftEntry glassPipeShift = SpriteShifter.get(
                    ResourceLocation.fromNamespaceAndPath("create", "block/glass_fluid_pipe"),
                    ResourceLocation.fromNamespaceAndPath(craftsconstruct.MOD_ID, "block/" + material + "_fluid_pipe/" + material + "_glass_fluid_pipe")
            );

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    bakedModel -> new SpriteShiftingBakedModel(PipeAttachmentModel.withAO(bakedModel), pipeShift, pipeConnectedShift, glassPipeShift)
            );
            CreateClient.MODEL_SWAPPER.getCustomItemModels().register(
                    block.getId(),
                    bakedModel -> new SpriteShiftingBakedModel(bakedModel, pipeShift, pipeConnectedShift, glassPipeShift)
            );
        }
    }

    private static class SpriteShiftingBakedModel extends BakedModelWrapper<BakedModel> {
        private final SpriteShiftEntry spriteShift;
        private final SpriteShiftEntry connectedSpriteShift;
        private final SpriteShiftEntry glassPipeShift;

        private SpriteShiftingBakedModel(BakedModel originalModel, SpriteShiftEntry spriteShift, SpriteShiftEntry connectedSpriteShift, SpriteShiftEntry glassPipeShift) {
            super(originalModel);
            this.spriteShift = spriteShift;
            this.connectedSpriteShift = connectedSpriteShift;
            this.glassPipeShift = glassPipeShift;
        }

        @Override
        public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand) {
            return getQuads(state, side, rand, ModelData.EMPTY, null);
        }

        @Override
        public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType) {
            List<BakedQuad> originalQuads = super.getQuads(state, side, rand, extraData, renderType);
            List<BakedQuad> shiftedQuads = new ArrayList<>(originalQuads.size());

            for (BakedQuad quad : originalQuads) {
                SpriteShiftEntry shift = getShift(quad);
                if (shift == null) {
                    shiftedQuads.add(quad);
                    continue;
                }

                BakedQuad clonedQuad = BakedQuadHelper.clone(quad);
                int[] vertices = clonedQuad.getVertices();
                for (int i = 0; i < 4; i++) {
                    float u = BakedQuadHelper.getU(vertices, i);
                    float v = BakedQuadHelper.getV(vertices, i);
                    BakedQuadHelper.setU(vertices, i, shift.getTargetU(u));
                    BakedQuadHelper.setV(vertices, i, shift.getTargetV(v));
                }
                shiftedQuads.add(clonedQuad);
            }
            return shiftedQuads;
        }

        private SpriteShiftEntry getShift(BakedQuad quad) {
            if (quad.getSprite() == null)
                return null;
            if (spriteShift != null && quad.getSprite() == spriteShift.getOriginal())
                return spriteShift;
            if (connectedSpriteShift != null && quad.getSprite() == connectedSpriteShift.getOriginal())
                return connectedSpriteShift;
            if (glassPipeShift != null && quad.getSprite() == glassPipeShift.getOriginal())
                return glassPipeShift;
            return null;
        }
    }
}
