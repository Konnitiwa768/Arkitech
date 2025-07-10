package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public class UnderBladeItem extends SwordItem {
    public UnderBladeItem(Item.Settings settings) {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 1667; // 耐久値：ネザーライトより少し低め
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 1.0F; // 採掘速度：非常に遅め（戦闘用想定）
            }

            @Override
            public float getAttackDamage() {
                return 7.0F; // 基本攻撃力：鉄剣以上、ダイヤ以下
            }

            @Override
            public int getMiningLevel() {
                return 3; // 採掘レベル：ダイヤ相当（3）
            }

            @Override
            public int getEnchantability() {
                return 20; // エンチャント性：高め
            }

            @Override
            public Ingredient getRepairIngredient() {
                // 修理素材：オブシディアン（もとはクダサンゴブロックだった？）
                return Ingredient.ofItems(Items.OBSIDIAN);
            }
        }, 
        0,        // 攻撃力ボーナス：0（ToolMaterialの値そのまま使う）
        2.0F,     // 攻撃速度補正：やや速め
        settings); // アイテム設定
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // 現時点では通常の右クリックと同じ動作。今後特殊能力を追加予定？
        return super.use(world, user, hand);
    }
}
