package superlord.wildlands.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.util.RegistryObject;
import superlord.wildlands.common.world.WLOverworldBiomes;

public class WildLandsBiomes {

    public static final List<RegistryObject<Biome>> BIOMES = new ArrayList<>();

	public static ResourceKey<Biome> BAYOU = createBiome("bayou", WLOverworldBiomes.bayou());
	public static ResourceKey<Biome> BURNT_FOREST = createBiome("burnt_forest", WLOverworldBiomes.burntForest());

	@SuppressWarnings("deprecation")
	public static ResourceKey<Biome> createBiome(String id, Biome biome) {
        ResourceLocation wlID = WildLands.createLocation(id);
        if (BuiltinRegistries.BIOME.keySet().contains(wlID)) {
            throw new IllegalStateException("Biome ID: \"" + wlID.toString() + "\" already exists in the Biome registry!");
        }
        BIOMES.add(new RegistryObject<>(biome, id));
        return ResourceKey.create(Registry.BIOME_REGISTRY, wlID);
    }

    public static Collection<RegistryObject<Biome>> bootStrap() {
        return BIOMES;
    }

}
