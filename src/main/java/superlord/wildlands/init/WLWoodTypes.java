package superlord.wildlands.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import superlord.wildlands.WildLands;

public class WLWoodTypes {
	
	public static final BlockSetType CYPRESS_TYPE = BlockSetType.register(new BlockSetType(new ResourceLocation(WildLands.MOD_ID, "bald_cypress").toString()));
	public static final BlockSetType COCONUT_TYPE = BlockSetType.register(new BlockSetType(new ResourceLocation(WildLands.MOD_ID, "coconut").toString()));
	public static final BlockSetType CHARRED_TYPE = BlockSetType.register(new BlockSetType(new ResourceLocation(WildLands.MOD_ID, "charred").toString()));
	public static final BlockSetType OLIVINE_TYPE = BlockSetType.register(new BlockSetType(new ResourceLocation(WildLands.MOD_ID, "olivine").toString()));

	public static WoodType CYPRESS = WoodType.register(new WoodType(new ResourceLocation(WildLands.MOD_ID, "bald_cypress").toString(), CYPRESS_TYPE));
	public static WoodType COCONUT = WoodType.register(new WoodType(new ResourceLocation(WildLands.MOD_ID, "coconut").toString(), COCONUT_TYPE));
	public static WoodType CHARRED = WoodType.register(new WoodType(new ResourceLocation(WildLands.MOD_ID, "charred").toString(), CHARRED_TYPE));

}
