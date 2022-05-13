package superlord.wildlands.common.world.biome;

import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import superlord.wildlands.common.core.world.WLPlacedFeatures;

public class WLDefaultBiomeFeatures {

	public static void addMudDisks(BiomeGenerationSettings.Builder gen) {
		gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MiscOverworldPlacements.DISK_CLAY);
	}
	
	public static void addBayouVegetation(BiomeGenerationSettings.Builder gen) {
		gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.CYPRESS_TREES);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_SWAMP);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_SWAMP);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PALMETTO_PATCH);
        gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.CATTAIL_PATCH);
    }
	
	public static void addBurntForestVegetation(BiomeGenerationSettings.Builder gen) {
		gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.CHARRED_LOGS);
		gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.BURNT_GRASS);
		gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.BURNT_BUSH);
	}

}