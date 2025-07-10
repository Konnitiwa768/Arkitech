package com.sakalti.takumin.entity.goal;

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
