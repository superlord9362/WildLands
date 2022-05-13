package superlord.wildlands.common.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GabbroOlivineBlock extends Block {

	public GabbroOlivineBlock(Properties properties) {
		super(properties);
	}

	public int getExperience(Random rand) {
		return Mth.nextInt(rand, 0, 2);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader reader, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? this.getExperience(RANDOM) : 0;
	}

}
