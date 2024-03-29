package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CharredGrassBlock extends Block {

	public CharredGrassBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
		net.minecraftforge.common.PlantType type = plantable.getPlantType(world, pos.relative(facing));
		if (net.minecraftforge.common.PlantType.PLAINS.equals(type)) {
			return true;
		} else return false;
	}

}
