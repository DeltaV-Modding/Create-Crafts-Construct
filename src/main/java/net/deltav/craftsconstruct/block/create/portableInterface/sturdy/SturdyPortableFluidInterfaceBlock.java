package net.deltav.craftsconstruct.block.create.portableInterface.sturdy;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlockEntity;
import com.simibubi.create.foundation.advancement.AdvancementBehaviour;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;
import net.deltav.craftsconstruct.registry.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SturdyPortableFluidInterfaceBlock extends WrenchableDirectionalBlock implements IBE<PortableStorageInterfaceBlockEntity> {
    public SturdyPortableFluidInterfaceBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean movedByPiston) {
        withBlockEntityDo(level, pos, PortableStorageInterfaceBlockEntity::neighbourChanged);
    }
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        AdvancementBehaviour.setPlacedBy(level, pos, placer);
    }
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getNearestLookingDirection();
        if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
            direction = direction.getOpposite();
        }
        return defaultBlockState().setValue(FACING, direction.getOpposite());
    }
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AllShapes.PORTABLE_STORAGE_INTERFACE.get(state.getValue(FACING));
    }
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return getBlockEntityOptional(level, pos).map(be -> isConnected(be) ? 15 : 0)
            .orElse(0);
    }

    private static boolean isConnected(PortableStorageInterfaceBlockEntity be) {
        try {
            var method = PortableStorageInterfaceBlockEntity.class.getDeclaredMethod("isConnected");
            method.setAccessible(true);
            return (boolean) method.invoke(be);
        } catch (ReflectiveOperationException e) {
            return false;
        }
    }
    public Class<PortableStorageInterfaceBlockEntity> getBlockEntityClass() {
        return PortableStorageInterfaceBlockEntity.class;
    }
    public BlockEntityType<? extends PortableStorageInterfaceBlockEntity> getBlockEntityType() {
        return ModBlockEntityTypes.STURDY_PORTABLE_FLUID_INTERFACE.get();
    }
}
