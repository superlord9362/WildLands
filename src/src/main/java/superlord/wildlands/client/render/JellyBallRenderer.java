package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import superlord.wildlands.common.entity.JellyBallEntity;

public class JellyBallRenderer extends ThrownItemRenderer<JellyBallEntity> {

    public JellyBallRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }
}