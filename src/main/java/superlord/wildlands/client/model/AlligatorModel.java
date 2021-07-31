package superlord.wildlands.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Alligator - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class AlligatorModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer Body;
	public ModelRenderer Head;
	public ModelRenderer rightlegfront;
	public ModelRenderer leftlegfront;
	public ModelRenderer leftlegback;
	public ModelRenderer rightlegback;
	public ModelRenderer Tail;
	public ModelRenderer Tail2;
	public ModelRenderer Teeth;
	public ModelRenderer UpperJaw;
	public ModelRenderer LeftEye;
	public ModelRenderer LeftEye_1;

	public AlligatorModel() {
		this.textureWidth = 128;
		this.textureHeight = 80;
		this.Tail = new ModelRenderer(this, 0, 28);
		this.Tail.setRotationPoint(0.0F, 1.0F, 10.0F);
		this.Tail.addBox(-3.5F, -1.0F, 0.0F, 7.0F, 7.0F, 24.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(Tail, -0.0911061832922575F, 0.0F, 0.0F);
		this.UpperJaw = new ModelRenderer(this, 0, 59);
		this.UpperJaw.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.UpperJaw.addBox(-4.5F, -2.0F, -15.0F, 9.0F, 2.0F, 15.0F, 0.0F, 0.0F, 0.0F);
		this.LeftEye_1 = new ModelRenderer(this, 0, 0);
		this.LeftEye_1.mirror = true;
		this.LeftEye_1.setRotationPoint(-0.5F, 0.0F, 0.0F);
		this.LeftEye_1.addBox(-3.0F, -4.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.leftlegfront = new ModelRenderer(this, 52, 5);
		this.leftlegfront.setRotationPoint(6.5F, 16.0F, -6.0F);
		this.leftlegfront.addBox(-2.0F, 1.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftlegfront, -0.2275909337942703F, 0.0F, -0.500909508638178F);
		this.LeftEye = new ModelRenderer(this, 0, 0);
		this.LeftEye.setRotationPoint(0.5F, 0.0F, 0.0F);
		this.LeftEye.addBox(0.0F, -4.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.rightlegfront = new ModelRenderer(this, 52, 5);
		this.rightlegfront.setRotationPoint(-6.5F, 16.0F, -6.0F);
		this.rightlegfront.addBox(-2.0F, 1.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightlegfront, -0.2275909337942703F, 0.0F, 0.500909508638178F);
		this.Tail2 = new ModelRenderer(this, 47, 5);
		this.Tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Tail2.addBox(-3.5F, -4.0F, 0.0F, 7.0F, 3.0F, 24.0F, 0.0F, 0.0F, 0.0F);
		this.leftlegback = new ModelRenderer(this, 52, 5);
		this.leftlegback.setRotationPoint(6.5F, 16.0F, 6.0F);
		this.leftlegback.addBox(-2.0F, 1.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftlegback, 0.2275909337942703F, 0.0F, -0.500909508638178F);
		this.rightlegback = new ModelRenderer(this, 52, 5);
		this.rightlegback.setRotationPoint(-6.5F, 16.0F, 6.0F);
		this.rightlegback.addBox(-2.0F, 1.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightlegback, 0.2275909337942703F, 0.0F, 0.500909508638178F);
		this.Head = new ModelRenderer(this, 47, 50);
		this.Head.setRotationPoint(0.0F, 15.0F, -7.0F);
		this.Head.addBox(-5.5F, 0.0F, -19.0F, 11.0F, 4.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.Teeth = new ModelRenderer(this, 39, 32);
		this.Teeth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Teeth.addBox(-5.5F, -1.0F, -19.0F, 11.0F, 1.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.Body = new ModelRenderer(this, 0, 0);
		this.Body.setRotationPoint(0.0F, 15.0F, 0.0F);
		this.Body.addBox(-7.5F, -2.0F, -10.0F, 15.0F, 8.0F, 20.0F, 0.0F, 0.0F, 0.0F);
		this.Body.addChild(this.Tail);
		this.Head.addChild(this.UpperJaw);
		this.UpperJaw.addChild(this.LeftEye_1);
		this.UpperJaw.addChild(this.LeftEye);
		this.Tail.addChild(this.Tail2);
		this.Head.addChild(this.Teeth);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
		ImmutableList.of(this.leftlegfront, this.rightlegfront, this.leftlegback, this.rightlegback, this.Head, this.Body).forEach((modelRenderer) -> { 
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		if(entityIn.isInWater()) {
			this.Tail.rotateAngleY = -1.0F * 0.45F * MathHelper.sin(0.6F * ageInTicks);
		} else {
			this.rightlegback.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.leftlegback.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
			this.rightlegfront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
			this.leftlegfront.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		}
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
