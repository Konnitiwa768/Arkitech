package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class CorruptedBladeItem extends SwordItem {
    public CorruptedBladeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1800; }
            @Override public float getMiningSpeedMultiplier() { return 2.0F; }
            @Override public float getAttackDamage() { return 13.0F; }
            @Override public int getMiningLevel() { return 5; }
            @Override public int getEnchantability() { return 12; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.NETHERITE_SCRAP); }
        }, 0, -3.0F, settings.group(ItemGroup.COMBAT));
    }
}
