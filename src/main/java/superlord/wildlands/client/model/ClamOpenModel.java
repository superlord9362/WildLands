package superlord.wildlands.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.entity.ClamEntity;

/**
 * Clam - Weastian
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ClamOpenModel extends EntityModel<ClamEntity> {
    public ModelRenderer Base;
    public ModelRenderer Base2;
    public ModelRenderer Top;
    public ModelRenderer shape4;

    public ClamOpenModel() {
        this.textureWidth = 64;
        this.textureHeight = 80;
        this.Top = new ModelRenderer(this, 0, 0);
        this.Top.setRotationPoint(0.0F, -2.0F, 8.0F);
        this.Top.addBox(-8.0F, -7.0F, -16.0F, 16.0F, 10.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Top, -0.7853981633974483F, 0.0F, 0.0F);
        this.Base2 = new ModelRenderer(this, 0, 47);
        this.Base2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Base2.addBox(-8.0F, -4.0F, -8.0F, 16.0F, 4.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.Base = new ModelRenderer(this, 0, 26);
        this.Base.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.Base.addBox(-8.0F, 0.0F, -8.0F, 16.0F, 5.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 68);
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape4.addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.Base.addChild(this.Top);
        this.Base.addChild(this.Base2);
        this.Base.addChild(this.shape4);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Base).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(ClamEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
