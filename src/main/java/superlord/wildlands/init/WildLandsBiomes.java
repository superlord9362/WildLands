package superlord.wildlands.init;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.world.WLOverworldBiomes;

public class WildLandsBiomes {
	
	@SuppressWarnings("deprecation")
public static final RegistrationProvider<Biome> PROVIDER = RegistrationProvider.get(BuiltinRegistries.BIOME, WildLands.MOD_ID);
	
	public static ResourceKey<Biome> BAYOU = createBiome("bayou", WLOverworldBiomes::bayou);
	public static ResourceKey<Biome> BURNT_FOREST = createBiome("burnt_forest", WLOverworldBiomes::burntForest);
	
	@SuppressWarnings("deprecation")
	public static <B extends Biome> ResourceKey<Biome> createBiome(String id, Supplier<? extends B> biome) {
		ResourceLocation wlID = WildLands.createLocation(id);
		if (BuiltinRegistries.BIOME.keySet().contains(wlID)) {
			throw new IllegalStateException("Biome ID: \"" + wlID + "\" already exists in the Biome registry!");
		}
		PROVIDER.register(id, biome);
		return ResourceKey.create(Registry.BIOME_REGISTRY, wlID);
	}
	
	public static void loadClass() {
		
	}

}
