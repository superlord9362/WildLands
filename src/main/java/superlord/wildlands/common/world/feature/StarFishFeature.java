package superlord.wildlands.common.world.feature;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import superlord.wildlands.init.WLBlocks;

public class StarFishFeature extends Feature<CountConfiguration> {

	public StarFishFeature(Codec<CountConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<CountConfiguration> p_160316_) {
		RandomSource rand = p_160316_.random();
		WorldGenLevel reader = p_160316_.level();
		BlockPos pos = p_160316_.origin();
		int i = rand.nextInt(8) - rand.nextInt(8);
		int j = rand.nextInt(8) - rand.nextInt(8);
		int k = reader.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
		BlockPos blockpos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
		if (reader.getBlockState(blockpos).is(Blocks.WATER)) {
			int star = rand.nextInt(4);
			if (star == 0) {
				BlockState BLUE = WLBlocks.BLUE_STARFISH.get().defaultBlockState();
				if (BLUE.canSurvive(reader, blockpos)) {
					reader.setBlock(blockpos, BLUE, 2);
					return true;
				}
			} else if (star == 1) {
				BlockState MAGENTA = WLBlocks.MAGENTA_STARFISH.get().defaultBlockState();
				if (MAGENTA.canSurvive(reader, blockpos)) {
					reader.setBlock(blockpos, MAGENTA, 2);
					return true;
				}
			} else if (star == 2) {
				BlockState ORANGE = WLBlocks.ORANGE_STARFISH.get().defaultBlockState();
				if (ORANGE.canSurvive(reader, blockpos)) {
					reader.setBlock(blockpos, ORANGE, 2);
					return true;
				}
			} else if (star == 3) {
				BlockState PINK = WLBlocks.PINK_STARFISH.get().defaultBlockState();
				if (PINK.canSurvive(reader, blockpos)) {
					reader.setBlock(blockpos, PINK, 2);
					return true;
				}
			} else if (star == 4) {
				BlockState RED = WLBlocks.RED_STARFISH.get().defaultBlockState();
				if (RED.canSurvive(reader, blockpos)) {
					reader.setBlock(blockpos, RED, 2);
					return true;
				}
			}
		}
		return false;
	}

}
