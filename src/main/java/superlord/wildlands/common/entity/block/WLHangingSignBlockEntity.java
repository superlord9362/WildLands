package superlord.wildlands.common.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import superlord.wildlands.init.WLBlockEntities;

public class WLHangingSignBlockEntity extends HangingSignBlockEntity {

	public WLHangingSignBlockEntity(BlockPos p_250603_, BlockState p_251674_) {
		super(p_250603_, p_251674_);
	}
	
	@Override
    public BlockEntityType<?> getType() {
        return WLBlockEntities.HANGING_SIGN.get();
    }

}
