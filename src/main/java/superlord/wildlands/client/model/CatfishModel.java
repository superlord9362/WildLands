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
import superlord.wildlands.common.entity.Catfish;

/**
 * catfish - weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CatfishModel<T extends Entity> extends EntityModel<Catfish> {
	private final ModelPart Body;
	private final ModelPart Tail;

	public CatfishModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Tail = Body.getChild("Tail");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -4.0F, -12.0F, 14.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition Tailfin = Tail.addOrReplaceChild("Tailfin", CubeListBuilder.create().texOffs(30, 18).addBox(0.0F, -6.0F, 0.0F, 0.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition BottomFin = Tail.addOrReplaceChild("BottomFin", CubeListBuilder.create().texOffs(44, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Dorsal = Body.addOrReplaceChild("Dorsal", CubeListBuilder.create().texOffs(44, -3).addBox(0.0F, -3.0F, 0.0F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -3.0F));

		PartDefinition LeftWhisker = Body.addOrReplaceChild("LeftWhisker", CubeListBuilder.create().texOffs(0, 44).mirror().addBox(-6.0F, 0.0F, 0.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 4.0F, -10.0F, 0.0F, 0.5463F, 0.0F));

		PartDefinition RightWhisker = Body.addOrReplaceChild("RightWhisker", CubeListBuilder.create().texOffs(0, 44).addBox(0.0F, 0.0F, 0.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 4.0F, -10.0F, 0.0F, -0.5463F, 0.0F));

		PartDefinition BottomWhiskers = Body.addOrReplaceChild("BottomWhiskers", CubeListBuilder.create().texOffs(34, 44).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, -10.0F, 0.6374F, 0.0F, 0.0F));

		PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin", CubeListBuilder.create().texOffs(44, 12).mirror().addBox(-4.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, 5.0F, -4.0F, 0.0F, 0.0F, -0.6829F));

		PartDefinition RightFin = Body.addOrReplaceChild("RightFin", CubeListBuilder.create().texOffs(44, 12).addBox(0.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 5.0F, -4.0F, 0.0F, 0.0F, 0.6829F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Catfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
