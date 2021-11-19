package superlord.wildlands.client;

import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.render.AlligatorRenderer;
import superlord.wildlands.client.render.CatfishRenderer;
import superlord.wildlands.client.render.ClamRenderer;
import superlord.wildlands.client.render.CoconutRenderer;
import superlord.wildlands.client.render.CrabRenderer;
import superlord.wildlands.client.render.FrogRenderer;
import superlord.wildlands.client.render.HammerheadRenderer;
import superlord.wildlands.client.render.WLBoatRenderer;
import superlord.wildlands.client.render.WLSignTileEntityRenderer;
import superlord.wildlands.common.item.WLSpawnEggItem;
import superlord.wildlands.init.WildLandsBlocks;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WLTileEntities;
import superlord.wildlands.init.WLWoodTypes;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.CATFISH.get(), CatfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.ALLIGATOR.get(), manager -> new AlligatorRenderer());
		RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.BOAT.get(), WLBoatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.COCONUT.get(), CoconutRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.CRAB.get(), CrabRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.CLAM.get(), manager -> new ClamRenderer());
		RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.FROG.get(), manager -> new FrogRenderer());
		RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.HAMMERHEAD.get(), HammerheadRenderer::new);
	}
	
	@SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((WLSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (WLSpawnEggItem e : WLSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
	
	@SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        RenderTypeLookup.setRenderLayer(WildLandsBlocks.CYPRESS_SIGN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(WildLandsBlocks.CYPRESS_WALL_SIGN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(WildLandsBlocks.COCONUT_SIGN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(WildLandsBlocks.COCONUT_WALL_SIGN.get(), RenderType.getCutout());
        ClientRegistry.bindTileEntityRenderer(WLTileEntities.WL_SIGNS.get(), WLSignTileEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onStitchEvent(TextureStitchEvent.Pre event)
    {
        ResourceLocation stitching = event.getMap().getTextureLocation();
        if (!stitching.equals(Atlases.SIGN_ATLAS))
            return;

        WLWoodTypes.getValues().forEach(woodType -> event.addSprite(new ResourceLocation(WildLands.MOD_ID, "entity/signs/" + woodType.getName())));
    }

}
