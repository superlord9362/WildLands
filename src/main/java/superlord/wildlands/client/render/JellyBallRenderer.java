package superlord.wildlands.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import superlord.wildlands.common.entity.JellyBallEntity;

public class JellyBallRenderer extends SpriteRenderer<JellyBallEntity> {

    public JellyBallRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
    }
}