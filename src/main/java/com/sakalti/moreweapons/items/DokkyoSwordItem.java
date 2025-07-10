package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;
import net.minecraft.entity.damage.DamageSource;

public class DokkyoSwordItem extends SwordItem {
    public DokkyoSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1234; }
            @Override public float getMiningSpeedMultiplier() { return 2.0F; }
            @Override public float getAttackDamage() { return 15.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 10; }
            @Override public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.ROTTEN_FLESH);
            }
        }, 0, -2.4F + 4.0F, settings.group(ItemGroup.COMBAT)); // 攻撃速度1.6に相当
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 1)); // 空腹II（5秒）
                attacker.damage(DamageSource.GENERIC, 1.0F); // 自傷1ダメージ
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
