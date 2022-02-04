package superlord.wildlands.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
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
	
	public void render(SeaLionEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if (entityIn.isChild()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(SeaLionEntity entity) {
		return TEXTURE;
	}
	
}
