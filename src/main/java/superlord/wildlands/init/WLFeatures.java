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
import superlord.wildlands.common.world.feature.tree.WLTreeConfig;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress1;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress2;
import superlord.wildlands.common.world.feature.tree.cypress.Cypress3;

public class WLFeatures {
	
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WildLands.MOD_ID);

    public static final RegistryObject<StarFishFeature> STARFISH = FEATURES.register("starfish_feature", () -> new StarFishFeature(CountConfiguration.CODEC.stable()));
    public static final RegistryObject<UrchinFeature> URCHIN = FEATURES.register("urchin_feature", () -> new UrchinFeature(CountConfiguration.CODEC.stable()));
    public static final RegistryObject<LargeRockFeature> LARGE_ROCK = FEATURES.register("large_rock", () -> new LargeRockFeature(BlockStateConfiguration.CODEC.stable()));

    public static final RegistryObject<BeardMossFeature> BEARD_MOSS = FEATURES.register("beard_moss", () -> new BeardMossFeature(NoneFeatureConfiguration.CODEC.stable()));
    
    public static final RegistryObject<Cypress1> CYPRESS_TREE_1 = FEATURES.register("cypress_tree_1", () -> new Cypress1(WLTreeConfig.CODEC.stable())); 
    public static final RegistryObject<Cypress2> CYPRESS_TREE_2 = FEATURES.register("cypress_tree_2", () -> new Cypress2(WLTreeConfig.CODEC.stable())); 
    public static final RegistryObject<Cypress3> CYPRESS_TREE_3 = FEATURES.register("cypress_tree_3", () -> new Cypress3(WLTreeConfig.CODEC.stable())); 

}
