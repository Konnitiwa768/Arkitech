package com.sakalti.moreweapons;

import com.sakalti.moreweapons.items.ChorusBladeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod("moreweapons")
public class MoreWeaponsMod {

    public static final String MODID = "moreweapons";

    // DeferredRegisterの作成
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // ChorusBladeItemの登録
    public static final RegistryObject<Item> CHORUS_BLADE = ITEMS.register(
        "chorus_blade",
        () -> new ChorusBladeItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT))
    );

    public MoreWeaponsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);

        // CreativeTabへのアイテム追加イベント（必要なら下記を有効化）
        // modEventBus.addListener(this::addCreative);
    }

    // Creativeタブへの追加（1.19.2では不要だが、カスタムタブを使う場合のみ必要）
    // private void addCreative(CreativeModeTabEvent.BuildContents event) {
    //     if (event.getTab() == CreativeModeTab.TAB_COMBAT) {
    //         event.accept(CHORUS_BLADE);
    //     }
    // }
}
