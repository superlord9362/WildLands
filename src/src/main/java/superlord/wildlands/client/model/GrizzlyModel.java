package superlord.wildlands.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.ArmedModel;
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
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.common.entity.GrizzlyEntity;

/**
 * Grizzly - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class GrizzlyModel<T extends Entity> extends EntityModel<GrizzlyEntity> implements ArmedModel {
	private final ModelPart Body;
	private final ModelPart FrontLeftLeg;
	private final ModelPart FrontRightLeg;
	private final ModelPart HindLeftLeg;
	private final ModelPart HindRightLeg;
	private final ModelPart Head;

	public GrizzlyModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.FrontLeftLeg = root.getChild("FrontLeftLeg");
		this.FrontRightLeg = root.getChild("FrontRightLeg");
		this.HindLeftLeg = root.getChild("HindLeftLeg");
		this.HindRightLeg = root.getChild("HindRightLeg");
		this.Head = root.getChild("Head");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -28.0F, -13.0F, 22.0F, 18.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition FrontLeftLeg = partdefinition.addOrReplaceChild("FrontLeftLeg", CubeListBuilder.create().texOffs(100, 44).addBox(2.0F, -10.0F, -10.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition FrontRightLeg = partdefinition.addOrReplaceChild("FrontRightLeg", CubeListBuilder.create().texOffs(100, 27).addBox(-9.0F, -10.0F, -10.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition HindLeftLeg = partdefinition.addOrReplaceChild("HindLeftLeg", CubeListBuilder.create().texOffs(50, 45).addBox(2.0F, -10.0F, 7.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition HindRightLeg = partdefinition.addOrReplaceChild("HindRightLeg", CubeListBuilder.create().texOffs(50, 45).addBox(-9.0F, -10.0F, 7.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(71, 3).addBox(-8.0F, -24.0F, -24.0F, 16.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Snout = Head.addOrReplaceChild("Snout", CubeListBuilder.create().texOffs(78, 45).addBox(-3.0F, 0.0F, -16.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.0F, -13.0F));

		PartDefinition RightEar = Head.addOrReplaceChild("RightEar", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -27.0F, -18.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftEar = Head.addOrReplaceChild("LeftEar", CubeListBuilder.create().texOffs(0, 0).addBox(3.0F, -27.0F, -18.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(GrizzlyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.0f;
		float degree = 1.0f;
		this.Body.zRot = Mth.cos(limbSwing * speed * 0.15F) * degree * 0.1F * limbSwingAmount;
		this.FrontLeftLeg.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 2.0F * limbSwingAmount;
		this.HindRightLeg.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 2.0F * limbSwingAmount;
		this.FrontRightLeg.xRot = Mth.cos(3.0F + limbSwing * speed * 0.2F) * degree * 2.0F * limbSwingAmount;
		this.HindLeftLeg.xRot = Mth.cos(3.0F + limbSwing * speed * 0.2F) * degree * 2.0F * limbSwingAmount;
		this.Head.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 0.2F * limbSwingAmount;

		this.Head.xRot = headPitch * ((float)Math.PI / 180F);
		this.Head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
		FrontLeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
		FrontRightLeg.render(poseStack, buffer, packedLight, packedOverlay);
		HindLeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
		HindRightLeg.render(poseStack, buffer, packedLight, packedOverlay);
		Head.render(poseStack, buffer, packedLight, packedOverlay);
	}



	public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
		float f = sideIn == HumanoidArm.RIGHT ? 1.0F : -1.0F;
		ModelPart modelrenderer = this.getArmForSide(sideIn);
		modelrenderer.x += f;
		modelrenderer.translateAndRotate(matrixStackIn);
		modelrenderer.x -= f;
		matrixStackIn.translate(-0.1, 0.5, 0);
	}

	protected ModelPart getArmForSide(HumanoidArm side) {
		return side == HumanoidArm.LEFT ? this.FrontLeftLeg : this.FrontRightLeg;
	}
}
