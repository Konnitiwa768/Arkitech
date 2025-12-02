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

public class ShulkerSwordItem extends SwordItem {
    public ShulkerSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1328; }
            @Override public float getMiningSpeedMultiplier() { return 3.5F; }
            @Override public float getAttackDamage() { return 12.0F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 28; }
            @Override public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.SHULKER_SHELL);
            }
        }, 1, -2.8F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 120, 1)); // 移動低下II（4秒）
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 120, 4)); // 夜目（8秒）
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
