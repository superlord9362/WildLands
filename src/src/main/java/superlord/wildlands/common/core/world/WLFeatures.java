package superlord.wildlands.common.core.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.util.RegistryObject;
import superlord.wildlands.common.world.feature.BeardMossFeature;
import superlord.wildlands.common.world.feature.BigBlockBlobFeature;
import superlord.wildlands.common.world.feature.SmolderingLogFeature;
import superlord.wildlands.common.world.feature.StarFishFeature;
import superlord.wildlands.common.world.feature.config.SmolderingLogFeatureConfig;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut1;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut10;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut11;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut12;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut13;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut2;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut3;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut4;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut5;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut6;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut7;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut8;
import superlord.wildlands.common.world.feature.tree.coconut.Coconut9;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress1;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress2;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress3;
import superlord.wildlands.common.world.feature.util.WLAbstractTreeFeature;

public class WLFeatures {
	
    public static List<RegistryObject<Feature<?>>> FEATURES = new ArrayList<>();
	
    public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE1 = createFeature("bald_cypress_1", new Cypress1(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE2 = createFeature("bald_cypress_2",  new Cypress2(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE3 = createFeature("bald_cypress_3",  new Cypress3(WLTreeConfig.CODEC.stable()));
    
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_1 = createFeature("coconut_palm_1",  new Coconut1(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_2 = createFeature("coconut_palm_2",  new Coconut2(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_3 = createFeature("coconut_palm_3",  new Coconut3(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_4 = createFeature("coconut_palm_4",  new Coconut4(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_5 = createFeature("coconut_palm_5",  new Coconut5(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_6 = createFeature("coconut_palm_6",  new Coconut6(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_7 = createFeature("coconut_palm_7",  new Coconut7(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_8 = createFeature("coconut_palm_8",  new Coconut8(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_9 = createFeature("coconut_palm_9",  new Coconut9(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_10 = createFeature("coconut_palm_10",  new Coconut10(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_11 = createFeature("coconut_palm_11",  new Coconut11(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_12 = createFeature("coconut_palm_12",  new Coconut12(WLTreeConfig.CODEC.stable()));
    public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_13 = createFeature("coconut_palm_13",  new Coconut13(WLTreeConfig.CODEC.stable()));

    public static final BigBlockBlobFeature BIG_BLOB = createFeature("big_blob",  new BigBlockBlobFeature(BlockStateConfiguration.CODEC.stable()));
    public static final StarFishFeature STARFISH = createFeature("starfish",  new StarFishFeature(CountConfiguration.CODEC.stable()));
    public static final SmolderingLogFeature SMOLDERING_LOG = createFeature("smoldering_log",  new SmolderingLogFeature(SmolderingLogFeatureConfig.field_236558_a_.stable()));
    public static final BeardMossFeature BEARD_MOSS = createFeature("weeping_vines",  new BeardMossFeature(NoneFeatureConfiguration.CODEC.stable()));

    //	public static final Feature<SmolderingLogFeatureConfig> SMOLDERING_LOG = createFeature("smoldering_log",  new SmolderingLogFeature(SmolderingLogFeatureConfig.field_236558_a_.stable()));

    
    @SuppressWarnings("deprecation")
	public static <C extends FeatureConfiguration, F extends Feature<C>> F createFeature(String id, F feature) {
        ResourceLocation wlID = WildLands.createLocation(id);
        if (Registry.FEATURE.keySet().contains(wlID))
            throw new IllegalStateException("Feature ID: \"" + wlID.toString() + "\" already exists in the Features registry!");

        WLFeatures.FEATURES.add(new RegistryObject<>(feature, id));
        return feature;
    }

    public static Collection<RegistryObject<Feature<?>>> bootStrap() {
        return FEATURES;
    }
    
    public static void loadClass() {
    }

//	private static final RegistrationProvider<Feature<?>> PROVIDER = RegistrationProvider.get(Registry.FEATURE_REGISTRY, WildLands.MOD_ID);
//	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_REGISTRY = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, WildLands.MOD_ID);
//
//	public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE1 = createFeature("bald_cypress_1",  new Cypress1(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE2 = createFeature("bald_cypress_2",  new Cypress2(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> CYPRESS_TREE3 = createFeature("bald_cypress_3",  new Cypress3(WLTreeConfig.CODEC.stable()));
//
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_1 = createFeature("coconut_palm_1",  new Coconut1(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_2 = createFeature("coconut_palm_2",  new Coconut2(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_3 = createFeature("coconut_palm_3",  new Coconut3(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_4 = createFeature("coconut_palm_4",  new Coconut4(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_5 = createFeature("coconut_palm_5",  new Coconut5(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_6 = createFeature("coconut_palm_6",  new Coconut6(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_7 = createFeature("coconut_palm_7",  new Coconut7(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_8 = createFeature("coconut_palm_8",  new Coconut8(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_9 = createFeature("coconut_palm_9",  new Coconut9(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_10 = createFeature("coconut_palm_10",  new Coconut10(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_11 = createFeature("coconut_palm_11",  new Coconut11(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_12 = createFeature("coconut_palm_12",  new Coconut12(WLTreeConfig.CODEC.stable()));
//	public static final WLAbstractTreeFeature<WLTreeConfig> COCONUT_TREE_13 = createFeature("coconut_palm_13",  new Coconut13(WLTreeConfig.CODEC.stable()));
//
//	public static final Feature<BlockStateConfiguration> DOLERITE = createFeature("dolerite",  new BlockBlobFeature(BlockStateConfiguration.CODEC));
//	public static final StarFishFeature STARFISH = createFeature("starfish",  new StarFishFeature(ProbabilityConfig.CODEC));
//	public static final UrchinFeature URCHIN = createFeature("urchin",  new UrchinFeature(ProbabilityConfig.CODEC));
//	public static final Feature<DuckweedConfig> DUCKWEED_FEATURE = createFeature("duckweed",  new DuckweedFeature(DuckweedConfig.field_236558_a_.stable()));
//	public static final Feature<NoneFeatureConfiguration> BEARD_MOSS_FEATURE = createFeature("beard_moss",  new BeardMossFeature(NoneFeatureConfiguration.CODEC));
//	public static final Feature<BlockStateConfiguration> BIG_BLOB = createFeature("big_block_blob",  new BigBlockBlobFeature(BlockStateConfiguration.CODEC));
//	public static final Feature<SmolderingLogFeatureConfig> SMOLDERING_LOG = createFeature("smoldering_log",  new SmolderingLogFeature(SmolderingLogFeatureConfig.field_236558_a_.stable()));
//
//	@SuppressWarnings("deprecation")
//	public static <C extends FeatureConfiguration, F extends Feature<C>> F> createFeature(String id, Supplier<? extends F> feature) {
//		return PROVIDER.register(id, feature);
//	}
//
//
//	public static void init() {
//	}

}
