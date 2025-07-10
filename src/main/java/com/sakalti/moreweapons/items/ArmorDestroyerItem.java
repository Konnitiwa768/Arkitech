package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

public class ArmorDestroyerItem extends SwordItem {
    public ArmorDestroyerItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 250;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 1.0F;
            }

            @Override
            public float getAttackDamage() {
                return 9.0F; // 攻撃力9
            }

            @Override
            public int getMiningLevel() {
                return 2;
            }

            @Override
            public int getEnchantability() {
                return 5;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.DIAMOND); // 修理アイテム
            }
        }, 0, -3.6F, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 相手の防具の耐久力を追加で64減少
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack armor = target.getEquippedStack(slot);
            if (!armor.isEmpty() && armor.isDamageable()) {
                armor.damage(64, target, (e) -> e.sendEquipmentBreakStatus(slot));
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
