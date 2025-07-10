package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class CoralGreatswordItem extends SwordItem {
    public CoralGreatswordItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 1152; // 耐久値1347
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 1.0F;
            }

            @Override
            public float getAttackDamage() {
                return 10.0F; // 攻撃力10
            }

            @Override
            public int getMiningLevel() {
                return 3;
            }

            @Override
            public int getEnchantability() {
                return 18;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.TUBE_CORAL_BLOCK); // 修理アイテム: 珊瑚ブロック
            }
        }, 0, -3.0F, new Settings().group(ItemGroup.COMBAT));
    }
}
