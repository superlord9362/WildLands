package superlord.worldgentest.init;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.worldgentest.WorldGenTest;

public class TestBiomes {
	
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, WorldGenTest.MOD_ID);
	
	static {
		bayou("bayou", OverworldBiomes::theVoid);
	}
	
	public static RegistryObject<Biome> bayou(String name, Supplier<Biome> biome) {
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(WorldGenTest.MOD_ID, name)), 1));
		return BIOMES.register(name, biome);
	}
	
}
