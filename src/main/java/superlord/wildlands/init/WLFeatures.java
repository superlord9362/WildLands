package superlord.wildlands.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.world.feature.BeardMossFeature;
import superlord.wildlands.common.world.feature.LargeRockFeature;
import superlord.wildlands.common.world.feature.StarFishFeature;
import superlord.wildlands.common.world.feature.UrchinFeature;
import superlord.wildlands.common.world.feature.tree.BurntTreeFeature;
import superlord.wildlands.common.world.feature.tree.CoconutTreeFeature;
import superlord.wildlands.common.world.feature.tree.CypressTreeFeature;

public class WLFeatures {
	
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WildLands.MOD_ID);

    public static final RegistryObject<StarFishFeature> STARFISH = FEATURES.register("starfish_feature", () -> new StarFishFeature(CountConfiguration.CODEC.stable()));
    public static final RegistryObject<UrchinFeature> URCHIN = FEATURES.register("urchin_feature", () -> new UrchinFeature(CountConfiguration.CODEC.stable()));
    public static final RegistryObject<LargeRockFeature> LARGE_ROCK = FEATURES.register("large_rock", () -> new LargeRockFeature(BlockStateConfiguration.CODEC.stable()));

    public static final RegistryObject<BeardMossFeature> BEARD_MOSS = FEATURES.register("beard_moss", () -> new BeardMossFeature(NoneFeatureConfiguration.CODEC.stable()));
    public static final RegistryObject<CypressTreeFeature> CYPRESS_TREE = FEATURES.register("cypress_tree", () -> new CypressTreeFeature(NoneFeatureConfiguration.CODEC.stable()));
    public static final RegistryObject<CoconutTreeFeature> COCONUT_TREE = FEATURES.register("coconut_tree", () -> new CoconutTreeFeature(NoneFeatureConfiguration.CODEC.stable()));
    
    public static final RegistryObject<BurntTreeFeature> CHARRED_TREE = FEATURES.register("charred_tree", () -> new BurntTreeFeature(NoneFeatureConfiguration.CODEC.stable()));
    
}
