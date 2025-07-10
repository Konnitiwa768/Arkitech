package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext; // ← セミコロン追加

public class IronBowItem extends BowItem {

    public IronBowItem(Settings settings) {
        super(settings.maxDamage(500)); // 耐久値を500に設定
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) {
            return;
        }

        // 矢を使用するかどうかチェック
        ItemStack arrowStack = BowItem.getArrowType(player); // 修正: getArrowTypeはstaticメソッド
        if (arrowStack.isEmpty() && !player.getAbilities().creativeMode) {
            return;
        }

        // チャージ時間による引き具合の計算
        int chargeTime = this.getMaxUseTime(stack) - remainingUseTicks;
        float pullProgress = getPullProgress(chargeTime);
        if (pullProgress < 0.1F) {
            return; // 引きが弱すぎる場合は発射しない
        }

        // カスタムダメージ計算: 基本ダメージ4.0 + (引き具合 * 8.0)
        float baseDamage = 4.0F + (pullProgress * 8.0F); // 4～12の範囲
        if (!world.isClient) {
            // 矢の作成と発射
            ArrowItem arrowItem = (ArrowItem) (arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
            PersistentProjectileEntity arrow = arrowItem.createArrow(world, arrowStack, player);
            arrow.setDamage(baseDamage); // カスタムダメージを設定
            arrow.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, pullProgress * 3.0F, 1.0F);

            // エフェクトやタグの設定（必要に応じて追加可能）
            if (pullProgress == 1.0F) {
                arrow.setCritical(true); // フルチャージでクリティカル
            }

            // 矢をスポーン
            world.spawnEntity(arrow);

            // 矢の消費（クリエイティブの場合は消費しない）
            if (!player.getAbilities().creativeMode) {
                arrowStack.decrement(1);
                if (arrowStack.isEmpty()) {
                    player.getInventory().removeOne(arrowStack);
                }
            }
        }

        // 弓の耐久値を消費
        stack.damage(1, user, (entity) -> entity.sendToolBreakStatus(user.getActiveHand()));
    }
}
