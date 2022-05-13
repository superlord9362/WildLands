package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ClientEvents;
import superlord.wildlands.client.model.HammerheadSharkModel;
import superlord.wildlands.common.entity.HammerheadEntity;

public class HammerheadRenderer extends MobRenderer<HammerheadEntity, HammerheadSharkModel<HammerheadEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/hammerhead/hammerhead.png");

	public HammerheadRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn, new HammerheadSharkModel<>(renderManagerIn.bakeLayer(ClientEvents.HAMMERHEAD)), 0.75F);
	}

	@Override
	public ResourceLocation getTextureLocation(HammerheadEntity entity) {
		return TEXTURE;
	}
	
}
