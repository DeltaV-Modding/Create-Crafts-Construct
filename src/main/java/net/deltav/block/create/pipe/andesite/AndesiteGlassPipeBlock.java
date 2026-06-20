package net.deltav.block.create.pipe.andesite;

import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.content.fluids.pipes.GlassFluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.StraightPipeBlockEntity;
import net.deltav.block.create.fluid.andesite.*;
import net.deltav.block.create.kinetic.andesite.*;
import net.deltav.block.create.portableInterface.andesite.*;
import net.deltav.block.create.drain.andesite.*;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import net.deltav.registry.ModBlockEntityTypes;
import net.deltav.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Supplier;

public class AndesiteGlassPipeBlock extends GlassFluidPipeBlock {


    public AndesiteGlassPipeBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends StraightPipeBlockEntity> getBlockEntityType() {
        return ModBlockEntityTypes.ANDESITE_GLASS_PIPE.get();
    }

    public BlockState toRegularPipe(LevelAccessor world, BlockPos pos, BlockState state) {
        Direction side = Direction.get(Direction.AxisDirection.POSITIVE, state.getValue(AXIS));
        Map<Direction, BooleanProperty> facingToPropertyMap = FluidPipeBlock.PROPERTY_BY_DIRECTION;
        return ModBlocks.ANDESITE_FLUID_PIPE.get()
                .updateBlockState(ModBlocks.ANDESITE_FLUID_PIPE.getDefaultState()
                        .setValue(facingToPropertyMap.get(side), true)
                        .setValue(facingToPropertyMap.get(side.getOpposite()), true), side, null, world, pos);
    }


    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        if (tryRemoveBracket(context))
            return InteractionResult.SUCCESS;
        BlockState newState;
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidTransportBehaviour.cacheFlows(world, pos);
        newState = this.toRegularPipe(world, pos, state).setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED));
        world.setBlock(pos, newState, Block.UPDATE_ALL);
        FluidTransportBehaviour.loadFlows(world, pos);
        return InteractionResult.SUCCESS;
    }
}