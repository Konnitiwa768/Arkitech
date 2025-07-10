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

public class PreciousBladeItem extends SwordItem {
    public PreciousBladeItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 2500; // 任意の耐久値
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 3.0F;
            }

            @Override
            public float getAttackDamage() {
                return 15.0F; // ダメージ
            }

            @Override
            public int getMiningLevel() {
                return 4;
            }

            @Override
            public int getEnchantability() {
                return 25;
            }

            @Override
            public Ingredient getRepairIngredient() {
                // 金・ダイヤ・ネザライト・エメラルドで修理可能
                return Ingredient.ofItems(
                    Items.GOLD_INGOT, Items.DIAMOND, Items.NETHERITE_INGOT, Items.EMERALD
                );
            }
        }, 0, -3.2F, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 特殊能力例：攻撃時、3秒間Speed IIを付与
        if (attacker instanceof PlayerEntity) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                ((PlayerEntity)attacker).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 1));
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
