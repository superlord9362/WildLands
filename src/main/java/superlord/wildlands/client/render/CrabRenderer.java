package superlord.wildlands.client.render;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
import superlord.wildlands.client.model.CrabModel;
import superlord.wildlands.common.entity.CrabEntity;

public class CrabRenderer extends MobRenderer<CrabEntity, EntityModel<CrabEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/crab/crab.png");
	private static final ResourceLocation FIGHTER = new ResourceLocation(WildLands.MOD_ID, "textures/entity/crab/crab_fighter.png");

	public CrabRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new CrabModel(renderManagerIn.bakeLayer(ClientEvents.CRAB)), 0.375F);
	}

	@Override
	public ResourceLocation getTextureLocation(CrabEntity entity) {
		if (entity.isFighter()) {
			return FIGHTER;
		} else {
			return TEXTURE;
		}
	}
	
}
