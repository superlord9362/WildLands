package superlord.wildlands.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import superlord.wildlands.WildLands;

public class WLWoodTypes {
	public static final WoodType CYPRESS = WoodType.create(new ResourceLocation(WildLands.MOD_ID, "bald_cypress").toString());
	public static final WoodType COCONUT = WoodType.create(new ResourceLocation(WildLands.MOD_ID, "coconut").toString());
	public static final WoodType CHARRED = WoodType.create(new ResourceLocation(WildLands.MOD_ID, "charred").toString());
}
