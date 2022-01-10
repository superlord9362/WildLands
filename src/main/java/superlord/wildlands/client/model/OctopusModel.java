package superlord.wildlands.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Octopus - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class OctopusModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer Body;
	public ModelRenderer Head;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	public ModelRenderer leg5;
	public ModelRenderer leg6;
	public ModelRenderer leg7;
	public ModelRenderer leg8;

	public OctopusModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.leg1 = new ModelRenderer(this, 0, 34);
		this.leg1.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.leg1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leg1, -1.1383037594559906F, 0.0F, 0.0F);
		this.leg2 = new ModelRenderer(this, 0, 34);
		this.leg2.setRotationPoint(2.5F, 0.0F, -2.5F);
		this.leg2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leg2, -1.1383037594559906F, -0.7853981633974483F, 0.0F);
		this.leg3 = new ModelRenderer(this, 18, 34);
		this.leg3.setRotationPoint(3.0F, 0.0F, 0.0F);
		this.leg3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leg3, 0.0F, 0.0F, -1.1383037594559906F);
		this.leg5 = new ModelRenderer(this, 9, 34);
		this.leg5.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.leg5.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leg5, 1.1383037594559906F, 0.0F, 0.0F);
		this.leg7 = new ModelRenderer(this, 27, 34);
		this.leg7.setRotationPoint(-3.0F, 0.0F, 0.0F);
		this.leg7.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leg7, 0.0F, 0.0F, 1.1383037594559906F);
		this.leg4 = new ModelRenderer(this, 9, 34);
		this.leg4.setRotationPoint(2.5F, 0.0F, 2.5F);
		this.leg4.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leg4, 1.1383037594559906F, 0.7853981633974483F, 0.0F);
		this.leg8 = new ModelRenderer(this, 0, 34);
		this.leg8.setRotationPoint(-2.5F, 0.0F, -2.5F);
		this.leg8.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leg8, -1.1383037594559906F, 0.7853981633974483F, 0.0F);
		this.Body = new ModelRenderer(this, 0, 18);
		this.Body.setRotationPoint(0.0F, 19.0F, 0.0F);
		this.Body.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.leg6 = new ModelRenderer(this, 9, 34);
		this.leg6.setRotationPoint(-2.5F, 0.0F, 2.5F);
		this.leg6.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leg6, 1.1383037594559906F, -0.7853981633974483F, 0.0F);
		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setRotationPoint(0.0F, -8.0F, 0.0F);
		this.Head.addBox(-7.0F, -5.0F, 0.0F, 14.0F, 9.0F, 9.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(Head, -0.18203784630933073F, 0.0F, 0.0F);
		this.Body.addChild(this.leg1);
		this.Body.addChild(this.leg2);
		this.Body.addChild(this.leg3);
		this.Body.addChild(this.leg5);
		this.Body.addChild(this.leg7);
		this.Body.addChild(this.leg4);
		this.Body.addChild(this.leg8);
		this.Body.addChild(this.leg6);
		this.Body.addChild(this.Head);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
		ImmutableList.of(this.Body).forEach((modelRenderer) -> { 
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		
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
