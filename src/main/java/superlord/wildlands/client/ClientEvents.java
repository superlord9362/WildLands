package superlord.wildlands.client;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.model.AlligatorModel;
import superlord.wildlands.client.model.AlligatorThreatenModel;
import superlord.wildlands.client.model.AnchovyModel;
import superlord.wildlands.client.model.CatfishModel;
import superlord.wildlands.client.model.CrabModel;
import superlord.wildlands.client.model.GrizzlyModel;
import superlord.wildlands.client.model.GrizzlySittingModel;
import superlord.wildlands.client.model.GrizzlySleepingModel;
import superlord.wildlands.client.model.HammerheadSharkModel;
import superlord.wildlands.client.model.JellyfishModel;
import superlord.wildlands.client.model.OctopusModel;
import superlord.wildlands.client.model.SeaLionModel;
import superlord.wildlands.client.render.AlligatorRenderer;
import superlord.wildlands.client.render.AnchovyRenderer;
import superlord.wildlands.client.render.CatfishRenderer;
import superlord.wildlands.client.render.CrabRenderer;
import superlord.wildlands.client.render.GrizzlyRenderer;
import superlord.wildlands.client.render.HammerheadRenderer;
import superlord.wildlands.client.render.JellyfishRenderer;
import superlord.wildlands.client.render.OctopusRenderer;
import superlord.wildlands.client.render.SeaLionRenderer;
import superlord.wildlands.client.render.WLBoatRenderer;
import superlord.wildlands.client.render.item.CoconutRenderer;
import superlord.wildlands.client.render.item.JellyBallRenderer;
import superlord.wildlands.common.entity.WLBoat;
import superlord.wildlands.common.entity.WLBoat.WLBoatTypes;
import superlord.wildlands.init.WLBlockEntities;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLWoodTypes;

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
		event.registerEntityRenderer(WLEntities.ALLIGATOR.get(), AlligatorRenderer::new);
		event.registerEntityRenderer(WLEntities.ANCHOVY.get(), AnchovyRenderer::new);
		event.registerEntityRenderer(WLEntities.CATFISH.get(), CatfishRenderer::new);
		event.registerEntityRenderer(WLEntities.BOAT.get(), (EntityRendererProvider.Context context) -> new WLBoatRenderer(context, false));
		event.registerEntityRenderer(WLEntities.CHEST_BOAT.get(), (EntityRendererProvider.Context context) -> new WLBoatRenderer(context, true));
		event.registerEntityRenderer(WLEntities.COCONUT.get(), CoconutRenderer::new);
		event.registerEntityRenderer(WLEntities.JELLY_BALL.get(), JellyBallRenderer::new);
		event.registerEntityRenderer(WLEntities.CRAB.get(), CrabRenderer::new);
		event.registerEntityRenderer(WLEntities.GRIZZLY.get(), GrizzlyRenderer::new);
		event.registerEntityRenderer(WLEntities.HAMMERHEAD.get(), HammerheadRenderer::new);
		event.registerEntityRenderer(WLEntities.JELLYFISH.get(), JellyfishRenderer::new);
		event.registerEntityRenderer(WLEntities.OCTOPUS.get(), OctopusRenderer::new);
		event.registerEntityRenderer(WLEntities.SEA_LION.get(), SeaLionRenderer::new);
		//RenderingRegistry.registerEntityRenderingHandler(WLEntities.CLAM.get(), manager -> new ClamRenderer());
	}

	@SubscribeEvent
	public static void init(final FMLClientSetupEvent event) {
		BlockEntityRenderers.register(WLBlockEntities.SIGN.get(), SignRenderer::new);
		event.enqueueWork(() -> {
			Sheets.addWoodType(WLWoodTypes.CYPRESS);
			Sheets.addWoodType(WLWoodTypes.COCONUT);
			Sheets.addWoodType(WLWoodTypes.CHARRED);
		});
		ClientProxy.setupBlockRenders();
	}


	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ALLIGATOR, AlligatorModel::createBodyLayer);
		event.registerLayerDefinition(ALLIGATOR_WARNING, AlligatorThreatenModel::createBodyLayer);
		event.registerLayerDefinition(ANCHOVY, AnchovyModel::createBodyLayer);
		event.registerLayerDefinition(CATFISH, CatfishModel::createBodyLayer);
		event.registerLayerDefinition(CRAB, CrabModel::createBodyLayer);
		event.registerLayerDefinition(GRIZZLY, GrizzlyModel::createBodyLayer);
		event.registerLayerDefinition(GRIZZLY_SITTING, GrizzlySittingModel::createBodyLayer);
		event.registerLayerDefinition(GRIZZLY_SLEEPING, GrizzlySleepingModel::createBodyLayer);
		event.registerLayerDefinition(HAMMERHEAD, HammerheadSharkModel::createBodyLayer);
		event.registerLayerDefinition(JELLYFISH, JellyfishModel::createBodyLayer);
		event.registerLayerDefinition(OCTOPUS, OctopusModel::createBodyLayer);
		event.registerLayerDefinition(SEA_LION, SeaLionModel::createBodyLayer);
		for (WLBoatTypes value : WLBoat.WLBoatTypes.values()) {
			event.registerLayerDefinition(WLBoatRenderer.createBoatModelName(value), BoatModel::createBodyModel);
			event.registerLayerDefinition(WLBoatRenderer.createChestBoatModelName(value), ChestBoatModel::createBodyModel);
		}	
	}

}
