package com.sakalti.moreweapons.items;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.entity.effect.StatusEffectInstance;

public class LaserBladeItem extends SwordItem {
    public LaserBladeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1561; }
            @Override public float getMiningSpeedMultiplier() { return 3.0F; }
            @Override public float getAttackDamage() { return 22.2F; }
            @Override public int getMiningLevel() { return 6; }
            @Override public int getEnchantability() { return 8; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.REDSTONE); }
        }, 0, -3.4F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 10, 3));
        }
        return super.use(world, user, hand);
    }
}
