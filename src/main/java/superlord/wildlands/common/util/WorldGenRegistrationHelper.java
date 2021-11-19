package superlord.wildlands.common.util;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import superlord.wildlands.WildLands;
import superlord.wildlands.init.WildLandsBiomes;
import superlord.wildlands.common.core.world.WLFeatures;
import superlord.wildlands.common.world.WLDecorators;
import superlord.wildlands.common.world.WLSurfaceBuilders;

@SuppressWarnings("deprecation")
public class WorldGenRegistrationHelper {
	
	public static <SBC extends ISurfaceBuilderConfig, SB extends SurfaceBuilder<SBC>> SB createSurfaceBuilder(String id, SB surfaceBuilder) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (Registry.SURFACE_BUILDER.keySet().contains(wlID)) {
			throw new IllegalStateException("Surface Builder ID: \"" + wlID.toString() + "\" already exists in the Surface Builder registry!");
		}
		surfaceBuilder.setRegistryName(wlID);
		WLSurfaceBuilders.surfaceBuilders.add(surfaceBuilder);
		return surfaceBuilder;
	}
	
	public static <SC extends ISurfaceBuilderConfig, CSB extends ConfiguredSurfaceBuilder<SC>> CSB createConfiguredSurfaceBuilder(String id, CSB configuredSurfaceBuilder) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (WorldGenRegistries.CONFIGURED_SURFACE_BUILDER.keySet().contains(wlID)) throw new IllegalStateException("Configured Surface Builder ID: \"" + wlID.toString() + "\" already exists in the Configured Surface Builder registry!");
		Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, wlID, configuredSurfaceBuilder);
		return configuredSurfaceBuilder;
	}
	
	public static <C extends IFeatureConfig, F extends Feature<C>> F createFeature(String id, F feature) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (Registry.FEATURE.keySet().contains(wlID)) throw new IllegalStateException("Feature ID: \"" + wlID + "\" already exists in the Features registry!");
		feature.setRegistryName(wlID);
		WLFeatures.features.add(feature);
		return feature;
	}
	
	public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(wlID)) throw new IllegalStateException("Configured Feature ID: \"" + wlID + "\" already exists in the Configured Features registry!");
		Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, wlID, configuredFeature);
		return configuredFeature;
	}
	
	public static <DC extends IPlacementConfig, D extends Placement<DC>> D createDecorator(String id, D decorator) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (Registry.DECORATOR.keySet().contains(wlID)) throw new IllegalStateException("Decorator ID: \"" + wlID + "\" already exists in the Decorator registry!");
		decorator.setRegistryName(wlID);
		WLDecorators.decorators.add(decorator);
		return decorator;
	}
	
	static Set<Integer> integerList = new HashSet<>();
	
	public static Biome createBiome(String id, Biome biome, int numericalId) {
		ResourceLocation wlID = new ResourceLocation(WildLands.MOD_ID, id);
		if (WorldGenRegistries.BIOME.keySet().contains(wlID)) throw new IllegalStateException("Biome ID: \"" + wlID + "\" already exists in the Biome registry!");
		biome.setRegistryName(wlID);
		if (integerList.contains(numericalId)) WildLands.LOGGER.warn("Duplicate Biome Numerical ID: " + numericalId + "at wl:" + id);
		WildLandsBiomes.biomeList.add(new WildLandsBiomes.PreserveBiomeOrder(biome, numericalId));
		integerList.add(numericalId);
		return biome;
	}

}
