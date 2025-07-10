package com.sakalti.takumin.entity;

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
