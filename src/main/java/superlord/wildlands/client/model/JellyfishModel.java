package superlord.wildlands.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.common.entity.Jellyfish;

/**
 * JellyfishModel - superlord9362
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class JellyfishModel<T extends Entity> extends EntityModel<Jellyfish> {
	private final ModelPart JellyFish;
	private final ModelPart TentaclesFront;
	private final ModelPart TentaclesBack;
	private final ModelPart TentaclesRight;
	private final ModelPart TentaclesLeft;

	public JellyfishModel(ModelPart root) {
		this.JellyFish = root.getChild("JellyFish");
		this.TentaclesBack = JellyFish.getChild("TentaclesBack");
		this.TentaclesFront = JellyFish.getChild("TentaclesFront");
		this.TentaclesLeft = JellyFish.getChild("TentaclesLeft");
		this.TentaclesRight = JellyFish.getChild("TentaclesRight");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition JellyFish = partdefinition.addOrReplaceChild("JellyFish", CubeListBuilder.create().texOffs(24, 31).addBox(-5.0F, -16.0F, -5.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Cape = JellyFish.addOrReplaceChild("Cape", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -18.0F, -8.0F, 16.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition TentaclesRight = JellyFish.addOrReplaceChild("TentaclesRight", CubeListBuilder.create().texOffs(0, 32).addBox(-6.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition TentaclesFront = JellyFish.addOrReplaceChild("TentaclesFront", CubeListBuilder.create().texOffs(0, 40).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition TentaclesBack = JellyFish.addOrReplaceChild("TentaclesBack", CubeListBuilder.create().texOffs(0, 40).addBox(-4.0F, -8.0F, 6.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition TentaclesLeft = JellyFish.addOrReplaceChild("TentaclesLeft", CubeListBuilder.create().texOffs(0, 32).addBox(6.0F, -8.0F, -4.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 48);
	}

	@Override
	public void setupAnim(Jellyfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	float speed = 1.0f;
    	float degree = 1.0f;
    	this.TentaclesFront.xRot = Mth.cos(limbSwing * speed * 0.05F) * degree * 1.0F * limbSwingAmount;
    	this.TentaclesBack.xRot = Mth.cos(3.0F + limbSwing * speed * 0.05F) * degree * 1.0F * limbSwingAmount;
    	this.TentaclesRight.zRot = Mth.cos(3.0F + limbSwing * speed * 0.05F) * degree * 1.0F * limbSwingAmount;
    	this.TentaclesLeft.zRot = Mth.cos(limbSwing * speed * 0.05F) * degree * 1.0F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		JellyFish.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
