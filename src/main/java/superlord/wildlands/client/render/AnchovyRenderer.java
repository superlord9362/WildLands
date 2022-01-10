package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.AnchovyModel;
import superlord.wildlands.common.entity.AnchovyEntity;

public class AnchovyRenderer extends MobRenderer<AnchovyEntity, AnchovyModel<AnchovyEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/fish/anchovy.png");

	public AnchovyRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new AnchovyModel<>(), 0.25F);
	}

	@Override
	public ResourceLocation getEntityTexture(AnchovyEntity entity) {
		return TEXTURE;
	}
	
}
