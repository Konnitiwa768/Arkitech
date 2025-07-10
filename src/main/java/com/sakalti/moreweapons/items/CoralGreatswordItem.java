package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class CoralGreatswordItem extends SwordItem {
    public CoralGreatswordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1152; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 10.0F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 18; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.TUBE_CORAL_BLOCK); }
        }, 0, -3.0F, settings.group(ItemGroup.COMBAT));
    }
}
