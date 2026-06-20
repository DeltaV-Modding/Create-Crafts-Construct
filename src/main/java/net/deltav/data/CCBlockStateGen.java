package net.deltav.data;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.math.Pointing;
import net.deltav.block.create.pipe.andesite.AndesiteFluidPipeBlock;
import net.deltav.block.create.pipe.brass.BrassFluidPipeBlock;
import net.deltav.block.create.pipe.train.TrainFluidPipeBlock;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class CCBlockStateGen {


    // Every person who sees this is going to kill me
    public static <P extends AndesiteFluidPipeBlock> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> pipeAndesite() {
        return (c, p) -> {
            String path = "block/" + c.getName();

            String LU = "lu";
            String RU = "ru";
            String LD = "ld";
            String RD = "rd";
            String LR = "lr";
            String UD = "ud";
            String U = "u";
            String D = "d";
            String L = "l";
            String R = "r";

            List<String> orientations = ImmutableList.of(LU, RU, LD, RD, LR, UD, U, D, L, R);
            Map<String, Pair<Integer, Integer>> uvs = ImmutableMap.<String, Pair<Integer, Integer>>builder()
                    .put(LU, Pair.of(12, 4))
                    .put(RU, Pair.of(8, 4))
                    .put(LD, Pair.of(12, 0))
                    .put(RD, Pair.of(8, 0))
                    .put(LR, Pair.of(4, 8))
                    .put(UD, Pair.of(0, 8))
                    .put(U, Pair.of(4, 4))
                    .put(D, Pair.of(0, 0))
                    .put(L, Pair.of(4, 0))
                    .put(R, Pair.of(0, 4))
                    .build();

            Map<Direction.Axis, ResourceLocation> coreTemplates = new IdentityHashMap<>();
            Map<Pair<String, Direction.Axis>, ModelFile> coreModels = new HashMap<>();

            for (Direction.Axis axis : Iterate.axes)
                coreTemplates.put(axis, ResourceLocation.fromNamespaceAndPath("create", "block/fluid_pipe/core_" + axis.getSerializedName()));

            for (Direction.Axis axis : Iterate.axes) {
                ResourceLocation parent = coreTemplates.get(axis);
                for (String s : orientations) {
                    Pair<String, Direction.Axis> key = Pair.of(s, axis);
                    String modelName = path + "/" + s + "_" + axis.getSerializedName();

                    coreModels.put(key, p.models()
                            .withExistingParent(modelName, parent)
                            .texture("#0", ResourceLocation.fromNamespaceAndPath("crafts_construct", "block/andesite_fluid_pipe"))
                    );
                }
            }

            MultiPartBlockStateBuilder builder = p.getMultipartBuilder(c.get());
            for (Direction.Axis axis : Iterate.axes) {
                putPart(coreModels, builder, axis, LU, true, false, true, false);
                putPart(coreModels, builder, axis, RU, true, false, false, true);
                putPart(coreModels, builder, axis, LD, false, true, true, false);
                putPart(coreModels, builder, axis, RD, false, true, false, true);
                putPart(coreModels, builder, axis, UD, true, true, false, false);
                putPart(coreModels, builder, axis, U, true, false, false, false);
                putPart(coreModels, builder, axis, D, false, true, false, false);
                putPart(coreModels, builder, axis, LR, false, false, true, true);
                putPart(coreModels, builder, axis, L, false, false, true, false);
                putPart(coreModels, builder, axis, R, false, false, false, true);
            }
        };
    }

    public static <P extends BrassFluidPipeBlock> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> pipeBrass() {
        return (c, p) -> {
            String path = "block/" + c.getName();

            String LU = "lu";
            String RU = "ru";
            String LD = "ld";
            String RD = "rd";
            String LR = "lr";
            String UD = "ud";
            String U = "u";
            String D = "d";
            String L = "l";
            String R = "r";

            List<String> orientations = ImmutableList.of(LU, RU, LD, RD, LR, UD, U, D, L, R);
            Map<String, Pair<Integer, Integer>> uvs = ImmutableMap.<String, Pair<Integer, Integer>>builder()
                    .put(LU, Pair.of(12, 4))
                    .put(RU, Pair.of(8, 4))
                    .put(LD, Pair.of(12, 0))
                    .put(RD, Pair.of(8, 0))
                    .put(LR, Pair.of(4, 8))
                    .put(UD, Pair.of(0, 8))
                    .put(U, Pair.of(4, 4))
                    .put(D, Pair.of(0, 0))
                    .put(L, Pair.of(4, 0))
                    .put(R, Pair.of(0, 4))
                    .build();

            Map<Direction.Axis, ResourceLocation> coreTemplates = new IdentityHashMap<>();
            Map<Pair<String, Direction.Axis>, ModelFile> coreModels = new HashMap<>();

            for (Direction.Axis axis : Iterate.axes)
                coreTemplates.put(axis, ResourceLocation.fromNamespaceAndPath("create", "block/fluid_pipe/core_" + axis.getSerializedName()));

            for (Direction.Axis axis : Iterate.axes) {
                ResourceLocation parent = coreTemplates.get(axis);
                for (String s : orientations) {
                    Pair<String, Direction.Axis> key = Pair.of(s, axis);
                    String modelName = path + "/" + s + "_" + axis.getSerializedName();

                    coreModels.put(key, p.models()
                            .withExistingParent(modelName, parent)
                            .texture("#0", ResourceLocation.fromNamespaceAndPath("crafts_construct", "block/brass_fluid_pipe"))
                    );
                }
            }

            MultiPartBlockStateBuilder builder = p.getMultipartBuilder(c.get());
            for (Direction.Axis axis : Iterate.axes) {
                putPart(coreModels, builder, axis, LU, true, false, true, false);
                putPart(coreModels, builder, axis, RU, true, false, false, true);
                putPart(coreModels, builder, axis, LD, false, true, true, false);
                putPart(coreModels, builder, axis, RD, false, true, false, true);
                putPart(coreModels, builder, axis, UD, true, true, false, false);
                putPart(coreModels, builder, axis, U, true, false, false, false);
                putPart(coreModels, builder, axis, D, false, true, false, false);
                putPart(coreModels, builder, axis, LR, false, false, true, true);
                putPart(coreModels, builder, axis, L, false, false, true, false);
                putPart(coreModels, builder, axis, R, false, false, false, true);
            }
        };
    }

    public static <P extends TrainFluidPipeBlock> NonNullBiConsumer<DataGenContext<Block, P>, RegistrateBlockstateProvider> pipeTrain() {
        return (c, p) -> {
            String path = "block/" + c.getName();

            String LU = "lu";
            String RU = "ru";
            String LD = "ld";
            String RD = "rd";
            String LR = "lr";
            String UD = "ud";
            String U = "u";
            String D = "d";
            String L = "l";
            String R = "r";

            List<String> orientations = ImmutableList.of(LU, RU, LD, RD, LR, UD, U, D, L, R);
            Map<String, Pair<Integer, Integer>> uvs = ImmutableMap.<String, Pair<Integer, Integer>>builder()
                    .put(LU, Pair.of(12, 4))
                    .put(RU, Pair.of(8, 4))
                    .put(LD, Pair.of(12, 0))
                    .put(RD, Pair.of(8, 0))
                    .put(LR, Pair.of(4, 8))
                    .put(UD, Pair.of(0, 8))
                    .put(U, Pair.of(4, 4))
                    .put(D, Pair.of(0, 0))
                    .put(L, Pair.of(4, 0))
                    .put(R, Pair.of(0, 4))
                    .build();

            Map<Direction.Axis, ResourceLocation> coreTemplates = new IdentityHashMap<>();
            Map<Pair<String, Direction.Axis>, ModelFile> coreModels = new HashMap<>();

            for (Direction.Axis axis : Iterate.axes)
                coreTemplates.put(axis, ResourceLocation.fromNamespaceAndPath("create", "block/fluid_pipe/core_" + axis.getSerializedName()));

            for (Direction.Axis axis : Iterate.axes) {
                ResourceLocation parent = coreTemplates.get(axis);
                for (String s : orientations) {
                    Pair<String, Direction.Axis> key = Pair.of(s, axis);
                    String modelName = path + "/" + s + "_" + axis.getSerializedName();

                    coreModels.put(key, p.models()
                            .withExistingParent(modelName, parent)
                            .texture("#0", ResourceLocation.fromNamespaceAndPath("crafts_construct", "block/train_fluid_pipe"))
                    );
                }
            }

            MultiPartBlockStateBuilder builder = p.getMultipartBuilder(c.get());
            for (Direction.Axis axis : Iterate.axes) {
                putPart(coreModels, builder, axis, LU, true, false, true, false);
                putPart(coreModels, builder, axis, RU, true, false, false, true);
                putPart(coreModels, builder, axis, LD, false, true, true, false);
                putPart(coreModels, builder, axis, RD, false, true, false, true);
                putPart(coreModels, builder, axis, UD, true, true, false, false);
                putPart(coreModels, builder, axis, U, true, false, false, false);
                putPart(coreModels, builder, axis, D, false, true, false, false);
                putPart(coreModels, builder, axis, LR, false, false, true, true);
                putPart(coreModels, builder, axis, L, false, false, true, false);
                putPart(coreModels, builder, axis, R, false, false, false, true);
            }
        };
    }



    private static void putPart(Map<Pair<String, Direction.Axis>, ModelFile> coreModels, MultiPartBlockStateBuilder builder,
                                Direction.Axis axis, String s, boolean up, boolean down, boolean left, boolean right) {
        Direction positiveAxis = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        Map<Direction, BooleanProperty> propertyMap = FluidPipeBlock.PROPERTY_BY_DIRECTION;

        Direction upD = Pointing.UP.getCombinedDirection(positiveAxis);
        Direction leftD = Pointing.LEFT.getCombinedDirection(positiveAxis);
        Direction rightD = Pointing.RIGHT.getCombinedDirection(positiveAxis);
        Direction downD = Pointing.DOWN.getCombinedDirection(positiveAxis);

        if (axis == Direction.Axis.Y || axis == Direction.Axis.X) {
            leftD = leftD.getOpposite();
            rightD = rightD.getOpposite();
        }

        builder.part()
                .modelFile(coreModels.get(Pair.of(s, axis)))
                .addModel()
                .condition(propertyMap.get(upD), up)
                .condition(propertyMap.get(leftD), left)
                .condition(propertyMap.get(rightD), right)
                .condition(propertyMap.get(downD), down)
                .end();
    }
}
