package net.buildercraft.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class FireballCharge extends Item {
    private static final int COOLDOWN = 160;
    private static final double SPAWN_OFFSET = 2.0;
    private static final float EXPLOSION_POWER = 1.0f;

    public FireballCharge(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.getCooldowns().addCooldown(this, COOLDOWN);

        if (!level.isClientSide()) {
            Vec3 lookVec = player.getLookAngle();
            Vec3 spawnPos = calculateSpawnPos(player, lookVec);

            CustomFireball fireball = new CustomFireball(
                    level,
                    player,
                    lookVec.x * 0.5,
                    lookVec.y * 0.5,
                    lookVec.z * 0.5,
                    EXPLOSION_POWER
            );

            fireball.setPos(spawnPos);
            level.addFreshEntity(fireball);

            level.playSound(null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.GHAST_SHOOT,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F);

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private Vec3 calculateSpawnPos(Player player, Vec3 lookVec) {
        return new Vec3(
                player.getX() + lookVec.x * SPAWN_OFFSET,
                player.getEyeY() + lookVec.y * SPAWN_OFFSET - 0.5,
                player.getZ() + lookVec.z * SPAWN_OFFSET
        );
    }

    public static class CustomFireball extends Fireball {
        private final float explosionPower;

        public CustomFireball(Level level,
                              Player owner,
                              double dx, double dy, double dz,
                              float explosionPower) {
            super(EntityType.FIREBALL, level);
            this.explosionPower = explosionPower;
            this.setOwner(owner);
            this.setDeltaMovement(dx, dy, dz);
        }

        @Override
        protected void onHitBlock(BlockHitResult result) {
            super.onHitBlock(result);
            this.explode();
        }

        @Override
        protected void onHitEntity(EntityHitResult result) {
            super.onHitEntity(result);
            this.explode();
        }

        private void explode() {
            if (!this.level().isClientSide) {
                this.level().explode(
                        this,
                        this.level().damageSources().fireball(this, this.getOwner()),
                        null,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        explosionPower,
                        true,
                        Level.ExplosionInteraction.MOB
                );
                this.discard();
            }
        }
    }
}