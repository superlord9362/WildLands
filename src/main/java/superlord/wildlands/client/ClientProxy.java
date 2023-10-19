package superlord.wildlands.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.particle.SnoreParticle;
import superlord.wildlands.common.CommonProxy;
import superlord.wildlands.init.WLBlocks;
import superlord.wildlands.init.WLParticles;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::setupParticles);
	}
	
    public static void setupParticles(RegisterParticleProvidersEvent registry) {
        registry.registerSpriteSet(WLParticles.SNORE_PARTICLE.get(), SnoreParticle.Provider::new);
    }
    
    @SuppressWarnings({ "deprecation" })
	public static void setupBlockRenders() {
		RenderType cutoutRenderType = RenderType.cutout();
		RenderType cutoutMippedRenderType = RenderType.cutoutMipped();
		RenderType translucentRenderType = RenderType.translucent();
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.CYPRESS_SAPLING.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.POTTED_CYPRESS_SAPLING.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.COCONUT_DOOR.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.BLUE_STARFISH.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.MAGENTA_STARFISH.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.ORANGE_STARFISH.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.PINK_STARFISH.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.RED_STARFISH.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.PALMETTO.get(), cutoutMippedRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.COCONUT_SAPLING.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.POTTED_COCONUT_SAPLING.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.CATTAIL.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.DUCKWEED.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.BEARD_MOSS.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.BEARD_MOSS_TOP.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.CHARRED_TALL_GRASS.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.CHARRED_BUSH.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.URCHIN.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.JELLY_BLOCK.get(), translucentRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.POTTED_CATTAIL.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.POTTED_PALMETTO.get(), cutoutRenderType);
		ItemBlockRenderTypes.setRenderLayer(WLBlocks.POTTED_CHARRED_BUSH.get(), cutoutRenderType);
    }

}
