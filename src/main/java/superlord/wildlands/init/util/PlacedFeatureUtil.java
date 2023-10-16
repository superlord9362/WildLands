package superlord.wildlands.init.util;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import java.util.function.Function;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import superlord.wildlands.WildLands;


public class PlacedFeatureUtil {
	public static final NoiseThresholdCountPlacement CLEARING_NOISE = NoiseThresholdCountPlacement.of(0.545, 1, 0);
	public static final Map<ResourceKey<PlacedFeature>, PlacedFeatureFactory> PLACED_FEATURE_FACTORIES = new Reference2ObjectOpenHashMap<>();
	public static final Map<ResourceKey<ConfiguredFeature<?, ?>>, ConfiguredFeatureFactory> CONFIGURED_FEATURES_FACTORIES = new Reference2ObjectOpenHashMap<>();

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



	public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String id, Supplier<? extends F> feature, Function<BootstapContext<ConfiguredFeature<?, ?>>, ? extends FC> config) {
		ResourceLocation wlID = WildLands.createLocation(id);

		ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, wlID);

		CONFIGURED_FEATURES_FACTORIES.put(configuredFeatureResourceKey, configuredFeatureHolderGetter -> new ConfiguredFeature<>(feature.get(), config.apply(configuredFeatureHolderGetter)));

		return configuredFeatureResourceKey;
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeature(String id, Supplier<? extends F> feature, Supplier<? extends FC> config) {
		ResourceLocation wlID = WildLands.createLocation(id);

		ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureResourceKey = ResourceKey.create(Registries.CONFIGURED_FEATURE, wlID);

		CONFIGURED_FEATURES_FACTORIES.put(configuredFeatureResourceKey, configuredFeatureHolderGetter -> new ConfiguredFeature<>(feature.get(), config.get()));

		return configuredFeatureResourceKey;
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> createConfiguredFeature(F feature, Supplier<? extends FC> config) {
		return Holder.direct(new ConfiguredFeature<>(feature, config.get()));
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> createConfiguredFeature(F feature, FC config) {
		return Holder.direct(new ConfiguredFeature<>(feature, config));
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> createConfiguredFeature(Supplier<F> feature, Supplier<FC> config) {
		return Holder.direct(new ConfiguredFeature<>(feature.get(), config.get()));
	}
	
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> ResourceKey<ConfiguredFeature<?, ?>> createPatchConfiguredFeatureWithBlock(String id, Supplier<? extends Block> block, int tries) {
        return createPatchConfiguredFeatureWithState(id, () -> block.get().defaultBlockState(), tries);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createPatchConfiguredFeatureWithState(String id, Supplier<BlockState> state, int tries) {
        return createConfiguredFeature(id, () -> Feature.RANDOM_PATCH, (configuredFeatureBootstapContext) -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(state.get())), List.of(), tries));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> createPatchConfiguredFeatureWithState(Block block, int tries) {
        return Holder.direct(new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(block)), List.of(), tries)));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createSimpleBlockConfiguredFeatureWithBlock(String id, Supplier<Block> block) {
        return createSimpleBlockConfiguredFeatureWithState(id, () -> block.get().defaultBlockState());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createSimpleBlockConfiguredFeatureWithState(String id, Supplier<BlockState> state) {
        return createConfiguredFeature(id, () -> Feature.SIMPLE_BLOCK, (configuredFeatureBootstapContext) -> new SimpleBlockConfiguration(BlockStateProvider.simple(state.get())));
    }

	public static BlockPredicateFilter createSolidDownAndAirAllAroundFilter(BlockPredicate... predicates) {
		return BlockPredicateFilter.forPredicate(BlockPredicate.allOf(new ImmutableList.Builder<BlockPredicate>().add(BlockPredicate.solid(BlockPos.ZERO.relative(Direction.DOWN)),
				BlockPredicate.not(BlockPredicate.solid(BlockPos.ZERO.relative(Direction.WEST))),
				BlockPredicate.not(BlockPredicate.solid(BlockPos.ZERO.relative(Direction.EAST))),
				BlockPredicate.not(BlockPredicate.solid(BlockPos.ZERO.relative(Direction.NORTH))),
				BlockPredicate.not(BlockPredicate.solid(BlockPos.ZERO.relative(Direction.SOUTH))),
				BlockPredicate.not(BlockPredicate.solid(BlockPos.ZERO.relative(Direction.UP))))
				.add(BlockPredicate.matchesBlocks(List.of(Blocks.AIR, Blocks.CAVE_AIR)))
				.add(predicates).build()));
	}

	@FunctionalInterface
	public interface PlacedFeatureFactory {
		PlacedFeature generate(HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter);
	}

	@FunctionalInterface
	public interface ConfiguredFeatureFactory {
		ConfiguredFeature<?, ?> generate(BootstapContext<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter);
	}

}
