package superlord.wildlands.init;

import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import superlord.wildlands.WildLands;

public class WLConfiguredFeatures {
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_STARFISH = registerConfiguredFeature("configured_starfish");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_URCHIN = registerConfiguredFeature("configured_urchin");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_DISK_MUD = registerConfiguredFeature("configured_disk_mud");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_DISK_FINE_SAND = registerConfiguredFeature("configured_disk_fine_sand");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_DISK_CONGLOMERATE = registerConfiguredFeature("configured_disk_conglomerate");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_DISK_GABBRO = registerConfiguredFeature("configured_disk_gabbro");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_DISK_DOLERITE = registerConfiguredFeature("configured_disk_dolerite");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_CONGLOMERATE_ROCK = registerConfiguredFeature("configured_conglomerate_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_GABBRO_ROCK = registerConfiguredFeature("configured_gabbro_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_DOLERITE_ROCK = registerConfiguredFeature("configured_dolerite_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_OLIVINE_GABBRO_ROCK = registerConfiguredFeature("configured_olivine_gabbro_rock");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_CATTAIL = registerConfiguredFeature("configured_cattail");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_PALMETTO = registerConfiguredFeature("configured_palmetto");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_BURNT_GRASS = registerConfiguredFeature("configured_burnt_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_BURNT_BUSH = registerConfiguredFeature("configured_burnt_bush");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_DUCKWEED = registerConfiguredFeature("configured_duckweed");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_BEARD_MOSS = registerConfiguredFeature("configured_beard_moss");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_CYPRESS_TREE = registerConfiguredFeature("configured_cypress_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_COCONUT_TREE = registerConfiguredFeature("configured_coconut_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_CHARRED_TREE = registerConfiguredFeature("configured_charred_tree");
	
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext) {
		FeatureUtils.register(bootstapContext, CONFIGURED_STARFISH, WLFeatures.STARFISH.get(), new CountConfiguration(4));
		FeatureUtils.register(bootstapContext, CONFIGURED_URCHIN, WLFeatures.URCHIN.get(), new CountConfiguration(4));
		FeatureUtils.register(bootstapContext, CONFIGURED_DISK_MUD, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.MUD.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.DIRT, Blocks.GRAVEL)), UniformInt.of(2, 3), 1));
		FeatureUtils.register(bootstapContext, CONFIGURED_DISK_FINE_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.FINE_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.DIRT, Blocks.GRAVEL)), UniformInt.of(2, 3), 1));
		FeatureUtils.register(bootstapContext, CONFIGURED_DISK_CONGLOMERATE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.CONGLOMERATE.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.DIRT, Blocks.GRAVEL)), UniformInt.of(2, 3), 1));
		FeatureUtils.register(bootstapContext, CONFIGURED_DISK_GABBRO, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.GABBRO.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.DIRT, Blocks.GRAVEL)), UniformInt.of(2, 3), 1));
		FeatureUtils.register(bootstapContext, CONFIGURED_DISK_DOLERITE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(WLBlocks.DOLERITE.get()), BlockPredicate.matchesBlocks(List.of(Blocks.SAND, Blocks.DIRT, Blocks.GRAVEL)), UniformInt.of(2, 3), 1));
		FeatureUtils.register(bootstapContext, CONFIGURED_CONGLOMERATE_ROCK, WLFeatures.LARGE_ROCK.get(), new BlockStateConfiguration(WLBlocks.CONGLOMERATE.get().defaultBlockState()));
		FeatureUtils.register(bootstapContext, CONFIGURED_GABBRO_ROCK, WLFeatures.LARGE_ROCK.get(), new BlockStateConfiguration(WLBlocks.GABBRO.get().defaultBlockState()));
		FeatureUtils.register(bootstapContext, CONFIGURED_DOLERITE_ROCK, WLFeatures.LARGE_ROCK.get(), new BlockStateConfiguration(WLBlocks.DOLERITE.get().defaultBlockState()));
		FeatureUtils.register(bootstapContext, CONFIGURED_OLIVINE_GABBRO_ROCK, Feature.FOREST_ROCK, new BlockStateConfiguration(WLBlocks.OLIVINE_GABBRO.get().defaultBlockState()));
		FeatureUtils.register(bootstapContext, CONFIGURED_CATTAIL, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.CATTAIL.get()))));
		FeatureUtils.register(bootstapContext, CONFIGURED_PALMETTO, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.PALMETTO.get()))));
		FeatureUtils.register(bootstapContext, CONFIGURED_BURNT_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.CHARRED_TALL_GRASS.get()))));
		FeatureUtils.register(bootstapContext, CONFIGURED_BURNT_BUSH, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.CHARRED_BUSH.get())));
		FeatureUtils.register(bootstapContext, CONFIGURED_DUCKWEED, Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WLBlocks.DUCKWEED.get())))));
		FeatureUtils.register(bootstapContext, CONFIGURED_BEARD_MOSS, WLFeatures.BEARD_MOSS.get(), new NoneFeatureConfiguration());
		FeatureUtils.register(bootstapContext, CONFIGURED_CYPRESS_TREE, WLFeatures.CYPRESS_TREE.get(), new NoneFeatureConfiguration());
		FeatureUtils.register(bootstapContext, CONFIGURED_COCONUT_TREE, WLFeatures.COCONUT_TREE.get(), new NoneFeatureConfiguration());
		FeatureUtils.register(bootstapContext, CONFIGURED_CHARRED_TREE, WLFeatures.CHARRED_TREE.get(), new NoneFeatureConfiguration());
	}
	
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext, ResourceKey<ConfiguredFeature<?, ?>> resourceKey, F feature, FC featureConfiguration) {
		bootstapContext.register(resourceKey, new ConfiguredFeature<>(feature, featureConfiguration));
	}
	
	public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String id) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(WildLands.MOD_ID, id));
	}

}
