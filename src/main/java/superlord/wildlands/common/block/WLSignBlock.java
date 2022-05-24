package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import superlord.wildlands.common.entity.block.WLSignBlockEntity;

public class WLSignBlock extends SignBlock {

	protected WLSignBlock(Properties p_56273_, WoodType p_56274_) {
		super(p_56273_, p_56274_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new WLSignBlockEntity(pos, state);
	}


}
