package superlord.wildlands.common.world;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.fml.common.Mod;
import superlord.wildlands.WildLands;
import superlord.wildlands.config.WildLandsConfig;
import superlord.wildlands.init.WLBiomes;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLPlacedFeatures;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WLWorldRegistry {
	
	private static ResourceLocation getBiomeName(Holder<Biome> biome) {
        return biome.unwrap().map((resourceKey) -> resourceKey.location(), (noKey) -> null);
    }
    
    public static void addBiomeSpawns(Holder<Biome> biome, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    	if (getBiomeName(biome) == Biomes.SWAMP.location()) {
			builder.getMobSpawnSettings().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.FROG, 15, 1, 3));
			if (WildLandsConfig.mudDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_MUD.getHolder().orElseThrow());
    	}
    	if (getBiomeName(biome) == Biomes.WARM_OCEAN.location()) {
    		if (WildLandsConfig.doleriteDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_DOLERITE.getHolder().orElseThrow());
    		if (WildLandsConfig.doleriteRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DOLERITE_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_GABBRO.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.olivineGabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.fineSandDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_FINE_SAND.getHolder().orElseThrow());
    		if (WildLandsConfig.starfish = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_STARFISH.getHolder().orElseThrow());
    		if (WildLandsConfig.urchin = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_URCHIN.getHolder().orElseThrow());
    		if (WildLandsConfig.hammerheadSharkSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.HAMMERHEAD.get(), WildLandsConfig.hammerheadSharkSpawnWeight, 3, 6));
    		if (WildLandsConfig.octopusSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.OCTOPUS.get(), WildLandsConfig.octopusSpawnWeight, 1, 2));
    		if (WildLandsConfig.jellyfishSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.JELLYFISH.get(), WildLandsConfig.jellyfishSpawnWeight, 1, 3));
    	}
    	if (getBiomeName(biome) == Biomes.LUKEWARM_OCEAN.location() || getBiomeName(biome) == Biomes.DEEP_LUKEWARM_OCEAN.location()) {
    		if (WildLandsConfig.doleriteDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_DOLERITE.getHolder().orElseThrow());
    		if (WildLandsConfig.doleriteRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DOLERITE_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_GABBRO.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.olivineGabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.fineSandDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_FINE_SAND.getHolder().orElseThrow());
    		if (WildLandsConfig.hammerheadSharkSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.HAMMERHEAD.get(), WildLandsConfig.hammerheadSharkSpawnWeight, 3, 6));
    		if (WildLandsConfig.octopusSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.OCTOPUS.get(), WildLandsConfig.octopusSpawnWeight, 1, 2));
    		if (WildLandsConfig.jellyfishSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.JELLYFISH.get(), WildLandsConfig.jellyfishSpawnWeight, 1, 3));
    	}
    	if (getBiomeName(biome) == Biomes.OCEAN.location() || getBiomeName(biome) == Biomes.DEEP_OCEAN.location()) {
    		if (WildLandsConfig.doleriteDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_DOLERITE.getHolder().orElseThrow());
    		if (WildLandsConfig.doleriteRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DOLERITE_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_GABBRO.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.olivineGabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.conglomerateDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE.getHolder().orElseThrow());
    		if (WildLandsConfig.conglomerateRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_CONGLOMERATE_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.jellyfishSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.JELLYFISH.get(), WildLandsConfig.jellyfishSpawnWeight, 1, 3));
    		if (WildLandsConfig.anchovySpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(WLEntities.ANCHOVY.get(), WildLandsConfig.anchovySpawnWeight, 2, 3));
    	}
    	if (getBiomeName(biome) == Biomes.COLD_OCEAN.location() || getBiomeName(biome) == Biomes.DEEP_COLD_OCEAN.location()) {
    		if (WildLandsConfig.doleriteDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_DOLERITE.getHolder().orElseThrow());
    		if (WildLandsConfig.doleriteRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DOLERITE_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_GABBRO.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.olivineGabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.conglomerateDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE.getHolder().orElseThrow());
    		if (WildLandsConfig.conglomerateRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_CONGLOMERATE_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.jellyfishSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.JELLYFISH.get(), WildLandsConfig.jellyfishSpawnWeight, 1, 3));
    		if (WildLandsConfig.anchovySpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(WLEntities.ANCHOVY.get(), WildLandsConfig.anchovySpawnWeight, 2, 3));
    	}
    	if (getBiomeName(biome) == Biomes.FROZEN_OCEAN.location() || getBiomeName(biome) == Biomes.DEEP_FROZEN_OCEAN.location()) {
    		if (WildLandsConfig.doleriteDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_DOLERITE.getHolder().orElseThrow());
    		if (WildLandsConfig.doleriteRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DOLERITE_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_GABBRO.getHolder().orElseThrow());
    		if (WildLandsConfig.gabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.olivineGabbroRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_OLIVINE_GABBRO_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.conglomerateDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE.getHolder().orElseThrow());
    		if (WildLandsConfig.conglomerateRock = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_CONGLOMERATE_ROCK.getHolder().orElseThrow());
    		if (WildLandsConfig.jellyfishSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.JELLYFISH.get(), WildLandsConfig.jellyfishSpawnWeight, 1, 3));
    	}
    	if (getBiomeName(biome) == Biomes.BEACH.location()) {
    		if (WildLandsConfig.fineSandDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_FINE_SAND.getHolder().orElseThrow());
    		if (WildLandsConfig.coconutTree = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WLPlacedFeatures.PLACED_COCONUT_TREES.getHolder().orElseThrow());
    		if (WildLandsConfig.crabSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(WLEntities.CRAB.get(), WildLandsConfig.crabSpawnWeight, 1, 3));
    		if (WildLandsConfig.seaLionSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.SEA_LION.get(), WildLandsConfig.seaLionSpawnWeight, 3, 6));
    	}
    	if (getBiomeName(biome) == Biomes.STONY_SHORE.location()) {
    		if (WildLandsConfig.conglomerateDisk = true) builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WLPlacedFeatures.PLACED_DISK_CONGLOMERATE.getHolder().orElseThrow());
    	}
    	if (getBiomeName(biome) == Biomes.FOREST.location() || getBiomeName(biome) == Biomes.BIRCH_FOREST.location() || getBiomeName(biome) == Biomes.DARK_FOREST.location() || getBiomeName(biome) == Biomes.FLOWER_FOREST.location() || getBiomeName(biome) == Biomes.OLD_GROWTH_BIRCH_FOREST.location() || getBiomeName(biome) == Biomes.WINDSWEPT_FOREST.location() || getBiomeName(biome) == Biomes.TAIGA.location() || getBiomeName(biome) == Biomes.OLD_GROWTH_PINE_TAIGA.location() || getBiomeName(biome) == Biomes.OLD_GROWTH_SPRUCE_TAIGA.location() || getBiomeName(biome) == Biomes.SNOWY_TAIGA.location()) {
    		if (WildLandsConfig.grizzlyBearSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.GRIZZLY.get(), WildLandsConfig.grizzlyBearSpawnWeight, 1, 2));
    	}
    	if (getBiomeName(biome) == WLBiomes.BAYOU.location()) {
    		if (WildLandsConfig.alligatorSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(WLEntities.ALLIGATOR.get(), WildLandsConfig.alligatorSpawnWeight, 1, 2));
    		if (WildLandsConfig.catfishSpawnWeight != 0) builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(WLEntities.CATFISH.get(), WildLandsConfig.catfishSpawnWeight, 1, 1));
    	}
    }

}
