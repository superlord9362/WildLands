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
import superlord.wildlands.common.entity.Hammerhead;

/**
 * HammerheadSharkModel - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class HammerheadSharkModel<T extends Entity> extends EntityModel<Hammerhead> {
	private final ModelPart Body;
	private final ModelPart Tail;

	public HammerheadSharkModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Tail = Body.getChild("Tail");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.0F, -8.0F, 12.0F, 10.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -2.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 48).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 12.0F));

		PartDefinition Caudalfin = Tail.addOrReplaceChild("Caudalfin", CubeListBuilder.create().texOffs(72, 28).addBox(-0.5F, -11.0F, 0.0F, 1.0F, 17.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 15.0F));

		PartDefinition Dorsal2 = Tail.addOrReplaceChild("Dorsal2", CubeListBuilder.create().texOffs(45, 11).addBox(-0.5F, -4.0F, 0.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 3.0F, -0.3187F, 0.0F, 0.0F));

		PartDefinition pelvicleft = Tail.addOrReplaceChild("pelvicleft", CubeListBuilder.create().texOffs(28, 31).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 3.0F, 0.0F, 0.2731F, 0.0F, 0.3187F));

		PartDefinition pelvicright = Tail.addOrReplaceChild("pelvicright", CubeListBuilder.create().texOffs(28, 31).addBox(0.0F, 0.0F, 0.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 0.0F, 0.2731F, 0.0F, -0.3187F));

		PartDefinition Dorsal = Body.addOrReplaceChild("Dorsal", CubeListBuilder.create().texOffs(88, 17).addBox(-0.5F, -11.0F, 0.0F, 1.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 2.0F, -0.3187F, 0.0F, 0.0F));

		PartDefinition Neck = Body.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(0, 30).addBox(-4.0F, -3.0F, -11.0F, 8.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -8.0F));

		PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(44, 0).addBox(-12.0F, -2.0F, -7.0F, 24.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -7.0F));

		PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin", CubeListBuilder.create().texOffs(38, 54).mirror().addBox(-9.0F, 0.0F, 0.0F, 9.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, 4.0F, -5.0F, 0.0F, 0.182F, -0.5918F));

		PartDefinition RightFin = Body.addOrReplaceChild("RightFin", CubeListBuilder.create().texOffs(38, 54).addBox(0.0F, 0.0F, 0.0F, 9.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 4.0F, -5.0F, 0.0F, -0.182F, 0.5918F));

		return LayerDefinition.create(meshdefinition, 128, 80);
	}


	@Override
	public void setupAnim(Hammerhead entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
        if (!entity.isInWater()) {
           f = 1.5F;
        }
        this.Body.xRot = headPitch * ((float)Math.PI / 180F);
        this.Body.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.Tail.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
