package superlord.wildlands.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.FrogModel;
import superlord.wildlands.client.model.TadpoleModel;
import superlord.wildlands.common.entity.FrogEntity;

public class FrogRenderer extends MobRenderer<FrogEntity, EntityModel<FrogEntity>> {

	private static final ResourceLocation FROG_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/frog/frog.png");
	private static final ResourceLocation TADPOLE_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/frog/tadpole.png");
	private static final FrogModel FROG = new FrogModel();
	private static final TadpoleModel<FrogEntity> TADPOLE = new TadpoleModel<>();

	public FrogRenderer() {
		super(Minecraft.getInstance().getRenderManager(), FROG, 0.75F);
	}

	public void render(FrogEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(entityIn.isChild()) {
			entityModel = TADPOLE;
		}  else {
			entityModel = FROG;
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public ResourceLocation getEntityTexture(FrogEntity entity) {
		if (entity.isChild()) {
			return TADPOLE_TEXTURE;
		} else {
			return FROG_TEXTURE;
		}
	}

}
