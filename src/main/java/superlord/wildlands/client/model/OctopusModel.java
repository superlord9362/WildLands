package superlord.wildlands.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.common.entity.OctopusEntity;

/**
 * Octopus - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class OctopusModel<T extends OctopusEntity> extends EntityModel<T> {
    public ModelRenderer Body;
    public ModelRenderer Tentacle1;
    public ModelRenderer Tentacle2;
    public ModelRenderer Tentacle3;
    public ModelRenderer Tentacle4;
    public ModelRenderer Tentacle5;
    public ModelRenderer Tentacle6;
    public ModelRenderer Tentacle7;
    public ModelRenderer Tentacle8;
    public ModelRenderer Head;
    public ModelRenderer eye1;
    public ModelRenderer eye2;
    public ModelRenderer bend1;
    public ModelRenderer bend2;
    public ModelRenderer bend3;
    public ModelRenderer bend4;
    public ModelRenderer bend5;
    public ModelRenderer bend6;
    public ModelRenderer bend7;
    public ModelRenderer bend8;

    public OctopusModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Tentacle2 = new ModelRenderer(this, 32, 18);
        this.Tentacle2.setRotationPoint(4.0F, 21.0F, -2.5F);
        this.Tentacle2.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tentacle2, -0.2275909337942703F, 1.7301448395650794F, 0.0F);
        this.Tentacle7 = new ModelRenderer(this, 32, 18);
        this.Tentacle7.setRotationPoint(-4.0F, 21.0F, 2.5F);
        this.Tentacle7.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tentacle7, -0.2275909337942703F, -1.2747885016356248F, 0.0F);
        this.bend5 = new ModelRenderer(this, 50, 0);
        this.bend5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bend5.addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.eye2 = new ModelRenderer(this, 37, 0);
        this.eye2.mirror = true;
        this.eye2.setRotationPoint(-1.0F, -6.0F, -1.0F);
        this.eye2.addBox(-3.0F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Tentacle8 = new ModelRenderer(this, 32, 18);
        this.Tentacle8.setRotationPoint(-2.0F, 21.0F, 4.0F);
        this.Tentacle8.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tentacle8, -0.2275909337942703F, -0.2275909337942703F, 0.0F);
        this.bend8 = new ModelRenderer(this, 50, 0);
        this.bend8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bend8.addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Tentacle1 = new ModelRenderer(this, 32, 18);
        this.Tentacle1.setRotationPoint(2.5F, 21.0F, -4.0F);
        this.Tentacle1.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tentacle1, -0.2275909337942703F, 2.9595549404385166F, 0.0F);
        this.Tentacle5 = new ModelRenderer(this, 32, 18);
        this.Tentacle5.setRotationPoint(-2.5F, 21.0F, -4.0F);
        this.Tentacle5.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tentacle5, -0.2275909337942703F, -2.9595549404385166F, 0.0F);
        this.bend2 = new ModelRenderer(this, 50, 0);
        this.bend2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bend2.addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.eye1 = new ModelRenderer(this, 37, 0);
        this.eye1.setRotationPoint(1.0F, -6.0F, -1.0F);
        this.eye1.addBox(0.0F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Tentacle6 = new ModelRenderer(this, 32, 18);
        this.Tentacle6.setRotationPoint(-4.0F, 21.0F, -2.5F);
        this.Tentacle6.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tentacle6, -0.2275909337942703F, -1.7301448395650794F, 0.0F);
        this.Tentacle4 = new ModelRenderer(this, 32, 18);
        this.Tentacle4.setRotationPoint(2.0F, 21.0F, 4.0F);
        this.Tentacle4.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tentacle4, -0.2275909337942703F, 0.2275909337942703F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.Head.addBox(-7.0F, -5.0F, 0.0F, 14.0F, 9.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Head, -0.18203784630933073F, 0.0F, 0.0F);
        this.bend6 = new ModelRenderer(this, 50, 0);
        this.bend6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bend6.addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.bend7 = new ModelRenderer(this, 50, 0);
        this.bend7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bend7.addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.bend1 = new ModelRenderer(this, 50, 0);
        this.bend1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bend1.addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 18);
        this.Body.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.Body.addBox(-4.0F, -6.0F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.Tentacle3 = new ModelRenderer(this, 32, 18);
        this.Tentacle3.setRotationPoint(4.0F, 21.0F, 2.5F);
        this.Tentacle3.addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tentacle3, -0.2275909337942703F, 1.2747885016356248F, 0.0F);
        this.bend3 = new ModelRenderer(this, 50, 0);
        this.bend3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bend3.addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.bend4 = new ModelRenderer(this, 50, 0);
        this.bend4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bend4.addBox(-1.5F, -3.0F, 6.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Tentacle5.addChild(this.bend5);
        this.Body.addChild(this.eye2);
        this.Tentacle8.addChild(this.bend8);
        this.Tentacle2.addChild(this.bend2);
        this.Body.addChild(this.eye1);
        this.Body.addChild(this.Head);
        this.Tentacle6.addChild(this.bend6);
        this.Tentacle7.addChild(this.bend7);
        this.Tentacle1.addChild(this.bend1);
        this.Tentacle3.addChild(this.bend3);
        this.Tentacle4.addChild(this.bend4);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Tentacle2, this.Tentacle7, this.Tentacle8, this.Tentacle1, this.Tentacle5, this.Tentacle6, this.Tentacle4, this.Body, this.Tentacle3).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(OctopusEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	if (entityIn.isScared()) {
    		float speed = 1.0f;
    		float degree = 1.0f;
    		this.Tentacle1.rotateAngleZ = 0;
    		this.Tentacle2.rotateAngleZ = 0;
    		this.Tentacle3.rotateAngleZ = 0;
    		this.Tentacle4.rotateAngleZ = 0;
    		this.Tentacle5.rotateAngleZ = 0;
    		this.Tentacle6.rotateAngleZ = 0;
    		this.Tentacle7.rotateAngleZ = 0;
    		this.Tentacle8.rotateAngleZ = 0;
    		this.Tentacle1.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle2.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle3.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Head.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 0.5F * limbSwingAmount;
    		this.Tentacle4.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle5.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle6.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle7.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    		this.Tentacle8.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 2.0F * limbSwingAmount - 1.0F;
    	} else {
    		float speed = 1.0f;
    		float degree = 1.0f;
    		this.Tentacle1.rotateAngleX = MathHelper.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle2.rotateAngleX = MathHelper.cos(1.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle3.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Head.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 0.5F * limbSwingAmount;
    		this.Tentacle4.rotateAngleX = MathHelper.cos(4.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle5.rotateAngleX = MathHelper.cos(4.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle6.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle7.rotateAngleX = MathHelper.cos(1.0F + limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle8.rotateAngleX = MathHelper.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle1.rotateAngleZ = MathHelper.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle2.rotateAngleZ = MathHelper.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle4.rotateAngleZ = MathHelper.cos(limbSwing * speed * 1.5F) * degree * 1.0F * limbSwingAmount;
    		this.Tentacle5.rotateAngleZ = MathHelper.cos(3.0F + limbSwing * speed * 1.5F) * degree * -1.0F * limbSwingAmount;
    		this.Tentacle8.rotateAngleZ = MathHelper.cos(limbSwing * speed * 1.5F) * degree * -1.0F * limbSwingAmount;

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
