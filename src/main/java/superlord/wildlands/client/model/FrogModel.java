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
import superlord.wildlands.common.entity.Frog;

/**
 * Frog - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class FrogModel extends EntityModel<Frog> {
	private final ModelPart Body;
	private final ModelPart LeftLeg;
	private final ModelPart RightLeg;
	private final ModelPart poach;
	private final ModelPart RightLegFront;
	private final ModelPart LeftLegFront;
	private final ModelPart Leftfoot;
	private final ModelPart Leftfoot2;
	@SuppressWarnings("unused")
	private float jumpRotation;

	public FrogModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.poach = root.getChild("poach");
		this.LeftLegFront = Body.getChild("LeftLegFront");
		this.RightLegFront = Body.getChild("RightLegFront");
		this.Leftfoot = LeftLeg.getChild("Leftfoot");
		this.Leftfoot2 = RightLeg.getChild("Leftfoot2");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 9).addBox(-5.0F, -6.0F, -4.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.0F, -4.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye1 = Head.addOrReplaceChild("eye1", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -2.0F, -7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 4.0F));

		PartDefinition eye2 = Head.addOrReplaceChild("eye2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -2.0F, -7.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -6.0F, 4.0F));

		PartDefinition RightLegFront = Body.addOrReplaceChild("RightLegFront", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLegFront_r1 = RightLegFront.addOrReplaceChild("RightLegFront_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0F, -4.0F, 0.2276F, 0.0F, 0.0F));

		PartDefinition LeftLegFront = Body.addOrReplaceChild("LeftLegFront", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftLegFront_r1 = LeftLegFront.addOrReplaceChild("LeftLegFront_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -1.0F, -4.0F, 0.2276F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(32, 8).addBox(1.5F, -3.0F, 2.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 23.0F, -4.0F));

		PartDefinition Leftfoot = LeftLeg.addOrReplaceChild("Leftfoot", CubeListBuilder.create().texOffs(-5, 9).addBox(1.5F, 1.0F, -1.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(32, 8).addBox(-7.0F, -4.0F, -2.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Leftfoot2 = RightLeg.addOrReplaceChild("Leftfoot2", CubeListBuilder.create().texOffs(-5, 9).addBox(-7.0F, 0.0F, -5.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition poach = partdefinition.addOrReplaceChild("poach", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.0F, -5.0F, 12.0F, 4.0F, 6.0F, new CubeDeformation(-1.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	@Override
	public void setupAnim(Frog entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ageInTicks - (float)entity.tickCount;
    	this.jumpRotation = Mth.sin(entity.getJumpCompletion(f) * (float)Math.PI);
        this.LeftLeg.xRot = (limbSwingAmount * 50.0F) * ((float)Math.PI / 180F);
        this.RightLeg.xRot = (limbSwingAmount * 50.0F) * ((float)Math.PI / 180F);
        this.Leftfoot.xRot = limbSwingAmount * 50.0F * ((float)Math.PI / 180F);
        this.Leftfoot2.xRot = limbSwingAmount * 50.0F * ((float)Math.PI / 180F);
        this.LeftLegFront.xRot = (limbSwingAmount * -40.0F + 15.0F) * ((float)Math.PI / 180F);
        this.RightLegFront.xRot = (limbSwingAmount * -40.0F + 15.0F) * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
		LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
		RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
		poach.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
