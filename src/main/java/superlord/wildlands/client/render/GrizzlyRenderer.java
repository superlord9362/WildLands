package superlord.wildlands.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
import superlord.wildlands.client.model.GrizzlyModel;
import superlord.wildlands.client.model.GrizzlySittingModel;
import superlord.wildlands.client.model.SleepingGrizzlyModel;
import superlord.wildlands.common.entity.GrizzlyEntity;

public class GrizzlyRenderer extends MobRenderer<GrizzlyEntity, EntityModel<GrizzlyEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/bear/grizzly.png");
	private static final ResourceLocation SLEEPING_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/bear/grizzly_sleeping.png");
	private static final ResourceLocation HONEY = new ResourceLocation(WildLands.MOD_ID, "textures/entity/bear/grizzly_honey.png");
	private static GrizzlyModel<?> MODEL;
	private static SleepingGrizzlyModel<?> SLEEPING ;
	private static GrizzlySittingModel SITTING;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GrizzlyRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new GrizzlyModel(renderManager.bakeLayer(ClientEvents.GRIZZLY)), 0.9375F);
		MODEL = new GrizzlyModel(renderManager.bakeLayer(ClientEvents.GRIZZLY));
		SLEEPING = new SleepingGrizzlyModel(renderManager.bakeLayer(ClientEvents.GRIZZLY_SLEEPING));
		SITTING = new GrizzlySittingModel(renderManager.bakeLayer(ClientEvents.GRIZZLY_SITTING));
		this.addLayer(new ItemInHandLayer(this));
	}
	
	public void scale(GrizzlyEntity entityIn, PoseStack matrixStackIn, float partialTickTime) {
		if(entityIn.isSleeping()) {
			model = SLEEPING;
		} else if (entityIn.isSitting()) {
			model = SITTING;
		} else {
			model = MODEL;
		}
		if (entityIn.isBaby()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
	}
	
	public ResourceLocation getTextureLocation(GrizzlyEntity entity) {
		if (entity.hasHoney()) {
			return HONEY;
		} else if (entity.isSleeping()) {
			return SLEEPING_TEXTURE;
		} else return TEXTURE;
	}

}
