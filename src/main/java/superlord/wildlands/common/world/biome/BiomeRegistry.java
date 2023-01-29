package superlord.wildlands.common.world.biome;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeRegistry {
	
    public static final DeferredRegister<Biome> REGISTER = DeferredRegister.create(ForgeRegistries.BIOMES, WildLands.MOD_ID);

	public static void registerBiomes() {
		register(BiomeInitializer.BAYOU, BayouBiomeDecorator::decorateBayou);
		register(BiomeInitializer.BURNT_FOREST, BurntForestBiomeDecorator::decorateBurntForest);
	}

	public static RegistryObject<Biome> register(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier) {
		return REGISTER.register(key.location().getPath(), biomeSupplier);
	}
	
}
