package net.deltav.craftsconstruct.block.create.kinetic.sturdy;

import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SturdySteamEngineBlockEntity extends SteamEngineBlockEntity {
    public SturdySteamEngineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public boolean isValid() {
        net.minecraft.core.Direction facing = com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock.getConnectedDirection(getBlockState());
        if (facing == null)
            return false;
        net.minecraft.core.Direction opposite = facing.getOpposite();
        net.minecraft.world.level.Level lvl = getLevel();
        if (lvl == null)
            return false;
        net.minecraft.world.level.block.state.BlockState state = lvl.getBlockState(getBlockPos().relative(opposite));
        if (state.is(com.simibubi.create.AllBlocks.FLUID_TANK.get()))
            return true;
        for (var block : net.deltav.craftsconstruct.registry.ModBlocks.PAINTED_FLUID_TANKS) {
            if (state.is(block.get()))
                return true;
        }
        return false;
    }

    @Override
    public Float getTargetAngle() {
        float angle = 0.0f;
        BlockState state = getBlockState();
        net.minecraft.core.Direction facing = com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock.getFacing(state);
        com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlockEntity shaft = getShaft();
        net.minecraft.core.Direction.Axis facingAxis = facing.getAxis();
        net.minecraft.core.Direction.Axis rotationAxis = net.minecraft.core.Direction.Axis.Y;
        if (shaft == null)
            return null;
        rotationAxis = com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer.getRotationAxisOf(shaft);
        angle = com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer.getAngleForBe(shaft, shaft.getBlockPos(), rotationAxis);
        if (rotationAxis == facingAxis)
            return null;
        if (rotationAxis.isHorizontal()) {
            if ((facingAxis == net.minecraft.core.Direction.Axis.X) == (facing.getAxisDirection() == net.minecraft.core.Direction.AxisDirection.POSITIVE)) {
                angle *= -1.0f;
            }
        }
        if (rotationAxis == net.minecraft.core.Direction.Axis.X && facing == net.minecraft.core.Direction.DOWN) {
            angle *= -1.0f;
        }
        return angle;
    }
}
