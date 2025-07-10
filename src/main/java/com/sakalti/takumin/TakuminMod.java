package com.sakalti.takumin;

import com.sakalti.takumin.registry.EntityRegistry;
import com.sakalti.takumin.registry.SpawnRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TakuminMod implements ModInitializer {
    public static final String MODID = "takumin";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info("匠MOD 起動中...");

        EntityRegistry.registerEntities();
        SpawnRegistry.registerSpawns();

        LOGGER.info("匠MOD 初期化完了！");
    }
}
