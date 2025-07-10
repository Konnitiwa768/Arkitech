package com.sakalti.moreweapons.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Hand;

public class DiamondQuarterStaffItem extends SwordItem {

    public DiamondQuarterStaffItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int armorValue = 0;
        if (target instanceof PlayerEntity) {
            armorValue = ((PlayerEntity) target).getArmor();
        } else {
            armorValue = 0;
        }

        if (attacker instanceof PlayerEntity playerAttacker) {
            if (armorValue >= 3) {
                target.damage(DamageSource.player(playerAttacker), 7.0F);
            } else {
                target.damage(DamageSource.player(playerAttacker), 8.5F);
            }
            // PlayerEntity なら getMainHand() に近い動作として Hand.MAIN_HAND を明示
            stack.damage(1, playerAttacker, (e) -> e.sendToolBreakStatus(Hand.MAIN_HAND));
        } else {
            if (armorValue >= 3) {
                target.damage(DamageSource.GENERIC, 7.0F);
            } else {
                target.damage(DamageSource.GENERIC, 8.5F);
            }
            // LivingEntity には sendToolBreakStatus も getActiveHand もないので耐久値だけ減らす
            stack.damage(1, attacker, (e) -> {});
        }
        return super.postHit(stack, target, attacker);
    }
}
