package superlord.wildlands.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
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

	protected void applyRotations(AnchovyEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}

}
