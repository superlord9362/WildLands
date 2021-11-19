package superlord.wildlands.common.block;

import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import superlord.wildlands.common.entity.tile.WLSignTileEntity;

public class WLWallSignBlock extends WallSignBlock {
	
	public WLWallSignBlock(Properties properties, WoodType type) {
		super(properties, type);
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader reader) {
		return new WLSignTileEntity();
	}

}
