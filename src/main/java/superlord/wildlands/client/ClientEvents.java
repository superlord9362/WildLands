package superlord.wildlands.client;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.AlligatorModel;
import superlord.wildlands.client.model.AlligatorThreatenModel;
import superlord.wildlands.client.model.AnchovyModel;
import superlord.wildlands.client.model.CatfishModel;
import superlord.wildlands.client.model.CrabModel;
import superlord.wildlands.client.model.FrogModel;
import superlord.wildlands.client.model.GrizzlyModel;
import superlord.wildlands.client.model.GrizzlySittingModel;
import superlord.wildlands.client.model.HammerheadSharkModel;
import superlord.wildlands.client.model.JellyfishModel;
import superlord.wildlands.client.model.OctopusModel;
import superlord.wildlands.client.model.SeaLionModel;
import superlord.wildlands.client.model.SleepingGrizzlyModel;
import superlord.wildlands.client.model.TadpoleModel;
import superlord.wildlands.client.render.AlligatorRenderer;
import superlord.wildlands.client.render.AnchovyRenderer;
import superlord.wildlands.client.render.CatfishRenderer;
import superlord.wildlands.client.render.CoconutRenderer;
import superlord.wildlands.client.render.CrabRenderer;
import superlord.wildlands.client.render.FrogRenderer;
import superlord.wildlands.client.render.GrizzlyRenderer;
import superlord.wildlands.client.render.HammerheadRenderer;
import superlord.wildlands.client.render.JellyBallRenderer;
import superlord.wildlands.client.render.JellyfishRenderer;
import superlord.wildlands.client.render.OctopusRenderer;
import superlord.wildlands.client.render.SeaLionRenderer;
import superlord.wildlands.client.render.WLBoatRenderer;
import superlord.wildlands.client.render.WLSignTileEntityRenderer;
import superlord.wildlands.common.item.WLSpawnEggItem;
import superlord.wildlands.init.WLTileEntities;
import superlord.wildlands.init.WLWoodTypes;
import superlord.wildlands.init.WildLandsBlocks;
import superlord.wildlands.init.WildLandsEntities;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	public static ModelLayerLocation ALLIGATOR = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "alligator"), "alligator");
	public static ModelLayerLocation ALLIGATOR_WARNING = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "alligator_warning"), "alligator_warning");
	public static ModelLayerLocation ANCHOVY = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "anchovy"), "anchovy");
	public static ModelLayerLocation CATFISH = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "catfish"), "catfish");
	public static ModelLayerLocation CRAB = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "crab"), "crab");
	public static ModelLayerLocation FROG = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "frog"), "frog");
	public static ModelLayerLocation TADPOLE = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "tadpole"), "tadpole");
	public static ModelLayerLocation GRIZZLY = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "grizzly"), "grizzly");
	public static ModelLayerLocation GRIZZLY_SITTING = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "grizzly_sitting"), "grizzly_sitting");
	public static ModelLayerLocation GRIZZLY_SLEEPING = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "grizzly_sleeping"), "grizzly_sleeping");
	public static ModelLayerLocation HAMMERHEAD = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "hammerhead"), "hammerhead");
	public static ModelLayerLocation JELLYFISH = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "jellyfish"), "jellyfish");
	public static ModelLayerLocation OCTOPUS = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "octopus"), "octopus");
	public static ModelLayerLocation SEA_LION = new ModelLayerLocation(new ResourceLocation(WildLands.MOD_ID, "sea_lion"), "sea_lion");
	
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(WildLandsEntities.ALLIGATOR.get(), AlligatorRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.ANCHOVY.get(), AnchovyRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.CATFISH.get(), CatfishRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.BOAT.get(), WLBoatRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.COCONUT.get(), CoconutRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.JELLY_BALL.get(), JellyBallRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.CRAB.get(), CrabRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.FROG.get(), FrogRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.GRIZZLY.get(), GrizzlyRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.HAMMERHEAD.get(), HammerheadRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.JELLYFISH.get(), JellyfishRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.OCTOPUS.get(), OctopusRenderer::new);
		event.registerEntityRenderer(WildLandsEntities.SEA_LION.get(), SeaLionRenderer::new);
		
		//RenderingRegistry.registerEntityRenderingHandler(WildLandsEntities.CLAM.get(), manager -> new ClamRenderer());
	}
	
	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ALLIGATOR, AlligatorModel::createBodyLayer);
		event.registerLayerDefinition(ALLIGATOR_WARNING, AlligatorThreatenModel::createBodyLayer);
		event.registerLayerDefinition(ANCHOVY, AnchovyModel::createBodyLayer);
		event.registerLayerDefinition(CATFISH, CatfishModel::createBodyLayer);
		event.registerLayerDefinition(CRAB, CrabModel::createBodyLayer);
		event.registerLayerDefinition(FROG, FrogModel::createBodyLayer);
		event.registerLayerDefinition(TADPOLE, TadpoleModel::createBodyLayer);
		event.registerLayerDefinition(GRIZZLY, GrizzlyModel::createBodyLayer);
		event.registerLayerDefinition(GRIZZLY_SITTING, GrizzlySittingModel::createBodyLayer);
		event.registerLayerDefinition(GRIZZLY_SLEEPING, SleepingGrizzlyModel::createBodyLayer);
		event.registerLayerDefinition(HAMMERHEAD, HammerheadSharkModel::createBodyLayer);
		event.registerLayerDefinition(JELLYFISH, JellyfishModel::createBodyLayer);
		event.registerLayerDefinition(OCTOPUS, OctopusModel::createBodyLayer);
		event.registerLayerDefinition(SEA_LION, SeaLionModel::createBodyLayer);
	}
	
	@SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        ItemColor eggColor = (stack, tintIndex) -> ((WLSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (WLSpawnEggItem e : WLSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
	
	@SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
		ItemBlockRenderTypes.setRenderLayer(WildLandsBlocks.CYPRESS_SIGN.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WildLandsBlocks.CYPRESS_WALL_SIGN.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WildLandsBlocks.COCONUT_SIGN.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(WildLandsBlocks.COCONUT_WALL_SIGN.get(), RenderType.cutout());
		BlockEntityRenderers.register(WLTileEntities.WL_SIGNS.get(), WLSignTileEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onStitchEvent(TextureStitchEvent.Pre event)
    {
        ResourceLocation stitching = event.getAtlas().location();
        if (!stitching.equals(Sheets.SIGN_SHEET))
            return;

        WLWoodTypes.getValues().forEach(woodType -> event.addSprite(new ResourceLocation(WildLands.MOD_ID, "entity/signs/" + woodType.name())));
    }

}
