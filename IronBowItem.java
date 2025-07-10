package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class IronBowItem extends BowItem {
    public IronBowItem(Settings settings) {
        super(settings.maxDamage(500));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        ItemStack arrowStack = BowItem.getArrowType(player);
        if (arrowStack.isEmpty() && !player.getAbilities().creativeMode) return;

        int chargeTime = this.getMaxUseTime(stack) - remainingUseTicks;
        float pullProgress = getPullProgress(chargeTime);
        if (pullProgress < 0.1F) return;

        float baseDamage = 4.0F + (pullProgress * 8.0F);
        if (!world.isClient) {
            ArrowItem arrowItem = (ArrowItem) (arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
            PersistentProjectileEntity arrow = arrowItem.createArrow(world, arrowStack, player);
            arrow.setDamage(baseDamage);
            arrow.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, pullProgress * 3.0F, 1.0F);
            if (pullProgress == 1.0F) arrow.setCritical(true);
            world.spawnEntity(arrow);

            if (!player.getAbilities().creativeMode) {
                arrowStack.decrement(1);
                if (arrowStack.isEmpty()) player.getInventory().removeOne(arrowStack);
            }
        }
        stack.damage(1, user, (entity) -> entity.sendToolBreakStatus(user.getActiveHand()));
    }
}
