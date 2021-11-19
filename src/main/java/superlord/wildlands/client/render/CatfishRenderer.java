package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.CatfishModel;
import superlord.wildlands.common.entity.CatfishEntity;

public class CatfishRenderer extends MobRenderer<CatfishEntity, CatfishModel<CatfishEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/catfish/catfish.png");

	public CatfishRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CatfishModel<>(), 0.375F);
	}

	@Override
	public ResourceLocation getEntityTexture(CatfishEntity entity) {
		return TEXTURE;
	}
	
}
