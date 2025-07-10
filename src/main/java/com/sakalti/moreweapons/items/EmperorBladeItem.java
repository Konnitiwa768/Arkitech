package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class EmperorBladeItem extends SwordItem {
    public EmperorBladeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1975; }
            @Override public float getMiningSpeedMultiplier() { return 2.0F; }
            @Override public float getAttackDamage() { return 12.5F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 15; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.NETHERITE_INGOT); }
        }, 0, -2.4F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 特殊能力：攻撃対象にウィザーIIIを5秒付与
        if (attacker instanceof PlayerEntity) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 2));
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
