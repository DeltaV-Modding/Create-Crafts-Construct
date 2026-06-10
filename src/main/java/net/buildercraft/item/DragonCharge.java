package net.buildercraft.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DragonCharge extends Item {
    public DragonCharge(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.getCooldowns().addCooldown(this, 160);

        if (!level.isClientSide()) {
            Vec3 lookVec = player.getLookAngle();

            DragonFireball fireball = new DragonFireball(
                    EntityType.DRAGON_FIREBALL,
                    level
            );

            fireball.setPos(
                    player.getX() + lookVec.x * 1.5,
                    player.getEyeY() + lookVec.y * 1.5 - 0.5,
                    player.getZ() + lookVec.z * 1.5
            );

            fireball.shoot(lookVec.x, lookVec.y, lookVec.z, 0.1F, 0.0F);
            fireball.setOwner(player);

            level.addFreshEntity(fireball);

            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.FIRECHARGE_USE,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}