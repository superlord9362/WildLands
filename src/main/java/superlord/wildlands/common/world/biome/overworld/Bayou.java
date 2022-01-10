package superlord.wildlands.common.world.biome.overworld;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.common.core.world.WLConfiguredFeatures;
import superlord.wildlands.common.util.WorldGenRegistrationHelper;
import superlord.wildlands.common.world.WLSurfaceBuilders;
import superlord.wildlands.common.world.biome.BiomeUtil;
import superlord.wildlands.common.world.biome.WLBiome;
import superlord.wildlands.common.world.biome.WLDefaultBiomeFeatures;
import superlord.wildlands.config.WildLandsConfig;

public class Bayou extends WLBiome {

	static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = WorldGenRegistrationHelper.createConfiguredSurfaceBuilder("bayou", new ConfiguredSurfaceBuilder<>(WLSurfaceBuilders.BAYOU, WLSurfaceBuilders.Configs.MUD));
	public static final Biome.RainType PRECIPITATION = Biome.RainType.RAIN;
	public static final Biome.Category CATEGORY = Biome.Category.SWAMP;
	static final float DEPTH = -0.2F;
	static final float SCALE = (float) WildLandsConfig.bayouScale;
	static final float TEMPERATURE = 0.9F;
	static final float DOWNFALL = 0.8F;
	static final int WATER_COLOR = 0x2E4011;
	static final int WATER_FOG_COLOR = 0x6D7239;

	static final Biome.Climate WEATHER = new Biome.Climate(PRECIPITATION, TEMPERATURE, Biome.TemperatureModifier.NONE, DOWNFALL);
	static final MobSpawnInfo.Builder SPAWN_SETTINGS = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();
	static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(SURFACE_BUILDER);

	public Bayou() {
		super(WEATHER, CATEGORY, DEPTH, SCALE, (new BiomeAmbience.Builder()).setWaterColor(WATER_COLOR).setWaterFogColor(WATER_FOG_COLOR).setFogColor(0x8A9B84).withSkyColor(BiomeUtil.calcSkyColor(0.8F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.copy());
	}

	@Override
	public int getWeight() {
		return 3;
	}

	@Override
	public BiomeManager.BiomeType getBiomeType() {
		return BiomeManager.BiomeType.WARM;
	}

	@Override
	public BiomeDictionary.Type[] getBiomeDictionary() {
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.OVERWORLD};
	}

	static {
		GENERATION_SETTINGS.withStructure(StructureFeatures.MINESHAFT);
		GENERATION_SETTINGS.withStructure(StructureFeatures.RUINED_PORTAL_SWAMP);
		DefaultBiomeFeatures.withPassiveMobs(SPAWN_SETTINGS);
		DefaultBiomeFeatures.withBatsAndHostiles(SPAWN_SETTINGS);
		DefaultBiomeFeatures.withLavaAndWaterLakes(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withCavesAndCanyons(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withMonsterRoom(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withCommonOverworldBlocks(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withOverworldOres(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withClayDisks(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withNormalMushroomGeneration(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withSwampSugarcaneAndPumpkin(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withLavaAndWaterSprings(GENERATION_SETTINGS);
		WLDefaultBiomeFeatures.addBayouVegetation(GENERATION_SETTINGS);
		GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_SWAMP);
		GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.DUCKWEED);
		GENERATION_SETTINGS.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.BEARD_MOSS);

		SPAWN_SETTINGS.withSpawner(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(WildLandsEntities.CATFISH.get(), WildLandsConfig.catfishSpawnWeight, 1, 1));
		SPAWN_SETTINGS.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(WildLandsEntities.ALLIGATOR.get(), WildLandsConfig.alligatorSpawnWeight, 1, 2));
		SPAWN_SETTINGS.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(WildLandsEntities.FROG.get(), WildLandsConfig.frogSpawnWeight, 1, 3));
	}

}
