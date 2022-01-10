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
import superlord.wildlands.common.world.feature.config.DuckweedConfig;
import superlord.wildlands.init.WildLandsBlocks;

public class DuckweedFeature extends Feature<DuckweedConfig> {
	
	public DuckweedFeature(Codec<DuckweedConfig> func) {
		super(func);
	}

	@SuppressWarnings("static-access")
	public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, DuckweedConfig config) {
		int i = 0;

		for(int j = 0; j < config.count; ++j) {
			int k = rand.nextInt(8) - rand.nextInt(8);
			int l = rand.nextInt(8) - rand.nextInt(8);
			int i1 = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, pos.getX() + k, pos.getZ() + l);
			BlockPos blockpos = new BlockPos(pos.getX() + k, i1, pos.getZ() + l);
			if (worldIn.getBlockState(blockpos).getBlock() == Blocks.AIR && worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.WATER) {
				BlockState blockstate = WildLandsBlocks.DUCKWEED.get().getDefaultState();
				if (blockstate.isValidPosition(worldIn, blockpos)) {
					worldIn.setBlockState(blockpos, blockstate, 2);
				}
				++i;
			}
		}
		return i > 0;
	}
}