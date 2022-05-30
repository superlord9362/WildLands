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
import superlord.wildlands.common.entity.Alligator;

/**
 * Alligator - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class AlligatorThreatenModel<T extends Entity> extends EntityModel<Alligator> {
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart Tail;
	private final ModelPart leftlegfront;
	private final ModelPart leftlegback;
	private final ModelPart rightlegback;
	private final ModelPart rightlegfront;

	public AlligatorThreatenModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.Tail = Body.getChild("Tail");
		this.leftlegfront = root.getChild("leftlegfront");
		this.leftlegback = root.getChild("leftlegback");
		this.rightlegback = root.getChild("rightlegback");
		this.rightlegfront = root.getChild("rightlegfront");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(49, 50).addBox(-4.5F, 0.0F, -17.0F, 9.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -7.0F));

		PartDefinition UpperJaw = Head.addOrReplaceChild("UpperJaw", CubeListBuilder.create().texOffs(0, 59).addBox(-4.5F, -4.0F, -15.0F, 9.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition LeftEye = UpperJaw.addOrReplaceChild("LeftEye", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -2.0F, 0.0F));

		PartDefinition LeftEye_1 = UpperJaw.addOrReplaceChild("LeftEye_1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -4.0F, -4.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.5F, -2.0F, 0.0F));

		PartDefinition Teeth = UpperJaw.addOrReplaceChild("Teeth", CubeListBuilder.create().texOffs(40, 33).addBox(-4.5F, 0.0F, -15.0F, 9.0F, 1.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightlegback = partdefinition.addOrReplaceChild("rightlegback", CubeListBuilder.create().texOffs(103, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(8.5F, 18.0F, 8.0F));

		PartDefinition leftfootback_1 = rightlegback.addOrReplaceChild("leftfootback_1", CubeListBuilder.create().texOffs(93, 12).addBox(-1.0F, 0.0F, -6.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 6.0F, 1.0F));

		PartDefinition rightlegfront = partdefinition.addOrReplaceChild("rightlegfront", CubeListBuilder.create().texOffs(54, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 18.0F, -6.0F));

		PartDefinition rightfootfront = rightlegfront.addOrReplaceChild("rightfootfront", CubeListBuilder.create().texOffs(48, 12).addBox(-1.5F, 0.0F, -4.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition leftlegfront = partdefinition.addOrReplaceChild("leftlegfront", CubeListBuilder.create().texOffs(54, 0).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 18.0F, -6.0F));

		PartDefinition leftfootfront = leftlegfront.addOrReplaceChild("leftfootfront", CubeListBuilder.create().texOffs(48, 12).addBox(-3.5F, 0.0F, -4.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

		PartDefinition leftlegback = partdefinition.addOrReplaceChild("leftlegback", CubeListBuilder.create().texOffs(103, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, 18.0F, 8.0F));

		PartDefinition leftfootback = leftlegback.addOrReplaceChild("leftfootback", CubeListBuilder.create().texOffs(93, 12).addBox(-5.0F, 0.0F, -6.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 6.0F, 1.0F));

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -2.0F, -10.0F, 13.0F, 8.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 28).addBox(-3.5F, -1.0F, 0.0F, 7.0F, 7.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 10.0F, -0.0911F, 0.0F, 0.0F));

		PartDefinition Tail2 = Tail.addOrReplaceChild("Tail2", CubeListBuilder.create().texOffs(47, 5).addBox(-3.5F, -4.0F, 0.0F, 7.0F, 3.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 80);
	}

	@Override
	public void setupAnim(Alligator entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.0f;
		float degree = 1.0f;
		if (entity.isInWater()) {
			this.Body.yRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount;
			this.Tail.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.25F) * degree * 1.0F * limbSwingAmount;
			this.leftlegback.xRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.1F * limbSwingAmount + 1.0F;
			this.leftlegback.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.25F) * degree * 0.75F * limbSwingAmount;
			this.leftlegback.x = (Mth.cos(limbSwing * speed * 0.25F) * degree * 0.25F * limbSwingAmount) - 7;
			this.rightlegback.xRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.1F * limbSwingAmount + 1.0F;
			this.rightlegback.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.25F) * degree * 0.75F * limbSwingAmount;
			this.rightlegback.x = (Mth.cos(limbSwing * speed * 0.25F) * degree * 0.25F * limbSwingAmount) + 7;
			this.leftlegfront.xRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.1F * limbSwingAmount + 1.0F;
			this.leftlegfront.yRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount;
			this.rightlegfront.xRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.1F * limbSwingAmount + 1.0F;
			this.rightlegfront.yRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount;
			this.Head.x = Mth.cos(1.0F + limbSwing * speed * 0.25F) * degree * 0.1F * limbSwingAmount;
		} else {
			this.Body.yRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.25F * limbSwingAmount;
			this.Tail.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount;
			this.Head.yRot = Mth.cos(1.0F + limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount;
			this.Head.xRot = Mth.cos(1.0F + limbSwing * speed * 0.5F) * degree * 0.1F * limbSwingAmount;
			this.rightlegfront.z = (Mth.cos(limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount) - 6;
			this.rightlegfront.y = (Mth.cos(0.8F + limbSwing * speed * 0.25F) * degree * 0.2F * limbSwingAmount) + 18;
			this.rightlegfront.xRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount;
			this.Body.zRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.25F * limbSwingAmount;
			this.leftlegfront.z = (Mth.cos(limbSwing * speed * 0.25F) * degree * -0.5F * limbSwingAmount) - 6;
			this.leftlegfront.y = (Mth.cos(0.8F + limbSwing * speed * 0.25F) * degree * -0.2F * limbSwingAmount) + 18;
			this.leftlegfront.xRot = Mth.cos(limbSwing * speed * 0.25F) * degree * -0.5F * limbSwingAmount;
			this.rightlegback.z = (Mth.cos(-1.0F + limbSwing * speed * 0.25F) * degree * -0.25F * limbSwingAmount) + 8;
			this.rightlegback.y = (Mth.cos(1.0F + limbSwing * speed * 0.25F) * degree * -0.2F * limbSwingAmount) + 18;
			this.rightlegback.xRot = Mth.cos(limbSwing * speed * 0.25F) * degree * -0.5F * limbSwingAmount;
			this.leftlegback.z = (Mth.cos(-1.0F + limbSwing * speed * 0.25F) * degree * 0.25F * limbSwingAmount) + 8;
			this.leftlegback.y = (Mth.cos(1.0F + limbSwing * speed * 0.25F) * degree * 0.2F * limbSwingAmount) + 18;
			this.leftlegback.xRot = Mth.cos(limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
		Head.render(poseStack, buffer, packedLight, packedOverlay);
		leftlegfront.render(poseStack, buffer, packedLight, packedOverlay);
		leftlegback.render(poseStack, buffer, packedLight, packedOverlay);
		rightlegback.render(poseStack, buffer, packedLight, packedOverlay);
		rightlegfront.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
