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

public class FlowerBladeItem extends SwordItem {
    public FlowerBladeItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 1000; // 耐久1000
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 1.0F;
            }

            @Override
            public float getAttackDamage() {
                return 6.0F; // 攻撃力6
            }

            @Override
            public int getMiningLevel() {
                return 2;
            }

            @Override
            public int getEnchantability() {
                return 15;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.FLOWER_POT); // 修理アイテム
            }
        }, 0, -2.2F, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20, 1)); // 20秒の毒効果
        }
        return super.use(world, user, hand);
    }
}
