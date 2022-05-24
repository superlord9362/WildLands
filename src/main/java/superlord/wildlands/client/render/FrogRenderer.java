package superlord.wildlands.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
import superlord.wildlands.client.model.FrogModel;
import superlord.wildlands.client.model.TadpoleModel;
import superlord.wildlands.common.entity.Frog;

public class FrogRenderer extends MobRenderer<Frog, EntityModel<Frog>> {

	private static final ResourceLocation FROG_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/frog/frog.png");
	private static final ResourceLocation TADPOLE_TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/frog/tadpole.png");
	private static FrogModel FROG;
	private static TadpoleModel<?> TADPOLE;

	@SuppressWarnings("rawtypes")
	public FrogRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new FrogModel(renderManager.bakeLayer(ClientEvents.FROG)), 0.75F);
		FROG = new FrogModel(renderManager.bakeLayer(ClientEvents.FROG));
		TADPOLE = new TadpoleModel(renderManager.bakeLayer(ClientEvents.TADPOLE));
	}

	public void scale(Frog entityIn, PoseStack matrixStackIn, float partialTickTime) {
		if(entityIn.isBaby()) {
			model = TADPOLE;
		}  else {
			model = FROG;
		}
	}

	public ResourceLocation getTextureLocation(Frog entity) {
		if (entity.isBaby()) {
			return TADPOLE_TEXTURE;
		} else {
			return FROG_TEXTURE;
		}
	}

}
