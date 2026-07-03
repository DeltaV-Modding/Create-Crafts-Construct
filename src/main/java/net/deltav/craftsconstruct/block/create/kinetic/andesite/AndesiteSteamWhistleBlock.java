package net.deltav.craftsconstruct.block.create.kinetic.andesite;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleBlockEntity;
import com.simibubi.create.content.decoration.steamWhistle.WhistleExtenderBlock;
import com.simibubi.create.content.decoration.steamWhistle.WhistleExtenderBlock.WhistleExtenderShape;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.foundation.advancement.AdvancementBehaviour;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class AndesiteSteamWhistleBlock extends WhistleBlock {
    private final Supplier<BlockEntityType<? extends WhistleBlockEntity>> blockEntityType;

    public AndesiteSteamWhistleBlock(BlockBehaviour.Properties properties,
                                     Supplier<BlockEntityType<? extends WhistleBlockEntity>> blockEntityType) {
        super(properties);
        this.blockEntityType = blockEntityType;
    }
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        AdvancementBehaviour.setPlacedBy(level, pos, placer);
    }
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return FluidTankBlock.isTank(level.getBlockState(pos.relative(getAttachedDirection(state))));
    }
    public BlockState getRotatedBlockState(BlockState originalState, Direction targetedFace) {
        return originalState.cycle(SIZE);
    }
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Direction face = context.getClickedFace();
        boolean wall = true;
        if (face.getAxis() == Axis.Y) {
            face = context.getHorizontalDirection().getOpposite();
            wall = false;
        }

        BlockState baseState = super.getStateForPlacement(context);
        if (baseState == null)
            return null;

        BlockState state = baseState.setValue(FACING, face.getOpposite())
                .setValue(POWERED, level.hasNeighborSignal(clickedPos))
                .setValue(WALL, wall);
        return canSurvive(state, level, clickedPos) ? state : null;
    }
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player == null)
            return InteractionResult.PASS;

        if (isWhistleItem(stack)) {
            incrementSize(level, pos);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private static boolean isWhistleItem(ItemStack stack) {
        if (AllBlocks.STEAM_WHISTLE.isIn(stack))
            return true;
        return stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof WhistleBlock;
    }

    public static void incrementSize(LevelAccessor level, BlockPos pos) {
        BlockState base = level.getBlockState(pos);
        if (!base.hasProperty(SIZE))
            return;
        WhistleSize size = base.getValue(SIZE);
        SoundType soundType = base.getSoundType();
        BlockPos currentPos = pos.above();

        for (int i = 1; i <= 6; i++) {
            BlockState blockState = level.getBlockState(currentPos);
            float volume = (soundType.getVolume() + 1.0F) / 2.0F;
            SoundEvent growSound = SoundEvents.NOTE_BLOCK_XYLOPHONE.value();
            SoundEvent hitSound = soundType.getHitSound();

            if (AllBlocks.STEAM_WHISTLE_EXTENSION.has(blockState)) {
                if (blockState.getValue(WhistleExtenderBlock.SHAPE) == WhistleExtenderShape.SINGLE) {
                    level.setBlock(currentPos,
                            blockState.setValue(WhistleExtenderBlock.SHAPE, WhistleExtenderShape.DOUBLE), 3);
                    float pitch = (float) Math.pow(2, -(i * 2) / 12.0);
                    level.playSound(null, currentPos, growSound, SoundSource.BLOCKS, volume / 4f, pitch);
                    level.playSound(null, currentPos, hitSound, SoundSource.BLOCKS, volume, pitch);
                    return;
                }
                currentPos = currentPos.above();
                continue;
            }

            if (!blockState.canBeReplaced())
                return;

            level.setBlock(currentPos, AllBlocks.STEAM_WHISTLE_EXTENSION.getDefaultState().setValue(SIZE, size), 3);
            float pitch = (float) Math.pow(2, -(i * 2 - 1) / 12.0);
            level.playSound(null, currentPos, growSound, SoundSource.BLOCKS, volume / 4f, pitch);
            level.playSound(null, currentPos, hitSound, SoundSource.BLOCKS, volume, pitch);
            return;
        }
    }

    public static void queuePitchUpdate(LevelAccessor level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos);
        if (blockState.getBlock() instanceof AndesiteSteamWhistleBlock whistle && !level.getBlockTicks().hasScheduledTick(pos, whistle))
            level.scheduleTick(pos, whistle, 1);
    }
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        withBlockEntityDo(level, pos, WhistleBlockEntity::updatePitch);
    }
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        FluidTankBlock.updateBoilerState(state, level, pos.relative(getAttachedDirection(state)));
        if (oldState.getBlock() != this || oldState.getValue(SIZE) != state.getValue(SIZE))
            queuePitchUpdate(level, pos);
    }
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        IBE.onRemove(state, level, pos, newState);
        FluidTankBlock.updateBoilerState(state, level, pos.relative(getAttachedDirection(state)));
    }
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (level.isClientSide)
            return;
        boolean previouslyPowered = state.getValue(POWERED);
        if (previouslyPowered != level.hasNeighborSignal(pos))
            level.setBlock(pos, state.cycle(POWERED), Block.UPDATE_CLIENTS);
    }
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level,
                                  BlockPos currentPos, BlockPos facingPos) {
        return getAttachedDirection(state) == facing && !state.canSurvive(level, currentPos)
                ? Blocks.AIR.defaultBlockState()
                : state;
    }
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        WhistleSize size = state.getValue(SIZE);
        if (!state.getValue(WALL))
            return size == WhistleSize.SMALL ? AllShapes.WHISTLE_SMALL_FLOOR
                    : size == WhistleSize.MEDIUM ? AllShapes.WHISTLE_MEDIUM_FLOOR : AllShapes.WHISTLE_LARGE_FLOOR;
        Direction direction = state.getValue(FACING);
        return (size == WhistleSize.SMALL ? AllShapes.WHISTLE_SMALL_WALL
                : size == WhistleSize.MEDIUM ? AllShapes.WHISTLE_MEDIUM_WALL : AllShapes.WHISTLE_LARGE_WALL).get(direction);
    }
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    public static Direction getAttachedDirection(BlockState state) {
        return state.getValue(WALL) ? state.getValue(FACING) : Direction.DOWN;
    }
    public BlockEntityType<? extends WhistleBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }
    public BlockState mirror(BlockState state, Mirror mirror) {
        return mirror == Mirror.NONE ? state : state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
