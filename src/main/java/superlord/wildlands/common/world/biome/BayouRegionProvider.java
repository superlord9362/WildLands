package superlord.wildlands.common.world.biome;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import superlord.wildlands.init.WLBiomes;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class BayouRegionProvider extends Region {
	
	public static final ResourceLocation LOCATION = new ResourceLocation("minecraft:overworld");
	
	public BayouRegionProvider(ResourceLocation name, int weight) {
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
		this.addBiome(mapper, Climate.parameters(0.5F, 0.7F, 0.3F, 1F, 0.5F, 0F, 0), WLBiomes.BAYOU);
		this.addBiome(mapper, Climate.parameters(0.9F, 0.0F, 1F, 0.8F, 0.7F, 0F, 0), WLBiomes.BURNT_FOREST);
	}

}
