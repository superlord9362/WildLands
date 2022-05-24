package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
import superlord.wildlands.client.model.CatfishModel;
import superlord.wildlands.common.entity.Catfish;

public class CatfishRenderer extends MobRenderer<Catfish, CatfishModel<Catfish>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/catfish/catfish.png");

	public CatfishRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new CatfishModel<>(renderManagerIn.bakeLayer(ClientEvents.CATFISH)), 0.375F);
	}

	@Override
	public ResourceLocation getTextureLocation(Catfish entity) {
		return TEXTURE;
	}
	
}
