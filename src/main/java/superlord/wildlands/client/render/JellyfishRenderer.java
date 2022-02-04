package superlord.wildlands.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.JellyfishModel;
import superlord.wildlands.common.entity.JellyfishEntity;

public class JellyfishRenderer extends MobRenderer<JellyfishEntity, JellyfishModel<JellyfishEntity>> {

	private static final ResourceLocation JELLYFISH_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/jellyfish/jellyfish.png");
	private static final ResourceLocation BLUE_JELLYFISH_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/jellyfish/blue_jellyfish.png");


	public JellyfishRenderer() {
		super(Minecraft.getInstance().getRenderManager(), new JellyfishModel<>(), 0.75F);
	}

	public ResourceLocation getEntityTexture(JellyfishEntity entity) {
		switch(entity.getJellyfishType()) {
		case 0:
			return BLUE_JELLYFISH_TEXTURE;
		case 1:
			return JELLYFISH_TEXTURE;
		default:
			return JELLYFISH_TEXTURE;
		}
	}

	protected void applyRotations(JellyfishEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		float f = MathHelper.lerp(partialTicks, entityLiving.prevjellyfishPitch, entityLiving.jellyfishPitch);
		float f1 = MathHelper.lerp(partialTicks, entityLiving.prevjellyfishYaw, entityLiving.jellyfishYaw);
		matrixStackIn.translate(0.0D, 0.5D, 0.0D);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
		matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f));
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
		matrixStackIn.translate(0.0D, (double)-1.2F, 0.0D);
	}

	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	protected float handleRotationFloat(JellyfishEntity livingBase, float partialTicks) {
		return MathHelper.lerp(partialTicks, livingBase.lastTentacleAngle, livingBase.tentacleAngle);
	}

}
