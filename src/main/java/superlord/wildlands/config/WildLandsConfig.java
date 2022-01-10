package superlord.wildlands.config;

import net.minecraftforge.fml.config.ModConfig;
import superlord.wildlands.WildLands;

public class WildLandsConfig {
	
	public static int catfishSpawnWeight = 1;
	public static int alligatorSpawnWeight = 10;
	public static int frogSpawnWeight = 15;
	public static int crabSpawnWeight = 5;
	public static int seaLionSpawnWeight = 10;
	public static int anchovySpawnWeight = 1;
	public static int hammerheadSharkSpawnWeight = 5;
	public static int octopusSpawnWeight = 3;
	public static double bayouScale = 0.1D;
	public static double burntForestScale = 0.1D;
	public static boolean superSecretSettings = false;
	
	public static void bakeClient(final ModConfig config) {
		
	}
	
	public static void bakeServer(final ModConfig config) {
		try {
			catfishSpawnWeight = WLConfigHolder.SERVER.catfishSpawnWeight.get();
			alligatorSpawnWeight = WLConfigHolder.SERVER.alligatorSpawnWeight.get();
			frogSpawnWeight = WLConfigHolder.SERVER.frogSpawnWeight.get();
			crabSpawnWeight = WLConfigHolder.SERVER.crabSpawnWeight.get();
			seaLionSpawnWeight = WLConfigHolder.SERVER.seaLionSpawnWeight.get();
			anchovySpawnWeight = WLConfigHolder.SERVER.anchovySpawnWeight.get();
			hammerheadSharkSpawnWeight = WLConfigHolder.SERVER.hammerheadSharkSpawnWeight.get();
			octopusSpawnWeight = WLConfigHolder.SERVER.octopusSpawnWeight.get();
			bayouScale = WLConfigHolder.SERVER.bayouScale.get();
			burntForestScale = WLConfigHolder.SERVER.burntForestScale.get();
			superSecretSettings = WLConfigHolder.SERVER.superSecretSettings.get();
		} catch (Exception e) {
			WildLands.LOGGER.warn("An exception was caused trying to load the config for Wild Lands");
			e.printStackTrace();
		}
	}

}