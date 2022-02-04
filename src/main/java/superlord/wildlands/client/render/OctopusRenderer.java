package superlord.wildlands.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.OctopusModel;
import superlord.wildlands.common.entity.OctopusEntity;

public class OctopusRenderer extends MobRenderer<OctopusEntity, OctopusModel<OctopusEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus.png");
	private static final ResourceLocation BLUE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_blue_coral.png");
	private static final ResourceLocation DIRT = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_dirty.png");
	private static final ResourceLocation DOLERITE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_dolerite.png");
	private static final ResourceLocation GABBRO = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_gabbro.png");
	private static final ResourceLocation GRAVEL = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_gravel.png");
	private static final ResourceLocation PINK = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_pink_coral.png");
	private static final ResourceLocation PURPLE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_purple_coral.png");
	private static final ResourceLocation RED = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_red_coral.png");
	private static final ResourceLocation SAND = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_sandy.png");
	private static final ResourceLocation STONE = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_stone.png");
	private static final ResourceLocation YELLOW = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_yellow_coral.png");
	private static final ResourceLocation SWIMMING = new ResourceLocation(WildLands.MOD_ID, "textures/entity/octopus/octopus_swimming.png");

	public OctopusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new OctopusModel<>(), 1.0F);
	}

	@Override
	public ResourceLocation getEntityTexture(OctopusEntity entity) {
		if (entity.isOnBlueCoral()) {
			return BLUE;
		} else if (entity.isOnDirt()) {
			return DIRT;
		} else if (entity.isOnDolerite()) {
			return DOLERITE;
		} else if (entity.isOnGabbro()) {
			return GABBRO;
		} else if (entity.isOnGravel()) {
			return GRAVEL;
		} else if (entity.isOnPinkCoral()) {
			return PINK;
		} else if (entity.isOnPurpleCoral()) {
			return PURPLE;
		} else if (entity.isOnRedCoral()) {
			return RED;
		} else if (entity.isOnSand()) {
			return SAND;
		} else if (entity.isOnStone()) {
			return STONE;
		} else if (entity.isOnYellowCoral()) {
			return YELLOW;
		} else if (entity.isScared()) {
			return SWIMMING;
		} else {
			return TEXTURE;
		}
	}

}
