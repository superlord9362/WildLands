package superlord.wildlands.common.world.feature.tree;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import superlord.wildlands.init.WLBlocks;

public class BurntTreeFeature extends Feature<NoneFeatureConfiguration> {

	public BurntTreeFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
	}

	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placeContext) {
		return place(placeContext.level(), placeContext.chunkGenerator(), placeContext.random(), placeContext.origin(), placeContext.config());
	}

	public boolean place(WorldGenLevel world, ChunkGenerator generator, RandomSource rand, BlockPos pos, NoneFeatureConfiguration config) {
		if (world.getBlockState(pos.below()).is(WLBlocks.CHARRED_GRASS.get()) && world.getBlockState(pos).is(Blocks.AIR)) {
			placeTree(world, rand, pos);
			return true;
		} else {
			return false;
		}
	}


	private void placeTree(LevelAccessor world, RandomSource random, BlockPos pos) {
		int height = random.nextInt(2) + 2;
		int burnChance = random.nextInt(5);
		for (int y = 0; y <= height; y++) {
			BlockPos blockPos = pos.above(y);
			world.setBlock(blockPos, WLBlocks.CHARRED_LOG.get().defaultBlockState(), 2);
			if (burnChance == 0) {
				BlockPos burningLogPos = blockPos.above();
				world.setBlock(burningLogPos, WLBlocks.SMOLDERING_LOG.get().defaultBlockState(), 2);
			}
		}
	}

}
