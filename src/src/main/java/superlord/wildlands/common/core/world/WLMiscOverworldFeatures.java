package superlord.wildlands.common.core.world;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import superlord.wildlands.init.WildLandsBlocks;

public class WLMiscOverworldFeatures {
	
	   public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_MUD = FeatureUtils.register("disk_mud", Feature.DISK, new DiskConfiguration(WildLandsBlocks.MUD.get().defaultBlockState(), UniformInt.of(2, 3), 1, List.of(WildLandsBlocks.MUD.get().defaultBlockState())));
	   public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_FINE_SAND = FeatureUtils.register("disk_fine_sand", Feature.DISK, new DiskConfiguration(WildLandsBlocks.FINE_SAND.get().defaultBlockState(), UniformInt.of(2, 3), 1, List.of(WildLandsBlocks.FINE_SAND.get().defaultBlockState())));
	   public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_CONGLOMERATE = FeatureUtils.register("disk_conglomerate", Feature.DISK, new DiskConfiguration(WildLandsBlocks.CONGLOMERATE.get().defaultBlockState(), UniformInt.of(2, 3), 1, List.of(WildLandsBlocks.CONGLOMERATE.get().defaultBlockState())));
	   public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_GABBRO = FeatureUtils.register("disk_gabbro", Feature.DISK, new DiskConfiguration(WildLandsBlocks.GABBRO.get().defaultBlockState(), UniformInt.of(2, 3), 1, List.of(WildLandsBlocks.GABBRO.get().defaultBlockState())));
	   public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_DOLERITE = FeatureUtils.register("disk_dolerite", Feature.DISK, new DiskConfiguration(WildLandsBlocks.DOLERITE.get().defaultBlockState(), UniformInt.of(2, 3), 1, List.of(WildLandsBlocks.DOLERITE.get().defaultBlockState())));

	   public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> CONGLOMERATE_ROCK = FeatureUtils.register("conglomerate_rock", WLFeatures.BIG_BLOB, new BlockStateConfiguration(WildLandsBlocks.CONGLOMERATE.get().defaultBlockState()));
	   public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> GABBRO_ROCK = FeatureUtils.register("gabbro_rock", WLFeatures.BIG_BLOB, new BlockStateConfiguration(WildLandsBlocks.GABBRO.get().defaultBlockState()));
	   public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> DOLERITE_ROCK = FeatureUtils.register("dolerite_rock", WLFeatures.BIG_BLOB, new BlockStateConfiguration(WildLandsBlocks.DOLERITE.get().defaultBlockState()));
	   public static final Holder<ConfiguredFeature<BlockStateConfiguration, ?>> OLIVINE_GABBRO_ROCK = FeatureUtils.register("olivine_gabbro_rock", Feature.FOREST_ROCK, new BlockStateConfiguration(WildLandsBlocks.OLIVINE_GABBRO.get().defaultBlockState()));

}
