package superlord.wildlands.common.core.world;

import static superlord.wildlands.common.core.world.WLPlacedFeatureUtil.clearingTreePlacement;
import static superlord.wildlands.common.core.world.WLPlacedFeatureUtil.clearingTreePlacementBaseOceanFloor;
import static superlord.wildlands.common.core.world.WLPlacedFeatureUtil.createPlacedFeature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CarvingMaskPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class WLPlacedFeatures {

	public static final Holder<PlacedFeature> CYPRESS_TREES = createPlacedFeature("cypress_trees", WLOverworldTreeFeatures.BAYOU_TREES, clearingTreePlacementBaseOceanFloor(PlacementUtils.countExtra(2, 0.3F, 2)));
	public static final Holder<PlacedFeature> COCONUT_TREES = createPlacedFeature("coconut_trees", WLOverworldTreeFeatures.COCONUT_TREES, clearingTreePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));
	public static final Holder<PlacedFeature> CHARRED_LOGS = createPlacedFeature("charred_logs", WLOverworldTreeFeatures.CHARRED_LOG, clearingTreePlacement(PlacementUtils.countExtra(2, 0.3F, 2)));
	
	public static final Holder<PlacedFeature> DISK_MUD = PlacementUtils.register("disk_mud", WLMiscOverworldFeatures.DISK_MUD, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
	public static final Holder<PlacedFeature> DISK_FINE_SAND = PlacementUtils.register("disk_fine_sand", WLMiscOverworldFeatures.DISK_FINE_SAND, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
	public static final Holder<PlacedFeature> DISK_CONGLOMERATE = PlacementUtils.register("disk_conglomerate", WLMiscOverworldFeatures.DISK_CONGLOMERATE, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
	public static final Holder<PlacedFeature> DISK_GABBRO = PlacementUtils.register("disk_gabbro", WLMiscOverworldFeatures.DISK_GABBRO, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
	public static final Holder<PlacedFeature> DISK_DOLERITE = PlacementUtils.register("disk_dolerite", WLMiscOverworldFeatures.DISK_DOLERITE, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());

	public static final Holder<PlacedFeature> CONGLOMERATE_ROCK = PlacementUtils.register("conglomerate_rock", WLMiscOverworldFeatures.CONGLOMERATE_ROCK, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
	public static final Holder<PlacedFeature> GABBRO_ROCK = PlacementUtils.register("gabbro_rock", WLMiscOverworldFeatures.GABBRO_ROCK, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
	public static final Holder<PlacedFeature> DOLERITE_ROCK = PlacementUtils.register("dolerite_rock", WLMiscOverworldFeatures.DOLERITE_ROCK, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
	public static final Holder<PlacedFeature> OLIVINE_GABBRO_ROCK = PlacementUtils.register("olivine_gabbro_rock", WLMiscOverworldFeatures.OLIVINE_GABBRO_ROCK, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());

	public static final Holder<PlacedFeature> URCHIN = PlacementUtils.register("urchin", WLVegetationFeatures.URCHIN, CarvingMaskPlacement.forStep(GenerationStep.Carving.LIQUID), RarityFilter.onAverageOnceEvery(10), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 0)), BlockPredicate.matchesBlock(Blocks.WATER, BlockPos.ZERO), BlockPredicate.matchesBlock(Blocks.WATER, new BlockPos(0, 1, 0)))), BiomeFilter.biome());
	public static final Holder<PlacedFeature> STARFISH = PlacementUtils.register("starfish", WLVegetationFeatures.STARFISH, CarvingMaskPlacement.forStep(GenerationStep.Carving.LIQUID), RarityFilter.onAverageOnceEvery(10), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 0)), BlockPredicate.matchesBlock(Blocks.WATER, BlockPos.ZERO), BlockPredicate.matchesBlock(Blocks.WATER, new BlockPos(0, 1, 0)))), BiomeFilter.biome());
	
	public static final Holder<PlacedFeature> CATTAIL_PATCH = PlacementUtils.register("cattail_patch", WLVegetationFeatures.CATTAIL_CONFIG, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> PALMETTO_PATCH = PlacementUtils.register("palmetto_patch", WLVegetationFeatures.CATTAIL_CONFIG, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> BURNT_GRASS = PlacementUtils.register("burnt_grass", WLVegetationFeatures.BURNT_GRASS, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	public static final Holder<PlacedFeature> BURNT_BUSH = PlacementUtils.register("burnt_bush", WLVegetationFeatures.BURNT_BUSH, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

}
