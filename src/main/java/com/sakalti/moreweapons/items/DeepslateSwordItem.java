package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class DeepslateSwordItem extends SwordItem {
    public DeepslateSwordItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 432; // 耐久値432
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 1.5F;
            }

            @Override
            public float getAttackDamage() {
                return 5.0F; // 攻撃力5
            }

            @Override
            public int getMiningLevel() {
                return 3;
            }

            @Override
            public int getEnchantability() {
                return 15;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.DEEPSLATE); // 修理アイテム: 深層岩
            }
        }, 0, 1.8F, new Settings().group(ItemGroup.COMBAT));
    }
}
