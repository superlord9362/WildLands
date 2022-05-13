package superlord.wildlands.common.core.world;

import static superlord.wildlands.common.core.world.WLFeatureUtil.createConfiguredFeature;
import static superlord.wildlands.common.core.world.WLPlacedFeatureUtil.createPlacedFeature;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import superlord.wildlands.common.block.WallCoconutBlock;
import superlord.wildlands.common.world.feature.config.SmolderingLogFeatureConfig;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;
import superlord.wildlands.init.WildLandsBlocks;

public class WLOverworldTreeFeatures {

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> CYPRESS_TREE_1 = createConfiguredFeature("cypress_tree1",
			WLFeatures.CYPRESS_TREE1.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.CYPRESS_LOG.get())
			.setLeavesBlock(WildLandsBlocks.CYPRESS_LEAVES.get())
			.setMaxHeight(14)
			.setMinHeight(11)
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> CYPRESS_TREE_2 = createConfiguredFeature("cypress_tree2",
			WLFeatures.CYPRESS_TREE2.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.CYPRESS_LOG.get())
			.setLeavesBlock(WildLandsBlocks.CYPRESS_LEAVES.get())
			.setMaxHeight(12)
			.setMinHeight(9)
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> CYPRESS_TREE_3 = createConfiguredFeature("cypress_tree3",
			WLFeatures.CYPRESS_TREE1.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.CYPRESS_LOG.get())
			.setLeavesBlock(WildLandsBlocks.CYPRESS_LEAVES.get())
			.setMaxHeight(17)
			.setMinHeight(14)
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_1 = createConfiguredFeature("coconut_palm1",
			WLFeatures.COCONUT_TREE_1.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(9)
			.setMinHeight(9)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_2 = createConfiguredFeature("coconut_palm2",
			WLFeatures.COCONUT_TREE_2.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(12)
			.setMinHeight(12)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_3 = createConfiguredFeature("coconut_palm3",
			WLFeatures.COCONUT_TREE_3.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(19)
			.setMinHeight(19)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_4 = createConfiguredFeature("coconut_palm4",
			WLFeatures.COCONUT_TREE_4.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(12)
			.setMinHeight(12)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_5 = createConfiguredFeature("coconut_palm5",
			WLFeatures.COCONUT_TREE_5.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(12)
			.setMinHeight(12)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_6 = createConfiguredFeature("coconut_palm6",
			WLFeatures.COCONUT_TREE_6.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(12)
			.setMinHeight(12)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_7 = createConfiguredFeature("coconut_palm7",
			WLFeatures.COCONUT_TREE_7.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(12)
			.setMinHeight(12)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_8 = createConfiguredFeature("coconut_palm8",
			WLFeatures.COCONUT_TREE_8.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(8)
			.setMinHeight(8)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_9 = createConfiguredFeature("coconut_palm9",
			WLFeatures.COCONUT_TREE_9.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(8)
			.setMinHeight(8)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_10 = createConfiguredFeature("coconut_palm10",
			WLFeatures.COCONUT_TREE_10.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(8)
			.setMinHeight(8)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_11 = createConfiguredFeature("coconut_palm11",
			WLFeatures.COCONUT_TREE_11.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(8)
			.setMinHeight(8)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_12 = createConfiguredFeature("coconut_palm12",
			WLFeatures.COCONUT_TREE_12.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(8)
			.setMinHeight(8)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<WLTreeConfig, ?>> COCONUT_PALM_13 = createConfiguredFeature("coconut_palm13",
			WLFeatures.COCONUT_TREE_13.get(),
			new WLTreeConfig.Builder()
			.setTrunkBlock(WildLandsBlocks.COCONUT_LOG.get())
			.setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get())
			.setMaxHeight(8)
			.setMinHeight(8)
			.setExtraBlock(WildLandsBlocks.WALL_COCONUT.get())
			.setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.WEST))
			.setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.EAST))
			.setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().defaultBlockState().setValue(WallCoconutBlock.FACING, Direction.SOUTH))
			.build()
			);

	public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CHARRED_LOG = createConfiguredFeature("charred_log",
			Feature.TREE,
			(new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(WildLandsBlocks.CHARRED_LOG.get()),
					new StraightTrunkPlacer(6, 4, 0),
					BlockStateProvider.simple(Blocks.AIR),
					new SpruceFoliagePlacer(UniformInt.of(2, 3),
							UniformInt.of(0, 2),
							UniformInt.of(1, 2)),
					new TwoLayersFeatureSize(2, 0, 2))
					).ignoreVines()
			.build()
			);

	public static final Holder<ConfiguredFeature<SmolderingLogFeatureConfig, ?>> SMOLDERING_LOG = createConfiguredFeature("smoldering_log",
			WLFeatures.SMOLDERING_LOG.get(),
			new SmolderingLogFeatureConfig(1)
			);

	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> BAYOU_TREES = createConfiguredFeature("bayou_trees",
			Feature.RANDOM_SELECTOR,
			new RandomFeatureConfiguration(ImmutableList.of(
					new WeightedPlacedFeature(createPlacedFeature(CYPRESS_TREE_1), 0.33F),
					new WeightedPlacedFeature(createPlacedFeature(CYPRESS_TREE_2), 0.33F)),
					createPlacedFeature(CYPRESS_TREE_3))
			);

	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> COCONUT_TREES = createConfiguredFeature("coconut_trees",
			Feature.RANDOM_SELECTOR,
			new RandomFeatureConfiguration(ImmutableList.of(
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_1), 0.03846153846F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_2), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_3), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_4), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_5), 0.03846153846F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_6), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_7), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_8), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_9), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_10), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_11), 0.07692307692F),
					new WeightedPlacedFeature(createPlacedFeature(COCONUT_PALM_12), 07692307692F)),
					createPlacedFeature(COCONUT_PALM_13))
			);

	public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CHARRED_LOGS = createConfiguredFeature("charred_logs",
			Feature.RANDOM_SELECTOR,
			new RandomFeatureConfiguration(ImmutableList.of(
					new WeightedPlacedFeature(createPlacedFeature(CHARRED_LOG), 0.75F)),
					createPlacedFeature(SMOLDERING_LOG))
			);

}
