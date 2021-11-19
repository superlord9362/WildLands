package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.HammerheadSharkModel;
import superlord.wildlands.common.entity.HammerheadEntity;

public class HammerheadRenderer extends MobRenderer<HammerheadEntity, HammerheadSharkModel<HammerheadEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/hammerhead/hammerhead.png");

	public HammerheadRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new HammerheadSharkModel<>(), 0.75F);
	}

	@Override
	public ResourceLocation getEntityTexture(HammerheadEntity entity) {
		return TEXTURE;
	}
	
}
