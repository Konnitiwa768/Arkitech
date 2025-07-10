package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class ChorusBladeItem extends SwordItem {
    public ChorusBladeItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 1000; // 耐久値1000
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 1.0F;
            }

            @Override
            public float getAttackDamage() {
                return 6.95F; // 攻撃力5.5
            }

            @Override
            public int getMiningLevel() {
                return 2;
            }

            @Override
            public int getEnchantability() {
                return 20;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.CHORUS_FRUIT); // 修理アイテム: コーラスフルーツ
            }
        }, 0, -2.2F, new Settings().group(ItemGroup.COMBAT));
    }
}
