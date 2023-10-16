package superlord.wildlands.common.world.biome;

import java.util.Map;
import java.util.function.Supplier;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeRegistry {

	public static final DeferredRegister<Biome> REGISTER = DeferredRegister.create(ForgeRegistries.BIOMES, WildLands.MOD_ID);
    public static final Map<ResourceKey<Biome>, BiomeFactory> BIOME_FACTORIES = new Reference2ObjectOpenHashMap<>();

	public static void registerBiomes() {
		register(BiomeInitializer.BAYOU, BayouBiomeDecorator::decorateBayou);
		register(BiomeInitializer.BURNT_FOREST, BurntForestBiomeDecorator::decorateBurntForest);
	}

	public static RegistryObject<Biome> register(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier) {
		return REGISTER.register(key.location().getPath(), biomeSupplier);
	}

	@FunctionalInterface
	public interface BiomeFactory {
		Biome generate(HolderGetter<PlacedFeature> placedFeatureHolderGetter, HolderGetter<ConfiguredWorldCarver<?>> worldCarverHolderGetter);
	}

}
