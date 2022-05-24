package superlord.wildlands.common.block;

import java.util.Queue;

import com.google.common.collect.Lists;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import superlord.wildlands.init.WLBlocks;

public class DriedMudBlock extends Block {

	public DriedMudBlock(Properties properties) {
		super(properties);
	}
	
	public void onBlockAdded(BlockState state, Level LevelIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (!oldState.is(state.getBlock())) {
			this.tryAbsorb(LevelIn, pos);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void neighborChanged(BlockState state, Level LevelIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		this.tryAbsorb(LevelIn, pos);
		super.neighborChanged(state, LevelIn, pos, blockIn, fromPos, isMoving);
	}

	protected void tryAbsorb(Level LevelIn, BlockPos pos) {
		if (this.absorb(LevelIn, pos)) {
			LevelIn.setBlock(pos, WLBlocks.MUD.get().defaultBlockState(), 2);
		}
	}
	
	private boolean absorb(Level LevelIn, BlockPos pos) {
		Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
		queue.add(new Tuple<>(pos, 0));
		int i = 0;

		while(!queue.isEmpty()) {
			Tuple<BlockPos, Integer> tuple = queue.poll();
			BlockPos blockpos = tuple.getA();

			for(Direction direction : Direction.values()) {
				BlockPos blockpos1 = blockpos.relative(direction);
				FluidState fluidstate = LevelIn.getFluidState(blockpos1);
				if (fluidstate.is(FluidTags.WATER)) {
					i++;
				}
			}

			if (i > 64) {
				break;
			}
		}

		return i > 0;
	}
	
}
