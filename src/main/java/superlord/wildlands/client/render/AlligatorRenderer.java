package superlord.wildlands.client.render;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.AlligatorModel;
import superlord.wildlands.client.model.AlligatorThreatenModel;
import superlord.wildlands.common.entity.AlligatorEntity;

public class AlligatorRenderer extends MobRenderer<AlligatorEntity, EntityModel<AlligatorEntity>> {

    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
    	hashMap.put(0, new ResourceLocation(WildLands.MOD_ID, "textures/entity/gator/gator_green.png"));
    	hashMap.put(1, new ResourceLocation(WildLands.MOD_ID, "textures/entity/gator/gator_light_green.png"));
    	hashMap.put(2, new ResourceLocation(WildLands.MOD_ID, "textures/entity/gator/gator_dark_green.png"));
    	hashMap.put(3, new ResourceLocation(WildLands.MOD_ID, "textures/entity/gator/gator_albino.png"));
    });
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
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
	}

}
