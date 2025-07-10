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

public class CeruleanBladeItem extends SwordItem {
    public CeruleanBladeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1748; }
            @Override public float getMiningSpeedMultiplier() { return 3.5F; }
            @Override public float getAttackDamage() { return 10.0F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 22; }
            @Override public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.AMETHYST_SHARD);
            }
        }, 1, -2.2F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 1)); // 移動低下II（4秒）
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 160, 0)); // 夜目（8秒）
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
