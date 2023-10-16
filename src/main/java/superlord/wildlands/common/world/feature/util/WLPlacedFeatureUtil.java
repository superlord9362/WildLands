package superlord.wildlands.common.world.feature.util;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import superlord.wildlands.WildLands;

public class WLPlacedFeatureUtil {

	public static final NoiseThresholdCountPlacement CLEARING_NOISE = NoiseThresholdCountPlacement.of(0.545, 1, 0);
	public static final Map<ResourceKey<PlacedFeature>, PlacedFeatureFactory> PLACED_FEATURE_FACTORIES = new Reference2ObjectOpenHashMap<>();

	public static List<PlacementModifier> clearingTreePlacementBaseOceanFloor(PlacementModifier $$0) {
		return ImmutableList.<PlacementModifier>builder().add($$0).add(InSquarePlacement.spread()).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(CLEARING_NOISE).add(BiomeFilter.biome()).build();
	}

	public static List<PlacementModifier> clearingTreePlacement(PlacementModifier placementModifier) {
		List<PlacementModifier> placementModifiers = new ArrayList<>(treePlacement(placementModifier));
		placementModifiers.add(CLEARING_NOISE);
		return placementModifiers;
	}

	public static List<PlacementModifier> clearingTreePlacement(PlacementModifier placementModifier, Block block) {
		List<PlacementModifier> placementModifiers = new ArrayList<>(treePlacement(placementModifier, block));
		placementModifiers.add(CLEARING_NOISE);
		return placementModifiers;
	}


	public static List<PlacementModifier> oceanFloorSquaredWithCount(int $$0, PlacementModifier... modifiers) {
		return oceanFloorSquaredWithCountAndMaxDepth($$0, OptionalInt.empty(), modifiers);
	}

	public static List<PlacementModifier> oceanFloorSquaredWithCountAndMaxDepth(int $$0, OptionalInt maxDepth, PlacementModifier... modifiers) {
		ArrayList<PlacementModifier> placementModifiers = new ArrayList<>(List.of(CountPlacement.of($$0), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
		if (maxDepth.isPresent()) {
			placementModifiers.add(SurfaceWaterDepthFilter.forMaxDepth(maxDepth.getAsInt()));
		}
		placementModifiers.addAll(Arrays.asList(modifiers));
		return placementModifiers;
	}


	@SafeVarargs
	public static <FC extends FeatureConfiguration> ResourceKey<PlacedFeature> createPlacedFeature(String id, ResourceKey<ConfiguredFeature<?, ?>> feature, Supplier<PlacementModifier>... placementModifiers) {
		return createPlacedFeature(id, feature, () -> Arrays.stream(placementModifiers).map(Supplier::get).toList());
	}


	public static <FC extends FeatureConfiguration> ResourceKey<PlacedFeature> createPlacedFeature(String id, ResourceKey<ConfiguredFeature<?, ?>> feature, Supplier<List<PlacementModifier>> placementModifiers) {
		ResourceLocation wlID = WildLands.createLocation(id);

		ResourceKey<PlacedFeature> placedFeatureKey = ResourceKey.create(Registries.PLACED_FEATURE, wlID);


		PLACED_FEATURE_FACTORIES.put(placedFeatureKey, configuredFeatureHolderGetter -> new PlacedFeature(configuredFeatureHolderGetter.getOrThrow(feature), placementModifiers.get()));


		return placedFeatureKey;
	}

	public static <FC extends FeatureConfiguration> Holder<PlacedFeature> createPlacedFeatureDirect(Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... placementModifiers) {
		return createPlacedFeatureDirect(feature, List.of(placementModifiers));
	}

	public static <FC extends FeatureConfiguration> Holder<PlacedFeature> createPlacedFeatureDirect(Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
		return Holder.direct(new PlacedFeature(feature, List.copyOf(placementModifiers)));
	}
	
	@FunctionalInterface
	public interface PlacedFeatureFactory {
		PlacedFeature generate(HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter);
	}
}
