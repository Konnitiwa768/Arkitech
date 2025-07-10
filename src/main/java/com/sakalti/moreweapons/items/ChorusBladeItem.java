package com.sakalti.moreweapons.items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ChorusBladeItem extends SwordItem {
    public static final Tier CHORUS_BLADE_TIER = new Tier() {
        @Override
        public int getUses() {
            return 1000; // 耐久値
        }

        @Override
        public float getSpeed() {
            return 1.0F; // 採掘速度
        }

        @Override
        public float getAttackDamageBonus() {
            return 6.95F; // 基本攻撃力
        }

        @Override
        public int getLevel() {
            return 2; // 採掘レベル
        }

        @Override
        public int getEnchantmentValue() {
            return 20; // エンチャント
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.CHORUS_FRUIT); // 修理素材
        }
    };

    public ChorusBladeItem() {
        super(
            CHORUS_BLADE_TIER,
            0, // 追加攻撃力
            -2.2F, // 攻撃速度
            new Item.Properties().tab(CreativeModeTabs.COMBAT)
        );
    }
}
