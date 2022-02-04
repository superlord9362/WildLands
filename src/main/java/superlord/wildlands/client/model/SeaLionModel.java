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
 * Sea Lion - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class SeaLionModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer body;
	public ModelRenderer frontflipper1;
	public ModelRenderer frontflipper2;
	public ModelRenderer head;
	public ModelRenderer hindflipper1;
	public ModelRenderer hindflipper2;
	public ModelRenderer snout;
	public ModelRenderer ear1;
	public ModelRenderer ear2;

	public SeaLionModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.hindflipper1 = new ModelRenderer(this, 36, 28);
		this.hindflipper1.setRotationPoint(4.0F, 6.0F, 6.0F);
		this.hindflipper1.addBox(0.0F, 0.0F, -1.0F, 8.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(hindflipper1, 0.0F, -0.45535640450848164F, 0.0F);
		this.ear2 = new ModelRenderer(this, 0, 1);
		this.ear2.setRotationPoint(5.0F, -11.0F, 0.0F);
		this.ear2.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.snout = new ModelRenderer(this, 29, 0);
		this.snout.setRotationPoint(0.0F, -9.0F, -5.0F);
		this.snout.addBox(-3.0F, 0.0F, -2.0F, 6.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.ear1 = new ModelRenderer(this, 0, 1);
		this.ear1.setRotationPoint(-5.0F, -11.0F, 0.0F);
		this.ear1.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		this.hindflipper2 = new ModelRenderer(this, 36, 28);
		this.hindflipper2.mirror = true;
		this.hindflipper2.setRotationPoint(-4.0F, 6.0F, 6.0F);
		this.hindflipper2.addBox(-8.0F, 0.0F, -1.0F, 8.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(hindflipper2, 0.0F, 0.45535640450848164F, 0.0F);
		this.frontflipper1 = new ModelRenderer(this, 0, 28);
		this.frontflipper1.setRotationPoint(5.0F, 23.0F, -5.0F);
		this.frontflipper1.addBox(-1.0F, 0.0F, -2.0F, 10.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -3.0F, -9.0F);
		this.head.addBox(-5.0F, -14.0F, -5.0F, 10.0F, 18.0F, 9.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 35);
		this.body.setRotationPoint(0.0F, 17.0F, 4.0F);
		this.body.addBox(-7.0F, -5.0F, -9.0F, 14.0F, 11.0F, 18.0F, 0.0F, 0.0F, 0.0F);
		this.frontflipper2 = new ModelRenderer(this, 0, 28);
		this.frontflipper2.mirror = true;
		this.frontflipper2.setRotationPoint(-5.0F, 23.0F, -5.0F);
		this.frontflipper2.addBox(-9.0F, 0.0F, -2.0F, 10.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.body.addChild(this.hindflipper1);
		this.head.addChild(this.ear2);
		this.head.addChild(this.snout);
		this.head.addChild(this.ear1);
		this.body.addChild(this.hindflipper2);
		this.body.addChild(this.head);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
		ImmutableList.of(this.frontflipper1, this.body, this.frontflipper2).forEach((modelRenderer) -> { 
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		float speed = 1.0f;
		float degree = 1.0f;
		if (entityIn.isSwimming()) {
			this.body.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.head.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * -0.5F * limbSwingAmount;
			this.frontflipper1.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper1.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper1.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper2.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper2.rotateAngleY = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.frontflipper2.rotateAngleZ = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper1.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper1.rotateAngleY = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper1.rotateAngleZ = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper2.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper2.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
			this.hindflipper2.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 1.0F * limbSwingAmount;
		} else {
			this.body.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.25F * limbSwingAmount;
			this.head.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * -0.25F * limbSwingAmount;
			this.frontflipper1.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper1.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper1.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper2.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper2.rotateAngleY = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.frontflipper2.rotateAngleZ = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.hindflipper1.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.hindflipper1.rotateAngleY = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.hindflipper1.rotateAngleZ = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.hindflipper2.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.hindflipper2.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
			this.hindflipper2.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.5F * limbSwingAmount;
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
