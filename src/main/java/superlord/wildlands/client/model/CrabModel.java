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
 * Crab - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CrabModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer leg1l;
    public ModelRenderer leg2l;
    public ModelRenderer leg3l;
    public ModelRenderer leg1r;
    public ModelRenderer leg2r;
    public ModelRenderer leg3r;
    public ModelRenderer eye1;
    public ModelRenderer eye2;
    public ModelRenderer claw1r;
    public ModelRenderer claw1l;
    public ModelRenderer bigclaw1;
    public ModelRenderer claw2r;
    public ModelRenderer claw2l;
    public ModelRenderer bigclaw2;

    public CrabModel() {
        this.textureWidth = 48;
        this.textureHeight = 32;
        this.eye1 = new ModelRenderer(this, 25, 0);
        this.eye1.setRotationPoint(-1.5F, -3.0F, -1.5F);
        this.eye1.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.claw2l = new ModelRenderer(this, 10, 17);
        this.claw2l.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.claw2l.addBox(-1.0F, -2.0F, -6.0F, 2.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.leg3l = new ModelRenderer(this, 0, 0);
        this.leg3l.setRotationPoint(3.0F, 21.0F, -4.0F);
        this.leg3l.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.claw1r = new ModelRenderer(this, 0, 23);
        this.claw1r.setRotationPoint(-7.0F, 0.0F, -3.0F);
        this.claw1r.addBox(-1.0F, 0.0F, -6.0F, 2.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(claw1r, 0.0F, -1.092750655326294F, 0.0F);
        this.leg2l = new ModelRenderer(this, 0, 0);
        this.leg2l.setRotationPoint(-3.0F, 21.0F, -4.0F);
        this.leg2l.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.body.addBox(-4.5F, -3.0F, -3.5F, 9.0F, 5.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(body, 0.0F, 1.5707963267948966F, 0.0F);
        this.leg1l = new ModelRenderer(this, 0, 0);
        this.leg1l.setRotationPoint(0.0F, 21.0F, -4.0F);
        this.leg1l.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.leg1r = new ModelRenderer(this, 0, 0);
        this.leg1r.setRotationPoint(0.0F, 21.0F, 4.0F);
        this.leg1r.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.bigclaw2 = new ModelRenderer(this, 23, 6);
        this.bigclaw2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bigclaw2.addBox(-1.5F, -3.0F, -9.0F, 3.0F, 4.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.claw1l = new ModelRenderer(this, 32, 19);
        this.claw1l.setRotationPoint(7.0F, 0.0F, -3.0F);
        this.claw1l.addBox(-1.0F, 0.0F, -6.0F, 2.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(claw1l, 0.0F, 1.092750655326294F, 0.0F);
        this.claw2r = new ModelRenderer(this, 0, 14);
        this.claw2r.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.claw2r.addBox(-1.0F, -2.0F, -6.0F, 2.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.eye2 = new ModelRenderer(this, 25, 0);
        this.eye2.setRotationPoint(1.5F, -3.0F, -1.5F);
        this.eye2.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.leg2r = new ModelRenderer(this, 0, 0);
        this.leg2r.setRotationPoint(-3.0F, 21.0F, 4.0F);
        this.leg2r.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.leg3r = new ModelRenderer(this, 0, 0);
        this.leg3r.setRotationPoint(3.0F, 21.0F, 4.0F);
        this.leg3r.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.bigclaw1 = new ModelRenderer(this, 17, 19);
        this.bigclaw1.setRotationPoint(8.0F, -1.0F, 0.0F);
        this.bigclaw1.addBox(-1.5F, 0.0F, -9.0F, 3.0F, 4.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(bigclaw1, 0.0911061832922575F, 0.6373942508178124F, 0.0F);
        this.body.addChild(this.eye1);
        this.claw1l.addChild(this.claw2l);
        this.body.addChild(this.claw1r);
        this.bigclaw1.addChild(this.bigclaw2);
        this.body.addChild(this.claw1l);
        this.claw1r.addChild(this.claw2r);
        this.body.addChild(this.eye2);
        this.body.addChild(this.bigclaw1);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.leg3l, this.leg2l, this.body, this.leg1l, this.leg1r, this.leg2r, this.leg3r).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	float speed = 1.0f;
    	float degree = 1.0f;
    	this.leg1l.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
    	this.leg2l.rotateAngleX = MathHelper.cos(2.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
    	this.leg3l.rotateAngleX = MathHelper.cos(2.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
    	this.claw1l.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
    	this.claw1l.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
    	this.claw2l.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.25F * limbSwingAmount - 0.1F;
    	this.claw1r.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
    	this.claw1r.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.1F * limbSwingAmount;
    	this.claw2r.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.25F * limbSwingAmount - 0.1F;
    	this.bigclaw1.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount;
    	this.bigclaw1.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
    	this.bigclaw2.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.25F * limbSwingAmount - 0.1F;
    	this.leg1r.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
    	this.leg2r.rotateAngleX = MathHelper.cos(5.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;
    	this.leg3r.rotateAngleX = MathHelper.cos(5.0F + limbSwing * speed * 0.3F) * degree * 1.0F * limbSwingAmount;

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
