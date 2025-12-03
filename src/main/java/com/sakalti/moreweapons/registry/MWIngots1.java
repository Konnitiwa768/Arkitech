package com.sakalti.moreweapons.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MWIngots1 {

    // 瑠璃（カブナイト） – 青
    public static final Item KABNITE_INGOT = register("kabnite_ingot");

    // ノーンガイト – 緑
    public static final Item NONGAIT_INGOT = register("nongait_ingot");

    // ヘグチェイト – 黄色
    public static final Item HEGCHEITE_INGOT = register("hegcheite_ingot");

    // ジェンミイト – 赤
    public static final Item JENMIITE_INGOT = register("jenmiite_ingot");

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM,
                new Identifier("moreweapons", name),
                new Item(new Item.Settings())
        );
    }

    public static void registerIngots() {
        // ここは呼び出すだけでOKです。
    }
}
