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
 * HammerheadSharkModel - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class HammerheadSharkModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer Body;
    public ModelRenderer Tail;
    public ModelRenderer Dorsal;
    public ModelRenderer Neck;
    public ModelRenderer LeftFin;
    public ModelRenderer RightFin;
    public ModelRenderer Caudalfin;
    public ModelRenderer Dorsal2;
    public ModelRenderer pelvicleft;
    public ModelRenderer pelvicright;
    public ModelRenderer Head;

    public HammerheadSharkModel() {
        this.textureWidth = 128;
        this.textureHeight = 80;
        this.Dorsal = new ModelRenderer(this, 88, 17);
        this.Dorsal.setRotationPoint(0.0F, -5.0F, 2.0F);
        this.Dorsal.addBox(-0.5F, -11.0F, 0.0F, 1.0F, 11.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Dorsal, -0.3186971254089062F, 0.0F, 0.0F);
        this.pelvicright = new ModelRenderer(this, 28, 31);
        this.pelvicright.setRotationPoint(-2.0F, 3.0F, 0.0F);
        this.pelvicright.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(pelvicright, 0.27314402127920984F, 0.0F, 0.3186971254089062F);
        this.RightFin = new ModelRenderer(this, 38, 54);
        this.RightFin.mirror = true;
        this.RightFin.setRotationPoint(-6.0F, 4.0F, -5.0F);
        this.RightFin.addBox(-9.0F, 0.0F, 0.0F, 9.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightFin, 0.0F, 0.18203784630933073F, -0.591841146688116F);
        this.Dorsal2 = new ModelRenderer(this, 45, 11);
        this.Dorsal2.setRotationPoint(0.0F, -3.0F, 3.0F);
        this.Dorsal2.addBox(-0.5F, -4.0F, 0.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Dorsal2, -0.3186971254089062F, 0.0F, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 48);
        this.Tail.setRotationPoint(0.0F, 1.0F, 12.0F);
        this.Tail.addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 44, 0);
        this.Head.setRotationPoint(0.0F, 2.0F, -7.0F);
        this.Head.addBox(-12.0F, -2.0F, -7.0F, 24.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.pelvicleft = new ModelRenderer(this, 28, 31);
        this.pelvicleft.setRotationPoint(2.0F, 3.0F, 0.0F);
        this.pelvicleft.addBox(0.0F, 0.0F, 0.0F, 1.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(pelvicleft, 0.27314402127920984F, 0.0F, -0.3186971254089062F);
        this.Neck = new ModelRenderer(this, 0, 30);
        this.Neck.setRotationPoint(0.0F, 1.0F, -8.0F);
        this.Neck.addBox(-4.0F, -3.0F, -11.0F, 8.0F, 7.0F, 11.0F, 0.0F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 19.0F, -2.0F);
        this.Body.addBox(-6.0F, -5.0F, -8.0F, 12.0F, 10.0F, 20.0F, 0.0F, 0.0F, 0.0F);
        this.LeftFin = new ModelRenderer(this, 38, 54);
        this.LeftFin.setRotationPoint(6.0F, 4.0F, -5.0F);
        this.LeftFin.addBox(0.0F, 0.0F, 0.0F, 9.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftFin, 0.0F, -0.18203784630933073F, 0.591841146688116F);
        this.Caudalfin = new ModelRenderer(this, 72, 28);
        this.Caudalfin.setRotationPoint(0.0F, 0.0F, 15.0F);
        this.Caudalfin.addBox(-0.5F, -11.0F, 0.0F, 1.0F, 17.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.Body.addChild(this.Dorsal);
        this.Tail.addChild(this.pelvicright);
        this.Body.addChild(this.RightFin);
        this.Tail.addChild(this.Dorsal2);
        this.Body.addChild(this.Tail);
        this.Neck.addChild(this.Head);
        this.Tail.addChild(this.pelvicleft);
        this.Body.addChild(this.Neck);
        this.Body.addChild(this.LeftFin);
        this.Tail.addChild(this.Caudalfin);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	float f = 1.0F;
        if (!entityIn.isInWater()) {
           f = 1.5F;
        }
        this.Body.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.Body.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.Tail.rotateAngleY = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
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
