package net.deltav.craftsconstruct.block.create.drain.train;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.content.fluids.transfer.GenericItemEmptying;
import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.foundation.advancement.AdvancementBehaviour;
import com.simibubi.create.foundation.blockEntity.ComparatorUtil;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.fluid.FluidHelper;
import net.deltav.craftsconstruct.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TrainItemDrainBlock extends ItemDrainBlock {
    public TrainItemDrainBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) {
        super.updateEntityAfterFallOn(worldIn, entityIn);
        if (!(entityIn instanceof ItemEntity itemEntity))
            return;
        if (!entityIn.isAlive())
            return;
        if (entityIn.level().isClientSide)
            return;

        DirectBeltInputBehaviour inputBehaviour =
            BlockEntityBehaviour.get(worldIn, entityIn.blockPosition(), DirectBeltInputBehaviour.TYPE);
        if (inputBehaviour == null)
            return;
        Vec3 deltaMovement = entityIn.getDeltaMovement()
            .multiply(1, 0, 1)
            .normalize();
        Direction nearest = Direction.getNearest(deltaMovement.x, deltaMovement.y, deltaMovement.z);
        ItemStack remainder = inputBehaviour.handleInsertion(itemEntity.getItem(), nearest, false);
        itemEntity.setItem(remainder);
        if (remainder.isEmpty())
            itemEntity.discard();
    }

    protected ItemInteractionResult tryExchange(Level worldIn, Player player, InteractionHand handIn, ItemStack heldItem,
                                                ItemDrainBlockEntity be) {
        if (FluidHelper.tryEmptyItemIntoBE(worldIn, player, handIn, heldItem, be))
            return ItemInteractionResult.SUCCESS;
        if (GenericItemEmptying.canItemBeEmptied(worldIn, heldItem))
            return ItemInteractionResult.SUCCESS;
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AllShapes.CASING_13PX.get(Direction.UP);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.hasBlockEntity() || state.getBlock() == newState.getBlock())
            return;
        withBlockEntityDo(worldIn, pos, be -> {
            ItemStack heldItemStack = be.getHeldItemStack();
            if (!heldItemStack.isEmpty())
                Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), heldItemStack);
        });
        worldIn.removeBlockEntity(pos);
    }

    @Override
    public Class<ItemDrainBlockEntity> getBlockEntityClass() {
        return ItemDrainBlockEntity.class;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        AdvancementBehaviour.setPlacedBy(level, pos, placer);
    }

    @Override
    public BlockEntityType<? extends ItemDrainBlockEntity> getBlockEntityType() {
        return ModBlockEntityTypes.TRAIN_ITEM_DRAIN.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.TRAIN_ITEM_DRAIN.get().create(pos, state);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
        return ComparatorUtil.levelOfSmartFluidTank(worldIn, pos);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }
}
