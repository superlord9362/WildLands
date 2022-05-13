package superlord.wildlands.common.core.world;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import superlord.wildlands.init.WildLandsBlocks;

public class WLVegetationFeatures {

	public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> URCHIN = FeatureUtils.register("urchin", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WildLandsBlocks.URCHIN.get())));
	public static final Holder<ConfiguredFeature<CountConfiguration, ?>> STARFISH = FeatureUtils.register("starfish", WLFeatures.STARFISH.get(), new CountConfiguration(5));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CATTAIL_CONFIG = FeatureUtils.register("cattail_config", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WildLandsBlocks.CATTAIL.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PALMETTO_CONFIG = FeatureUtils.register("palmetto_config", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WildLandsBlocks.PALMETTO.get()))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> DUCKWEED_CONFIG = FeatureUtils.register("duckweed_config", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WildLandsBlocks.DUCKWEED.get()))));
	public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> BURNT_GRASS = FeatureUtils.register("burnt_grass", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WildLandsBlocks.CHARRED_TALL_GRASS.get())));
	public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> BURNT_BUSH = FeatureUtils.register("burnt_bush", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WildLandsBlocks.CHARRED_BUSH.get())));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> DUCKWEED_PATCH = FeatureUtils.register("duckweed_patch", Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WildLandsBlocks.DUCKWEED.get())))));
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> BEARD_MOSS = FeatureUtils.register("beard_moss", WLFeatures.BEARD_MOSS.get(), new NoneFeatureConfiguration());

}
