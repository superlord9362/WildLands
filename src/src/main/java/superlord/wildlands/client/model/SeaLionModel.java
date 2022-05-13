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
import superlord.wildlands.common.entity.SeaLionEntity;

/**
 * Sea Lion - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class SeaLionModel<T extends Entity> extends EntityModel<SeaLionEntity> implements ArmedModel {
	private final ModelPart body;
	private final ModelPart frontflipper1;
	private final ModelPart frontflipper2;
	private final ModelPart head;
	private final ModelPart hindflipper1;
	private final ModelPart hindflipper2;

	public SeaLionModel(ModelPart root) {
		this.body = root.getChild("body");
		this.frontflipper1 = root.getChild("frontflipper1");
		this.frontflipper2 = root.getChild("frontflipper2");
		this.head = body.getChild("head");
		this.hindflipper1 = body.getChild("hindflipper1");
		this.hindflipper2 = body.getChild("hindflipper2");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 35).addBox(-7.0F, -5.0F, -5.0F, 14.0F, 11.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 18.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -5.0F));

		PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(29, 0).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -5.0F));

		PartDefinition ear1 = head.addOrReplaceChild("ear1", CubeListBuilder.create().texOffs(0, 1).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -10.0F, 0.0F));

		PartDefinition ear2 = head.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -10.0F, 0.0F));

		PartDefinition hindflipper1 = body.addOrReplaceChild("hindflipper1", CubeListBuilder.create().texOffs(36, 28).addBox(0.0F, 0.0F, -1.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 6.0F, 10.0F, 0.0F, -0.4554F, 0.0F));

		PartDefinition hindflipper2 = body.addOrReplaceChild("hindflipper2", CubeListBuilder.create().texOffs(36, 28).mirror().addBox(-8.0F, 0.0F, -1.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 6.0F, 10.0F, 0.0F, 0.4554F, 0.0F));

		PartDefinition frontflipper1 = partdefinition.addOrReplaceChild("frontflipper1", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-9.0F, 0.0F, -2.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 23.0F, -5.0F));

		PartDefinition frontflipper2 = partdefinition.addOrReplaceChild("frontflipper2", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, 0.0F, -2.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 23.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(SeaLionEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		float speed = 1.0f;
		float degree = 1.0f;
		if (entity.isSwimming()) {
			this.body.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.head.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * -0.5F * limbSwingAmount;
			this.frontflipper1.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper1.yRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper1.zRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper2.xRot = Mth.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper2.yRot = Mth.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper2.zRot = Mth.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper1.xRot = Mth.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper1.yRot = Mth.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper1.zRot = Mth.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper2.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper2.yRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper2.zRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
		} else {
			this.frontflipper1.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper1.yRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper1.zRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper2.xRot = Mth.cos(3.0F + limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper2.yRot = Mth.cos(3.0F + limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper2.zRot = Mth.cos(3.0F + limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.head.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.25F * limbSwingAmount;
			this.hindflipper1.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.3F * limbSwingAmount;
			this.hindflipper1.yRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.3F * limbSwingAmount;
			this.hindflipper1.zRot = Mth.cos(3.0F + limbSwing * speed * 0.2F) * degree * 0.3F * limbSwingAmount;
			this.hindflipper2.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.3F * limbSwingAmount;
			this.hindflipper2.yRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.3F * limbSwingAmount;
			this.hindflipper2.yRot = Mth.cos(limbSwing * speed * 0.2F) * degree * -0.3F * limbSwingAmount;

		}
	}
	
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
		frontflipper1.render(poseStack, buffer, packedLight, packedOverlay);
		frontflipper2.render(poseStack, buffer, packedLight, packedOverlay);
	}

	public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
		float f = sideIn == HumanoidArm.RIGHT ? 1.0F : -1.0F;
		ModelPart modelrenderer = this.getArmForSide(sideIn);
		modelrenderer.x += f;
		modelrenderer.translateAndRotate(matrixStackIn);
		modelrenderer.x -= f;
		matrixStackIn.translate(0, 0.1, 0);
	}

	protected ModelPart getArmForSide(HumanoidArm side) {
		return side == HumanoidArm.LEFT ? this.head : this.head;
	}
}
