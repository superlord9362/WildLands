package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import superlord.wildlands.common.entity.tile.WLSignTileEntity;

public class WLWallSignBlock extends WallSignBlock {

	public WLWallSignBlock(Properties properties, WoodType type) {
		super(properties, type);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new WLSignTileEntity(pos, state);
	}

}
