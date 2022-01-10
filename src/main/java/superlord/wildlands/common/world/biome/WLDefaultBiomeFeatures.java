package superlord.wildlands.common.world.biome;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import superlord.wildlands.common.core.world.WLConfiguredFeatures;

public class WLDefaultBiomeFeatures {

	public static void addMudDisks(BiomeGenerationSettings.Builder gen) {
		gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.DISK_CLAY);
	}
	
	public static void addBayouVegetation(BiomeGenerationSettings.Builder gen) {
		gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.RANDOM_BAYOU_TREE);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FLOWER_SWAMP);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_NORMAL);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_DEAD_BUSH);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_WATERLILLY);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_SWAMP);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_SWAMP);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.PALMETTO_PATCH);
        gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.CATTAIL_PATCH);
    }
	
	public static void addBurntForestVegetation(BiomeGenerationSettings.Builder gen) {
		gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.RANDOM_BURNT_LOG);
		gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.BURNT_GRASS);
		gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, WLConfiguredFeatures.BURNT_BUSH);
	}

}