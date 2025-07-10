package com.sakalti.moreweapons.items;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import com.sakalti.moreweapons.MoreWeaponsMod;
import java.util.List;

public class ShulkerGreatSwordItem extends Item {

    public ShulkerGreatSwordItem(Settings settings) {
        super(settings.maxDamage(1502).group(MoreWeaponsMod.COMBAT));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            // ここは実装に応じて修正してください
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, net.minecraft.client.item.TooltipContext context) {
        tooltip.add(Text.literal("特性: ダメージ増加エンチャント効果を常に 1 + (1 * レベル) に変更"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
