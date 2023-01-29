package superlord.wildlands.common.world.biome;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import superlord.wildlands.WildLands;

public class BiomeInitializer {

	public static final ResourceKey<Biome> BAYOU = register("bayou");
	public static final ResourceKey<Biome> BURNT_FOREST = register("burnt_forest");

	private static ResourceKey<Biome> register(String name) {
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(WildLands.MOD_ID, name));
	}
	
}
