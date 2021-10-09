package superlord.wildlands.client.render.overlay;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.WildLands;
import superlord.wildlands.entity.FrogEntity;

@OnlyIn(Dist.CLIENT)
public class TadpoleSlimeLayer extends AbstractOverlay<FrogEntity, EntityModel<FrogEntity>> {
	private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(WildLands.MOD_ID, "textures/entity/frog/slime.png"));

	public TadpoleSlimeLayer(IEntityRenderer<FrogEntity, EntityModel<FrogEntity>> rendererIn) {
		super(rendererIn);
	}

	public RenderType getRenderType() {
		return RENDER_TYPE;
	}

}