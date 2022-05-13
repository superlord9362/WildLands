package superlord.wildlands.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
import superlord.wildlands.client.model.AnchovyModel;
import superlord.wildlands.common.entity.AnchovyEntity;

public class AnchovyRenderer extends MobRenderer<AnchovyEntity, AnchovyModel<AnchovyEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/fish/anchovy.png");

	public AnchovyRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new AnchovyModel<>(renderManagerIn.bakeLayer(ClientEvents.ANCHOVY)), 0.25F);
	}

	@Override
	public ResourceLocation getTextureLocation(AnchovyEntity entity) {
		return TEXTURE;
	}

	protected void setupRotations(AnchovyEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
			matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}

}
