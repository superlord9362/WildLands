package superlord.wildlands.common.world.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BurntForestBiomeDecorator {
	
	static HolderGetter<PlacedFeature> placedFeatureGetter;
	static HolderGetter<ConfiguredWorldCarver<?>> carverGetter;
	
	public BurntForestBiomeDecorator(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
		BayouBiomeDecorator.placedFeatureGetter = placedFeatureGetter;
		BayouBiomeDecorator.carverGetter = carverGetter;
	}
	
	@SuppressWarnings("unused")
	private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder)
	{
		return biome(precipitation, temperature, downfall, spawnBuilder, biomeBuilder);
	}
	
	private static Biome biome(boolean precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder) {
		return (new Biome.BiomeBuilder())
				.hasPrecipitation(precipitation)
				.temperature(temperature)
				.downfall(downfall)
				.specialEffects((new BiomeSpecialEffects.Builder())
						.waterColor(waterColor)
						.waterFogColor(waterFogColor)
						.fogColor(0x110D08)
						.skyColor(0x275646)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.build())
				.mobSpawnSettings(spawnBuilder.build())
				.generationSettings(biomeBuilder.build())
				.build();
	}

	public static Biome decorateBurntForest() {
		MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder biomeFeatures = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
		return biome(false, 0.9F, 0.0F, 0x4E4439, 0x2D2821, spawnSettings, biomeFeatures);
	}

}
