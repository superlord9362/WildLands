package superlord.wildlands.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
import superlord.wildlands.client.model.SeaLionModel;
import superlord.wildlands.common.entity.SeaLion;

public class SeaLionRenderer extends MobRenderer<SeaLion, SeaLionModel<SeaLion>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/sea_lion.png");

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SeaLionRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new SeaLionModel<>(renderManagerIn.bakeLayer(ClientEvents.SEA_LION)), 0.25F);
		this.addLayer(new ItemInHandLayer(this, renderManagerIn.getItemInHandRenderer()));
	}
	
	public void scale(SeaLion entityIn, PoseStack matrixStackIn, float partialTickTime) {
		if (entityIn.isBaby()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(SeaLion entity) {
		return TEXTURE;
	}
	
}
