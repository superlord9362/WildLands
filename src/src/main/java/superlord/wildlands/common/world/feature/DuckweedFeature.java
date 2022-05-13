package superlord.wildlands.common.world.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import superlord.wildlands.common.world.feature.config.DuckweedConfig;
import superlord.wildlands.init.WildLandsBlocks;

public class DuckweedFeature extends Feature<DuckweedConfig> {

	public DuckweedFeature(Codec<DuckweedConfig> func) {
		super(func);
	}

	@SuppressWarnings("static-access")
	public boolean place(FeaturePlaceContext<DuckweedConfig> p_159471_) {
		int i = 0;
		BlockPos pos = p_159471_.origin();
		WorldGenLevel worldIn = p_159471_.level();
		Random rand = p_159471_.random();
		for(int j = 0; j < p_159471_.config().count; ++j) {
			int k = rand.nextInt(8) - rand.nextInt(8);
			int l = rand.nextInt(8) - rand.nextInt(8);
			int i1 = worldIn.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX() + k, pos.getZ() + l);
			BlockPos blockpos = new BlockPos(pos.getX() + k, i1, pos.getZ() + l);
			if (worldIn.getBlockState(blockpos).getBlock() == Blocks.AIR && worldIn.getBlockState(blockpos.below()).getBlock() == Blocks.WATER) {
				BlockState blockstate = WildLandsBlocks.DUCKWEED.get().defaultBlockState();
				if (blockstate.canSurvive(worldIn, blockpos)) {
					worldIn.setBlock(blockpos, blockstate, 2);
				}
				++i;
			}
		}
		return i > 0;
	}
}