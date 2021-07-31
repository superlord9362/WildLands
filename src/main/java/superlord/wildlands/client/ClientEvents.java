package superlord.wildlands.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.render.AlligatorRenderer;
import superlord.wildlands.client.render.CatfishRenderer;
import superlord.wildlands.client.render.CoconutRenderer;
import superlord.wildlands.client.render.CrabRenderer;
import superlord.wildlands.client.render.WLBoatRenderer;
import superlord.wildlands.init.EntityInit;
import superlord.wildlands.item.WLSpawnEggItem;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CATFISH.get(), CatfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.ALLIGATOR.get(), manager -> new AlligatorRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.BOAT.get(), WLBoatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.COCONUT.get(), CoconutRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CRAB.get(), CrabRenderer::new);
	}
	
	@SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((WLSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (WLSpawnEggItem e : WLSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }

}
