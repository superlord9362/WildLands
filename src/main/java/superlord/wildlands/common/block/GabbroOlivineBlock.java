package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GabbroOlivineBlock extends Block {

	public GabbroOlivineBlock(Properties properties) {
		super(properties);
	}

	public int getExperience(RandomSource rand) {
		return Mth.nextInt(rand, 0, 2);
	}

	@Override
	public int getExpDrop(BlockState state, net.minecraft.world.level.LevelReader level, net.minecraft.util.RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
		return silkTouchLevel == 0 ? this.getExperience(randomSource) : 0;
	}

}
