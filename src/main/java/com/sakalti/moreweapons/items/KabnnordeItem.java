package com.sakalti.moreweapons.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class KabnnordeItem extends SwordItem {

    public KabnnordeItem(Item.Settings settings) {
        super(new SimpleMaterial(), 0, -2.28f, settings);
    }

    public static class SimpleMaterial implements ToolMaterial {
        @Override public int getDurability() { return 2500; }
        @Override public float getMiningSpeedMultiplier() { return 1.0f; }
        @Override public float getAttackDamage() { return 16.5f; }
        @Override public int getMiningLevel() { return 4; }
        @Override public int getEnchantability() { return 18; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player) {
            if (player.isUsingItem()) {
                Vec3d v = player.getVelocity();
                player.setVelocity(v.x, v.y, Math.max(v.z, 0));
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (!attacker.world.isClient && attacker instanceof PlayerEntity player) {

            // ▼ ランダム後退距離（3〜5）
            float back = 3.0f + attacker.world.random.nextFloat() * 2.0f;

            // ▼ 後退方向（視線の逆）
            Vec3d backDir = attacker.getRotationVector().normalize().multiply(-1);

            // ▼ レイキャストでブロック判定
            Vec3d start = attacker.getPos().add(0, attacker.getStandingEyeHeight(), 0);
            Vec3d end = start.add(backDir.multiply(back));

            HitResult result = attacker.world.raycast(new RaycastContext(
                    start,
                    end,
                    RaycastContext.ShapeType.COLLIDER,
                    RaycastContext.FluidHandling.NONE,
                    attacker
            ));

            double moveDist = back;

            // 衝突した場合 → 衝突地点までの距離に変更
            if (result.getType() == HitResult.Type.BLOCK) {
                double hitDist = result.getPos().distanceTo(start);

                // めり込み防止（少し手前で止まる）
                moveDist = Math.max(0.1, hitDist - 0.3);
            }

            // ▼ 移動ベクトル
            Vec3d finalMove = backDir.multiply(moveDist);

            // ▼ 後退実行（埋まらない）
            attacker.setPosition(
                    attacker.getX() + finalMove.x,
                    attacker.getY(),
                    attacker.getZ() + finalMove.z
            );

            // ▼ 背後の敵に追加ダメージ
            var nearby = attacker.world.getOtherEntities(attacker,
                    attacker.getBoundingBox().expand(1.5),
                    e -> e instanceof LivingEntity && e != attacker);

            Vec3d look = attacker.getRotationVector().normalize();

            for (var e : nearby) {
                LivingEntity mob = (LivingEntity) e;
                Vec3d dir = mob.getPos().subtract(attacker.getPos()).normalize();

                float dot = (float) look.dotProduct(dir);

                if (dot < -0.5f) {
                    float extra = 4f + attacker.world.random.nextFloat() * 11f;
                    mob.damage(DamageSource.player(player), extra);
                }
            }
        }

        return super.postHit(stack, target, attacker);
    }
}
