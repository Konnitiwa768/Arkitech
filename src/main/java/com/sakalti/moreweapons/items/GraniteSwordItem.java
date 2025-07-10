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

public class GraniteSwordItem extends SwordItem {
    public GraniteSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 154; }
            @Override public float getMiningSpeedMultiplier() { return 4.0F; }
            @Override public float getAttackDamage() { return 6.0F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 5; }
            @Override public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.NETHER_STAR);
            }
        }, 0, -2.4F, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 10, 1)); // 弱体化II（0.5秒）

            double armor = target.getAttributeValue(EntityAttributes.GENERIC_ARMOR);
            if (armor >= 10.0 && attacker instanceof PlayerEntity player) {
                target.damage(DamageSource.player(player), 2.0F); // 防御が高い敵に追加ダメージ
            }

            target.takeKnockback(0.5F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ()); // ノックバック
        }
        return super.postHit(stack, target, attacker);
    }
}
