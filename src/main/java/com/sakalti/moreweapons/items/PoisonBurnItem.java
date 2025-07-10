package com.sakalti.moreweapons.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class PoisonBurnItem extends Item {
    private static final int COOLDOWN_TICKS = 40;
    private static final int MAX_AMMO = 5;

    public PoisonBurnItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.isSneaking()) {
            if (reload(user, stack)) {
                if (!world.isClient) {
                    user.getItemCooldownManager().set(this, 40);
                    user.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 1.0F);
                }
            }
            return TypedActionResult.success(stack, world.isClient());
        }

        if (!stack.hasNbt() || stack.getOrCreateNbt().getInt("ammo") < 2) {
            user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BASS, 0.5F, 0.5F);
            return TypedActionResult.fail(stack);
        }

        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
        if (!world.isClient) {
            // 2発連続で撃つ
            shoot(world, user);
            shoot(world, user);
            decrementAmmo(stack, 2);
        }

        user.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F);
        return TypedActionResult.success(stack, world.isClient());
    }

    private void shoot(World world, PlayerEntity user) {
        Vec3d start = user.getCameraPosVec(1.0F);
        Vec3d direction = user.getRotationVec(1.0F);
        Vec3d end = start.add(direction.multiply(30));

        EntityHitResult hitResult = raycastEntity(world, user, start, end, 1.0F);

        if (hitResult != null) {
            Entity entity = hitResult.getEntity();
            if (entity instanceof LivingEntity target) {
                target.damage(DamageSource.player(user), 25.0F);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1));      // 毒 5秒（100tick）、レベル2
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0));   // 弱体化 5秒（100tick）、レベル1
            }
        }
    }

    private EntityHitResult raycastEntity(World world, PlayerEntity user, Vec3d start, Vec3d end, double radius) {
        Box box = user.getBoundingBox().stretch(user.getRotationVec(1.0F).multiply(30)).expand(radius);
        List<Entity> entities = world.getOtherEntities(user, box, e -> e instanceof LivingEntity && e.isAlive());
        Entity closest = null;
        Vec3d closestPos = null;
        double closestDist = Double.MAX_VALUE;

        for (Entity entity : entities) {
            Box entityBox = entity.getBoundingBox().expand(0.3);
            Optional<Vec3d> optional = entityBox.raycast(start, end);
            if (optional.isPresent()) {
                Vec3d hitPos = optional.get();
                double distance = start.squaredDistanceTo(hitPos);
                if (distance < closestDist) {
                    closest = entity;
                    closestPos = hitPos;
                    closestDist = distance;
                }
            }
        }

        if (closest != null && closestPos != null) {
            return new EntityHitResult(closest, closestPos);
        }

        return null;
    }

    private boolean reload(PlayerEntity player, ItemStack stack) {
        if (!player.isCreative()) {
            boolean hasDiamond = false;
            boolean hasSpiderEye = false;
            PlayerInventory inv = player.getInventory();

            for (int i = 0; i < inv.size(); i++) {
                ItemStack is = inv.getStack(i);
                if (!hasDiamond && is.isOf(Items.DIAMOND)) {
                    is.decrement(1);
                    hasDiamond = true;
                } else if (!hasSpiderEye && is.isOf(Items.SPIDER_EYE)) {
                    is.decrement(1);
                    hasSpiderEye = true;
                }
                if (hasDiamond && hasSpiderEye) break;
            }

            if (!hasDiamond || !hasSpiderEye) return false;
        }

        stack.getOrCreateNbt().putInt("ammo", MAX_AMMO);
        return true;
    }

    private void decrementAmmo(ItemStack stack, int amount) {
        int ammo = stack.getOrCreateNbt().getInt("ammo");
        stack.getOrCreateNbt().putInt("ammo", Math.max(0, ammo - amount));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        int ammo = stack.getOrCreateNbt().getInt("ammo");
        tooltip.add(Text.of("弾数: " + ammo + " / " + MAX_AMMO));
        tooltip.add(Text.of("Shift+右クリックでリロード"));
        tooltip.add(Text.of("25ダメージ、毒と弱体化を付与、2発連続発射"));
    }
}
