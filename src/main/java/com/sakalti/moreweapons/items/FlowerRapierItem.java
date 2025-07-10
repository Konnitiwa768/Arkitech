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

public class FlowerRapierItem extends SwordItem {
    public FlowerRapierItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1000; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 3.5F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 16; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.FLOWER_POT); }
        }, 0, -1.8F, settings); // 2.4 - 1.8 = 0.6
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20, 1));

            double armor = target.getAttributeValue(EntityAttributes.GENERIC_ARMOR);
            if (armor >= 10.0 && attacker instanceof PlayerEntity player) {
                target.damage(DamageSource.player(player), 4.0F); // 防御力の高い敵に追加ダメージ
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
