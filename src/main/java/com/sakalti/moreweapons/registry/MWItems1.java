package com.sakalti.moreweapons.registry;

import com.sakalti.moreweapons.items.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MWItems1 {

    // 他のクラスからも使えるよう public に
    public static final ItemGroup COMBAT = FabricItemGroupBuilder.create(
            new Identifier("moreweapons", "combat"))
            .icon(() -> new ItemStack(Items.DIAMOND_SWORD))
            .build();

    // 内部クラス：アイテム名とインスタンスを保持
    private static class ItemRegisterInfo {
        String name;
        Item item;

        ItemRegisterInfo(String name, Item item) {
            this.name = name;
            this.item = item;
        }
    }

    // アイテム登録処理
    public static void registerItems() {
        ItemRegisterInfo[] items = new ItemRegisterInfo[] {
            new ItemRegisterInfo("flower_blade", new FlowerBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("wool_sword", new WoolSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("diorite_sword", new DioriteSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("andesite_sword", new AndesiteSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("granite_sword", new GraniteSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("bed_sword", new BedSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("normal_rifle", new NormalRifleItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("dokkyo_sword", new DokkyoSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("hot_shot", new HotShotItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("poison_burn", new PoisonBurnItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("lapis_trident", new LapisTridentItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("cerulean_blade", new CeruleanBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("lunar_blade", new LunarBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("flower_rapier", new FlowerRapierItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("adventurer_greatsword", new AdventurerGreatswordItem()), // 引数なしに変更
            // new ItemRegisterInfo("adventurer_greatsword", new AdventurerGreatswordItem()), // 引数なしに変更した場合はクラス修正必要
            new ItemRegisterInfo("precious_blade", new PreciousBladeItem()), // 引数なしコンストラクタ
            new ItemRegisterInfo("emperor_blade", new EmperorBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("legendary_greatsword", new LegendaryGreatSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("corrupted_blade", new CorruptedBladeItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("iron_bow", new IronBowItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("matchlock", new MatchlockItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("armor_destroyer", new ArmorDestroyerItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("laser_blade", new LaserBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("under_blade", new UnderBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("coral_greatsword", new CoralGreatswordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("bent_diamond_blade", new BentDiamondBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("chorus_blade", new ChorusBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("deepslate_sword", new DeepslateSwordItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("normal_rifle", new NormalRifleItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("shulker_great_sword", new ShulkerGreatSwordItem(new Item.Settings().group(COMBAT))),
            // ToolMaterials.DIAMONDは標準ツール素材。攻撃力3、攻撃速度6.0Fなどのカスタム値を渡す例
            // new ItemRegisterInfo("blood_agate", new BloodAgateItem(ToolMaterials.DIAMOND, 3, 6.0F, new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("diamond_quarterstaff", new DiamondQuarterStaffItem(new Item.Settings().group(COMBAT))),
        };

        for (ItemRegisterInfo info : items) {
            try {
                Registry.register(
                    Registry.ITEM,
                    new Identifier("moreweapons", info.name),
                    info.item
                );
                System.out.println("アイテム " + info.name + " の登録に成功しました。");
            } catch (Exception e) {
                System.err.println("アイテム " + info.name + " の登録に失敗しました: " + e.getMessage());
            }
        }
    }
}
