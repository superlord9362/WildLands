package superlord.wildlands.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Grizzly - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class GrizzlySittingModel<T extends Entity> extends EntityModel<T> implements IHasArm {
    public ModelRenderer Body;
    public ModelRenderer FrontLeftLeg;
    public ModelRenderer FrontRightLeg;
    public ModelRenderer HindLeftLeg;
    public ModelRenderer HindRightLeg;
    public ModelRenderer Head;
    public ModelRenderer Snout;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;

    public GrizzlySittingModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 17.0F, 3.0F);
        this.Body.addBox(-11.0F, -9.0F, -20.0F, 22.0F, 18.0F, 27.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Body, -1.5707963267948966F, 0.0F, 0.0F);
        this.RightEar = new ModelRenderer(this, 0, 0);
        this.RightEar.setRotationPoint(-5.0F, -6.0F, -4.0F);
        this.RightEar.addBox(-2.0F, -3.0F, -1.0F, 4.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.FrontLeftLeg = new ModelRenderer(this, 100, 44);
        this.FrontLeftLeg.setRotationPoint(6.0F, 5.0F, -7.0F);
        this.FrontLeftLeg.addBox(-4.0F, 0.0F, -3.0F, 7.0F, 10.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.FrontRightLeg = new ModelRenderer(this, 100, 27);
        this.FrontRightLeg.setRotationPoint(-6.0F, 5.0F, -7.0F);
        this.FrontRightLeg.addBox(-3.0F, 0.0F, -3.0F, 7.0F, 10.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.HindRightLeg = new ModelRenderer(this, 50, 45);
        this.HindRightLeg.setRotationPoint(-6.0F, 20.0F, -3.0F);
        this.HindRightLeg.addBox(-3.0F, 0.0F, -3.0F, 7.0F, 10.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(HindRightLeg, -1.5707963267948966F, 0.45535640450848164F, 0.0F);
        this.LeftEar = new ModelRenderer(this, 0, 0);
        this.LeftEar.setRotationPoint(5.0F, -6.0F, -4.0F);
        this.LeftEar.addBox(-2.0F, -3.0F, -1.0F, 4.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.HindLeftLeg = new ModelRenderer(this, 50, 45);
        this.HindLeftLeg.setRotationPoint(6.0F, 20.0F, -3.0F);
        this.HindLeftLeg.addBox(-4.0F, 0.0F, -3.0F, 7.0F, 10.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(HindLeftLeg, -1.5707963267948966F, -0.45535640450848164F, 0.0F);
        this.Head = new ModelRenderer(this, 71, 3);
        this.Head.setRotationPoint(0.0F, -3.0F, -1.0F);
        this.Head.addBox(-8.0F, -6.0F, -11.0F, 16.0F, 13.0F, 11.0F, 0.0F, 0.0F, 0.0F);
        this.Snout = new ModelRenderer(this, 78, 45);
        this.Snout.setRotationPoint(0.0F, 1.0F, -11.0F);
        this.Snout.addBox(-3.0F, 0.0F, -5.0F, 6.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Head.addChild(this.RightEar);
        this.Head.addChild(this.LeftEar);
        this.Head.addChild(this.Snout);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Body, this.FrontLeftLeg, this.FrontRightLeg, this.HindRightLeg, this.HindLeftLeg, this.Head).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {
		float f = sideIn == HandSide.RIGHT ? 1.0F : -1.0F;
		ModelRenderer modelrenderer = this.getArmForSide(sideIn);
		modelrenderer.rotationPointX += f;
		modelrenderer.translateRotate(matrixStackIn);
		modelrenderer.rotationPointX -= f;
		matrixStackIn.translate(-0.1, -0.3, 0);
	}

	protected ModelRenderer getArmForSide(HandSide side) {
		return side == HandSide.LEFT ? this.FrontLeftLeg : this.FrontRightLeg;
	}
}
