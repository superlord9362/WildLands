package superlord.wildlands.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import superlord.wildlands.common.entity.block.WLHangingSignBlockEntity;

public class WLCeilingHangingSignBlock extends CeilingHangingSignBlock {

	public WLCeilingHangingSignBlock(Properties p_250481_, WoodType p_248716_) {
		super(p_250481_, p_248716_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_250745_, BlockState p_250905_) {
		return new WLHangingSignBlockEntity(p_250745_, p_250905_);
	}

}
