package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

public class LunarBladeItem extends SwordItem {
    public LunarBladeItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1500; }
            @Override public float getMiningSpeedMultiplier() { return 4.0F; }
            @Override public float getAttackDamage() { return 9.5F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 18; }
            @Override public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.NETHER_STAR);
            }
        }, 0, -2.4F, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 1)); // 弱体化II（4秒）

            double armor = target.getAttributeValue(EntityAttributes.GENERIC_ARMOR);
            if (armor >= 10.0 && attacker instanceof PlayerEntity player) {
                target.damage(DamageSource.player(player), 5.0F); // 防御が高い敵に追加ダメージ
            }

            target.takeKnockback(1.5F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ()); // ノックバック
        }
        return super.postHit(stack, target, attacker);
    }
}
