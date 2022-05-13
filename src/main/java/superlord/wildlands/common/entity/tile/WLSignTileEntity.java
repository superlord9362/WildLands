package superlord.wildlands.common.entity.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import superlord.wildlands.init.WLTileEntities;

public class WLSignTileEntity extends SignBlockEntity {
	
	public WLSignTileEntity(BlockPos p_155700_, BlockState p_155701_) {
		super(p_155700_, p_155701_);
	}

	@Override
	public BlockEntityType<?> getType() {
		return WLTileEntities.WL_SIGNS.get();
	}

}
