package net.deltav.craftsconstruct.block.create.fluid.sturdy;

import com.simibubi.create.content.equipment.symmetryWand.SymmetryWandItem;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.deltav.craftsconstruct.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

public class SturdyFluidTankItem extends BlockItem {
    public SturdyFluidTankItem(Block block, Properties properties) {
        super(block, properties);
    }
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        if (result.consumesAction())
            tryMultiPlace(context);
        return result;
    }
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level level, Player player, ItemStack stack,
                                                 BlockState state) {
        var server = level.getServer();
        if (server == null)
            return false;
        CompoundTag tag = stack.getTagElement("BlockEntityTag");
        if (tag != null) {
            tag.remove("Luminosity");
            tag.remove("Size");
            tag.remove("Height");
            tag.remove("Controller");
            tag.remove("LastKnownPos");
            if (tag.contains("TankContent")) {
                FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(tag.getCompound("TankContent"));
                if (!fluidStack.isEmpty()) {
                    fluidStack.setAmount(Math.min(FluidTankBlockEntity.getCapacityMultiplier(), fluidStack.getAmount()));
                    tag.put("TankContent", fluidStack.writeToNBT(new CompoundTag()));
                }
            }
            BlockEntity.addEntityType(tag, ModBlockEntityTypes.STURDY_FLUID_TANK.get());
        }
        return super.updateCustomBlockEntityTag(pos, level, player, stack, state);
    }

    private void tryMultiPlace(BlockPlaceContext context) {
        Player player = context.getPlayer();
        if (player == null || player.isShiftKeyDown())
            return;
        Direction clickedFace = context.getClickedFace();
        if (!clickedFace.getAxis().isVertical() || SymmetryWandItem.presentInHotbar(player))
            return;
        ItemStack stack = context.getItemInHand();
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockPos tankPos = clickedPos.relative(clickedFace.getOpposite());
        if (!FluidTankBlock.isTank(level.getBlockState(tankPos)))
            return;
        BlockEntityType<? extends FluidTankBlockEntity> type = ModBlockEntityTypes.STURDY_FLUID_TANK.get();
        BlockEntity blockEntity = level.getBlockEntity(tankPos);
        if (!(blockEntity instanceof FluidTankBlockEntity tank) || blockEntity.getType() != type)
            return;
        FluidTankBlockEntity controller = tank.getControllerBE();
        if (controller == null)
            return;
        int width = controller.getWidth();
        if (width == 1)
            return;
        BlockPos targetOrigin = clickedFace == Direction.DOWN ? controller.getBlockPos().below()
            : controller.getBlockPos().above(controller.getHeight());
        if (targetOrigin.getY() != clickedPos.getY())
            return;
        int blocksToPlace = 0;
        for (int x = 0; x < width; x++)
            for (int z = 0; z < width; z++) {
                BlockState targetState = level.getBlockState(targetOrigin.offset(x, 0, z));
                if (FluidTankBlock.isTank(targetState))
                    continue;
                if (!targetState.canBeReplaced())
                    return;
                blocksToPlace++;
            }
        if (!player.isCreative() && stack.getCount() < blocksToPlace)
            return;
        for (int x = 0; x < width; x++)
            for (int z = 0; z < width; z++) {
                BlockPos targetPos = targetOrigin.offset(x, 0, z);
                if (FluidTankBlock.isTank(level.getBlockState(targetPos)))
                    continue;
                player.getPersistentData().putBoolean("SilenceTankSound", true);
                super.place(BlockPlaceContext.at(context, targetPos, clickedFace));
                player.getPersistentData().remove("SilenceTankSound");
            }
    }
}
