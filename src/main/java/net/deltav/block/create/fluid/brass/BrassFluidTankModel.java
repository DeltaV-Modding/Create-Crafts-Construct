package net.deltav.block.create.fluid.brass;

import net.deltav.block.create.fluid.brass.*;
import net.deltav.block.create.kinetic.brass.*;
import net.deltav.block.create.pipe.brass.*;
import net.deltav.block.create.portableInterface.brass.*;
import net.deltav.block.create.drain.brass.*;
import net.deltav.block.create.fluid.HorizontalFluidTankCTBehaviour;
import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.foundation.block.connected.CTModel;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.content.fluids.tank.FluidTankCTBehaviour;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelData.Builder;
import net.neoforged.neoforge.client.model.data.ModelProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BrassFluidTankModel extends CTModel {
    protected static final ModelProperty<CullData> CULL_PROPERTY = new ModelProperty<>();

    public BrassFluidTankModel(BakedModel originalModel, CTSpriteShiftEntry side, CTSpriteShiftEntry top, CTSpriteShiftEntry inner) {
        this(originalModel, side, top, inner, false);
    }

    public BrassFluidTankModel(BakedModel originalModel, CTSpriteShiftEntry side, CTSpriteShiftEntry top, CTSpriteShiftEntry inner, boolean horizontal) {
        super(originalModel, horizontal ? 
                new HorizontalFluidTankCTBehaviour(side, top, inner) : 
                new FluidTankCTBehaviour(side, top, inner));
    }

    @Override
    protected ModelData.Builder gatherModelData(Builder builder, BlockAndTintGetter world, BlockPos pos, BlockState state, ModelData blockEntityData) {
        super.gatherModelData(builder, world, pos, state, blockEntityData);
        CullData cullData = new CullData();

        Direction.Axis axis = Direction.Axis.Y;
        if (state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
        }

        for (Direction d : Direction.values()) {
            if (d.getAxis() == axis) {
                // Do not cull along the main connection axis (handled by model shape)
                continue;
            }
            cullData.setCulled(d, ConnectivityHandler.isConnected(world, pos, pos.relative(d)));
        }

        return builder.with(CULL_PROPERTY, cullData);
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType) {
        if (side != null)
            return Collections.emptyList();

        List<BakedQuad> quads = new ArrayList<>();
        for (Direction d : Direction.values()) {
            if (extraData.has(CULL_PROPERTY) && extraData.get(CULL_PROPERTY).isCulled(d))
                continue;
            quads.addAll(super.getQuads(state, d, rand, extraData, renderType));
        }
        quads.addAll(super.getQuads(state, null, rand, extraData, renderType));
        return quads;
    }

    private static class CullData {
        private final boolean[] culledFaces = new boolean[6];

        public CullData() {
            Arrays.fill(culledFaces, false);
        }

        void setCulled(Direction face, boolean cull) {
            culledFaces[face.ordinal()] = cull;
        }

        boolean isCulled(Direction face) {
            return culledFaces[face.ordinal()];
        }
    }
}