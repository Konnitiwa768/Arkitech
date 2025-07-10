package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

public class AdventurerGreatswordItem extends SwordItem {
    public AdventurerGreatswordItem() {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 2032; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 14.0F; }
            @Override public int getMiningLevel() { return 4; }
            @Override public int getEnchantability() { return 15; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.NETHERITE_INGOT); }
        }, 0, -3.0F, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        float missingHealth = target.getMaxHealth() - target.getHealth();
        float extra = Math.min(6.0F, missingHealth * 0.25F); // 体力が減るほど追加ダメージ（最大+6）
        // 安全のためPlayerEntityかどうかチェックする
        if (attacker instanceof PlayerEntity player) {
            target.damage(target.getDamageSources().playerAttack(player), extra);
        }
        return super.postHit(stack, target, attacker);
    }
}
