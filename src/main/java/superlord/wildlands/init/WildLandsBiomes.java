package superlord.wildlands.init;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import superlord.wildlands.common.util.BiomeData;
import superlord.wildlands.common.util.WorldGenRegistrationHelper;
import superlord.wildlands.common.world.biome.WLBiome;
import superlord.wildlands.common.world.biome.overworld.Bayou;

public class WildLandsBiomes {

	public static List<PreserveBiomeOrder> biomeList = new ArrayList<>();

	public static final WLBiome BAYOU = new Bayou();

	
	public static Biome BAYOU_BIOME = WorldGenRegistrationHelper.createBiome("bayou", BAYOU.getBiome(), 1);

	public static void init() {

	}
	
	public static void addBiomeEntries() {
		BiomeManager.addBiome(BAYOU.getBiomeType(), new BiomeManager.BiomeEntry(BAYOU.getKey(), 6));
	}

	@SuppressWarnings("deprecation")
	public static void fillBiomeDictionary(Registry<Biome> biomeRegistry) {
		for (BiomeData pfBiomes : WLBiome.biomeData) {
			BiomeDictionary.addTypes(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, WorldGenRegistries.BIOME.getKey(pfBiomes.getBiome())), pfBiomes.getDictionaryTypes());
		}
	}
	
    public static void addWLFeaturesToBiomes(Biome biome, ResourceLocation locationKey) {
    }
    
    public static void addFeatureToBiome(Biome biome, GenerationStage.Decoration feature, ConfiguredFeature<?, ?> configuredFeature) {
        ConvertImmutableFeatures(biome);
        List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = biome.getGenerationSettings().features;
        while (biomeFeatures.size() <= feature.ordinal()) {
            biomeFeatures.add(Lists.newArrayList());
        }
        biomeFeatures.get(feature.ordinal()).add(() -> configuredFeature);
    }
    
    private static void ConvertImmutableFeatures(Biome biome) {
    	if (biome.getGenerationSettings().features instanceof ImmutableList) {
            biome.getGenerationSettings().features = biome.getGenerationSettings().features.stream().map(Lists::newArrayList).collect(Collectors.toList());
        }
    }
    
    public static void addStructureToBiome(Biome biome, StructureFeature<?, ?> configuredStructure) {
        convertImmutableStructures(biome);
        List<Supplier<StructureFeature<?, ?>>> biomeFeatures = biome.getGenerationSettings().structures;
        biomeFeatures.add(() -> configuredStructure);
    }

    private static void convertImmutableStructures(Biome biome) {
        biome.getGenerationSettings().structures = new ArrayList<>(biome.getGenerationSettings().structures);
    }

	@SuppressWarnings("deprecation")
	public static void fillBiomeDictionary() {
		for (BiomeData wlBiome : WLBiome.biomeData) {
			BiomeDictionary.addTypes(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, WorldGenRegistries.BIOME.getKey(wlBiome.getBiome())), wlBiome.getDictionaryTypes());
		}
		/**for (SubBiomeData wlSubBiome : SubBiomeInit.subBiomeData) {
			BiomeDictionary.addTypes(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, WorldGenRegistries.BIOME.getKey(wlSubBiome.getBiome())), wlSubBiome.getDictionaryTypes());
		}*/
	}

	public static class PreserveBiomeOrder {
		private final Biome biome;
		private final int orderPosition;

		public PreserveBiomeOrder(Biome biome, int orderPosition) {
			this.biome = biome;
			this.orderPosition = orderPosition;
		}

		public Biome getBiome() {
			return biome;
		}

		public int getOrderPosition() {
			return orderPosition;
		}
	}

}