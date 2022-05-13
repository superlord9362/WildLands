package superlord.wildlands.client.render;

import java.util.Map;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.WLBoatModel;
import superlord.wildlands.common.entity.WLBoatEntity;

@OnlyIn(Dist.CLIENT)
public class WLBoatRenderer extends EntityRenderer<WLBoatEntity> {
	private final Map<WLBoatEntity.Type, Pair<ResourceLocation, WLBoatModel>> boatResources;


	public WLBoatRenderer(EntityRendererProvider.Context renderManagerIn) {
		super(renderManagerIn);
		this.shadowRadius = 0.8F;
		this.boatResources = Stream.of(WLBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap((p_173938_) -> {
			return p_173938_;
		}, (p_173941_) -> {
			return Pair.of(new ResourceLocation(WildLands.MOD_ID + "textures/entity/boat/" + p_173941_.getName() + ".png"), new WLBoatModel(renderManagerIn.bakeLayer(ModelLayers.createBoatModelName(p_173941_))));
		}));
	}

	public void render(WLBoatEntity p_113929_, float p_113930_, float p_113931_, PoseStack p_113932_, MultiBufferSource p_113933_, int p_113934_) {
		p_113932_.pushPose();
		p_113932_.translate(0.0D, 0.375D, 0.0D);
		p_113932_.mulPose(Vector3f.YP.rotationDegrees(180.0F - p_113930_));
		float f = (float)p_113929_.getHurtTime() - p_113931_;
		float f1 = p_113929_.getDamage() - p_113931_;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f > 0.0F) {
			p_113932_.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)p_113929_.getHurtDir()));
		}

		float f2 = p_113929_.getBubbleAngle(p_113931_);
		if (!Mth.equal(f2, 0.0F)) {
			p_113932_.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), p_113929_.getBubbleAngle(p_113931_), true));
		}

		Pair<ResourceLocation, WLBoatModel> pair = getModelWithLocation(p_113929_);
		ResourceLocation resourcelocation = pair.getFirst();
		WLBoatModel boatmodel = pair.getSecond();
		p_113932_.scale(-1.0F, -1.0F, 1.0F);
		p_113932_.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		boatmodel.setupAnim(p_113929_, p_113931_, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexconsumer = p_113933_.getBuffer(boatmodel.renderType(resourcelocation));
		boatmodel.renderToBuffer(p_113932_, vertexconsumer, p_113934_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		if (!p_113929_.isUnderWater()) {
			VertexConsumer vertexconsumer1 = p_113933_.getBuffer(RenderType.waterMask());
			boatmodel.waterPatch().render(p_113932_, vertexconsumer1, p_113934_, OverlayTexture.NO_OVERLAY);
		}

		p_113932_.popPose();
		super.render(p_113929_, p_113930_, p_113931_, p_113932_, p_113933_, p_113934_);
	}

	@Deprecated // forge: override getModelWithLocation to change the texture / model
	public ResourceLocation getTextureLocation(WLBoatEntity p_113927_) {
		return getModelWithLocation(p_113927_).getFirst();
	}

	public Pair<ResourceLocation, WLBoatModel> getModelWithLocation(WLBoatEntity boat) { return this.boatResources.get(boat.getBoatType()); }

}