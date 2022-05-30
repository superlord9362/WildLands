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
	private final ModelPart RightLegfront;
	private final ModelPart LeftLegfront;
	private final ModelPart Leftfoot;
	private final ModelPart Leftfoot2;
	@SuppressWarnings("unused")
	private float jumpRotation;

	public FrogModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLegfront = Body.getChild("LeftLegfront");
		this.RightLegfront = Body.getChild("RightLegfront");
		this.Leftfoot = LeftLeg.getChild("Leftfoot");
		this.Leftfoot2 = RightLeg.getChild("Leftfoot2");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 9).addBox(-5.0F, -2.0F, -4.0F, 10.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -8.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 4.0F));

		PartDefinition eye1 = Head.addOrReplaceChild("eye1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -1.0F, -6.0F));

		PartDefinition eye2 = Head.addOrReplaceChild("eye2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, -1.0F, -6.0F));

		PartDefinition RightLegfront = Body.addOrReplaceChild("RightLegfront", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 3.0F, -4.0F, 0.2276F, 0.0F, 0.0F));

		PartDefinition LeftLegfront = Body.addOrReplaceChild("LeftLegfront", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 3.0F, -4.0F, 0.2276F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(32, 8).addBox(-3.0F, -2.0F, -4.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 22.0F, 2.0F));

		PartDefinition Leftfoot = LeftLeg.addOrReplaceChild("Leftfoot", CubeListBuilder.create().texOffs(-5, 9).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 2.0F, -2.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(32, 8).addBox(0.0F, -2.0F, -4.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 22.0F, 2.0F));

		PartDefinition Leftfoot2 = RightLeg.addOrReplaceChild("Leftfoot2", CubeListBuilder.create().texOffs(-5, 9).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.0F, -2.0F));

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
        this.LeftLegfront.xRot = (limbSwingAmount * -40.0F + 15.0F) * ((float)Math.PI / 180F);
        this.RightLegfront.xRot = (limbSwingAmount * -40.0F + 15.0F) * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
		LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
		RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
