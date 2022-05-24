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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.common.entity.Octopus;

/**
 * Octopus - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class OctopusModel<T extends Octopus> extends EntityModel<Octopus> {
	private final ModelPart Body;
	private final ModelPart Tentacle1;
	private final ModelPart Tentacle2;
	private final ModelPart Tentacle3;
	private final ModelPart Tentacle4;
	private final ModelPart Tentacle5;
	private final ModelPart Tentacle6;
	private final ModelPart Tentacle7;
	private final ModelPart Tentacle8;
	private final ModelPart Head;

	public OctopusModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Tentacle1 = root.getChild("Tentacle1");
		this.Tentacle2 = root.getChild("Tentacle2");
		this.Tentacle3 = root.getChild("Tentacle3");
		this.Tentacle4 = root.getChild("Tentacle4");
		this.Tentacle5 = root.getChild("Tentacle5");
		this.Tentacle6 = root.getChild("Tentacle6");
		this.Tentacle7 = root.getChild("Tentacle7");
		this.Tentacle8 = root.getChild("Tentacle8");
		this.Head = Body.getChild("Head");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -5.0F, 0.0F, 14.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.182F, 0.0F, 0.0F));

		PartDefinition eye1 = Body.addOrReplaceChild("eye1", CubeListBuilder.create().texOffs(37, 0).addBox(0.0F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -7.0F, -1.0F));

		PartDefinition eye2 = Body.addOrReplaceChild("eye2", CubeListBuilder.create().texOffs(37, 0).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -7.0F, -1.0F));

		PartDefinition Tentacle1 = partdefinition.addOrReplaceChild("Tentacle1", CubeListBuilder.create().texOffs(32, 18).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 21.0F, -4.0F, -0.2276F, 2.9596F, 0.0F));

		PartDefinition bend1 = Tentacle1.addOrReplaceChild("bend1", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tentacle2 = partdefinition.addOrReplaceChild("Tentacle2", CubeListBuilder.create().texOffs(32, 18).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 21.0F, -2.5F, -0.2276F, 1.7301F, 0.0F));

		PartDefinition bend2 = Tentacle2.addOrReplaceChild("bend2", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tentacle3 = partdefinition.addOrReplaceChild("Tentacle3", CubeListBuilder.create().texOffs(32, 18).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 21.0F, 2.5F, -0.2276F, 1.2748F, 0.0F));

		PartDefinition bend3 = Tentacle3.addOrReplaceChild("bend3", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tentacle4 = partdefinition.addOrReplaceChild("Tentacle4", CubeListBuilder.create().texOffs(32, 18).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 21.0F, 4.0F, -0.2276F, 0.2276F, 0.0F));

		PartDefinition bend4 = Tentacle4.addOrReplaceChild("bend4", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tentacle5 = partdefinition.addOrReplaceChild("Tentacle5", CubeListBuilder.create().texOffs(32, 18).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 21.0F, -4.0F, -0.2276F, -2.9596F, 0.0F));

		PartDefinition bend5 = Tentacle5.addOrReplaceChild("bend5", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tentacle6 = partdefinition.addOrReplaceChild("Tentacle6", CubeListBuilder.create().texOffs(32, 18).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 21.0F, -2.5F, -0.2276F, -1.7301F, 0.0F));

		PartDefinition bend6 = Tentacle6.addOrReplaceChild("bend6", CubeListBuilder.create().texOffs(50, 0).addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Tentacle7 = partdefinition.addOrReplaceChild("Tentacle7", CubeListBuilder.create().texOffs(32, 18).addBox(-1.0F, -1.0F, 0.5F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 21.0F, 2.0F, -0.2276F, -1.2748F, 0.0F));

		PartDefinition bend7 = Tentacle7.addOrReplaceChild("bend7", CubeListBuilder.create().texOffs(50, 0).addBox(-1.0F, -3.0F, 7.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));

		PartDefinition Tentacle8 = partdefinition.addOrReplaceChild("Tentacle8", CubeListBuilder.create().texOffs(32, 18).addBox(-2.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 21.0F, 4.0F, -0.2276F, -0.2276F, 0.0F));

		PartDefinition bend8 = Tentacle8.addOrReplaceChild("bend8", CubeListBuilder.create().texOffs(50, 0).addBox(-2.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Octopus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isScared()) {
    		float speed = 1.0f;
    		float degree = 1.0f;
    		this.Tentacle1.zRot = 0;
    		this.Tentacle2.zRot = 0;
    		this.Tentacle3.zRot = 0;
    		this.Tentacle4.zRot = 0;
    		this.Tentacle5.zRot = 0;
    		this.Tentacle6.zRot = 0;
    		this.Tentacle7.zRot = 0;
    		this.Tentacle8.zRot = 0;
    		this.Tentacle1.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle2.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle3.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Head.xRot = Mth.cos(limbSwing * speed * 0.05F) * degree * 0.5F * limbSwingAmount;
    		this.Tentacle4.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle5.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle6.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle7.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle8.xRot = Mth.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    	} else {
    		float speed = 1.0f;
    		float degree = 1.0f;
    		this.Tentacle1.xRot = Mth.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle2.xRot = Mth.cos(1.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle3.xRot = Mth.cos(3.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Head.xRot = Mth.cos(limbSwing * speed * 0.05F) * degree * 0.5F * limbSwingAmount;
    		this.Tentacle4.xRot = Mth.cos(4.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle5.xRot = Mth.cos(4.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle6.xRot = Mth.cos(3.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle7.xRot = Mth.cos(1.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle8.xRot = Mth.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle1.zRot = Mth.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle2.zRot = Mth.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle4.zRot = Mth.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle5.zRot = Mth.cos(3.0F + limbSwing * speed * 1.5F) * degree * -1.0F * limbSwingAmount;
    		this.Tentacle8.zRot = Mth.cos(limbSwing * speed * 1.5F) * degree * -1.0F * limbSwingAmount;
    	}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
		Tentacle1.render(poseStack, buffer, packedLight, packedOverlay);
		Tentacle2.render(poseStack, buffer, packedLight, packedOverlay);
		Tentacle3.render(poseStack, buffer, packedLight, packedOverlay);
		Tentacle4.render(poseStack, buffer, packedLight, packedOverlay);
		Tentacle5.render(poseStack, buffer, packedLight, packedOverlay);
		Tentacle6.render(poseStack, buffer, packedLight, packedOverlay);
		Tentacle7.render(poseStack, buffer, packedLight, packedOverlay);
		Tentacle8.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
