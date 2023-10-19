package superlord.wildlands.common.world.feature.tree;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import superlord.wildlands.init.WLBiomes;
import superlord.wildlands.init.WLBlocks;

public class CypressTreeFeature extends Feature<NoneFeatureConfiguration> {
	
	public CypressTreeFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}
	
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placeContext) {
		return place(placeContext.level(), placeContext.chunkGenerator(), placeContext.random(), placeContext.origin(), placeContext.config());
	}
	
    public boolean place(WorldGenLevel world, ChunkGenerator generator, RandomSource rand, BlockPos pos, NoneFeatureConfiguration config) {
    	if ((world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) && world.getBlockState(pos.below().east()).is(Blocks.GRASS_BLOCK) && world.getBlockState(pos.below().north()).is(Blocks.GRASS_BLOCK)) || (world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.below()).is(Blocks.DIRT) && world.getBlockState(pos.east()).is(Blocks.WATER) && world.getBlockState(pos.below().east()).is(Blocks.DIRT) && world.getBlockState(pos.north()).is(Blocks.WATER) && world.getBlockState(pos.below().north()).is(Blocks.DIRT) && world.getBlockState(pos.north().east()).is(Blocks.WATER) && world.getBlockState(pos.north().east().below()).is(Blocks.DIRT)) && world.getBiome(pos).is(WLBiomes.BAYOU)) {
    		placeTree(world, rand, pos);
    		return true;
    	} else {
    		return false;
    	}
    }

	
	private void placeTree(LevelAccessor world, RandomSource random, BlockPos pos) {
		int height1 = random.nextInt(3) + 2;
		int height2 = random.nextInt(3) + 2;
		int height3 = random.nextInt(3) + 2;
		int height4 = random.nextInt(3) + 2;
		int number = random.nextInt(4);
		int tall = random.nextInt(4) + 3;
		if (number == 0) {
			int heightNumber = height1 + tall;
			for (int y = 0; y <= height1; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height2; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height3; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.east().north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height4; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= heightNumber; y++) {
				BlockPos blockPos = pos.above(y);
				BlockPos highPos = pos.above(heightNumber);
				world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				//leaves
				world.setBlock(highPos.north(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
			}
		}
		if (number == 1) {
			for (int y = 0; y <= height2; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height3; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height4; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.east().north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height1; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			int heightNumber = height2 + tall;
			for (int y = 0; y <= heightNumber; y++) {
				BlockPos blockPos = pos.above(y).east();
				BlockPos highPos = pos.above(heightNumber).east();
				world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				//leaves
				world.setBlock(highPos.north(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
			}
		}
		if (number == 2) {
			for (int y = 0; y <= height3; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height4; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height1; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.east().north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height2; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			int heightNumber = height3 + tall;
			for (int y = 0; y <= heightNumber; y++) {
				BlockPos blockPos = pos.above(y).east().north();
				BlockPos highPos = pos.above(heightNumber).east().north();
				world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				//leaves
				world.setBlock(highPos.north(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
			}
		}
		if (number == 3) {
			for (int y = 0; y <= height4; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height1; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height2; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.east().north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			for (int y = 0; y <= height3; y++) {
				BlockPos blockPos = pos.above(y);
				world.setBlock(blockPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
			}
			int heightNumber = height4 + tall;
			for (int y = 0; y <= heightNumber; y++) {
				BlockPos blockPos = pos.above(y).north();
				BlockPos highPos = pos.above(heightNumber).north();
				world.setBlock(blockPos, WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.east(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.west(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.north(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				//leaves
				world.setBlock(highPos.north(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(4).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(3).above(2).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(3).above(2), WLBlocks.CYPRESS_LOG.get().defaultBlockState(), 2);
				world.setBlock(highPos.south(4).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(4).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(4).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(3).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.south(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.north(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west(2).above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.west().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east().above(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.east().above(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).north().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(3).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south(2).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().east(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(3).south().west(3), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).east().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().north(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west().south(2), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2).north(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
				world.setBlock(highPos.above(4).west(2).south(), WLBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 2);
			}
		}
	}

}