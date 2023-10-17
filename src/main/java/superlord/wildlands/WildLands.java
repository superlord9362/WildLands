package superlord.wildlands;

import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.client.ClientProxy;
import superlord.wildlands.common.CommonEvents;
import superlord.wildlands.common.CommonProxy;
import superlord.wildlands.common.entity.Alligator;
import superlord.wildlands.common.entity.Anchovy;
import superlord.wildlands.common.entity.Catfish;
import superlord.wildlands.common.entity.Crab;
import superlord.wildlands.common.entity.Grizzly;
import superlord.wildlands.common.entity.Hammerhead;
import superlord.wildlands.common.entity.Jellyfish;
import superlord.wildlands.common.entity.Octopus;
import superlord.wildlands.common.entity.SeaLion;
import superlord.wildlands.common.world.WLBiomeModifier;
import superlord.wildlands.common.world.WLFeatureAndBiomeGenerator;
import superlord.wildlands.common.world.biome.BayouRegionProvider;
import superlord.wildlands.common.world.biome.BiomeRegistry;
import superlord.wildlands.common.world.biome.BurntForestRegionProvider;
import superlord.wildlands.config.WLConfigHolder;
import superlord.wildlands.config.WildLandsConfig;
import superlord.wildlands.init.WLBlockEntities;
import superlord.wildlands.init.WLBlocks;
import superlord.wildlands.init.WLEffects;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLFeatures;
import superlord.wildlands.init.WLItems;
import superlord.wildlands.init.WLParticles;
import superlord.wildlands.init.WLPlacedFeatures;
import superlord.wildlands.init.WLSounds;
import superlord.wildlands.init.WLSurfaceRules;
import superlord.wildlands.init.WLTabs;
import superlord.wildlands.init.WLWoodTypes;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(WildLands.MOD_ID)
@Mod.EventBusSubscriber(modid = WildLands.MOD_ID)
public class WildLands {

	public static final String MOD_ID = "wildlands";
	public static final Logger LOGGER = LogManager.getLogger();
	@SuppressWarnings("deprecation")
	public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

	public WildLands() {
		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::commonSetup);
		bus.addListener(this::registerEntityAttributes);
		bus.addListener(this::setup);
		bus.addListener(this::onModConfigEvent);

		BiomeRegistry.registerBiomes();

		WLBlocks.REGISTER.register(bus);
		WLEntities.REGISTER.register(bus);
		WLItems.REGISTER.register(bus);
		WLItems.BLOCKS.register(bus);
		WLItems.SPAWN_EGGS.register(bus);
		WLTabs.REGISTER.register(bus);
		WLFeatures.REGISTER.register(bus);
		WLBlockEntities.REGISTER.register(bus);
		WLEffects.EFFECTS.register(bus);
		WLParticles.REGISTRY.register(bus);
		BiomeRegistry.REGISTER.register(bus);
		WLSounds.REGISTRY.register(bus);
		final DeferredRegister<Codec<? extends BiomeModifier>> biomeModifiers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, WildLands.MOD_ID);
		biomeModifiers.register(bus);
		biomeModifiers.register("wildlands_biome_modifiers", WLBiomeModifier::makeCodec);
		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, WLConfigHolder.CLIENT_SPEC, "wildlands.toml");
		modLoadingContext.registerConfig(ModConfig.Type.COMMON, WLConfigHolder.SERVER_SPEC, "wildlands.toml");
		
		bus.addListener(this::gatherData);
		
		PROXY.init();
	}

	private void setup(final FMLCommonSetupEvent event) {
		if (WildLandsConfig.bayouBiome && WildLandsConfig.bayouWeight != 0) {
			Regions.register(new BayouRegionProvider(new ResourceLocation(MOD_ID, "bayou"), WildLandsConfig.bayouWeight));
		}
		if (WildLandsConfig.burntForestBiome && WildLandsConfig.burntForestWeight != 0) {
			Regions.register(new BurntForestRegionProvider(new ResourceLocation(MOD_ID, "burnt_forest"), WildLandsConfig.burntForestWeight));
		}
		event.enqueueWork(() -> {
			WLPlacedFeatures.init();
			WoodType.register(WLWoodTypes.CHARRED);
			WoodType.register(WLWoodTypes.COCONUT);
			WoodType.register(WLWoodTypes.CYPRESS);
		});
	}

	@SuppressWarnings("deprecation")
	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(this::registerTerraBlender);
		SpawnPlacements.register(WLEntities.CATFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(WLEntities.ANCHOVY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(WLEntities.ALLIGATOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Alligator::canAlligatorSpawn);
		SpawnPlacements.register(WLEntities.GRIZZLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(WLEntities.CRAB.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Crab::canCrabSpawn);
		// SpawnPlacements.register(WLEntities.CLAM.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Type.OCEAN_FLOOR, ClamEntity::canSpawn);
		SpawnPlacements.register(WLEntities.SEA_LION.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SeaLion::canSeaLionSpawn);
		SpawnPlacements.register(WLEntities.HAMMERHEAD.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, Hammerhead::func_223364_b);
		SpawnPlacements.register(WLEntities.OCTOPUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, Octopus::canSpawn);
		SpawnPlacements.register(WLEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, Jellyfish::canSpawn);
	}

	private void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(WLEntities.CATFISH.get(), Catfish.createAttributes().build());
		event.put(WLEntities.ANCHOVY.get(), Anchovy.createAttributes().build());
		event.put(WLEntities.ALLIGATOR.get(), Alligator.createAttributes().build());
		event.put(WLEntities.CRAB.get(), Crab.createAttributes().build());
		event.put(WLEntities.HAMMERHEAD.get(), Hammerhead.createAttributes().build());
		event.put(WLEntities.OCTOPUS.get(), Octopus.createAttributes().build());
		event.put(WLEntities.SEA_LION.get(), SeaLion.createAttributes().build());
		event.put(WLEntities.JELLYFISH.get(), Jellyfish.createAttributes().build());
		event.put(WLEntities.GRIZZLY.get(), Grizzly.createAttributes().build());
	}
	
	public void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean server = event.includeServer();
        dataGenerator.addProvider(server, new WLFeatureAndBiomeGenerator(packOutput, lookupProvider));
    }
	
	@SubscribeEvent
	public void onModConfigEvent(final ModConfigEvent event) {
		final ModConfig config = event.getConfig();
		if (config.getSpec() == WLConfigHolder.SERVER_SPEC) {
			WildLandsConfig.bakeServer(config);
		}
		if (config.getSpec() == WLConfigHolder.CLIENT_SPEC) {
			WildLandsConfig.bakeClient(config);
		}
	}

	private void registerTerraBlender() {
		try {
			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, WildLands.MOD_ID,  WLSurfaceRules.OVERWORLD_SURFACE_RULES);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static ResourceLocation createLocation(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	public static ResourceLocation createLocation(ResourceKey<?> path) {
		return path.location();
	}

	public static ResourceLocation createLocation(Holder<?> holder) {
		return createLocation(holder.unwrapKey().orElseThrow());
	}

}
