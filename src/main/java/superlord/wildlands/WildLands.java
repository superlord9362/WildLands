package superlord.wildlands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.wildlands.common.entity.Alligator;
import superlord.wildlands.common.entity.Anchovy;
import superlord.wildlands.common.entity.Catfish;
import superlord.wildlands.common.entity.Crab;
import superlord.wildlands.common.entity.Frog;
import superlord.wildlands.common.entity.Grizzly;
import superlord.wildlands.common.entity.Hammerhead;
import superlord.wildlands.common.entity.Jellyfish;
import superlord.wildlands.common.entity.Octopus;
import superlord.wildlands.common.entity.SeaLion;
import superlord.wildlands.common.world.biome.BayouRegionProvider;
import superlord.wildlands.config.WLConfigHolder;
import superlord.wildlands.config.WildLandsConfig;
import superlord.wildlands.init.WLBlockEntities;
import superlord.wildlands.init.WLBlocks;
import superlord.wildlands.init.WLConfiguredFeatures;
import superlord.wildlands.init.WLEffects;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLFeatures;
import superlord.wildlands.init.WLItems;
import superlord.wildlands.init.WLPlacedFeatures;
import superlord.wildlands.init.WLSurfaceRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(WildLands.MOD_ID)
@Mod.EventBusSubscriber(modid = WildLands.MOD_ID)
public class WildLands {

	public static final String MOD_ID = "wildlands";
	public static final Logger LOGGER = LogManager.getLogger();

	public WildLands() {
		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, WLConfigHolder.CLIENT_SPEC);
		modLoadingContext.registerConfig(ModConfig.Type.SERVER, WLConfigHolder.SERVER_SPEC);

		bus.addListener(this::commonSetup);
		bus.addListener(this::registerEntityAttributes);
		bus.addListener(this::setup);

		WLBlocks.REGISTER.register(bus);
		WLEntities.REGISTER.register(bus);
		WLItems.REGISTER.register(bus);
		WLFeatures.FEATURES.register(bus);
		WLConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
		WLPlacedFeatures.PLACED_FEATURES.register(bus);
		WLBlockEntities.REGISTER.register(bus);
		WLEffects.EFFECTS.register(bus);
	}

	private void setup(final FMLCommonSetupEvent event) {
		Regions.register(new BayouRegionProvider(new ResourceLocation(MOD_ID, "overworld"), 30));
	}

	public final static CreativeModeTab BLOCK_GROUP = new CreativeModeTab("wildlands_block_item_group") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(WLBlocks.MUD.get().asItem());
		}
	};

	public final static CreativeModeTab ITEM_GROUP = new CreativeModeTab("wildlands_item_item_group") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(WLItems.OLIVINE.get());
		}
	};

	public final static CreativeModeTab SPAWN_EGG_GROUP = new CreativeModeTab("wildlands_spawn_item_group") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(WLItems.CATFISH_SPAWN_EGG.get());
		}
	};

	private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(this::registerTerraBlender);
		SpawnPlacements.register(WLEntities.CATFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(WLEntities.ANCHOVY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(WLEntities.ALLIGATOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Alligator::canAlligatorSpawn);
		SpawnPlacements.register(WLEntities.GRIZZLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(WLEntities.CRAB.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Crab::canCrabSpawn);
		// SpawnPlacements.register(WLEntities.CLAM.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Type.OCEAN_FLOOR, ClamEntity::canSpawn);
		SpawnPlacements.register(WLEntities.FROG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
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
		event.put(WLEntities.FROG.get(), Frog.createAttributes().build());
		event.put(WLEntities.HAMMERHEAD.get(), Hammerhead.createAttributes().build());
		event.put(WLEntities.OCTOPUS.get(), Octopus.createAttributes().build());
		event.put(WLEntities.SEA_LION.get(), SeaLion.createAttributes().build());
		event.put(WLEntities.JELLYFISH.get(), Jellyfish.createAttributes().build());
		event.put(WLEntities.GRIZZLY.get(), Grizzly.createAttributes().build());
	}

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder builder = event.getGeneration();
		String name = event.getName().getPath();
		if (event.getCategory() == BiomeCategory.OCEAN) {
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_DOLERITE.getHolder().orElseThrow());
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DOLERITE_ROCK.getHolder().orElseThrow());
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_GABBRO.getHolder().orElseThrow());
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_GABBRO_ROCK.getHolder().orElseThrow());
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK.getHolder().orElseThrow());
			event.getSpawns().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.JELLYFISH.get(), WildLandsConfig.jellyfishSpawnWeight, 1, 3));
		}
		if (event.getCategory() == BiomeCategory.FOREST || event.getCategory() == BiomeCategory.TAIGA) {
			event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.GRIZZLY.get(), WildLandsConfig.grizzlyBearSpawnWeight, 1, 2));
		}
		if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean") || name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("beach")) {
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_FINE_SAND.getHolder().orElseThrow());
		}
		if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean") || name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("ocean") || name.equals("deep_ocean")) {
			//event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(WLEntities.CLAM.get(), WildLandsConfig.clamSpawnWeight, 1, 1));
		}
		if (name.equals("warm_ocean") || name.equals("deep_warm_ocean")) {
			//    		event.getSpawns().getSpawner(MobCategory.AMBIENT).add(new MobSpawnSettings.SpawnerData(WLEntities.CLAM.get(), 5, 1, 3));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_STARFISH.getHolder().orElseThrow());
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_URCHIN.getHolder().orElseThrow());
		}
		if (name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean")) {
			event.getSpawns().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.HAMMERHEAD.get(), WildLandsConfig.hammerheadSharkSpawnWeight, 3, 6));
			event.getSpawns().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.OCTOPUS.get(), WildLandsConfig.octopusSpawnWeight, 1, 2));
		}
		if (name.equals("ocean") || name.equals("deep_ocean") || name.equals("cold_ocean") || name.equals("deep_cold_ocean") || name.equals("frozen_ocean") || name.equals("deep_frozen_ocean")) {
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE.getHolder().orElseThrow());
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_CONGLOMERATE_ROCK.getHolder().orElseThrow());
		}
		if (name.equals("stone_shore")) {
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE.getHolder().orElseThrow());
		}
		if (name.equals("beach")) {
			event.getSpawns().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(WLEntities.CRAB.get(), WildLandsConfig.crabSpawnWeight, 1, 3));
			event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.SEA_LION.get(), WildLandsConfig.seaLionSpawnWeight, 3, 6));
			event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).add(WLPlacedFeatures.PLACED_COCONUT_TREES.getHolder().orElseThrow());
		}
		if (name.equals("swamp")) {
			event.getSpawns().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(WLEntities.FROG.get(), WildLandsConfig.frogSpawnWeight, 1, 3));
			builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_MUD.getHolder().orElseThrow());
		}
		if (name.equals("ocean") || name.equals("deep_ocean") || name.equals("cold_ocean") || name.equals("deep_cold_ocean")) {
			event.getSpawns().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(WLEntities.ANCHOVY.get(), WildLandsConfig.anchovySpawnWeight, 2, 3));
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
