package superlord.wildlands.common.world;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;
import superlord.wildlands.init.WLBiomeFeatures;
import superlord.wildlands.init.WLConfiguredFeatures;
import superlord.wildlands.init.WLPlacedFeatures;

public class WLFeatureAndBiomeGenerator extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ctx -> WLConfiguredFeatures.bootstrap(ctx))
            .add(Registries.PLACED_FEATURE, WLPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, WLBiomeFeatures::bootstrap);

    public WLFeatureAndBiomeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(WildLands.MOD_ID));
    }

}
