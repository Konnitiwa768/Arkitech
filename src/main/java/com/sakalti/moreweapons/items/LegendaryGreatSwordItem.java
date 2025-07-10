package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class LegendaryGreatSwordItem extends SwordItem {
    public LegendaryGreatSwordItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 2900; // 耐久値2900
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 2.0F;
            }

            @Override
            public float getAttackDamage() {
                return 6.0F; // 攻撃力6
            }

            @Override
            public int getMiningLevel() {
                return 2;
            }

            @Override
            public int getEnchantability() {
                return 10;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.DIAMOND_BLOCK); // 修理アイテム: ダイヤモンドブロック
            }
        }, 0, -1.14F, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 特殊能力：攻撃対象に衰退IIIを3秒付与
        if (attacker instanceof PlayerEntity) {
            World world = attacker.getWorld();
            if (!world.isClient) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 2)); // 継続時間60tick = 3秒
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
