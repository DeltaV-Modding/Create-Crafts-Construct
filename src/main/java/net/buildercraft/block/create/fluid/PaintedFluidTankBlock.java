package net.buildercraft.block.create.fluid;

import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PaintedFluidTankBlock extends FluidTankBlock {
    private final Supplier<BlockEntityType<? extends FluidTankBlockEntity>> blockEntityType;

    public PaintedFluidTankBlock(BlockBehaviour.Properties properties,
                                 Supplier<BlockEntityType<? extends FluidTankBlockEntity>> blockEntityType) {
        super(properties, false);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public BlockEntityType<? extends FluidTankBlockEntity> getBlockEntityType() {
        return blockEntityType.get();
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide || !(placer instanceof Player player) || player.isShiftKeyDown()) {
            return;
        }

        if (player.isCreative()) {
            placeLargestAvailableSquare(level, pos, state, stack, Integer.MAX_VALUE, false);
            return;
        }

        placeLargestAvailableSquare(level, pos, state, stack, stack.getCount(), true);
    }

    private void placeLargestAvailableSquare(Level level, BlockPos origin, BlockState state, ItemStack stack, int available, boolean consume) {
        if (available >= 8 && tryPlaceSquare(level, origin, state, stack, 3, consume)) {
            return;
        }
        if (available >= 3) {
            tryPlaceSquare(level, origin, state, stack, 2, consume);
        }
    }

    private boolean tryPlaceSquare(Level level, BlockPos origin, BlockState state, ItemStack stack, int size, boolean consume) {
        List<BlockPos> positions = positionsForSquare(origin, size);
        List<BlockPos> missing = new ArrayList<>();
        for (BlockPos target : positions) {
            if (target.equals(origin)) {
                continue;
            }
            if (!level.getBlockState(target).isAir()) {
                return false;
            }
            missing.add(target);
        }

        int extraBlocks = missing.size();
        if (consume && stack.getCount() < extraBlocks) {
            return false;
        }

        for (BlockPos target : missing) {
            level.setBlock(target, state, 3);
        }
        if (consume) {
            stack.shrink(extraBlocks);
        }
        return true;
    }

    private List<BlockPos> positionsForSquare(BlockPos origin, int size) {
        List<BlockPos> positions = new ArrayList<>();
        if (size == 3) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    positions.add(origin.offset(x, 0, z));
                }
            }
            return positions;
        }

        positions.add(origin);
        positions.add(origin.east());
        positions.add(origin.south());
        positions.add(origin.east().south());
        return positions;
    }
}
