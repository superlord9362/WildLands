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
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.common.entity.FrogEntity;

/**
 * TadpoleModel - superlord9362
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class TadpoleModel<T extends LivingEntity> extends EntityModel<FrogEntity> {
	private final ModelPart Tad;
	private final ModelPart Pole;

	public TadpoleModel(ModelPart root) {
		this.Tad = root.getChild("Tad");
		this.Pole = Tad.getChild("Pole");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Tad = partdefinition.addOrReplaceChild("Tad", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 24.0F, -5.0F));

		PartDefinition Pole = Tad.addOrReplaceChild("Pole", CubeListBuilder.create().texOffs(0, 1).addBox(-2.0F, -4.0F, 4.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Slime = Tad.addOrReplaceChild("Slime", CubeListBuilder.create().texOffs(16, 0).addBox(-4.0F, -4.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(FrogEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 0.8f;
		float degree = 1.0f;
		this.Tad.xRot = headPitch * ((float)Math.PI / 180F);
		this.Tad.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.Pole.yRot = (-0.12F * Mth.sin(0.2F * ageInTicks / 5)) + (Mth.cos(limbSwing * speed * 0.15F) * degree * 0.2F * limbSwingAmount);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Tad.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
