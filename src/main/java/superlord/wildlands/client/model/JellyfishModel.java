package superlord.wildlands.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * JellyfishModel - superlord9362
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class JellyfishModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer JellyFish;
    public ModelRenderer Cape;
    public ModelRenderer TentaclesFront;
    public ModelRenderer TentaclesBack;
    public ModelRenderer TentaclesLeft;
    public ModelRenderer TentaclesRight;

    public JellyfishModel() {
		super(RenderType::getEntityTranslucent);
        this.textureWidth = 64;
        this.textureHeight = 48;
        this.JellyFish = new ModelRenderer(this, 24, 31);
        this.JellyFish.setRotationPoint(-5.0F, 8.0F, -5.0F);
        this.JellyFish.addBox(0.0F, 0.0F, 0.0F, 10.0F, 7.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.TentaclesRight = new ModelRenderer(this, 0, 32);
        this.TentaclesRight.setRotationPoint(-1.0F, 8.0F, 5.0F);
        this.TentaclesRight.addBox(0.0F, 0.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.TentaclesFront = new ModelRenderer(this, 0, 40);
        this.TentaclesFront.setRotationPoint(5.0F, 8.0F, -1.0F);
        this.TentaclesFront.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.TentaclesBack = new ModelRenderer(this, 0, 40);
        this.TentaclesBack.setRotationPoint(5.0F, 8.0F, 11.0F);
        this.TentaclesBack.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.Cape = new ModelRenderer(this, 0, 0);
        this.Cape.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cape.addBox(-3.0F, -2.0F, -3.0F, 16.0F, 10.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.TentaclesLeft = new ModelRenderer(this, 0, 32);
        this.TentaclesLeft.setRotationPoint(11.0F, 8.0F, 5.0F);
        this.TentaclesLeft.addBox(0.0F, 0.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.JellyFish.addChild(this.TentaclesRight);
        this.JellyFish.addChild(this.TentaclesFront);
        this.JellyFish.addChild(this.TentaclesBack);
        this.JellyFish.addChild(this.Cape);
        this.JellyFish.addChild(this.TentaclesLeft);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.JellyFish).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	float speed = 1.0f;
    	float degree = 1.0f;
    	this.TentaclesFront.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 1.0F * limbSwingAmount;
    	this.TentaclesBack.rotateAngleX = MathHelper.cos(3.0F + limbSwing * speed * 0.05F) * degree * 1.0F * limbSwingAmount;
    	this.TentaclesRight.rotateAngleZ = MathHelper.cos(3.0F + limbSwing * speed * 0.05F) * degree * 1.0F * limbSwingAmount;
    	this.TentaclesLeft.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 1.0F * limbSwingAmount;
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
