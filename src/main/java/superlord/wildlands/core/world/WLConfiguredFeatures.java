package superlord.wildlands.core.world;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
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
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import superlord.wildlands.WildLands;
import superlord.wildlands.init.BlockInit;
import superlord.wildlands.world.WLDecorators;
import superlord.wildlands.world.feature.config.WLTreeConfig;

public class WLConfiguredFeatures {

	protected static final BlockState DIRT_STATE = Blocks.DIRT.getDefaultState();
	protected static final BlockState MUD_STATE = BlockInit.MUD.get().getDefaultState();
	public static final BlockState DOLERITE_STATE = BlockInit.DOLERITE.get().getDefaultState();
	public static final BlockState GABBRO_STATE = BlockInit.GABBRO.get().getDefaultState();
	public static final BlockState OLIVINE_GABBRO_STATE = BlockInit.OLIVINE_GABBRO.get().getDefaultState();
	public static final BlockState FINE_SAND_STATE = BlockInit.FINE_SAND.get().getDefaultState();
	public static final BlockState CONGLOMERATE_STATE = BlockInit.CONGLOMERATE.get().getDefaultState();
	public static final BlockState SAND_STATE = Blocks.SAND.getDefaultState();
	public static final BlockState GRAVEL_STATE = Blocks.GRAVEL.getDefaultState();
	public static final BlockState STONE_STATE = Blocks.STONE.getDefaultState();

	public static final BlockClusterFeatureConfig PALMETTO_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.PALMETTO.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(16).build();

	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE1 = createConfiguredFeature("bald_cypress_tree_1", WLFeatures.CYPRESS_TREE1.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(BlockInit.CYPRESS_LOG.get().getDefaultState()).setLeavesBlock(BlockInit.CYPRESS_LEAVES.get()).setMinHeight(11).setMaxHeight(14).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE2 = createConfiguredFeature("bald_cypress_tree_2", WLFeatures.CYPRESS_TREE2.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(BlockInit.CYPRESS_LOG.get().getDefaultState()).setLeavesBlock(BlockInit.CYPRESS_LEAVES.get()).setMinHeight(9).setMaxHeight(12).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE3 = createConfiguredFeature("bald_cypress_tree_3", WLFeatures.CYPRESS_TREE3.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(BlockInit.CYPRESS_LOG.get().getDefaultState()).setLeavesBlock(BlockInit.CYPRESS_LEAVES.get()).setMinHeight(13).setMaxHeight(17).build()));

	public static final ConfiguredFeature<?, ?> MUD_DISK = createConfiguredFeature("mud_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(MUD_STATE, FeatureSpread.func_242253_a(2, 1), 1, ImmutableList.of(DIRT_STATE, MUD_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> PALMETTO_PATCH = createConfiguredFeature("palmetto_patch", Feature.RANDOM_PATCH.withConfiguration(PALMETTO_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(3));

	public static final ConfiguredFeature<?, ?> DOLERITE_ROCK = createConfiguredFeature("dolerite_rock", WLFeatures.DOLERITE.withConfiguration(new BlockStateFeatureConfig(DOLERITE_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));
	public static final ConfiguredFeature<?, ?> DOLERITE_DISK = createConfiguredFeature("dolerite_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(DOLERITE_STATE, FeatureSpread.func_242253_a(2, 1), 1, ImmutableList.of(SAND_STATE, GRAVEL_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> GABBRO_ROCK = createConfiguredFeature("gabbro_rock", WLFeatures.DOLERITE.withConfiguration(new BlockStateFeatureConfig(GABBRO_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));
	public static final ConfiguredFeature<?, ?> OLIVINE_GABBRO_ROCK = createConfiguredFeature("olivine_gabbro_rock", WLFeatures.DOLERITE.withConfiguration(new BlockStateFeatureConfig(OLIVINE_GABBRO_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));
	public static final ConfiguredFeature<?, ?> GABBRO_DISK = createConfiguredFeature("gabbro_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(GABBRO_STATE, FeatureSpread.func_242253_a(2, 1), 1, ImmutableList.of(SAND_STATE, GRAVEL_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> FINE_SAND_DISK = createConfiguredFeature("fine_sand_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(FINE_SAND_STATE, FeatureSpread.func_242253_a(4, 1), 2, ImmutableList.of(SAND_STATE, GRAVEL_STATE, GABBRO_STATE, DOLERITE_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> CONGLOMERATE_DISK = createConfiguredFeature("conglomerate_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(CONGLOMERATE_STATE, FeatureSpread.func_242253_a(4, 1), 2, ImmutableList.of(GRAVEL_STATE, DIRT_STATE, GABBRO_STATE, DOLERITE_STATE, STONE_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	public static final ConfiguredFeature<?, ?> CONGLOMERATE_ROCK = createConfiguredFeature("conglomerate_rock", WLFeatures.DOLERITE.withConfiguration(new BlockStateFeatureConfig(CONGLOMERATE_STATE)).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242732_c(3));

	public static final ConfiguredFeature<?, ?> STARFISH = createConfiguredFeature("starfish", WLFeatures.STARFISH.withConfiguration(new ProbabilityConfig(0.1F)).func_242731_b(5).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
	
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

}
