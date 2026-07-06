package net.deltav.craftsconstruct.block.create.pipe;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.foundation.model.BakedQuadHelper;

import net.deltav.craftsconstruct.craftsconstruct;
import net.deltav.craftsconstruct.registry.ModBlocks;
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
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.ModelData;
import com.tterrag.registrate.util.entry.BlockEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PipeClient {
    private PipeClient() {
    }

    public static void registerRenderLayers() {
        for (var block : ModBlocks.PAINTED_FLUID_PIPES) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
        for (var block : ModBlocks.PAINTED_GLASS_PIPES) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
        for (var block : ModBlocks.PAINTED_SMART_FLUID_PIPES) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutoutMipped());
        }
    }

    public static void registerModelSwappers() {
        registerPipeModels(ModBlocks.PAINTED_FLUID_PIPES);
        registerPipeModels(ModBlocks.PAINTED_GLASS_PIPES);
        registerPipeModels(ModBlocks.PAINTED_SMART_FLUID_PIPES);
        registerPipeModels(ModBlocks.PAINTED_FLUID_VALVES);
    }

    private static void registerPipeModels(List<BlockEntry<? extends Block>> blocks) {
        for (var block : blocks) {

            String path = block.getId().getPath();
            String material = path.substring(0, path.indexOf('_'));
            String pipeTexture = pipeTexture(material, false);
            String connectedPipeTexture = pipeTexture(material, true);
            String targetPipeTexture = paintablePipeTexture(material, false);
            String targetConnectedPipeTexture = paintablePipeTexture(material, true);

            List<SpriteShiftEntry> pipeShifts = Arrays.asList(
                    SpriteShifter.get(createBlockTexture("pipes"), modTexture(targetPipeTexture)),
                    SpriteShifter.get(modTexture(pipeTexture), modTexture(targetPipeTexture))
            );
            List<SpriteShiftEntry> pipeConnectedShifts = Arrays.asList(
                    SpriteShifter.get(createBlockTexture("pipes_connected"), modTexture(targetConnectedPipeTexture)),
                    SpriteShifter.get(modTexture(connectedPipeTexture), modTexture(targetConnectedPipeTexture))
            );

            List<SpriteShiftEntry> glassPipeShifts = Arrays.asList(
                    SpriteShifter.get(createBlockTexture("glass_fluid_pipe"), modTexture("block/" + material + "_glass_fluid_pipe/" + material + "_glass_fluid_pipe")),
                    SpriteShifter.get(modTexture("block/" + material + "_fluid_pipe/" + material + "_glass_fluid_pipe"), modTexture("block/" + material + "_glass_fluid_pipe/" + material + "_glass_fluid_pipe")),
                    SpriteShifter.get(modTexture("block/" + material + "_glass_fluid_pipe/" + material + "_glass_fluid_pipe"), modTexture("block/" + material + "_glass_fluid_pipe/" + material + "_glass_fluid_pipe"))
            );
            List<SpriteShiftEntry> smartPipeShifts = Arrays.asList(
                    SpriteShifter.get(
                            createBlockTexture("smart_pipe_1"),
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_1")
                    ),
                    SpriteShifter.get(
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_1"),
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_1")
                    ),
                    SpriteShifter.get(
                            createBlockTexture("smart_pipe_2"),
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_2")
                    ),
                    SpriteShifter.get(
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_2"),
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_2")
                    ),
                    SpriteShifter.get(
                            createBlockTexture("smart_pipe_3"),
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_3")
                    ),
                    SpriteShifter.get(
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_3"),
                            modTexture("block/" + material + "_smart_fluid_pipe/" + material + "_smart_pipe_3")
                    )
            );

            CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(
                    block.getId(),
                    bakedModel -> new SpriteShiftingBakedModel(PipeAttachmentModel.withAO(bakedModel), pipeShifts, pipeConnectedShifts, glassPipeShifts, smartPipeShifts)
            );
            CreateClient.MODEL_SWAPPER.getCustomItemModels().register(
                    block.getId(),
                    bakedModel -> new SpriteShiftingBakedModel(bakedModel, pipeShifts, pipeConnectedShifts, glassPipeShifts, smartPipeShifts)
            );
        }
    }

    private static ResourceLocation createBlockTexture(String path) {
        return new ResourceLocation("create", "block/" + path);
    }

    private static String pipeTexture(String material, boolean connected) {
        String baseName = material.equals("brass") ? "brass_fluid_pipe" : material + "_pipes";
        return "block/" + material + "_fluid_pipe/" + baseName + (connected ? "_connected" : "");
    }

    private static String paintablePipeTexture(String material, boolean connected) {
        return "block/" + material + "_fluid_pipe/" + material + "_pipes" + (connected ? "_connected" : "");
    }

    private static ResourceLocation modTexture(String path) {
        return new ResourceLocation(craftsconstruct.MOD_ID, path);
    }

    private static class SpriteShiftingBakedModel extends BakedModelWrapper<BakedModel> {
        private final List<SpriteShiftEntry> spriteShifts;
        private final List<SpriteShiftEntry> connectedSpriteShifts;
        private final List<SpriteShiftEntry> glassPipeShifts;
        private final List<SpriteShiftEntry> smartPipeShifts;

        private SpriteShiftingBakedModel(BakedModel originalModel, List<SpriteShiftEntry> spriteShifts, List<SpriteShiftEntry> connectedSpriteShifts, List<SpriteShiftEntry> glassPipeShifts, List<SpriteShiftEntry> smartPipeShifts) {
            super(originalModel);
            this.spriteShifts = spriteShifts;
            this.connectedSpriteShifts = connectedSpriteShifts;
            this.glassPipeShifts = glassPipeShifts;
            this.smartPipeShifts = smartPipeShifts;
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
            SpriteShiftEntry shift = getShift(quad, spriteShifts);
            if (shift != null)
                return shift;
            shift = getShift(quad, connectedSpriteShifts);
            if (shift != null)
                return shift;
            shift = getShift(quad, glassPipeShifts);
            if (shift != null)
                return shift;
            return getShift(quad, smartPipeShifts);
        }

        private SpriteShiftEntry getShift(BakedQuad quad, List<SpriteShiftEntry> shifts) {
            for (SpriteShiftEntry shift : shifts) {
                if (shift != null && (quad.getSprite() == shift.getOriginal()
                        || quad.getSprite().contents().name().equals(shift.getOriginalResourceLocation())))
                    return shift;
            }
            return null;
        }
    }
}
