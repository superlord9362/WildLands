package superlord.wildlands.common.world.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import superlord.wildlands.init.WildLandsBlocks;

public class StarFishFeature extends Feature<ProbabilityConfig> {

	public StarFishFeature(Codec<ProbabilityConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ProbabilityConfig config) {
		int i = rand.nextInt(8) - rand.nextInt(8);
		int j = rand.nextInt(8) - rand.nextInt(8);
		int k = reader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
		BlockPos blockpos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
		if (reader.getBlockState(blockpos).isIn(Blocks.WATER)) {
			int star = rand.nextInt(4);
			if (star == 0) {
				BlockState BLUE = WildLandsBlocks.BLUE_STARFISH.get().getDefaultState();
				if (BLUE.isValidPosition(reader, blockpos) ) {
					reader.setBlockState(blockpos, BLUE, 2);
					return true;
				}
			} else if (star == 1) {
				BlockState MAGENTA = WildLandsBlocks.MAGENTA_STARFISH.get().getDefaultState();
				if (MAGENTA.isValidPosition(reader, blockpos)) {
					reader.setBlockState(blockpos, MAGENTA, 2);
					return true;
				}
			} else if (star == 2) {
				BlockState ORANGE = WildLandsBlocks.ORANGE_STARFISH.get().getDefaultState();
				if (ORANGE.isValidPosition(reader, blockpos)) {
					reader.setBlockState(blockpos, ORANGE, 2);
					return true;
				}
			} else if (star == 3) {
				BlockState PINK = WildLandsBlocks.PINK_STARFISH.get().getDefaultState();
				if (PINK.isValidPosition(reader, blockpos)) {
					reader.setBlockState(blockpos, PINK, 2);
					return true;
				}
			} else if (star == 4) {
				BlockState RED = WildLandsBlocks.RED_STARFISH.get().getDefaultState();
				if (RED.isValidPosition(reader, blockpos)) {
					reader.setBlockState(blockpos, RED, 2);
					return true;
				}
			}
		}
		return false;
	}

}
