package superlord.wildlands.common.core.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockBlobFeature;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.world.feature.BeardMossFeature;
import superlord.wildlands.common.world.feature.BigBlockBlobFeature;
import superlord.wildlands.common.world.feature.DuckweedFeature;
import superlord.wildlands.common.world.feature.SmolderingLogFeature;
import superlord.wildlands.common.world.feature.StarFishFeature;
import superlord.wildlands.common.world.feature.UrchinFeature;
import superlord.wildlands.common.world.feature.config.DuckweedConfig;
import superlord.wildlands.common.world.feature.config.SmolderingLogFeatureConfig;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut1;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut2;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut3;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut4;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut5;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress1;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress2;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress3;
import superlord.wildlands.common.world.feature.util.WLAbstractTreeFeature;
import superlord.wildlands.init.WildLandsBlocks;

public class WLFeatures {

	public static List<Feature<?>> features = new ArrayList<>();
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_REGISTRY = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, WildLands.MOD_ID);

	public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE1 = createFeature("bald_cypress_1", new Cypress1(WLTreeConfig.CODEC.stable()));
	public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE2 = createFeature("bald_cypress_2", new Cypress2(WLTreeConfig.CODEC.stable()));
	public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE3 = createFeature("bald_cypress_3", new Cypress3(WLTreeConfig.CODEC.stable()));

	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_1 = createFeature("coconut_palm_1", new Coconut1(WLTreeConfig.CODEC.stable()));
	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_2 = createFeature("coconut_palm_2", new Coconut2(WLTreeConfig.CODEC.stable()));
	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_3 = createFeature("coconut_palm_3", new Coconut3(WLTreeConfig.CODEC.stable()));
	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_4 = createFeature("coconut_palm_4", new Coconut4(WLTreeConfig.CODEC.stable()));
	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_5 = createFeature("coconut_palm_5", new Coconut5(WLTreeConfig.CODEC.stable()));

	public static final Feature<BlockStateFeatureConfig> DOLERITE = createFeature("dolerite", new BlockBlobFeature(BlockStateFeatureConfig.field_236455_a_));
	public static final StarFishFeature STARFISH = createFeature("starfish", new StarFishFeature(ProbabilityConfig.CODEC));
	public static final UrchinFeature URCHIN = createFeature("urchin", new UrchinFeature(ProbabilityConfig.CODEC));
	public static final Feature<DuckweedConfig> DUCKWEED_FEATURE = createFeature("duckweed", new DuckweedFeature(DuckweedConfig.field_236558_a_.stable()));
	public static final Feature<NoFeatureConfig> BEARD_MOSS_FEATURE = createFeature("beard_moss", new BeardMossFeature(NoFeatureConfig.field_236558_a_.stable()));
	public static final Feature<BlockStateFeatureConfig> BIG_BLOB = createFeature("big_block_blob", new BigBlockBlobFeature(BlockStateFeatureConfig.field_236455_a_.stable()));
	public static final Feature<SmolderingLogFeatureConfig> SMOLDERING_LOG = createFeature("smoldering_log", new SmolderingLogFeature(SmolderingLogFeatureConfig.field_236558_a_.stable()));
    public static final BlockClusterFeatureConfig BURNT_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WildLandsBlocks.CHARRED_TALL_GRASS.getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
    public static final BlockClusterFeatureConfig BURNT_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(WildLandsBlocks.CHARRED_BUSH.getDefaultState()), SimpleBlockPlacer.PLACER)).tries(4).build();

	@SuppressWarnings("deprecation")
	public static <C extends IFeatureConfig, F extends Feature<C>> F createFeature(String id, F feature) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (Registry.FEATURE.keySet().contains(wlID))
			throw new IllegalStateException("Feature ID: \"" + wlID.toString() + "\" already exists in the Features registry!");

		//        Registry.register(Registry.FEATURE, wlID, feature);
		feature.setRegistryName(wlID); //Forge
		WLFeatures.features.add(feature);
		return feature;
	}

	public static void init() {
	}

}
