package superlord.wildlands.core.world;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import superlord.wildlands.WildLands;
import superlord.wildlands.init.BlockInit;
import superlord.wildlands.world.feature.config.WLTreeConfig;

public class WLConfiguredFeatures {

    protected static final BlockState DIRT_STATE = Blocks.DIRT.getDefaultState();
    protected static final BlockState MUD_STATE = BlockInit.MUD.get().getDefaultState();

	
	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE1 = createConfiguredFeature("bald_cypress_tree_1", WLFeatures.CYPRESS_TREE1.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(BlockInit.CYPRESS_LOG.get().getDefaultState()).setLeavesBlock(BlockInit.CYPRESS_LEAVES.get()).setMinHeight(11).setMaxHeight(14).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE2 = createConfiguredFeature("bald_cypress_tree_2", WLFeatures.CYPRESS_TREE2.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(BlockInit.CYPRESS_LOG.get().getDefaultState()).setLeavesBlock(BlockInit.CYPRESS_LEAVES.get()).setMinHeight(9).setMaxHeight(12).build()));
	public static final ConfiguredFeature<WLTreeConfig, ?> BALD_CYPRESS_TREE3 = createConfiguredFeature("bald_cypress_tree_3", WLFeatures.CYPRESS_TREE3.withConfiguration(new WLTreeConfig.Builder().setTrunkBlock(BlockInit.CYPRESS_LOG.get().getDefaultState()).setLeavesBlock(BlockInit.CYPRESS_LEAVES.get()).setMinHeight(13).setMaxHeight(17).build()));
	public static final ConfiguredFeature<?, ?> MUD_DISK = createConfiguredFeature("mud_disk", Feature.DISK.withConfiguration(new SphereReplaceConfig(MUD_STATE, FeatureSpread.func_242253_a(2, 1), 1, ImmutableList.of(DIRT_STATE, MUD_STATE))).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));

	public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(wlID))
			throw new IllegalStateException("Configured Feature ID: \"" + wlID.toString() + "\" already exists in the Configured Features registry!");

		Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, wlID, configuredFeature);
		return configuredFeature;
	}

	public static final ConfiguredFeature<?, ?> RANDOM_CYPRESS_TREE = createConfiguredFeature("cypress_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(
			BALD_CYPRESS_TREE1.withChance(0.33F),
			BALD_CYPRESS_TREE2.withChance(0.33F)),
			BALD_CYPRESS_TREE3)).withPlacement(Placement.COUNT_EXTRA.configure(
					new AtSurfaceWithExtraConfig(8, 0.3F, 2))));

}
