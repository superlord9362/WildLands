package superlord.wildlands.client.render;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
import superlord.wildlands.client.model.JellyfishModel;
import superlord.wildlands.common.entity.Jellyfish;

public class JellyfishRenderer extends MobRenderer<Jellyfish, JellyfishModel<Jellyfish>> {

	private static final ResourceLocation JELLYFISH_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/jellyfish/jellyfish.png");
	private static final ResourceLocation BLUE_JELLYFISH_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/jellyfish/blue_jellyfish.png");


	public JellyfishRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new JellyfishModel<>(renderManager.bakeLayer(ClientEvents.JELLYFISH)), 0.75F);
	}

	public ResourceLocation getTextureLocation(Jellyfish entity) {
		switch(entity.getJellyfishType()) {
		case 0:
			return BLUE_JELLYFISH_TEXTURE;
		case 1:
			return JELLYFISH_TEXTURE;
		default:
			return JELLYFISH_TEXTURE;
		}
	}

	protected void setupRotations(Jellyfish entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		float f = Mth.lerp(partialTicks, entityLiving.prevjellyfishPitch, entityLiving.jellyfishPitch);
		float f1 = Mth.lerp(partialTicks, entityLiving.prevjellyfishYaw, entityLiving.jellyfishYaw);
		matrixStackIn.translate(0.0D, 0.5D, 0.0D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(f));
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f1));
		matrixStackIn.translate(0.0D, (double)-1.2F, 0.0D);
	}

	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	protected float getBob(Jellyfish livingBase, float partialTicks) {
		return Mth.lerp(partialTicks, livingBase.lastTentacleAngle, livingBase.tentacleAngle);
	}
	
	@Nullable
	protected RenderType getRenderType(Jellyfish p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
        ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
        return RenderType.entityTranslucent(resourcelocation, false);
	}

}
