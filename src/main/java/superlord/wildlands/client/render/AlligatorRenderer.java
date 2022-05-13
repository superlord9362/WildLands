package superlord.wildlands.client.render;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
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
	private static AlligatorModel<?> IDLE;
	private static AlligatorThreatenModel<?> WARNING;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AlligatorRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new AlligatorModel(renderManager.bakeLayer(ClientEvents.ALLIGATOR)), 0.9375F);
		IDLE = new AlligatorModel(renderManager.bakeLayer(ClientEvents.ALLIGATOR));
		WARNING = new AlligatorThreatenModel(renderManager.bakeLayer(ClientEvents.ALLIGATOR_WARNING));
	}

	public void scale(AlligatorEntity entityIn, PoseStack matrixStackIn, float partialTickTime) {
		if (entityIn.isWarning()) {
			model = WARNING;
		}  else {
			model = IDLE;
		}
		if (entityIn.isBaby()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
	}

	public ResourceLocation getTextureLocation(AlligatorEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
	}

}
