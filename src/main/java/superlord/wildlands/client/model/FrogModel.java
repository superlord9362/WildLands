package superlord.wildlands.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.common.entity.FrogEntity;

/**
 * Frog - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class FrogModel extends EntityModel<FrogEntity> {
    public ModelRenderer Body;
    public ModelRenderer LeftLeg;
    public ModelRenderer RightLeg;
    public ModelRenderer poach;
    public ModelRenderer Head;
    public ModelRenderer RightLegfront;
    public ModelRenderer LeftLegfront;
    public ModelRenderer eye1;
    public ModelRenderer eye2;
    public ModelRenderer Leftfoot;
    public ModelRenderer Leftfoot_1;
    @SuppressWarnings("unused")
	private float jumpRotation;

    public FrogModel() {
        this.textureWidth = 48;
        this.textureHeight = 32;
        this.LeftLeg = new ModelRenderer(this, 32, 8);
        this.LeftLeg.setRotationPoint(4.0F, 22.0F, 2.0F);
        this.LeftLeg.addBox(0.0F, -2.0F, -4.0F, 3.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Leftfoot = new ModelRenderer(this, -5, 9);
        this.Leftfoot.setRotationPoint(1.5F, 2.0F, -2.0F);
        this.Leftfoot.addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.eye2 = new ModelRenderer(this, 0, 0);
        this.eye2.mirror = true;
        this.eye2.setRotationPoint(-1.0F, -1.0F, -6.0F);
        this.eye2.addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.poach = new ModelRenderer(this, 0, 22);
        this.poach.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.poach.addBox(-6.0F, 0.0F, -5.0F, 12.0F, 4.0F, 6.0F, -1.5F, 0.0F, -1.5F);
        this.RightLegfront = new ModelRenderer(this, 24, 0);
        this.RightLegfront.setRotationPoint(-2.5F, 3.0F, -4.0F);
        this.RightLegfront.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightLegfront, 0.2275909337942703F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 9);
        this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Body.addBox(-5.0F, -2.0F, -4.0F, 10.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.Leftfoot_1 = new ModelRenderer(this, -5, 9);
        this.Leftfoot_1.setRotationPoint(-1.5F, 2.0F, -2.0F);
        this.Leftfoot_1.addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -2.0F, 4.0F);
        this.Head.addBox(-5.0F, -1.0F, -8.0F, 10.0F, 1.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.LeftLegfront = new ModelRenderer(this, 24, 0);
        this.LeftLegfront.setRotationPoint(2.5F, 3.0F, -4.0F);
        this.LeftLegfront.addBox(-1.5F, 0.0F, -4.0F, 3.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftLegfront, 0.2275909337942703F, 0.0F, 0.0F);
        this.eye1 = new ModelRenderer(this, 0, 0);
        this.eye1.setRotationPoint(1.0F, -1.0F, -6.0F);
        this.eye1.addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 32, 8);
        this.RightLeg.setRotationPoint(-4.0F, 22.0F, 2.0F);
        this.RightLeg.addBox(-3.0F, -2.0F, -4.0F, 3.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.LeftLeg.addChild(this.Leftfoot);
        this.Head.addChild(this.eye2);
        this.Body.addChild(this.RightLegfront);
        this.RightLeg.addChild(this.Leftfoot_1);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.LeftLegfront);
        this.Head.addChild(this.eye1);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.LeftLeg, this.poach, this.Body, this.RightLeg).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(FrogEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	float f = ageInTicks - (float)entityIn.ticksExisted;
    	this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(f) * (float)Math.PI);
        this.LeftLeg.rotateAngleX = (limbSwingAmount * 50.0F) * ((float)Math.PI / 180F);
        this.RightLeg.rotateAngleX = (limbSwingAmount * 50.0F) * ((float)Math.PI / 180F);
        this.Leftfoot.rotateAngleX = limbSwingAmount * 50.0F * ((float)Math.PI / 180F);
        this.Leftfoot_1.rotateAngleX = limbSwingAmount * 50.0F * ((float)Math.PI / 180F);
        this.LeftLegfront.rotateAngleX = (limbSwingAmount * -40.0F + 15.0F) * ((float)Math.PI / 180F);
        this.RightLegfront.rotateAngleX = (limbSwingAmount * -40.0F + 15.0F) * ((float)Math.PI / 180F);
     }

     public void setLivingAnimations(FrogEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(partialTick) * (float)Math.PI);
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
