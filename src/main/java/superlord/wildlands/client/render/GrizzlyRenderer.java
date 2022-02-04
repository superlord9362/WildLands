package superlord.wildlands.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.GrizzlyModel;
import superlord.wildlands.client.model.GrizzlySittingModel;
import superlord.wildlands.client.model.SleepingGrizzlyModel;
import superlord.wildlands.common.entity.GrizzlyEntity;

public class GrizzlyRenderer extends MobRenderer<GrizzlyEntity, EntityModel<GrizzlyEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/bear/grizzly.png");
	private static final ResourceLocation SLEEPING_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/bear/grizzly_sleeping.png");
	private static final ResourceLocation HONEY = new ResourceLocation(WildLands.MOD_ID, "textures/entity/bear/grizzly_honey.png");
	private static final GrizzlyModel<GrizzlyEntity> MODEL = new GrizzlyModel<>();
	private static final SleepingGrizzlyModel<GrizzlyEntity> SLEEPING = new SleepingGrizzlyModel<>();
	private static final GrizzlySittingModel<GrizzlyEntity> SITTING = new GrizzlySittingModel<>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GrizzlyRenderer() {
		super(Minecraft.getInstance().getRenderManager(), MODEL, 0.9375F);
		this.addLayer(new HeldItemLayer(this));
	}
	
	public void render(GrizzlyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(entityIn.isSleeping()) {
			entityModel = SLEEPING;
		} else if (entityIn.isSitting()) {
			entityModel = SITTING;
		} else {
			entityModel = MODEL;
		}
		if (entityIn.isChild()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public ResourceLocation getEntityTexture(GrizzlyEntity entity) {
		if (entity.hasHoney()) {
			return HONEY;
		} else if (entity.isSleeping()) {
			return SLEEPING_TEXTURE;
		} else return TEXTURE;
	}

}
