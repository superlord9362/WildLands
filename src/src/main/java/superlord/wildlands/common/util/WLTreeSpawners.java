package superlord.wildlands.common.util;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import superlord.wildlands.common.core.world.WLOverworldTreeFeatures;
import superlord.wildlands.common.world.feature.config.WLTreeConfig;

public class WLTreeSpawners {

	public static final TreeSpawner CYPRESS = new TreeSpawner() {
		@Nullable
		public ConfiguredFeature<WLTreeConfig, ?> getTreeFeature(Random random) {
			int number = random.nextInt(3);
			if (number == 0) {
				return WLOverworldTreeFeatures.CYPRESS_TREE_1.value();
			} else if ( number == 1) {
				return WLOverworldTreeFeatures.CYPRESS_TREE_2.value();
			} else {
				return WLOverworldTreeFeatures.CYPRESS_TREE_3.value();
			}
		}
	};

	public static final TreeSpawner COCONUT = new TreeSpawner() {
		@Nullable
		public ConfiguredFeature<WLTreeConfig, ?> getTreeFeature(Random random) {
			int number = random.nextInt(23);
			if (number == 0) {
				return WLOverworldTreeFeatures.COCONUT_PALM_1.value();
			} else if (number == 1 || number == 2) {
				return WLOverworldTreeFeatures.COCONUT_PALM_2.value();
			} else if (number == 3 || number == 4) {
				return WLOverworldTreeFeatures.COCONUT_PALM_3.value();
			} else if (number == 5 || number == 6) {
				return WLOverworldTreeFeatures.COCONUT_PALM_4.value();
			} else if (number == 7) {
				return WLOverworldTreeFeatures.COCONUT_PALM_5.value();
			} else if (number == 8 || number == 9) {
				return WLOverworldTreeFeatures.COCONUT_PALM_6.value();
			} else if (number == 10 || number == 11) {
				return WLOverworldTreeFeatures.COCONUT_PALM_7.value();
			} else if (number == 12 || number == 13) {
				return WLOverworldTreeFeatures.COCONUT_PALM_8.value();
			} else if (number == 14 || number == 15) {
				return WLOverworldTreeFeatures.COCONUT_PALM_9.value();
			} else if (number == 16 || number == 17) {
				return WLOverworldTreeFeatures.COCONUT_PALM_10.value();
			} else if (number == 18 || number == 19) {
				return WLOverworldTreeFeatures.COCONUT_PALM_11.value();
			} else if (number == 20 || number == 21) {
				return WLOverworldTreeFeatures.COCONUT_PALM_12.value();
			} else return WLOverworldTreeFeatures.COCONUT_PALM_13.value();
		}
	};

}
