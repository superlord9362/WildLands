package superlord.wildlands.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.AlligatorModel;
import superlord.wildlands.client.model.AlligatorThreatenModel;
import superlord.wildlands.common.entity.AlligatorEntity;

public class AlligatorRenderer extends MobRenderer<AlligatorEntity, EntityModel<AlligatorEntity>> {

	private static final ResourceLocation NORMAL = new ResourceLocation(WildLands.MOD_ID, "textures/entity/gator/gator_green.png");
	private static final ResourceLocation LIGHT = new ResourceLocation(WildLands.MOD_ID, "textures/entity/gator/gator_light_green.png");
	private static final ResourceLocation DARK = new ResourceLocation(WildLands.MOD_ID, "textures/entity/gator/gator_dark_green.png");
	private static final ResourceLocation ALBINO = new ResourceLocation(WildLands.MOD_ID, "textures/entity/gator/gator_albino.png");
	private static final AlligatorModel<AlligatorEntity> IDLE = new AlligatorModel<>();
	private static final AlligatorThreatenModel<AlligatorEntity> WARNING = new AlligatorThreatenModel<>();

	public AlligatorRenderer() {
		super(Minecraft.getInstance().getRenderManager(), IDLE, 0.9375F);
	}

	public void render(AlligatorEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if (entityIn.isWarning()) {
			entityModel = WARNING;
		}  else {
			entityModel = IDLE;
		}
		if (entityIn.isChild()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public ResourceLocation getEntityTexture(AlligatorEntity entity) {
		if (entity.isLight()) {
			return LIGHT;
		} else if (entity.isDark()) {
			return DARK;
		} else if(entity.isAlbino()) {
			return ALBINO;
		} else {
			return NORMAL;
		}
	}

}
