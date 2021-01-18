package superlord.wildlands.world.biome;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import superlord.wildlands.core.world.WLConfiguredFeatures;

public class WLDefaultBiomeFeatures {

	public static void addMudDisks(BiomeGenerationSettings.Builder builder) {
		builder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.DISK_CLAY);
	}
	
	public static void addBayouVegetation(BiomeGenerationSettings.Builder gen) {
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.RANDOM_CYPRESS_TREE);
    }

}
