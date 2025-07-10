package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

public class AndesiteSwordItem extends SwordItem {
    public AndesiteSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 97; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 7.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 23; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.ANDESITE); }
        }, 0, -3.0F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack armor = target.getEquippedStack(slot);
            if (!armor.isEmpty() && armor.isDamageable()) {
                armor.damage(6, target, (e) -> e.sendEquipmentBreakStatus(slot));
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
