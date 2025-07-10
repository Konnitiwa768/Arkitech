package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class BloodAgateItem {
    public BloodAgateItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 2031; // 耐久値1000
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 3.0F;
            }

            @Override
            public float getAttackDamage() {
                return 7.5F; // 攻撃力7.5
            }

            @Override
            public int getMiningLevel() {
                return 6;
            }

            @Override
            public int getEnchantability() {
                return 10;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.REDSTONE); // 修理アイテム: レッドストーン
            }
        }, 0, -2.15F, new Settings().group(ItemGroup.COMBAT));
    }
}
