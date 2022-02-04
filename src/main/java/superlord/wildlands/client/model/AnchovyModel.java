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
 * AnchovyModel - superlord9362
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class AnchovyModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer Body;
    public ModelRenderer Tail;
    public ModelRenderer PecFin;
    public ModelRenderer TailFin;

    public AnchovyModel() {
        this.textureWidth = 11;
        this.textureHeight = 15;
        this.PecFin = new ModelRenderer(this, 7, 0);
        this.PecFin.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.PecFin.addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.TailFin = new ModelRenderer(this, 0, 9);
        this.TailFin.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.TailFin.addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Tail = new ModelRenderer(this, 0, 6);
        this.Tail.setRotationPoint(0.5F, 1.0F, 4.0F);
        this.Tail.addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 0);
        this.Body.setRotationPoint(-0.5F, 22.0F, -5.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.Tail.addChild(this.PecFin);
        this.Tail.addChild(this.TailFin);
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
