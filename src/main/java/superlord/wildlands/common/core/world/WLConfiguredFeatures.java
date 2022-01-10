package superlord.wildlands.common.core.world;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.block.WallCoconutBlock;
import superlord.wildlands.common.world.WLDecorators;
import superlord.wildlands.common.world.feature.config.SmolderingLogFeatureConfig;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;
import superlord.wildlands.init.WildLandsBlocks;

public class WLConfiguredFeatures {

	protected static final BlockState DIRT_STATE = Blocks.DIRT.getDefaultState();
	protected static final BlockState MUD_STATE = WildLandsBlocks.MUD.get().getDefaultState();
	public static final BlockState DOLERITE_STATE = WildLandsBlocks.DOLERITE.get().getDefaultState();
	public static final BlockState GABBRO_STATE = WildLandsBlocks.GABBRO.get().getDefaultState();
	public static final BlockState OLIVINE_GABBRO_STATE = WildLandsBlocks.OLIVINE_GABBRO.get().getDefaultState();
	public static final BlockState FINE_SAND_STATE = WildLandsBlocks.FINE_SAND.get().getDefaultState();
	public static final BlockState CONGLOMERATE_STATE = WildLandsBlocks.CONGLOMERATE.get().getDefaultState();
	public static final BlockState SAND_STATE = Blocks.SAND.getDefaultState();
	public static final BlockState GRAVEL_STATE = Blocks.GRAVEL.getDefaultState();
	public static final BlockState STONE_STATE = Blocks.STONE.getDefaultState();

	public static final BlockClusterFeatureConfig PALMETTO_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WildLandsBlocks.PALMETTO.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(16).build();
	public static final BlockClusterFeatureConfig CATTAIL_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WildLandsBlocks.CATTAIL.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
	public static final BlockClusterFeatureConfig DUCKWEED_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WildLandsBlocks.DUCKWEED.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(16).build();

	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE1 = createConfiguredFeature("bald_cypress_tree_1", WLFeatures.CYPRESS_TREE1.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(WildLandsBlocks.CYPRESS_LOG.getDefaultState()).setLeavesBlock(WildLandsBlocks.CYPRESS_LEAVES.get()).setMinHeight(11).setMaxHeight(14).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE2 = createConfiguredFeature("bald_cypress_tree_2", WLFeatures.CYPRESS_TREE2.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(WildLandsBlocks.CYPRESS_LOG.getDefaultState()).setLeavesBlock(WildLandsBlocks.CYPRESS_LEAVES.get()).setMinHeight(9).setMaxHeight(12).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE3 = createConfiguredFeature("bald_cypress_tree_3", WLFeatures.CYPRESS_TREE3.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(WildLandsBlocks.CYPRESS_LOG.getDefaultState()).setLeavesBlock(WildLandsBlocks.CYPRESS_LEAVES.get()).setMinHeight(13).setMaxHeight(17).build()));

	public static final ConfiguredFeature<WLTreeConfig, ?> COCONUT_PALM_1 = createConfiguredFeature("coconut_palm_1", WLFeatures.COCONUT_TREE_1.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(WildLandsBlocks.COCONUT_LOG.getDefaultState()).setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get()).setExtraBlock(WildLandsBlocks.WALL_COCONUT.get()).setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.EAST)).setMinHeight(20).setMaxHeight(20).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> COCONUT_PALM_2 = createConfiguredFeature("coconut_palm_2", WLFeatures.COCONUT_TREE_2.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(WildLandsBlocks.COCONUT_LOG.getDefaultState()).setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get()).setMinHeight(5).setMaxHeight(5).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> COCONUT_PALM_3 = createConfiguredFeature("coconut_palm_3", WLFeatures.COCONUT_TREE_3.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(WildLandsBlocks.COCONUT_LOG.getDefaultState()).setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get()).setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.WEST)).setExtraBlock(WildLandsBlocks.WALL_COCONUT.get()).setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.EAST)).setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.SOUTH)).setMinHeight(9).setMaxHeight(9).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> COCONUT_PALM_4 = createConfiguredFeature("coconut_palm_4", WLFeatures.COCONUT_TREE_4.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(WildLandsBlocks.COCONUT_LOG.getDefaultState()).setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get()).setExtraBlock(WildLandsBlocks.WALL_COCONUT.get()).setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.WEST)).setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.SOUTH)).setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.EAST)).setMinHeight(12).setMaxHeight(12).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> COCONUT_PALM_5 = createConfiguredFeature("coconut_palm_5", WLFeatures.COCONUT_TREE_5.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(WildLandsBlocks.COCONUT_LOG.getDefaultState()).setLeavesBlock(WildLandsBlocks.COCONUT_LEAVES.get()).setExtraBlock(WildLandsBlocks.WALL_COCONUT.get()).setExtraBlockWest(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.WEST)).setExtraBlockSouth(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.SOUTH)).setExtraBlockEast(WildLandsBlocks.WALL_COCONUT.get().getDefaultState().with(WallCoconutBlock.FACING, Direction.EAST)).setMinHeight(19).setMaxHeight(19).build()));

	public static final ConfiguredFeature<?, ?> MUD_DISK = createConfiguredFeature("mud_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(MUD_STATE, FeatureSpread.func_242253_a(2, 1), 1, ImmutableList.of(DIRT_STATE, MUD_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> PALMETTO_PATCH = createConfiguredFeature("palmetto_patch", Feature.RANDOM_PATCH.withConfiguration(PALMETTO_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(3));
	public static final ConfiguredFeature<?, ?> CATTAIL_PATCH = createConfiguredFeature("cattail_patch", Feature.RANDOM_PATCH.withConfiguration(CATTAIL_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(7));

	public static final ConfiguredFeature<?, ?> DOLERITE_ROCK = createConfiguredFeature("dolerite_rock", WLFeatures.BIG_BLOB.withConfiguration(new BlockStateFeatureConfig(DOLERITE_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));
	public static final ConfiguredFeature<?, ?> DOLERITE_DISK = createConfiguredFeature("dolerite_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(DOLERITE_STATE, FeatureSpread.func_242253_a(2, 1), 1, ImmutableList.of(SAND_STATE, GRAVEL_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> GABBRO_ROCK = createConfiguredFeature("gabbro_rock", WLFeatures.BIG_BLOB.withConfiguration(new BlockStateFeatureConfig(GABBRO_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));
	public static final ConfiguredFeature<?, ?> OLIVINE_GABBRO_ROCK = createConfiguredFeature("olivine_gabbro_rock", WLFeatures.BIG_BLOB.withConfiguration(new BlockStateFeatureConfig(OLIVINE_GABBRO_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));
	public static final ConfiguredFeature<?, ?> GABBRO_DISK = createConfiguredFeature("gabbro_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(GABBRO_STATE, FeatureSpread.func_242253_a(2, 1), 1, ImmutableList.of(SAND_STATE, GRAVEL_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> FINE_SAND_DISK = createConfiguredFeature("fine_sand_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(FINE_SAND_STATE, FeatureSpread.func_242253_a(4, 1), 2, ImmutableList.of(SAND_STATE, GRAVEL_STATE, GABBRO_STATE, DOLERITE_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> CONGLOMERATE_DISK = createConfiguredFeature("conglomerate_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(CONGLOMERATE_STATE, FeatureSpread.func_242253_a(4, 1), 2, ImmutableList.of(GRAVEL_STATE, DIRT_STATE, GABBRO_STATE, DOLERITE_STATE, STONE_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> CONGLOMERATE_ROCK = createConfiguredFeature("conglomerate_rock", WLFeatures.BIG_BLOB.withConfiguration(new BlockStateFeatureConfig(CONGLOMERATE_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));
	public static final ConfiguredFeature<?, ?> DUCKWEED = createConfiguredFeature("duckweed", Feature.RANDOM_PATCH.withConfiguration(DUCKWEED_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(3));
	public static final ConfiguredFeature<?, ?> BEARD_MOSS = createConfiguredFeature("beard_moss", WLFeatures.BEARD_MOSS_FEATURE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(128).square().func_242731_b(10));
	public static final ConfiguredFeature<?, ?> BEACH_ROCK = createConfiguredFeature("beach_rock", Feature.FOREST_ROCK.withConfiguration(new BlockStateFeatureConfig(CONGLOMERATE_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));

	public static final ConfiguredFeature<?, ?> CHARRED_LOG = createConfiguredFeature("charred_log", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(WildLandsBlocks.CHARRED_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).setIgnoreVines().build()).withPlacement(Features.Placements.PATCH_PLACEMENT.chance(15)).func_242731_b(5));
	public static final ConfiguredFeature<?, ?> SMOLDERING_LOG = createConfiguredFeature("smoldering_log", WLFeatures.SMOLDERING_LOG.withConfiguration(new SmolderingLogFeatureConfig(1)).withPlacement(Features.Placements.PATCH_PLACEMENT.chance(15)));
	public static final ConfiguredFeature<?, ?> BURNT_GRASS = createConfiguredFeature("burnt_grass", Feature.RANDOM_PATCH.withConfiguration(WLFeatures.BURNT_GRASS_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(5));
	public static final ConfiguredFeature<?, ?> BURNT_BUSH = createConfiguredFeature("burnt_bush", Feature.RANDOM_PATCH.withConfiguration(WLFeatures.BURNT_BUSH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT));

	
	public static final ConfiguredFeature<?, ?> STARFISH = createConfiguredFeature("starfish", WLFeatures.STARFISH.withConfiguration(new ProbabilityConfig(0.1F)).func_242731_b(5).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> URCHIN = createConfiguredFeature("urchin", WLFeatures.URCHIN.withConfiguration(new ProbabilityConfig(0.1F)).func_242731_b(5).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));

	public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(wlID))
			throw new IllegalStateException("Configured Feature ID: \"" + wlID.toString() + "\" already exists in the Configured Features registry!");

		Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, wlID, configuredFeature);
		return configuredFeature;
	}

	public static final ConfiguredFeature<?, ?> RANDOM_BAYOU_TREE = createConfiguredFeature("bayou_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(
			BALD_CYPRESS_TREE1.withChance(0.33F),
			BALD_CYPRESS_TREE2.withChance(0.33F)),
			BALD_CYPRESS_TREE3)).withPlacement(WLDecorators.OCEAN_FLOOR_COUNT_EXTRA.configure(
					new AtSurfaceWithExtraConfig(2, 0.3F, 2))));

	public static final ConfiguredFeature<?, ?> RANDOM_COCONUT_TREE = createConfiguredFeature("coconut_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(
			COCONUT_PALM_1.withChance(0.05F),
			COCONUT_PALM_2.withChance(0.5F),
			COCONUT_PALM_3.withChance(02F),
			COCONUT_PALM_4.withChance(0.2F)),
			COCONUT_PALM_5))
			.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

	public static final ConfiguredFeature<?, ?> RANDOM_BURNT_LOG = createConfiguredFeature("burnt_logs", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(
			CHARRED_LOG.withChance(0.75F)),
			SMOLDERING_LOG)).withPlacement(WLDecorators.OCEAN_FLOOR_COUNT_EXTRA.configure(
					new AtSurfaceWithExtraConfig(2, 0.3F, 2))));

}
