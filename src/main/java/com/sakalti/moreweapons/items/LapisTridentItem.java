package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class LapisTridentItem extends SwordItem {
    public LapisTridentItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 500; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 8.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 15; }
            @Override public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.LAPIS_LAZULI);
            }
        }, 3, -2.8F, settings); // 攻撃速度 = 1.2（→ modifierは-1.2F）
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient() && attacker instanceof PlayerEntity player) {
            // 経験値オーブをスポーンさせる（位置はプレイヤー座標）
            ExperienceOrbEntity orb = new ExperienceOrbEntity(
                target.getWorld(),
                player.getX(),
                player.getY() + 0.5,
                player.getZ(),
                3 // 付与する経験値量
            );
            target.getWorld().spawnEntity(orb);
        }
        return super.postHit(stack, target, attacker);
    }
}
