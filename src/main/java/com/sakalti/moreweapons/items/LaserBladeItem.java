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
    public LaserBladeItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 1561; // 耐久1000
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 3.0F;
            }

            @Override
            public float getAttackDamage() {
                return 22.2F; // 攻撃力22.2
            }

            @Override
            public int getMiningLevel() {
                return 6;
            }

            @Override
            public int getEnchantability() {
                return 8;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.REDSTONE); // 修理アイテム
            }
        }, 0, -3.4F, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 10, 3)); // 10秒の毒効果
        }
        return super.use(world, user, hand);
    }
}
