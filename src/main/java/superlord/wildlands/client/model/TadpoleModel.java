package superlord.wildlands.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * TadpoleModel - superlord9362
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class TadpoleModel<T extends LivingEntity> extends EntityModel<T> {
	public ModelRenderer Tad;
	public ModelRenderer Pole;
	public ModelRenderer Slime;

	public TadpoleModel() {
		super(RenderType::getEntityTranslucent);
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.Tad = new ModelRenderer(this, 0, 0);
		this.Tad.setRotationPoint(0.0F, 22.0F, -3.0F);
		this.Tad.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.Pole = new ModelRenderer(this, 0, 1);
		this.Pole.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.Pole.addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(Pole, 0.0F, -0.02652900429741867F, 0.038920842652368684F);
		this.Slime = new ModelRenderer(this, 16, 0);
		this.Slime.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Slime.addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.5F, 0.5F, 0.5F);
		this.Tad.addChild(this.Pole);
		this.Tad.addChild(this.Slime);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
		ImmutableList.of(this.Tad).forEach((modelRenderer) -> { 
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 0.8f;
		float degree = 1.0f;
		this.Tad.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Tad.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.Pole.rotateAngleY = (-0.12F * MathHelper.sin(0.2F * ageInTicks / 5)) + (MathHelper.cos(limbSwing * speed * 0.15F) * degree * 0.2F * limbSwingAmount);
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
