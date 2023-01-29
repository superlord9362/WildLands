package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import superlord.wildlands.init.WLBlocks;

public class CharredGrassBlock extends Block {

	public CharredGrassBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
		BlockPos plantPos = pos.above();
		if (world.getBlockState(plantPos).getBlock() == WLBlocks.CHARRED_TALL_GRASS.get() || world.getBlockState(plantPos).getBlock() == WLBlocks.CHARRED_BUSH.get()) {
			return true;
		} else {
			return false;
		}
	}

}
