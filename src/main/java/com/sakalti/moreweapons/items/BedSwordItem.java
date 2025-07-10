package com.sakalti.moreweapons.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedSwordItem extends SwordItem {

    public BedSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 250; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 6.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 10; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.WHITE_BED); }
        }, 0, -2.4F, settings.group(ItemGroup.COMBAT));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack armor = target.getEquippedStack(slot);
            if (!armor.isEmpty() && armor.isDamageable()) {
                armor.damage(6, target, (e) -> e.sendEquipmentBreakStatus(slot));
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user instanceof ServerPlayerEntity serverPlayer) {
            BlockPos pos = serverPlayer.getBlockPos();
            serverPlayer.setSpawnPoint(world.getRegistryKey(), pos, 0.0F, true, false);
            serverPlayer.sendMessage(Text.literal("リスポーン地点を設定しました: " + pos.toShortString()), true);
            serverPlayer.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1.0F, 1.0F);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return super.useOnEntity(stack, user, entity, hand);
    }
}
