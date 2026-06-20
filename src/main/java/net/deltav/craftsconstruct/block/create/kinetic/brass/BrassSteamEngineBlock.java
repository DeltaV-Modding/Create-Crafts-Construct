package net.deltav.craftsconstruct.block.create.kinetic.brass;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import com.simibubi.create.foundation.advancement.AdvancementBehaviour;
import com.simibubi.create.foundation.utility.BlockHelper;
import net.createmod.catnip.data.Couple;
import net.createmod.catnip.placement.IPlacementHelper;
import net.createmod.catnip.placement.PlacementHelpers;
import net.createmod.catnip.placement.PlacementOffset;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

public class BrassSteamEngineBlock extends SteamEngineBlock {
    private static final int placementHelperId = PlacementHelpers.register(new PlacementHelper());
    private final Supplier<BlockEntityType<? extends SteamEngineBlockEntity>> blockEntityType;

    public BrassSteamEngineBlock(BlockBehaviour.Properties properties,
                                 Supplier<BlockEntityType<? extends SteamEngineBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        AdvancementBehaviour.setPlacedBy(level, pos, placer);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canAttach(level, pos, getConnectedDirection(state).getOpposite());
    }

    public static boolean canAttach(LevelReader reader, BlockPos pos, Direction direction) {
        return reader.getBlockState(pos.relative(direction)).getBlock() instanceof FluidTankBlock;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        IPlacementHelper placementHelper = PlacementHelpers.get(placementHelperId);
        if (placementHelper.matchesItem(stack))
            return placementHelper.getOffset(player, level, state, pos, hitResult)
                    .placeInWorld(level, (BlockItem) stack.getItem(), player, hand, hitResult);
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState, LevelAccessor world,
                                  BlockPos pos, BlockPos neighbourPos) {
        if (state.getValue(WATERLOGGED))
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        return state;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        FluidTankBlock.updateBoilerState(state, level, pos.relative(getFacing(state).getOpposite()));
        BlockPos shaftPos = getShaftPos(state, pos);
        BlockState shaftState = level.getBlockState(shaftPos);
        if (isShaftValid(state, shaftState))
            level.setBlock(shaftPos, PoweredShaftBlock.getEquivalent(shaftState), Block.UPDATE_ALL);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasBlockEntity() && (!state.is(newState.getBlock()) || !newState.hasBlockEntity()))
            level.removeBlockEntity(pos);
        FluidTankBlock.updateBoilerState(state, level, pos.relative(getFacing(state).getOpposite()));
        BlockPos shaftPos = getShaftPos(state, pos);
        BlockState shaftState = level.getBlockState(shaftPos);
        if (AllBlocks.POWERED_SHAFT.has(shaftState))
            level.scheduleTick(shaftPos, shaftState.getBlock(), 1);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        AttachFace face = state.getValue(FACE);
        Direction direction = state.getValue(FACING);
        return face == AttachFace.CEILING ? AllShapes.STEAM_ENGINE_CEILING.get(direction.getAxis())
                : face == AttachFace.FLOOR ? AllShapes.STEAM_ENGINE.get(direction.getAxis())
                : AllShapes.STEAM_ENGINE_WALL.get(direction);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = level.getFluidState(pos);
        BlockState state = super.getStateForPlacement(context);
        return state == null ? null : state.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    public static Direction getFacing(BlockState sideState) {
        return getConnectedDirection(sideState);
    }

    public static BlockPos getShaftPos(BlockState sideState, BlockPos pos) {
        return pos.relative(getConnectedDirection(sideState), 2);
    }

    public static boolean isShaftValid(BlockState state, BlockState shaft) {
        return (AllBlocks.SHAFT.has(shaft) || AllBlocks.POWERED_SHAFT.has(shaft))
                && shaft.getValue(ShaftBlock.AXIS) != getFacing(state).getAxis();
    }

    @Override
    public BlockEntityType<? extends SteamEngineBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }

    private static class PlacementHelper implements IPlacementHelper {
        @Override
        public Predicate<ItemStack> getItemPredicate() {
            return AllBlocks.SHAFT::isIn;
        }

        @Override
        public Predicate<BlockState> getStatePredicate() {
            return state -> state.getBlock() instanceof BrassSteamEngineBlock;
        }

        @Override
        public PlacementOffset getOffset(Player player, Level world, BlockState state, BlockPos pos, BlockHitResult ray) {
            BlockPos shaftPos = BrassSteamEngineBlock.getShaftPos(state, pos);
            BlockState shaft = AllBlocks.SHAFT.getDefaultState();
            for (Direction direction : Direction.orderedByNearest(player)) {
                shaft = shaft.setValue(ShaftBlock.AXIS, direction.getAxis());
                if (isShaftValid(state, shaft))
                    break;
            }

            BlockState newState = world.getBlockState(shaftPos);
            if (!newState.canBeReplaced())
                return PlacementOffset.fail();

            Axis axis = shaft.getValue(ShaftBlock.AXIS);
            return PlacementOffset.success(shaftPos,
                    s -> BlockHelper.copyProperties(s,
                                    (world.isClientSide ? AllBlocks.SHAFT : AllBlocks.POWERED_SHAFT).getDefaultState())
                            .setValue(PoweredShaftBlock.AXIS, axis));
        }
    }

    public static Couple<Integer> getSpeedRange() {
        return Couple.create(16, 64);
    }

    public static Direction getConnectedDirection(BlockState state) {
        return FaceAttachedHorizontalDirectionalBlock.getConnectedDirection(state);
    }
}
