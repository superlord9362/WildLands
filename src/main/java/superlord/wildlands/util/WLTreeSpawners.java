package superlord.wildlands.util;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import superlord.wildlands.core.world.WLConfiguredFeatures;
import superlord.wildlands.world.feature.config.WLTreeConfig;

public class WLTreeSpawners {
	
	public static class Cypress extends TreeSpawner {
    	@Nullable
    	@Override
    	protected ConfiguredFeature<WLTreeConfig, ?> getTreeFeature(Random random) {
    		int number = random.nextInt(2);
    		if (number == 0) {
    			return WLConfiguredFeatures.BALD_CYPRESS_TREE1;
    		} else if (number == 1) {
    			return WLConfiguredFeatures.BALD_CYPRESS_TREE2;
     		} else {
     			return WLConfiguredFeatures.BALD_CYPRESS_TREE3;
     		}
    	}
    }
	
	public static class Coconut extends TreeSpawner {
    	@Nullable
    	@Override
    	protected ConfiguredFeature<WLTreeConfig, ?> getTreeFeature(Random random) {
   			return WLConfiguredFeatures.COCONUT_PALM_5;
    	}
    }

}
