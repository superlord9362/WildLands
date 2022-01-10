package superlord.wildlands.common.world.biome.overworld;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.biome.ParticleEffectAmbience;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import superlord.wildlands.common.util.WorldGenRegistrationHelper;
import superlord.wildlands.common.world.WLSurfaceBuilders;
import superlord.wildlands.common.world.biome.WLBiome;
import superlord.wildlands.common.world.biome.WLDefaultBiomeFeatures;
import superlord.wildlands.config.WildLandsConfig;

public class BurntForest extends WLBiome {

	static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = WorldGenRegistrationHelper.createConfiguredSurfaceBuilder("burnt_forest", new ConfiguredSurfaceBuilder<>(WLSurfaceBuilders.BURNT_FOREST, WLSurfaceBuilders.Configs.CHARRED_GRASS));
	public static final Biome.RainType PRECIPITATION = Biome.RainType.NONE;
	public static final Biome.Category CATEGORY = Biome.Category.FOREST;
	static final float DEPTH = 0.2F;
	static final float SCALE = (float) WildLandsConfig.burntForestScale;
	static final float TEMPERATURE = 0.9F;
	static final float DOWNFALL = 0.0F;
	static final int WATER_COLOR = 0x4E4439;
	static final int WATER_FOG_COLOR = 0x2D2821;

	static final Biome.Climate WEATHER = new Biome.Climate(PRECIPITATION, TEMPERATURE, Biome.TemperatureModifier.NONE, DOWNFALL);
	static final MobSpawnInfo.Builder SPAWN_SETTINGS = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();
	static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(SURFACE_BUILDER);

	public BurntForest() {
		super(WEATHER, CATEGORY, DEPTH, SCALE, (new BiomeAmbience.Builder()).setWaterColor(WATER_COLOR).setWaterFogColor(WATER_FOG_COLOR).setFogColor(0x110D08).withSkyColor(0x275646).setParticle(new ParticleEffectAmbience(ParticleTypes.ASH, 0.118093334F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.copy());
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
		return new BiomeDictionary.Type[] {BiomeDictionary.Type.DEAD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD};
	}

	static {
		GENERATION_SETTINGS.withStructure(StructureFeatures.MINESHAFT);
		GENERATION_SETTINGS.withStructure(StructureFeatures.RUINED_PORTAL_SWAMP);
		DefaultBiomeFeatures.withBatsAndHostiles(SPAWN_SETTINGS);
		DefaultBiomeFeatures.withLavaAndWaterLakes(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withCavesAndCanyons(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withMonsterRoom(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withCommonOverworldBlocks(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withOverworldOres(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withClayDisks(GENERATION_SETTINGS);
		DefaultBiomeFeatures.withLavaAndWaterSprings(GENERATION_SETTINGS);
		WLDefaultBiomeFeatures.addBurntForestVegetation(GENERATION_SETTINGS);
	}

}
