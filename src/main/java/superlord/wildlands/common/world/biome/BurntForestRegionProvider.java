package superlord.wildlands.common.world.biome;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import superlord.wildlands.config.WildLandsConfig;
import superlord.wildlands.init.WLBiomes;
import terrablender.api.ParameterUtils;
import terrablender.api.ParameterUtils.Continentalness;
import terrablender.api.ParameterUtils.Depth;
import terrablender.api.ParameterUtils.Erosion;
import terrablender.api.ParameterUtils.Humidity;
import terrablender.api.ParameterUtils.Temperature;
import terrablender.api.ParameterUtils.Weirdness;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class BurntForestRegionProvider  extends Region {
	
	public static final ResourceLocation LOCATION = new ResourceLocation("minecraft:overworld");
	
	public BurntForestRegionProvider(ResourceLocation name, int weight) {
		super(name, RegionType.OVERWORLD, weight);
	}
	
	//Temperature
	//Humidity
	//Continentalness (lower the value, the more watery it is
	//Erosion (Higher the number, the flatter the terrain
	//Weirdness (Lower the number, the more likely it will generate
	//Depth (Higher the number, lower in the world it generates)
	
	@Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
		Temperature temperature = ParameterUtils.Temperature.HOT;
		Humidity humidity = ParameterUtils.Humidity.ARID;
		Continentalness continentalness = ParameterUtils.Continentalness.FAR_INLAND;
		Erosion erosion = ParameterUtils.Erosion.EROSION_1;
		Depth depth = ParameterUtils.Depth.SURFACE;
		Weirdness weirdness = ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING;
		if (WildLandsConfig.burntForestBiome = true) this.addBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, depth, 0, WLBiomes.BURNT_FOREST);
	}
}
