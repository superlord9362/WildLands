package superlord.wildlands.config;

import net.minecraftforge.fml.config.ModConfig;
import superlord.wildlands.WildLands;

public class WildLandsConfig {
	
	public static int catfishSpawnWeight = 4;
	public static int alligatorSpawnWeight = 20;
	public static int frogSpawnWeight = 15;
	public static int crabSpawnWeight = 5;
	public static int seaLionSpawnWeight = 10;
	public static int anchovySpawnWeight = 1;
	//public static int clamSpawnWeight = 1;
	public static int hammerheadSharkSpawnWeight = 5;
	public static int octopusSpawnWeight = 3;
	public static int jellyfishSpawnWeight = 6;
	public static int grizzlyBearSpawnWeight = 10;
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
			jellyfishSpawnWeight = WLConfigHolder.SERVER.jellyfishSpawnWeight.get();
			grizzlyBearSpawnWeight = WLConfigHolder.SERVER.grizzlyBearSpawnWeight.get();
			superSecretSettings = WLConfigHolder.SERVER.superSecretSettings.get();
			//clamSpawnWeight = WLConfigHolder.SERVER.clamSpawnWeight.get();
		} catch (Exception e) {
			WildLands.LOGGER.warn("An exception was caused trying to load the config for Wild Lands");
			e.printStackTrace();
		}
	}

}
