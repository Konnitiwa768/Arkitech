package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class CorruptedBladeItem extends SwordItem {
    public CorruptedBladeItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 2048;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 2.0F;
            }

            @Override
            public float getAttackDamage() {
                return 10.0F;
            }

            @Override
            public int getMiningLevel() {
                return 4;
            }

            @Override
            public int getEnchantability() {
                return 18;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.NETHER_STAR, Items.SHULKER_SHELL);
            }
        }, 0, -2.7F, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 衰退（Wither）IIIを7秒間付与（7秒=140tick）
        if (attacker instanceof PlayerEntity) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 140, 2)); // 2 = Wither III
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
