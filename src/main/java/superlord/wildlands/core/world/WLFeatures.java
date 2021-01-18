package superlord.wildlands.core.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import superlord.wildlands.WildLands;
import superlord.wildlands.world.feature.config.WLTreeConfig;
import superlord.wildlands.world.feature.tree.cypress.Cypress1;
import superlord.wildlands.world.feature.tree.cypress.Cypress2;
import superlord.wildlands.world.feature.tree.cypress.Cypress3;

public class WLFeatures {
	
    public static List<Feature<?>> features = new ArrayList<>();
    
    public static final Feature<WLTreeConfig> CYPRESS_TREE1 = createFeature("bald_cypress_1", new Cypress1(WLTreeConfig.CODEC.stable()));
    public static final Feature<WLTreeConfig> CYPRESS_TREE2 = createFeature("bald_cypress_2", new Cypress2(WLTreeConfig.CODEC.stable()));
    public static final Feature<WLTreeConfig> CYPRESS_TREE3 = createFeature("bald_cypress_3", new Cypress3(WLTreeConfig.CODEC.stable()));

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
