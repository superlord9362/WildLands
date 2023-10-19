package superlord.wildlands.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.world.biome.BayouBiomeDecorator;
import superlord.wildlands.common.world.biome.BurntForestBiomeDecorator;

public class WLBiomes {

	public static final ResourceKey<Biome> BAYOU = register("bayou");
	public static final ResourceKey<Biome> BURNT_FOREST = register("burnt_forest");
	
	public static void bootstrap(BootstapContext<Biome> bootstapContext) {
		HolderGetter<PlacedFeature> holderGetter = bootstapContext.lookup(Registries.PLACED_FEATURE);
		HolderGetter<ConfiguredWorldCarver<?>> holdergetter1 = bootstapContext.lookup(Registries.CONFIGURED_CARVER);
		bootstapContext.register(BAYOU, BayouBiomeDecorator.decorateBayou(holderGetter, holdergetter1));
		bootstapContext.register(BURNT_FOREST, BurntForestBiomeDecorator.decorateBurntForest(holderGetter, holdergetter1));
	}

	private static ResourceKey<Biome> register(String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(WildLands.MOD_ID, name));
	}


}
