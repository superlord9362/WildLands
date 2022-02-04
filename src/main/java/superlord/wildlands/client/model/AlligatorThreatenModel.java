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
public class AlligatorThreatenModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer Body;
    public ModelRenderer Head;
    public ModelRenderer leftlegfront;
    public ModelRenderer leftlegback;
    public ModelRenderer rightlegfront;
    public ModelRenderer rightlegback;
    public ModelRenderer Tail;
    public ModelRenderer Tail2;
    public ModelRenderer UpperJaw;
    public ModelRenderer LeftEye;
    public ModelRenderer LeftEye_1;
    public ModelRenderer Teeth;
    public ModelRenderer leftfootfront;
    public ModelRenderer leftfootback;
    public ModelRenderer rightfootfront;
    public ModelRenderer leftfootback_1;

    public AlligatorThreatenModel() {
        this.textureWidth = 128;
        this.textureHeight = 80;
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.Body.addBox(-6.5F, -2.0F, -10.0F, 13.0F, 8.0F, 20.0F, 0.0F, 0.0F, 0.0F);
        this.leftfootback_1 = new ModelRenderer(this, 93, 12);
        this.leftfootback_1.mirror = true;
        this.leftfootback_1.setRotationPoint(1.0F, 6.0F, 1.0F);
        this.leftfootback_1.addBox(-5.0F, 0.0F, -6.0F, 6.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 49, 50);
        this.Head.setRotationPoint(0.0F, 18.0F, -7.0F);
        this.Head.addBox(-4.5F, 0.0F, -17.0F, 9.0F, 2.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.rightlegfront = new ModelRenderer(this, 54, 0);
        this.rightlegfront.setRotationPoint(-8.0F, 18.0F, -6.0F);
        this.rightlegfront.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leftfootback = new ModelRenderer(this, 93, 12);
        this.leftfootback.setRotationPoint(-1.0F, 6.0F, 1.0F);
        this.leftfootback.addBox(-1.0F, 0.0F, -6.0F, 6.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.UpperJaw = new ModelRenderer(this, 0, 59);
        this.UpperJaw.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.UpperJaw.addBox(-4.5F, -4.0F, -15.0F, 9.0F, 4.0F, 15.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(UpperJaw, -0.6981317007977318F, 0.0F, 0.0F);
        this.leftlegback = new ModelRenderer(this, 103, 0);
        this.leftlegback.setRotationPoint(8.5F, 18.0F, 8.0F);
        this.leftlegback.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.LeftEye = new ModelRenderer(this, 0, 0);
        this.LeftEye.setRotationPoint(0.5F, -2.0F, 0.0F);
        this.LeftEye.addBox(0.0F, -4.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.leftfootfront = new ModelRenderer(this, 48, 12);
        this.leftfootfront.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.leftfootfront.addBox(-1.5F, 0.0F, -4.0F, 5.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.Tail2 = new ModelRenderer(this, 47, 5);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Tail2.addBox(-3.5F, -4.0F, 0.0F, 7.0F, 3.0F, 24.0F, 0.0F, 0.0F, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 28);
        this.Tail.setRotationPoint(0.0F, 1.0F, 10.0F);
        this.Tail.addBox(-3.5F, -1.0F, 0.0F, 7.0F, 7.0F, 24.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tail, -0.0911061832922575F, 0.0F, 0.0F);
        this.LeftEye_1 = new ModelRenderer(this, 0, 0);
        this.LeftEye_1.mirror = true;
        this.LeftEye_1.setRotationPoint(-0.5F, -2.0F, 0.0F);
        this.LeftEye_1.addBox(-3.0F, -4.0F, -4.0F, 3.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.rightlegback = new ModelRenderer(this, 103, 0);
        this.rightlegback.setRotationPoint(-8.5F, 18.0F, 8.0F);
        this.rightlegback.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.rightfootfront = new ModelRenderer(this, 48, 12);
        this.rightfootfront.mirror = true;
        this.rightfootfront.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.rightfootfront.addBox(-3.5F, 0.0F, -4.0F, 5.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.leftlegfront = new ModelRenderer(this, 54, 0);
        this.leftlegfront.setRotationPoint(8.0F, 18.0F, -6.0F);
        this.leftlegfront.addBox(-1.5F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Teeth = new ModelRenderer(this, 40, 33);
        this.Teeth.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Teeth.addBox(-4.5F, 0.0F, -15.0F, 9.0F, 1.0F, 15.0F, 0.0F, 0.0F, 0.0F);
        this.rightlegback.addChild(this.leftfootback_1);
        this.leftlegback.addChild(this.leftfootback);
        this.Head.addChild(this.UpperJaw);
        this.UpperJaw.addChild(this.LeftEye);
        this.leftlegfront.addChild(this.leftfootfront);
        this.Tail.addChild(this.Tail2);
        this.Body.addChild(this.Tail);
        this.UpperJaw.addChild(this.LeftEye_1);
        this.rightlegfront.addChild(this.rightfootfront);
        this.UpperJaw.addChild(this.Teeth);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Body, this.Head, this.rightlegfront, this.leftlegback, this.rightlegback, this.leftlegfront).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	float speed = 1.0f;
		float degree = 1.0f;
		this.Head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.Tail.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 0.75F * limbSwingAmount;
		this.Head.rotateAngleX = (MathHelper.cos(limbSwing * speed * 0.02F) * degree * 0.25F * limbSwingAmount) + (headPitch * ((float)Math.PI / 180F));
		this.leftlegfront.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.25F) * degree * 1.0F * limbSwingAmount;
		this.leftlegback.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 0.25F) * degree * 1.0F * limbSwingAmount;
		this.rightlegfront.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 0.25F) * degree * 1.0F * limbSwingAmount;
		this.rightlegback.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.25F) * degree * 1.0F * limbSwingAmount;
		this.Tail.rotateAngleY = 0;
		this.leftlegfront.rotationPointY = Math.abs(MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.2F * limbSwingAmount) + 17.5F;
		this.leftlegback.rotationPointY = Math.abs(MathHelper.cos(3.0F + limbSwing * speed * 0.2F) * degree * 0.2F * limbSwingAmount) + 17.5F;
		this.rightlegfront.rotationPointY = Math.abs(MathHelper.cos(3.0F + limbSwing * speed * 0.2F) * degree * 0.2F * limbSwingAmount) + 17.5F;
		this.rightlegback.rotationPointY = Math.abs(MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.2F * limbSwingAmount) + 17.5F;
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
