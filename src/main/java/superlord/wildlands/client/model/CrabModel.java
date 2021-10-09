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
import superlord.wildlands.entity.CrabEntity;

/**
 * Crab - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CrabModel<T extends Entity> extends EntityModel<CrabEntity> {
    public ModelRenderer Body;
    public ModelRenderer eye1;
    public ModelRenderer eye2;
    public ModelRenderer leftleg1;
    public ModelRenderer leftleg2;
    public ModelRenderer leftleg3;
    public ModelRenderer rightleg1;
    public ModelRenderer rightleg2;
    public ModelRenderer rightleg3;
    public ModelRenderer rightclaw;
    public ModelRenderer leftclaw;

    public CrabModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Body.addBox(-4.5F, -3.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.leftleg3 = new ModelRenderer(this, 8, 11);
        this.leftleg3.setRotationPoint(4.5F, 0.0F, 2.0F);
        this.leftleg3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftleg3, 0.500909508638178F, 0.0F, -0.6373942508178124F);
        this.rightleg3 = new ModelRenderer(this, 8, 11);
        this.rightleg3.setRotationPoint(-4.5F, 0.0F, 2.0F);
        this.rightleg3.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightleg3, 0.500909508638178F, 0.0F, 0.6373942508178124F);
        this.eye2 = new ModelRenderer(this, 0, 11);
        this.eye2.setRotationPoint(2.5F, -3.0F, -1.0F);
        this.eye2.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.rightleg2 = new ModelRenderer(this, 8, 11);
        this.rightleg2.setRotationPoint(-4.5F, 0.0F, 0.5F);
        this.rightleg2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightleg2, 0.0F, 0.0F, 0.8196066007575706F);
        this.leftleg2 = new ModelRenderer(this, 8, 11);
        this.leftleg2.setRotationPoint(4.5F, 0.0F, 0.5F);
        this.leftleg2.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftleg2, 0.0F, 0.0F, -0.8196066007575706F);
        this.leftclaw = new ModelRenderer(this, 0, 18);
        this.leftclaw.setRotationPoint(6.0F, -1.0F, -3.0F);
        this.leftclaw.addBox(-6.0F, -2.0F, -2.0F, 7.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftclaw, 0.0F, -0.3186971254089062F, -0.3186971254089062F);
        this.rightleg1 = new ModelRenderer(this, 8, 11);
        this.rightleg1.setRotationPoint(-4.5F, 0.0F, -1.0F);
        this.rightleg1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightleg1, -0.500909508638178F, 0.0F, 0.6373942508178124F);
        this.eye1 = new ModelRenderer(this, 0, 11);
        this.eye1.setRotationPoint(-2.5F, -3.0F, -1.0F);
        this.eye1.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.leftleg1 = new ModelRenderer(this, 8, 11);
        this.leftleg1.setRotationPoint(4.5F, 0.0F, -1.0F);
        this.leftleg1.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftleg1, -0.500909508638178F, 0.0F, -0.6373942508178124F);
        this.rightclaw = new ModelRenderer(this, 16, 11);
        this.rightclaw.setRotationPoint(-6.0F, 0.0F, -3.0F);
        this.rightclaw.addBox(-1.0F, -2.0F, -1.0F, 5.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightclaw, 0.0F, 0.591841146688116F, 0.3186971254089062F);
        this.Body.addChild(this.leftleg3);
        this.Body.addChild(this.rightleg3);
        this.Body.addChild(this.eye2);
        this.Body.addChild(this.rightleg2);
        this.Body.addChild(this.leftleg2);
        this.Body.addChild(this.leftclaw);
        this.Body.addChild(this.rightleg1);
        this.Body.addChild(this.eye1);
        this.Body.addChild(this.leftleg1);
        this.Body.addChild(this.rightclaw);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(CrabEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.leftleg1.rotateAngleZ = (-(float)Math.PI / 4F);
        this.rightleg1.rotateAngleZ = ((float)Math.PI / 4F);
        this.leftleg2.rotateAngleZ = -0.58119464F;
        this.rightleg2.rotateAngleZ = 0.58119464F;
        this.leftleg3.rotateAngleZ = -0.58119464F;
        this.rightleg3.rotateAngleZ = 0.58119464F;
        this.leftleg1.rotateAngleY = ((float)Math.PI / 4F);
        this.rightleg1.rotateAngleY = (-(float)Math.PI / 4F);
        this.leftleg2.rotateAngleY = ((float)Math.PI / 8F);
        this.rightleg2.rotateAngleY = (-(float)Math.PI / 8F);
        this.leftleg3.rotateAngleY = (-(float)Math.PI / 8F);
        this.rightleg3.rotateAngleY = ((float)Math.PI / 8F);
        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        this.leftleg1.rotateAngleY += f3;
        this.rightleg1.rotateAngleY += -f3;
        this.leftleg2.rotateAngleY += f4;
        this.rightleg2.rotateAngleY += -f4;
        this.leftleg3.rotateAngleY += f5;
        this.rightleg3.rotateAngleY += -f5;
        this.leftleg1.rotateAngleZ += f7;
        this.rightleg1.rotateAngleZ += -f7;
        this.leftleg2.rotateAngleZ += f8;
        this.rightleg2.rotateAngleZ += -f8;
        this.leftleg3.rotateAngleZ += f9;
        this.rightleg3.rotateAngleZ += -f9;
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
