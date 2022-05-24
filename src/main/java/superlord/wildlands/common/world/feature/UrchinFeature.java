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
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import superlord.wildlands.init.WLBlocks;

public class UrchinFeature extends Feature<CountConfiguration> {

	public UrchinFeature(Codec<CountConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<CountConfiguration> p_160316_) {
		Random rand = p_160316_.random();
		WorldGenLevel worldgenlevel = p_160316_.level();
		BlockPos pos = p_160316_.origin();		
		int i = rand.nextInt(8) - rand.nextInt(8);
		int j = rand.nextInt(8) - rand.nextInt(8);
		int k = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
		BlockPos blockpos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
		if (worldgenlevel.getBlockState(blockpos).is(Blocks.WATER)) {
			BlockState URCHIN = WLBlocks.URCHIN.get().defaultBlockState();
			if (URCHIN.canSurvive(worldgenlevel, blockpos)) {
				worldgenlevel.setBlock(blockpos, URCHIN, 2);
				return true;	
			}
			return false;
		}
		return false;
	}

}
