package superlord.wildlands.common.entity.tile;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import superlord.wildlands.init.WLTileEntities;

public class WLSignTileEntity extends SignTileEntity {
	
	@Override
	public TileEntityType<?> getType() {
		return WLTileEntities.WL_SIGNS.get();
	}

}
