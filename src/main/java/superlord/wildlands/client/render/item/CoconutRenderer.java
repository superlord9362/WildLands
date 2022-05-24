package superlord.wildlands.client.render.item;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import superlord.wildlands.common.entity.item.Coconut;

public class CoconutRenderer extends ThrownItemRenderer<Coconut> {

    public CoconutRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }
}