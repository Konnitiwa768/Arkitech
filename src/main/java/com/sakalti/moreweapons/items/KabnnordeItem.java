package com.sakalti.moreweapons.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class KabnnordeItem extends SwordItem {

    public KabnnordeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override
            public int getDurability() { return 2500; }
            @Override
            public float getMiningSpeedMultiplier() { return 1.0f; }
            @Override
            public float getAttackDamage() { return 16.5f; }  // 実ダメ17.5
            @Override
            public int getMiningLevel() { return 4; }
            @Override
            public int getEnchantability() { return 18; }
            @Override
            public net.minecraft.recipe.Ingredient getRepairIngredient() {
                return net.minecraft.recipe.Ingredient.EMPTY;
            }
        }, 0, -2.28f, settings); 
        // 攻撃速度 = 4 - 2.28 ≒ 0.72
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!world.isClient && entity instanceof PlayerEntity player) {
            if (player.isUsingItem()) {
                // 使用中は後退できない
                player.setVelocity(player.getVelocity().x, player.getVelocity().y, 
                                   Math.max(0, player.getVelocity().z));
            }
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (!attacker.world.isClient && attacker instanceof PlayerEntity player) {
            // プレイヤーの背後に敵がいるか判定
            var nearby = attacker.world.getOtherEntities(attacker,
                    attacker.getBoundingBox().expand(1.5),
                    e -> e instanceof LivingEntity && e != attacker);

            for (var e : nearby) {
                LivingEntity mob = (LivingEntity) e;

                // プレイヤーの背後方向との角度チェック
                float dot = attacker.getRotationVector().dotProduct(
                        mob.getPos().subtract(attacker.getPos()).normalize()
                );

                if (dot < -0.5f) {
                    // 追加ダメージ
                    float extra = 4f + attacker.world.random.nextFloat() * 11f;
                    mob.damage(attacker.getDamageSources().playerAttack(player), extra);
                }
            }
        }

        return super.postHit(stack, target, attacker);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        // 右クリック長押しの「使用状態」は特別な効果は不要
        return super.finishUsing(stack, world, user);
    }
}
