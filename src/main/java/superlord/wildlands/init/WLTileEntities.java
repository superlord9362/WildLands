package superlord.wildlands.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.tile.WLSignTileEntity;

public class WLTileEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, WildLands.MOD_ID);

	public static final RegistryObject<BlockEntityType<WLSignTileEntity>> WL_SIGNS = REGISTER.register("wl_sign_tile", () -> BlockEntityType.Builder.of(WLSignTileEntity::new, WildLandsBlocks.CYPRESS_SIGN.get(), WildLandsBlocks.CYPRESS_WALL_SIGN.get(), WildLandsBlocks.COCONUT_SIGN.get(), WildLandsBlocks.COCONUT_WALL_SIGN.get(), WildLandsBlocks.CHARRED_SIGN.get(), WildLandsBlocks.CHARRED_WALL_SIGN.get()).build(null));

}
