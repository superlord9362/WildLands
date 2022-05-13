package superlord.wildlands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Supplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import superlord.wildlands.client.network.WLPacketHandler;
import superlord.wildlands.common.CommonEvents;
import superlord.wildlands.common.core.world.WLFeatures;
import superlord.wildlands.common.core.world.WLPlacedFeatures;
import superlord.wildlands.common.entity.AlligatorEntity;
import superlord.wildlands.common.entity.AnchovyEntity;
import superlord.wildlands.common.entity.CatfishEntity;
import superlord.wildlands.common.entity.CrabEntity;
import superlord.wildlands.common.entity.FrogEntity;
import superlord.wildlands.common.entity.GrizzlyEntity;
import superlord.wildlands.common.entity.HammerheadEntity;
import superlord.wildlands.common.entity.JellyfishEntity;
import superlord.wildlands.common.entity.OctopusEntity;
import superlord.wildlands.common.entity.SeaLionEntity;
import superlord.wildlands.common.util.RegistryObject;
import superlord.wildlands.config.WLConfigHolder;
import superlord.wildlands.config.WildLandsConfig;
import superlord.wildlands.init.WLTileEntities;
import superlord.wildlands.init.WildLandsBiomes;
import superlord.wildlands.init.WildLandsBlocks;
import superlord.wildlands.init.WildLandsEffects;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;

@Mod(WildLands.MOD_ID)
@Mod.EventBusSubscriber(modid = WildLands.MOD_ID)
public class WildLands {

	public static final String MOD_ID = "wildlands";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final List<Runnable> CALLBACKS = new ArrayList<>();

	public WildLands() {
		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, WLConfigHolder.CLIENT_SPEC);
		modLoadingContext.registerConfig(ModConfig.Type.SERVER, WLConfigHolder.SERVER_SPEC);
		bus.addListener(this::commonSetup);
		bus.addListener(this::registerEntityAttributes);
		bus.addListener(this::registerClient);
		bus.addListener(this::setup);
		WildLandsBlocks.REGISTER.register(bus);
		WildLandsEntities.REGISTER.register(bus);
		WildLandsItems.REGISTER.register(bus);
		WLTileEntities.REGISTER.register(bus);
		WildLandsEffects.EFFECTS.register(bus);
		WLPacketHandler.registerPackets();
		bootStrap(bus);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerBiomes(BiomeLoadingEvent event) {
		String name = event.getName().getPath();
		if (event.getCategory() == BiomeCategory.OCEAN) {
			event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(WLPlacedFeatures.DISK_DOLERITE);
			event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(WLPlacedFeatures.DOLERITE_ROCK);
			event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_DECORATION).add(WLPlacedFeatures.DISK_GABBRO);
			event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(WLPlacedFeatures.GABBRO_ROCK);
			event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(WLPlacedFeatures.OLIVINE_GABBRO_ROCK);
			event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.JELLYFISH.get(), WildLandsConfig.jellyfishSpawnWeight, 1, 3));
		}
		if (event.getCategory() == BiomeCategory.FOREST || event.getCategory() == BiomeCategory.TAIGA) {
			event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.GRIZZLY.get(), WildLandsConfig.grizzlyBearSpawnWeight, 1, 2));
		}
		if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean") || name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("beach")) {
			event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(WLPlacedFeatures.DISK_FINE_SAND);
		}
		if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean") || name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("ocean") || name.equals("deep_ocean")) {
			//event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.CLAM.get(), WildLandsConfig.clamSpawnWeight, 1, 1));
		}
		if (name.equals("warm_ocean") || name.equals("deep_warm_ocean")) {
			//    		event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.CLAM.get(), 5, 1, 3));
			event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(WLPlacedFeatures.STARFISH);
			event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(WLPlacedFeatures.URCHIN);
		}
		if (name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean")) {
			event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.HAMMERHEAD.get(), WildLandsConfig.hammerheadSharkSpawnWeight, 3, 6));
			event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.OCTOPUS.get(), WildLandsConfig.octopusSpawnWeight, 1, 2));
		}
		if (name.equals("ocean") || name.equals("deep_ocean") || name.equals("cold_ocean") || name.equals("deep_cold_ocean") || name.equals("frozen_ocean") || name.equals("deep_frozen_ocean")) {
			event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(WLPlacedFeatures.DISK_CONGLOMERATE);
			event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(WLPlacedFeatures.CONGLOMERATE_ROCK);
		}
		if (name.equals("stone_shore")) {
			event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(WLPlacedFeatures.DISK_CONGLOMERATE);
		}
		if (name.equals("beach")) {
			event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.CRAB.get(), WildLandsConfig.crabSpawnWeight, 1, 3));
			event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(WLPlacedFeatures.DISK_FINE_SAND);
			event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(WLPlacedFeatures.COCONUT_TREES);
			event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.SEA_LION.get(), WildLandsConfig.seaLionSpawnWeight, 3, 6));
		}
		if (name.equals("swamp")) {
			event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.FROG.get(), WildLandsConfig.frogSpawnWeight, 1, 3));
			event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(WLPlacedFeatures.DISK_MUD);
		}
		if (name.equals("ocean") || name.equals("deep_ocean") || name.equals("cold_ocean") || name.equals("deep_cold_ocean")) {
			event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(WildLandsEntities.ANCHOVY.get(), WildLandsConfig.anchovySpawnWeight, 2, 3));
		}
	}

	private void registerClient(FMLClientSetupEvent event) {
		CALLBACKS.forEach(Runnable::run);
		CALLBACKS.clear();
	}

	public void setup(final FMLCommonSetupEvent event) {
		CommonEvents.setup();
	}

	public final static CreativeModeTab BLOCK_GROUP = new CreativeModeTab("wildlands_block_item_group") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(WildLandsBlocks.MUD.get().asItem());
		}
	};

	public final static CreativeModeTab ITEM_GROUP = new CreativeModeTab("wildlands_item_item_group") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(WildLandsItems.OLIVINE.get());
		}
	};

	public final static CreativeModeTab SPAWN_EGG_GROUP = new CreativeModeTab("wildlands_spawn_item_group") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(WildLandsItems.CATFISH_SPAWN_EGG.get());
		}
	};

	private void commonSetup(FMLCommonSetupEvent event) {
		SpawnPlacements.register(WildLandsEntities.CATFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(WildLandsEntities.ANCHOVY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(WildLandsEntities.ALLIGATOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AlligatorEntity::canAlligatorSpawn);
		SpawnPlacements.register(WildLandsEntities.GRIZZLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(WildLandsEntities.CRAB.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrabEntity::canCrabSpawn);
		// SpawnPlacements.register(WildLandsEntities.CLAM.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Type.OCEAN_FLOOR, ClamEntity::canSpawn);
		SpawnPlacements.register(WildLandsEntities.FROG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(WildLandsEntities.SEA_LION.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SeaLionEntity::canSeaLionSpawn);
		SpawnPlacements.register(WildLandsEntities.HAMMERHEAD.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, HammerheadEntity::func_223364_b);
		SpawnPlacements.register(WildLandsEntities.OCTOPUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, OctopusEntity::canSpawn);
		SpawnPlacements.register(WildLandsEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, JellyfishEntity::canSpawn);
	}

	private void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(WildLandsEntities.CATFISH.get(), CatfishEntity.createAttributes().build());
		event.put(WildLandsEntities.ANCHOVY.get(), AnchovyEntity.createAttributes().build());
		event.put(WildLandsEntities.ALLIGATOR.get(), AlligatorEntity.createAttributes().build());
		event.put(WildLandsEntities.CRAB.get(), CrabEntity.createAttributes().build());
		event.put(WildLandsEntities.FROG.get(), FrogEntity.createAttributes().build());
		event.put(WildLandsEntities.HAMMERHEAD.get(), HammerheadEntity.createAttributes().build());
		event.put(WildLandsEntities.OCTOPUS.get(), OctopusEntity.createAttributes().build());
		event.put(WildLandsEntities.SEA_LION.get(), SeaLionEntity.createAttributes().build());
		event.put(WildLandsEntities.JELLYFISH.get(), JellyfishEntity.createAttributes().build());
		event.put(WildLandsEntities.GRIZZLY.get(), GrizzlyEntity.createAttributes().build());
	}

	public static ResourceLocation createLocation(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	private void bootStrap(IEventBus eventBus) {
        register(Feature.class, eventBus, () -> WLFeatures.bootStrap());
        register(Biome.class, eventBus, () -> WildLandsBiomes.bootStrap());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T extends IForgeRegistryEntry<T>> void register(Class clazz, IEventBus eventBus, Supplier<Collection<RegistryObject<T>>> registryObjectsSupplier) {
		eventBus.addGenericListener(clazz, (RegistryEvent.Register<T> event) -> {
			Collection<RegistryObject<T>> registryObjects = registryObjectsSupplier.get();
			IForgeRegistry<T> registry = event.getRegistry();
			for (RegistryObject<T> registryObject : registryObjects) {
				registryObject.object().setRegistryName(WildLands.createLocation(registryObject.id()));
				registry.register(registryObject.object());
			}
			WildLands.LOGGER.info("WL registered: " + registry.getRegistryName());
		});
	}

}
