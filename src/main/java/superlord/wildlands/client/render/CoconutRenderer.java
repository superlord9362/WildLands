package superlord.wildlands.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import superlord.wildlands.common.entity.CoconutEntity;

public class CoconutRenderer extends SpriteRenderer<CoconutEntity> {

    public CoconutRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
    }
}