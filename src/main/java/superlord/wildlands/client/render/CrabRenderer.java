package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.CrabModel;
import superlord.wildlands.common.entity.CrabEntity;

public class CrabRenderer extends MobRenderer<CrabEntity, CrabModel<CrabEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/crab/crab.png");

	public CrabRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CrabModel<>(), 0.375F);
	}

	@Override
	public ResourceLocation getEntityTexture(CrabEntity entity) {
		return TEXTURE;
	}
	
}
