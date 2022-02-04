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
 * catfish - weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CatfishModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer Body;
    public ModelRenderer Tail;
    public ModelRenderer Dorsal;
    public ModelRenderer LeftWhisker;
    public ModelRenderer RightWhisker;
    public ModelRenderer BottomWhiskers;
    public ModelRenderer LeftFin;
    public ModelRenderer RightFin;
    public ModelRenderer Tailfin;
    public ModelRenderer BottomFin;

    public CatfishModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Body.addBox(-7.0F, -4.0F, -12.0F, 14.0F, 10.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.BottomFin = new ModelRenderer(this, 44, 2);
        this.BottomFin.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.BottomFin.addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.Tailfin = new ModelRenderer(this, 30, 18);
        this.Tailfin.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.Tailfin.addBox(0.0F, -6.0F, 0.0F, 0.0F, 12.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.RightWhisker = new ModelRenderer(this, 0, 44);
        this.RightWhisker.mirror = true;
        this.RightWhisker.setRotationPoint(-7.0F, 4.0F, -10.0F);
        this.RightWhisker.addBox(-6.0F, 0.0F, 0.0F, 6.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightWhisker, 0.0F, 0.5462880425584197F, 0.0F);
        this.RightFin = new ModelRenderer(this, 44, 12);
        this.RightFin.mirror = true;
        this.RightFin.setRotationPoint(-7.0F, 5.0F, -4.0F);
        this.RightFin.addBox(-4.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightFin, 0.0F, 0.0F, -0.6829473549475088F);
        this.LeftFin = new ModelRenderer(this, 44, 12);
        this.LeftFin.setRotationPoint(7.0F, 5.0F, -4.0F);
        this.LeftFin.addBox(0.0F, 0.0F, 0.0F, 4.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftFin, 0.0F, 0.0F, 0.6829473549475088F);
        this.BottomWhiskers = new ModelRenderer(this, 34, 44);
        this.BottomWhiskers.setRotationPoint(0.0F, 6.0F, -10.0F);
        this.BottomWhiskers.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(BottomWhiskers, 0.6373942508178124F, 0.0F, 0.0F);
        this.Dorsal = new ModelRenderer(this, 44, -3);
        this.Dorsal.setRotationPoint(0.0F, -4.0F, -3.0F);
        this.Dorsal.addBox(0.0F, -3.0F, 0.0F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.LeftWhisker = new ModelRenderer(this, 0, 44);
        this.LeftWhisker.setRotationPoint(7.0F, 4.0F, -10.0F);
        this.LeftWhisker.addBox(0.0F, 0.0F, 0.0F, 6.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftWhisker, 0.0F, -0.5462880425584197F, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 26);
        this.Tail.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.Tail.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 8.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.Tail.addChild(this.BottomFin);
        this.Tail.addChild(this.Tailfin);
        this.Body.addChild(this.RightWhisker);
        this.Body.addChild(this.RightFin);
        this.Body.addChild(this.LeftFin);
        this.Body.addChild(this.BottomWhiskers);
        this.Body.addChild(this.Dorsal);
        this.Body.addChild(this.LeftWhisker);
        this.Body.addChild(this.Tail);
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
