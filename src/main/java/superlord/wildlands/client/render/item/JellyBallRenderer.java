package superlord.wildlands.client.render.item;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import superlord.wildlands.common.entity.item.JellyBall;

public class JellyBallRenderer extends ThrownItemRenderer<JellyBall> {

    public JellyBallRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }
}