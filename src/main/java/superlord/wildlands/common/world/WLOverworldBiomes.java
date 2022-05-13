package superlord.wildlands.common.world;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import superlord.wildlands.common.world.biome.BiomeUtil;
import superlord.wildlands.common.world.biome.WLDefaultBiomeFeatures;

public class WLOverworldBiomes {

	public static void addDefaultOverworldGeneration(BiomeGenerationSettings.Builder generationSettings) {
		BiomeDefaultFeatures.addDefaultOres(generationSettings);
		BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
	}

	public static Biome bayou() {
		MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();

		addDefaultOverworldGeneration(generationSettings);
		BiomeDefaultFeatures.addSwampVegetation(generationSettings);
		BiomeDefaultFeatures.addSwampClayDisk(generationSettings);
		BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
		BiomeDefaultFeatures.addSwampExtraVegetation(generationSettings);
		BiomeDefaultFeatures.farmAnimals(spawnSettings);
		BiomeDefaultFeatures.commonSpawns(spawnSettings);
		return new Biome.BiomeBuilder().precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.SWAMP).temperature(0.9F).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(0x2E4011).waterFogColor(0x6D7239).fogColor(0x8A0B84).skyColor(BiomeUtil.calcSkyColor(0.8F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
	}
	
	public static Biome burntForest() {
		MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();

		addDefaultOverworldGeneration(generationSettings);
		BiomeDefaultFeatures.commonSpawns(spawnSettings);
		WLDefaultBiomeFeatures.addBurntForestVegetation(generationSettings);
		return new Biome.BiomeBuilder().precipitation(Biome.Precipitation.NONE).biomeCategory(Biome.BiomeCategory.FOREST).temperature(0.9F).downfall(0.0F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(0x4E4439).waterFogColor(0x2D2821).fogColor(0x110D08).skyColor(0x275646).ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.118093334F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
	}

}
