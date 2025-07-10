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

public class LegendaryGreatSwordItem extends SwordItem {
    public LegendaryGreatSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 2900; }
            @Override public float getMiningSpeedMultiplier() { return 2.0F; }
            @Override public float getAttackDamage() { return 6.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 10; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.DIAMOND_BLOCK); }
        }, 0, -1.14F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 特殊能力：攻撃対象にウィザーIIIを3秒付与
        if (attacker instanceof PlayerEntity) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 2));
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
