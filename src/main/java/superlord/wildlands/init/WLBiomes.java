package superlord.wildlands.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import superlord.wildlands.WildLands;

public class WLBiomes {

	public static final ResourceKey<Biome> BAYOU = register("bayou");

	private static ResourceKey<Biome> register(String name) {
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(WildLands.MOD_ID, name));
	}


}
