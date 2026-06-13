package net.deltav.block.create.fluid;

import com.simibubi.create.content.fluids.tank.FluidTankCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class HorizontalFluidTankCTBehaviour extends FluidTankCTBehaviour {

    public HorizontalFluidTankCTBehaviour(CTSpriteShiftEntry side, CTSpriteShiftEntry top, CTSpriteShiftEntry inner) {
        super(side, top, inner);
    }

    private Direction.Axis getAxis(BlockState state) {
        if (state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            return state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
        }
        return Direction.Axis.X;
    }

    private Direction worldToLocal(Direction worldFace, Direction.Axis connectionAxis) {
        if (connectionAxis == Direction.Axis.Y) {
            return worldFace;
        }
        if (worldFace.getAxis() == connectionAxis) {
            return worldFace.getAxisDirection() == Direction.AxisDirection.POSITIVE ? Direction.UP : Direction.DOWN;
        }
        if (worldFace.getAxis() == Direction.Axis.Y) {
            return worldFace.getAxisDirection() == Direction.AxisDirection.POSITIVE ? Direction.SOUTH : Direction.NORTH;
        }
        return worldFace.getAxisDirection() == Direction.AxisDirection.POSITIVE ? Direction.EAST : Direction.WEST;
    }

    private Direction localToWorld(Direction localFace, Direction.Axis connectionAxis) {
        if (connectionAxis == Direction.Axis.Y) {
            return localFace;
        }
        if (localFace.getAxis() == Direction.Axis.Y) {
            return localFace.getAxisDirection() == Direction.AxisDirection.POSITIVE ?
                    (connectionAxis == Direction.Axis.X ? Direction.EAST : Direction.SOUTH) :
                    (connectionAxis == Direction.Axis.X ? Direction.WEST : Direction.NORTH);
        }
        if (localFace.getAxis() == Direction.Axis.Z) {
            return localFace.getAxisDirection() == Direction.AxisDirection.POSITIVE ? Direction.UP : Direction.DOWN;
        }
        Direction.Axis remainingAxis = connectionAxis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
        return Direction.fromAxisAndDirection(remainingAxis, localFace.getAxisDirection());
    }

    @Override
    public CTSpriteShiftEntry getShift(BlockState state, Direction direction, TextureAtlasSprite sprite) {
        Direction.Axis connectionAxis = getAxis(state);
        Direction localDir = worldToLocal(direction, connectionAxis);
        return super.getShift(state, localDir, sprite);
    }

    @Override
    protected Direction getUpDirection(BlockAndTintGetter reader, BlockPos pos, BlockState state, Direction face) {
        Direction.Axis connectionAxis = getAxis(state);
        Direction localFace = worldToLocal(face, connectionAxis);
        Direction localUp = super.getUpDirection(reader, pos, state, localFace);
        return localToWorld(localUp, connectionAxis);
    }

    @Override
    protected Direction getRightDirection(BlockAndTintGetter reader, BlockPos pos, BlockState state, Direction face) {
        Direction.Axis connectionAxis = getAxis(state);
        Direction localFace = worldToLocal(face, connectionAxis);
        Direction localRight = super.getRightDirection(reader, pos, state, localFace);
        return localToWorld(localRight, connectionAxis);
    }

    @Override
    protected boolean reverseUVs(BlockState state, Direction direction) {
        Direction.Axis axis = getAxis(state);
        if (axis == Direction.Axis.X) {
            return direction.getAxisDirection() == Direction.AxisDirection.NEGATIVE && direction.getAxis() != Direction.Axis.X;
        }
        if (axis == Direction.Axis.Z) {
            return direction != Direction.NORTH && direction.getAxisDirection() != Direction.AxisDirection.POSITIVE;
        }
        return super.reverseUVs(state, direction);
    }

    @Override
    protected boolean reverseUVsVertically(BlockState state, Direction direction) {
        Direction.Axis axis = getAxis(state);
        if (axis == Direction.Axis.X) {
            if (direction == Direction.NORTH) {
                return false;
            }
        }
        if (axis == Direction.Axis.Z) {
            if (direction == Direction.WEST) {
                return false;
            }
        }
        return super.reverseUVsVertically(state, direction);
    }
}
