package superlord.wildlands.init;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.tile.WLSignTileEntity;

public class WLTileEntities {
	public static final DeferredRegister<TileEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, WildLands.MOD_ID);

	public static final RegistryObject<TileEntityType<WLSignTileEntity>> WL_SIGNS = REGISTER.register("wl_sign_tile", () -> TileEntityType.Builder.create(WLSignTileEntity::new, WildLandsBlocks.CYPRESS_SIGN.get(), WildLandsBlocks.CYPRESS_WALL_SIGN.get(), WildLandsBlocks.COCONUT_SIGN.get(), WildLandsBlocks.COCONUT_WALL_SIGN.get(), WildLandsBlocks.CHARRED_SIGN.get(), WildLandsBlocks.CHARRED_WALL_SIGN.get()).build(null));

}
