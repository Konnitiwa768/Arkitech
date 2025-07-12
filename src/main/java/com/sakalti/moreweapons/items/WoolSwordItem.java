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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.math.MathHelper;

public class WoolSwordItem extends SwordItem {
    public WoolSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 120; }
            @Override public float getMiningSpeedMultiplier() { return 4.0F; }
            @Override public float getAttackDamage() { return 3.5F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 24; }
            @Override public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.WHITE_WOOL);
            }
        }, 0, -1.0F, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            // --- NBTデータ取得 ---
            NbtCompound nbt = stack.getOrCreateNbt();
            long currentTime = target.getWorld().getTime(); // 現在のtick時刻
            long lastHitTime = nbt.getLong("last_hit_time");
            int hitCombo = nbt.getInt("hit_combo");

            // --- 連続ヒット判定（3秒=60tick以内） ---
            if (currentTime - lastHitTime <= 60) {
                hitCombo++;
            } else {
                hitCombo = 1;
            }

            // --- 状態保存 ---
            nbt.putLong("last_hit_time", currentTime);
            nbt.putInt("hit_combo", hitCombo);

            // --- 追加ダメージ算出 ---
            float extraDamage = 0.0F;
            if (hitCombo >= 50) {
                extraDamage = 9.0F;
            } else if (hitCombo >= 25) {
                extraDamage = 4.0F;
            } else if (hitCombo >= 15) {
                extraDamage = 2.0F;
            } else if (hitCombo >= 5) {
                extraDamage = 1.0F;
            }

            // --- 弱体化効果 ---
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 1));

            // --- 防御がある敵に追加ダメージ ---
            double armor = target.getAttributeValue(EntityAttributes.GENERIC_ARMOR);
            if (armor >= 1.0 && attacker instanceof PlayerEntity player) {
                target.damage(DamageSource.player(player), 4.0F);
            }

            // --- コンボ追加ダメージ ---
            if (attacker instanceof PlayerEntity player) {
                target.damage(DamageSource.player(player), extraDamage);
            }

            // --- ノックバック ---
            target.takeKnockback(1.5F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }

        return super.postHit(stack, target, attacker);
    }
}
