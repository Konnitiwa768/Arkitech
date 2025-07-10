package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

public class BentDiamondBladeItem extends SwordItem {
    public BentDiamondBladeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1561; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 7.0F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 10; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.DIAMOND); }
        }, 0, -2.67F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack armor = target.getEquippedStack(slot);
            if (!armor.isEmpty() && armor.isDamageable()) {
                armor.damage(5, target, (e) -> e.sendEquipmentBreakStatus(slot));
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
