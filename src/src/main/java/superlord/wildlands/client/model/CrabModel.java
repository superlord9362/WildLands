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
import superlord.wildlands.common.entity.CrabEntity;

/**
 * Crab - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CrabModel extends EntityModel<CrabEntity> {
	private final ModelPart leg3l;
	private final ModelPart leg1l;
	private final ModelPart leg2l;
	private final ModelPart body;
	private final ModelPart leg3r;
	private final ModelPart leg1r;
	private final ModelPart leg2r;
	private final ModelPart claw1r;
	private final ModelPart claw2r;
	private final ModelPart claw1l;
	private final ModelPart claw2l;
	private final ModelPart bigclaw1;
	private final ModelPart bigclaw2;
	private final ModelPart eye1;
	private final ModelPart eye2;

	public CrabModel(ModelPart root) {
		this.leg3l = root.getChild("leg3l");
		this.leg1l = root.getChild("leg1l");
		this.leg2l = root.getChild("leg2l");
		this.body = root.getChild("body");
		this.leg3r = root.getChild("leg3r");
		this.leg1r = root.getChild("leg1r");
		this.leg2r = root.getChild("leg2r");
		this.eye1 = body.getChild("eye1");
		this.eye2 = body.getChild("eye2");
		this.claw1l = body.getChild("claw1l");
		this.claw2l = claw1l.getChild("claw2l");
		this.claw1r = body.getChild("claw1r");
		this.claw2r = claw1r.getChild("claw2r");
		this.bigclaw1 = body.getChild("bigclaw1");
		this.bigclaw2 = bigclaw1.getChild("bigclaw2");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leg3l = partdefinition.addOrReplaceChild("leg3l", CubeListBuilder.create().texOffs(0, 0).addBox(2.5F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leg1l = partdefinition.addOrReplaceChild("leg1l", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leg2l = partdefinition.addOrReplaceChild("leg2l", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -3.5F, 9.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition eye1 = body.addOrReplaceChild("eye1", CubeListBuilder.create().texOffs(25, 0).addBox(-1.0F, -4.0F, -0.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.0F, 2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition claw1r = body.addOrReplaceChild("claw1r", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, 0.0F, -6.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -5.0F, 7.0F, 0.0F, 0.4718F, 0.0F));

		PartDefinition claw2r = claw1r.addOrReplaceChild("claw2r", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -1.0F));

		PartDefinition claw2r_r1 = claw2r.addOrReplaceChild("claw2r_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-0.5F, -2.0F, -6.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, -0.1F, 0.0F, 0.0F));

		PartDefinition claw1l = body.addOrReplaceChild("claw1l", CubeListBuilder.create().texOffs(32, 19).addBox(-0.75F, 0.0F, 0.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.75F, -5.0F, -6.0F, 0.0F, -0.4718F, 0.0F));

		PartDefinition claw2l = claw1l.addOrReplaceChild("claw2l", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition claw2l_r1 = claw2l.addOrReplaceChild("claw2l_r1", CubeListBuilder.create().texOffs(10, 17).addBox(-0.75F, -2.0F, 0.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0937F, 0.0F, 0.0F));

		PartDefinition eye2 = body.addOrReplaceChild("eye2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye2_r1 = eye2.addOrReplaceChild("eye2_r1", CubeListBuilder.create().texOffs(25, 0).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -8.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bigclaw1 = body.addOrReplaceChild("bigclaw1", CubeListBuilder.create().texOffs(17, 19).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -6.0F, -9.0F, 0.0F, -0.65F, 0.0F));

		PartDefinition bigclaw2 = bigclaw1.addOrReplaceChild("bigclaw2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bigclaw2_r1 = bigclaw2.addOrReplaceChild("bigclaw2_r1", CubeListBuilder.create().texOffs(23, 6).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.1F, 0.0F, 0.0F));

		PartDefinition leg3r = partdefinition.addOrReplaceChild("leg3r", CubeListBuilder.create().texOffs(0, 0).addBox(2.5F, -3.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 8.0F));

		PartDefinition leg1r = partdefinition.addOrReplaceChild("leg1r", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -3.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leg2r = partdefinition.addOrReplaceChild("leg2r", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	@Override
	public void setupAnim(CrabEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.0f;
		float degree = 1.0f;
		if (entity.isPartying()) {
			this.body.xRot = (-0.2F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.eye1.zRot = (-0.1F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.eye2.zRot = (0.1F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.claw1l.xRot = (-0.2F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.claw1l.yRot = (-0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.bigclaw1.xRot = (-0.2F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.bigclaw1.yRot = (-0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.claw1r.xRot = (-0.2F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.claw1r.yRot = (-0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.claw2r.xRot = -Math.abs(-0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.claw2l.xRot = -Math.abs(-0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.bigclaw2.xRot = -Math.abs(-0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.leg1l.xRot = -Math.abs(0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.leg2l.xRot = -Math.abs(0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.leg3l.xRot = -Math.abs(0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.leg1r.xRot = Math.abs(0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.leg2r.xRot = Math.abs(0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
			this.leg3r.xRot = Math.abs(0.5F * 1F * Mth.sin((1.5F * ageInTicks) / 10));
		} else {
			this.body.xRot = 0;
			this.eye1.zRot = 0;
			this.eye2.zRot = 0;
			this.claw1l.xRot = 0;
			this.claw1l.yRot = 1.1F;
			this.bigclaw1.xRot = 0;
			this.bigclaw1.yRot = 0.65F;
			this.claw1r.xRot = 0;
			this.claw1r.yRot = -1.1F;
			this.claw2r.xRot = 0;
			this.claw2l.xRot = 0;
			this.bigclaw2.xRot = 0;
			this.leg1l.xRot = 0;
			this.leg2l.xRot = 0;
			this.leg3l.xRot = 0;
			this.leg1r.xRot = 0;
			this.leg2r.xRot = 0;
			this.leg3r.xRot = 0;
			
			this.leg1l.xRot = Mth.cos(limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
			this.leg2l.xRot = Mth.cos(2.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
			this.leg3l.xRot = Mth.cos(2.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
			this.claw1l.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.claw1l.zRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
			this.claw2l.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.25F * limbSwingAmount - 0.1F;
			this.claw1r.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.claw1r.zRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.1F * limbSwingAmount;
			this.claw2r.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.25F * limbSwingAmount - 0.1F;
			this.bigclaw1.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
			this.bigclaw1.zRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
			this.bigclaw2.xRot = Mth.cos(limbSwing * speed * 0.2F) * degree * 0.25F * limbSwingAmount - 0.1F;
			this.leg1r.xRot = Mth.cos(3.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
			this.leg2r.xRot = Mth.cos(5.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
			this.leg3r.xRot = Mth.cos(5.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		leg3l.render(poseStack, buffer, packedLight, packedOverlay);
		leg1l.render(poseStack, buffer, packedLight, packedOverlay);
		leg2l.render(poseStack, buffer, packedLight, packedOverlay);
		body.render(poseStack, buffer, packedLight, packedOverlay);
		leg3r.render(poseStack, buffer, packedLight, packedOverlay);
		leg1r.render(poseStack, buffer, packedLight, packedOverlay);
		leg2r.render(poseStack, buffer, packedLight, packedOverlay);
	}
	
}
