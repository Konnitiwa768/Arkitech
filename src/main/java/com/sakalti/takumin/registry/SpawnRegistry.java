package com.sakalti.takumin.registry;

import com.sakalti.takumin.entity.OniCreeperEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.SpawnRestriction;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class SpawnRegistry {
    public static void registerSpawns() {
        for (RegistryKey<Biome> key : Registry.BIOME.getKeys()) {
            Identifier id = key.getValue();
            if (id.getNamespace().equals("minecraft") && id.getPath().contains("nether")) {
                Registry.BIOME.getOrEmpty(key).ifPresent(biome -> {
                    biome.getSpawnSettings().addSpawn(SpawnGroup.MONSTER,
                        new SpawnSettings.SpawnEntry(EntityRegistry.ONI_CREEPER, 20, 1, 3));
                });
            }
        }

        SpawnRestriction.register(EntityRegistry.ONI_CREEPER, SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, OniCreeperEntity::canSpawnInDarkness);
    }
}
