package com.sakalti.takumin.registry;

import com.sakalti.takumin.TakuminMod;
import com.sakalti.takumin.entity.OniCreeperEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    public static EntityType<OniCreeperEntity> ONI_CREEPER;

    public static void registerEntities() {
        ONI_CREEPER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(TakuminMod.MODID, "oni_creeper"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, OniCreeperEntity::new)
                .dimensions(EntityDimensions.fixed(0.6F, 1.7F))
                .trackRangeBlocks(8)
                .trackedUpdateRate(3)
                .build()
        );

        FabricDefaultAttributeRegistry.register(ONI_CREEPER, OniCreeperEntity.createMobAttributes());
    }
}
