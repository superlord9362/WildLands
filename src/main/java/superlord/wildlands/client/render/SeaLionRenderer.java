package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.SeaLionModel;
import superlord.wildlands.common.entity.SeaLionEntity;

public class SeaLionRenderer extends MobRenderer<SeaLionEntity, SeaLionModel<SeaLionEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/sea_lion.png");

	public SeaLionRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SeaLionModel<>(), 0.25F);
	}

	@Override
	public ResourceLocation getEntityTexture(SeaLionEntity entity) {
		return TEXTURE;
	}
	
}
