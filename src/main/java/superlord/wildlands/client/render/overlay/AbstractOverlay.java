package superlord.wildlands.client.render.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractOverlay<T extends AnimalEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
   public AbstractOverlay(IEntityRenderer<T, M> p_i226039_1_) {
      super(p_i226039_1_);
   }

   public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
      if (entitylivingbaseIn.isChild() && !entitylivingbaseIn.isInvisible()) {
	   IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.getRenderType());
      this.getEntityModel().render(matrixStackIn, ivertexbuilder, 2000, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public abstract RenderType getRenderType();
}

