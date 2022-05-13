package superlord.wildlands.client.render;

public class ClamRenderer {/** extends MobRenderer<ClamEntity, EntityModel<ClamEntity>> {
	
	private static final ResourceLocation CLAM_1 = new ResourceLocation(WildLands.MOD_ID, "textures/entity/clam/clam1.png");
	private static final ResourceLocation CLAM_2 = new ResourceLocation(WildLands.MOD_ID, "textures/entity/clam/clam2.png");
	private static final ResourceLocation CLAM_3 = new ResourceLocation(WildLands.MOD_ID, "textures/entity/clam/clam3.png");
	private static final ClamModel CLOSED = new ClamModel();
	private static final ClamOpenModel OPEN = new ClamOpenModel();
	
	public ClamRenderer() {
		super(Minecraft.getInstance().getRenderManager(), CLOSED, 0.9375F);
	}
	
	public void render(ClamEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(entityIn.isOpen()) {
			entityModel = OPEN;
		} else {
			entityModel = CLOSED;
		}
		if (entityIn.isChild()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public ResourceLocation getEntityTexture(ClamEntity entity) {
		if (entity.isOne()) {
			return CLAM_1;
		} else if (entity.isTwo()) {
			return CLAM_2;
		} else {
			return CLAM_3;
		}
	}
*/
}
