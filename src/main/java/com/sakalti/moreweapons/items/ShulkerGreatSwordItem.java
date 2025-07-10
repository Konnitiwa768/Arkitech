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

    public ShulkerGreatSwordItem() {
        super(new Settings().group(MoreWeaponsMod.COMBAT).maxDamage(1502));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    // 攻撃力や攻撃速度のカスタムが必要な場合はAttributeModifierを使いましょう

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            int sharpnessLevel = stack.getEnchantments().stream()
                .filter(e -> Enchantments.SHARPNESS.equals(e.getId()))
                .mapToInt(e -> e.getLevel())
                .findFirst()
                .orElse(0);
            if (sharpnessLevel > 0) {
                float finalDamage = 19.5F + (1 + (sharpnessLevel * 1));
                // 実際のダメージ計算は攻撃部分に適用する必要あり
            }
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, net.minecraft.client.item.TooltipContext context) {
        tooltip.add(Text.literal("特性: ダメージ増加エンチャント効果を常に 1 + (1 * レベル) に変更"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
