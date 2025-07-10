package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

public class FlowerBladeItem extends SwordItem {
    public FlowerBladeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1000; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 6.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 15; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.FLOWER_POT); }
        }, 0, -2.2F, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20, 1));
        }
        return super.postHit(stack, target, attacker);
    }
}
