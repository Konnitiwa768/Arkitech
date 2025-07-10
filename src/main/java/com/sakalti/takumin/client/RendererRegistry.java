package com.sakalti.takumin.client;

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
