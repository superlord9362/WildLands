package superlord.wildlands.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
	
	public final ForgeConfigSpec.IntValue catfishSpawnWeight;
	public final ForgeConfigSpec.IntValue alligatorSpawnWeight;
	public final ForgeConfigSpec.IntValue crabSpawnWeight;
	public final ForgeConfigSpec.IntValue seaLionSpawnWeight;
	public final ForgeConfigSpec.IntValue anchovySpawnWeight;
	//public final ForgeConfigSpec.IntValue clamSpawnWeight;
	public final ForgeConfigSpec.IntValue hammerheadSharkSpawnWeight;
	public final ForgeConfigSpec.IntValue octopusSpawnWeight;
	public final ForgeConfigSpec.IntValue jellyfishSpawnWeight;
	public final ForgeConfigSpec.IntValue grizzlyBearSpawnWeight;
	
	public final ForgeConfigSpec.BooleanValue superSecretSettings;
	public final ForgeConfigSpec.BooleanValue coconutTree;
	public final ForgeConfigSpec.BooleanValue doleriteDisk;
	public final ForgeConfigSpec.BooleanValue doleriteRock;
	public final ForgeConfigSpec.BooleanValue gabbroDisk;
	public final ForgeConfigSpec.BooleanValue gabbroRock;
	public final ForgeConfigSpec.BooleanValue olivineGabbroRock;
	public final ForgeConfigSpec.BooleanValue fineSandDisk;
	public final ForgeConfigSpec.BooleanValue starfish;
	public final ForgeConfigSpec.BooleanValue urchin;
	public final ForgeConfigSpec.BooleanValue conglomerateDisk;
	public final ForgeConfigSpec.BooleanValue conglomerateRock;
	public final ForgeConfigSpec.BooleanValue mudDisk;
	public final ForgeConfigSpec.BooleanValue bayouBiome;
	public final ForgeConfigSpec.BooleanValue burntForestBiome;
	public final ForgeConfigSpec.IntValue bayouWeight;
	public final ForgeConfigSpec.IntValue burntForestWeight;
	
	public ServerConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		builder.comment("Bayou Config");
		this.catfishSpawnWeight = buildInt(builder, "Catfish Spawn Weight", "all", 4, 0, 300, "The weight of Catfish in vanilla's spawn rate. Default is 4");
		this.alligatorSpawnWeight = buildInt(builder, "Alligator Spawn Weight", "all", 20, 0, 300, "The weight of Alligators in vanilla's spawn rate. Default is 20");
		builder.comment("Beach Config");
		this.crabSpawnWeight = buildInt(builder, "Crab Spawn Weight", "all", 5, 0, 300, "The weight of Crabs in vanilla's spawn rate. Default is 5");
		this.seaLionSpawnWeight = buildInt(builder, "Sea Lion Spawn Weight", "all", 10, 0, 300, "The weight of Sea Lions in vanilla's spawn rate. Default is 10");
		builder.comment("Ocean Config");
		this.anchovySpawnWeight = buildInt(builder, "Anchovy Spawn Weight", "all", 1, 0, 300, "The weight of Anchovy in vanilla's spawn rate. Default is 1");
		//this.clamSpawnWeight = buildInt(builder, "Clam Spawn Weight", "all", 1, 1, 300, "The weight of Clams in vanilla's spawn rate. Default is 3");
		this.hammerheadSharkSpawnWeight = buildInt(builder, "Hammerhead Shark Spawn Weight", "all", 5, 0, 300, "The weight of Hammerhead Sharks in vanilla's spawn rate. Default is 5");
		this.octopusSpawnWeight = buildInt(builder, "Octopus Spawn Weight", "all", 3, 0, 300, "The weight of Octopi in vanilla's spawn rate. Default is 3");
		this.jellyfishSpawnWeight = buildInt(builder, "Jellyfish Spawn Weight", "all", 6, 0, 300, "The weight of Jellyfish in vanilla's spawn rate. Default is 6");
		builder.comment("Forest Config");
		this.grizzlyBearSpawnWeight = buildInt(builder, "Grizzly Bear Spawn Weight", "all", 10, 0, 300, "The weight of Grizzly Bears in vanilla's spawn rate. Default is 10");
		builder.comment("Misc Config");
		this.superSecretSettings = buildBoolean(builder, "Super Secret Settings", "all", false, "Even I don't know what this does. Default is false");
		builder.comment("Biome Modifications");
		this.coconutTree = buildBoolean(builder, "Coconut Trees", "all", true, "Coconut Trees will generate on beaches. Default is true");
		this.doleriteDisk = buildBoolean(builder, "Dolerite Disks", "all", true, "Dolerite Disks will generate in oceans. Default is true");
		this.doleriteRock = buildBoolean(builder, "Dolerite Rocks", "all", true, "Dolerite Rocks will generate in oceans. Default is true");
		this.gabbroDisk = buildBoolean(builder, "Gabbro Disks", "all", true, "Gabbro Disks will generate in oceans. Default is true");
		this.gabbroRock = buildBoolean(builder, "Gabbro Rocks", "all", true, "Gabbro Rocks will generate in oceans. Default is true");
		this.olivineGabbroRock = buildBoolean(builder, "Olivine Gabbro Rocks", "all", true, "Olivine Gabbro Rocks will generate in oceans. Default is true");
		this.fineSandDisk = buildBoolean(builder, "Fine Sand Disks", "all", true, "Fine Sand Disks will generate in oceans and beaches. Default is true");
		this.starfish = buildBoolean(builder, "Stafish", "all", true, "Starfish will generate in oceans. Default is true");
		this.urchin = buildBoolean(builder, "Urchins", "all", true, "Urchins will generate in oceans. Default is true");
		this.conglomerateDisk = buildBoolean(builder, "Conglomerate Disks", "all", true, "Conglomerate Disks will generate in oceans. Default is true");
		this.conglomerateRock = buildBoolean(builder, "Conglomerate Rocks", "all", true, "Conglomerate Rocks will generate in oceans. Default is true");
		this.mudDisk = buildBoolean(builder, "Mud Disks", "all", true, "Mud Disks will generate in swamps. Default is true");
		builder.comment("Biomes");
		this.burntForestBiome = buildBoolean(builder, "Burnt Forest Generation", "all", true, "Burnt Forests will generate. Default is true");
		this.burntForestWeight = buildInt(builder, "Burnt Forest Biome Weight", "all", 1, 0, 300, "The weight of the Burnt Forest biome generating. Default is 1");
		this.bayouBiome = buildBoolean(builder, "Bayou Generation", "all", true, "Bayous will generate. Default is true");
		this.bayouWeight = buildInt(builder, "Bayou Biome Weight", "all", 1, 0, 300, "The weight of the Bayou biome generating. Default is 1");
		builder.pop();
	}
	
	private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, String category, boolean defaultValue, String comment) {
		return builder.comment(comment).translation(name).define(name, defaultValue);
	}
	
	private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, String category, int defaultValue, int min, int max, String comment) {
		return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
	}

}
