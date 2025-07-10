import os

BASE = "src/main/java/com/sakalti/takumin"

FILES = {
    "entity/OniCreeperEntity.java": """package com.sakalti.takumin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.world.World;
import com.sakalti.takumin.entity.goal.OniCreeperFuseGoal;

public class OniCreeperEntity extends CreeperEntity {
    public OniCreeperEntity(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createHostileAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D)
            .add(EntityAttributes.GENERIC_ARMOR, 5.0D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new OniCreeperFuseGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, net.minecraft.entity.player.PlayerEntity.class, 8.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, net.minecraft.entity.player.PlayerEntity.class, true));
    }

    @Override
    protected void explode() {
        this.explosionRadius = 6;
        super.explode();
    }

    @Override
    public float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.5F;
    }
}
""",

    "entity/goal/OniCreeperFuseGoal.java": """package com.sakalti.takumin.entity.goal;

import net.minecraft.entity.ai.goal.FuseGoal;
import com.sakalti.takumin.entity.OniCreeperEntity;

public class OniCreeperFuseGoal extends FuseGoal {
    private final OniCreeperEntity oniCreeper;

    public OniCreeperFuseGoal(OniCreeperEntity oniCreeper) {
        super(oniCreeper);
        this.oniCreeper = oniCreeper;
    }

    @Override
    public boolean canStart() {
        return this.oniCreeper.getTarget() != null && this.oniCreeper.squaredDistanceTo(this.oniCreeper.getTarget()) < 16.0D;
    }
}
""",

    "registry/EntityRegistry.java": """package com.sakalti.takumin.registry;

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
""",

    "registry/SpawnRegistry.java": """package com.sakalti.takumin.registry;

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
""",

    "client/RendererRegistry.java": """package com.sakalti.takumin.client;

import com.sakalti.takumin.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.CreeperEntityRenderer;

public class RendererRegistry implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityRegistry.ONI_CREEPER, CreeperEntityRenderer::new);
    }
}
"""
}


def write_java_file(relative_path, content):
    full_path = os.path.join(BASE, relative_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"[✔] Wrote: {full_path}")


if __name__ == "__main__":
    for path, content in FILES.items():
        write_java_file(path, content)
