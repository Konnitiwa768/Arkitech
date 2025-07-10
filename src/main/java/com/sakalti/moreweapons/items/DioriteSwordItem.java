package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

public class DioriteSwordItem extends SwordItem {
    public DioriteSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 168; }
            @Override public float getMiningSpeedMultiplier() { return 3.0F; }
            @Override public float getAttackDamage() { return 3.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 24; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.DIORITE); }
        }, 0, -1.0F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack armor = target.getEquippedStack(slot);
            if (!armor.isEmpty() && armor.isDamageable()) {
                armor.damage(1, target, (e) -> e.sendEquipmentBreakStatus(slot));
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
