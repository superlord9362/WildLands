package superlord.wildlands.config;

import net.minecraftforge.fml.config.ModConfig;
import superlord.wildlands.WildLands;

public class WildLandsConfig {
	
	public static int catfishSpawnWeight = 4;
	public static int alligatorSpawnWeight = 20;
	public static int crabSpawnWeight = 5;
	public static int seaLionSpawnWeight = 10;
	public static int anchovySpawnWeight = 1;
	//public static int clamSpawnWeight = 1;
	public static int hammerheadSharkSpawnWeight = 5;
	public static int octopusSpawnWeight = 3;
	public static int jellyfishSpawnWeight = 6;
	public static int grizzlyBearSpawnWeight = 10;
	public static boolean superSecretSettings = false;
	public static boolean coconutTree = true;
	public static boolean doleriteDisk = true;
	public static boolean doleriteRock = true;
	public static boolean gabbroDisk = true;
	public static boolean gabbroRock = true;
	public static boolean olivineGabbroRock = true;
	public static boolean fineSandDisk = true;
	public static boolean starfish = true;
	public static boolean urchin = true;
	public static boolean conglomerateDisk = true;
	public static boolean conglomerateRock = true;
	public static boolean mudDisk = true;
	public static boolean bayouBiome = true;
	public static boolean burntForestBiome = true;
	public static int bayouWeight = 1;
	public static int burntForestWeight = 1;
	
	public static void bakeClient(final ModConfig config) {
		
	}
	
	public static void bakeServer(final ModConfig config) {
		try {
			catfishSpawnWeight = WLConfigHolder.SERVER.catfishSpawnWeight.get();
			alligatorSpawnWeight = WLConfigHolder.SERVER.alligatorSpawnWeight.get();
			crabSpawnWeight = WLConfigHolder.SERVER.crabSpawnWeight.get();
			seaLionSpawnWeight = WLConfigHolder.SERVER.seaLionSpawnWeight.get();
			anchovySpawnWeight = WLConfigHolder.SERVER.anchovySpawnWeight.get();
			hammerheadSharkSpawnWeight = WLConfigHolder.SERVER.hammerheadSharkSpawnWeight.get();
			octopusSpawnWeight = WLConfigHolder.SERVER.octopusSpawnWeight.get();
			jellyfishSpawnWeight = WLConfigHolder.SERVER.jellyfishSpawnWeight.get();
			grizzlyBearSpawnWeight = WLConfigHolder.SERVER.grizzlyBearSpawnWeight.get();
			superSecretSettings = WLConfigHolder.SERVER.superSecretSettings.get();
			//clamSpawnWeight = WLConfigHolder.SERVER.clamSpawnWeight.get();
			coconutTree = WLConfigHolder.SERVER.coconutTree.get();
			doleriteDisk = WLConfigHolder.SERVER.doleriteDisk.get();
			doleriteRock = WLConfigHolder.SERVER.doleriteRock.get();
			gabbroDisk = WLConfigHolder.SERVER.gabbroDisk.get();
			gabbroRock = WLConfigHolder.SERVER.gabbroRock.get();
			olivineGabbroRock = WLConfigHolder.SERVER.olivineGabbroRock.get();
			fineSandDisk = WLConfigHolder.SERVER.fineSandDisk.get();
			starfish = WLConfigHolder.SERVER.starfish.get();
			urchin = WLConfigHolder.SERVER.urchin.get();
			conglomerateDisk = WLConfigHolder.SERVER.conglomerateDisk.get();
			conglomerateRock = WLConfigHolder.SERVER.conglomerateRock.get();
			mudDisk = WLConfigHolder.SERVER.mudDisk.get();
			bayouBiome = WLConfigHolder.SERVER.bayouBiome.get();
			burntForestBiome = WLConfigHolder.SERVER.burntForestBiome.get();
			bayouWeight = WLConfigHolder.SERVER.bayouWeight.get();
			burntForestWeight = WLConfigHolder.SERVER.burntForestWeight.get();
		} catch (Exception e) {
			WildLands.LOGGER.warn("An exception was caused trying to load the config for Wild Lands");
			e.printStackTrace();
		}
	}

}
