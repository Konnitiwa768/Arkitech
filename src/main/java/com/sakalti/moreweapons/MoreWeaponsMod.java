package com.sakalti.moreweapons;

import com.sakalti.moreweapons.registry.MWItems1;
import net.fabricmc.api.ModInitializer;

public class MoreWeaponsMod implements ModInitializer {
    @Override
    public void onInitialize() {
        MWItems1.registerItems(); // アイテム登録呼び出し
    }
}
