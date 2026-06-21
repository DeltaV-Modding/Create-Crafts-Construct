package net.deltav.craftsconstruct.block.create.sewing;

import java.util.List;
import java.util.Optional;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.nbt.NBTHelper;
import net.deltav.craftsconstruct.item.SewingThreadItem;
import net.deltav.craftsconstruct.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class SewingMachineBlockEntity extends KineticBlockEntity implements Clearable {
    public State state;
    public int timer;
    public float reach;
    protected FilteringBehaviour filtering;

    public enum State {
        WAITING, EXPANDING, RETRACTING
    }

    public SewingMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.state = State.WAITING;
        this.reach = 1;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        filtering = new FilteringBehaviour(this, new SewingMachineFilterSlot()).withPredicate(stack -> stack.is(ModTags.Items.SEWING_PATTERNS));
        behaviours.add(filtering);
    }

    @Override
    public void tick() {
        super.tick();

        if (getSpeed() == 0) {
            if (!level.isClientSide && state != State.WAITING)
                stop();
            return;
        }

        if (timer > 0) {
            timer -= getTimerSpeed();
            return;
        }

        if (level.isClientSide)
            return;

        if (state == State.WAITING) {
            if (canProcess())
                start();
            return;
        }

        if (state == State.EXPANDING) {
            process();
            state = State.RETRACTING;
            timer = 1000;
            sendData();
            return;
        }

        if (state == State.RETRACTING) {
            state = State.WAITING;
            timer = 500;
            sendData();
        }
    }

    public int getTimerSpeed() {
        return (int) (getSpeed() == 0 ? 0 : net.minecraft.util.Mth.clamp(Math.abs(getSpeed() * 2), 8, 512));
    }

    protected void start() {
        state = State.EXPANDING;
        reach = 1;
        timer = 1000;
        sendData();
    }

    protected void stop() {
        state = State.WAITING;
        timer = 0;
        sendData();
    }

    protected boolean canProcess() {
        SpoolBlockEntity spool = getSpool();
        if (spool == null || !spool.hasUsableThread())
            return false;
        DepotBlockEntity depot = getDepot();
        if (depot == null)
            return false;
        ItemStack depotStack = depot.getHeldItem();
        if (depotStack.getCount() != 1)
            return false;
        Optional<SewingRecipe> recipe = SewingRecipe.find(depotStack);
        return recipe.filter(sewingRecipe -> sewingRecipe.matches(depotStack)).isPresent();
    }

    protected void process() {
        SpoolBlockEntity spool = getSpool();
        DepotBlockEntity depot = getDepot();
        if (spool == null || depot == null)
            return;
        ItemStack depotStack = depot.getHeldItem();
        Optional<SewingRecipe> recipe = SewingRecipe.find(depotStack);
        if (recipe.isEmpty() || !spool.consumeThread())
            return;
        depot.setHeldItem(recipe.get().output().copy());
    }

    protected DepotBlockEntity getDepot() {
        BlockEntity blockEntity = level.getBlockEntity(worldPosition.below());
        return blockEntity instanceof DepotBlockEntity depot ? depot : null;
    }

    protected SpoolBlockEntity getSpool() {
        BlockEntity west = level.getBlockEntity(worldPosition.west());
        if (west instanceof SpoolBlockEntity spool)
            return spool;
        BlockEntity east = level.getBlockEntity(worldPosition.east());
        return east instanceof SpoolBlockEntity spool ? spool : null;
    }

    public PartialModel getHandPose() {
        return AllPartialModels.DEPLOYER_HAND_PUNCHING;
    }

    public float getHandOffset(float partialTicks) {
        float progress = 0;
        int timerSpeed = getTimerSpeed();
        if (state == State.EXPANDING)
            progress = 1 - (timer - partialTicks * timerSpeed) / 1000f;
        if (state == State.RETRACTING)
            progress = (timer - partialTicks * timerSpeed) / 1000f;
        return Math.min(net.minecraft.util.Mth.clamp(progress, 0, 1) * (reach + 3 / 16f), 21 / 16f);
    }

    @Override
    protected void read(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket) {
        state = NBTHelper.readEnum(compound, "State", State.class);
        timer = compound.getInt("Timer");
        reach = compound.getFloat("Reach");
        super.read(compound, registries, clientPacket);
    }

    @Override
    protected void write(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket) {
        NBTHelper.writeEnum(compound, "State", state);
        compound.putInt("Timer", timer);
        compound.putFloat("Reach", reach);
        super.write(compound, registries, clientPacket);
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return super.createRenderBoundingBox().inflate(3);
    }

    @Override
    public void clearContent() {
        filtering.setFilter(ItemStack.EMPTY);
    }
}
