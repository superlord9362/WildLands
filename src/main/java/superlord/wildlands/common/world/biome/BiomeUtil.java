package superlord.wildlands.common.world.biome;

import net.minecraft.util.Mth;

public class BiomeUtil {
	
	public static int calcSkyColor(float f) {
		float g = f / 3.0F;
		g = Mth.clamp(g, -1.0F, 1.0f);
		return Mth.hsvToRgb(0.62222224F - g * 0.05F, 0.5F + g * 0.1F, 1.0F);
	}

}