package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import superlord.wildlands.common.entity.CoconutEntity;

public class CoconutRenderer extends ThrownItemRenderer<CoconutEntity> {

    public CoconutRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }
}