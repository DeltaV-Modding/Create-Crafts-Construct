package net.deltav.craftsconstruct.block.create.pipe.brass;

import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.GlassFluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.StraightPipeBlockEntity;
import net.deltav.craftsconstruct.registry.ModBlockEntityTypes;
import net.deltav.craftsconstruct.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.HitResult;

import java.util.Map;

public class BrassGlassPipeBlock extends GlassFluidPipeBlock {


    public BrassGlassPipeBlock(Properties properties) {
        super(properties);
    }
    public BlockEntityType<? extends StraightPipeBlockEntity> getBlockEntityType() {
        return ModBlockEntityTypes.BRASS_GLASS_PIPE.get();
    }

    public BlockState toRegularPipe(LevelAccessor world, BlockPos pos, BlockState state) {
        Direction side = Direction.get(Direction.AxisDirection.POSITIVE, state.getValue(AXIS));
        Map<Direction, BooleanProperty> facingToPropertyMap = FluidPipeBlock.PROPERTY_BY_DIRECTION;
        return ModBlocks.BRASS_FLUID_PIPE.get()
                .updateBlockState(ModBlocks.BRASS_FLUID_PIPE.getDefaultState()
                        .setValue(facingToPropertyMap.get(side), true)
                        .setValue(facingToPropertyMap.get(side.getOpposite()), true), side, null, world, pos);
    }
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
    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos,
                                       Player player) {
        return ModBlocks.BRASS_FLUID_PIPE.asStack();
    }
}