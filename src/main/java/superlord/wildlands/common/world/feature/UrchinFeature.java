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

public class UrchinFeature extends Feature<ProbabilityConfig> {

	public UrchinFeature(Codec<ProbabilityConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ProbabilityConfig config) {
		int i = rand.nextInt(8) - rand.nextInt(8);
		int j = rand.nextInt(8) - rand.nextInt(8);
		int k = reader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
		BlockPos blockpos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
		if (reader.getBlockState(blockpos).isIn(Blocks.WATER)) {
			BlockState URCHIN = WildLandsBlocks.URCHIN.get().getDefaultState();
			reader.setBlockState(blockpos, URCHIN, 2);
			return true;
		}
		return false;
	}

}
