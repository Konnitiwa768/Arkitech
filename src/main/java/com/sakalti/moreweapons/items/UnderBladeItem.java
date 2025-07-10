package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class UnderBladeItem extends SwordItem {
    public UnderBladeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 1667;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 1.0F;
            }

            @Override
            public float getAttackDamage() {
                return 7.0F;
            }

            @Override
            public int getMiningLevel() {
                return 3;
            }

            @Override
            public int getEnchantability() {
                return 20;
            }

            @Override
            public Ingredient getRepairIngredient() {
                // クダサンゴブロック
                return Ingredient.ofItems(Items.TUBE_CORAL_BLOCK);
            }
        }, 0, 2.0F, settings);
    }

    @Override
    public ActionResult<ItemStack> use(World world, net.minecraft.entity.player.PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
