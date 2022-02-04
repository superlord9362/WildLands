package superlord.wildlands.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
	
	public final ForgeConfigSpec.IntValue catfishSpawnWeight;
	public final ForgeConfigSpec.IntValue alligatorSpawnWeight;
	public final ForgeConfigSpec.IntValue frogSpawnWeight;
	public final ForgeConfigSpec.IntValue crabSpawnWeight;
	public final ForgeConfigSpec.IntValue seaLionSpawnWeight;
	public final ForgeConfigSpec.IntValue anchovySpawnWeight;
	public final ForgeConfigSpec.IntValue clamSpawnWeight;
	public final ForgeConfigSpec.IntValue hammerheadSharkSpawnWeight;
	public final ForgeConfigSpec.IntValue octopusSpawnWeight;
	public final ForgeConfigSpec.IntValue jellyfishSpawnWeight;
	public final ForgeConfigSpec.DoubleValue bayouScale;
	public final ForgeConfigSpec.IntValue grizzlyBearSpawnWeight;
	public final ForgeConfigSpec.DoubleValue burntForestScale;
	
	public final ForgeConfigSpec.BooleanValue superSecretSettings;
	
	public ServerConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		builder.comment("Bayou Config");
		this.catfishSpawnWeight = buildInt(builder, "Catfish Spawn Weight", "all", 1, 1, 300, "The weight of Catfish in vanilla's spawn rate. Default is 1");
		this.alligatorSpawnWeight = buildInt(builder, "Alligator Spawn Weight", "all", 10, 1, 300, "The weight of Alligators in vanilla's spawn rate. Default is 10");
		this.frogSpawnWeight = buildInt(builder, "Frog Spawn Weight", "all", 15, 1, 300, "The weight of Frogs in vanilla's spawn rate. Default is 15");
		this.bayouScale = buildDouble(builder, "Bayou Biome Size", "all", 0.1, 0, 300, "The size of the Bayou biome. Default is 0.1");
		builder.comment("Beach Config");
		this.crabSpawnWeight = buildInt(builder, "Crab Spawn Weight", "all", 5, 1, 300, "The weight of Crabs in vanilla's spawn rate. Default is 5");
		this.seaLionSpawnWeight = buildInt(builder, "Sea Lion Spawn Weight", "all", 10, 1, 300, "The weight of Sea Lions in vanilla's spawn rate. Default is 10");
		builder.comment("Ocean Config");
		this.anchovySpawnWeight = buildInt(builder, "Anchovy Spawn Weight", "all", 1, 1, 300, "The weight of Anchovy in vanilla's spawn rate. Default is 1");
		this.clamSpawnWeight = buildInt(builder, "Clam Spawn Weight", "all", 1, 1, 300, "The weight of Clams in vanilla's spawn rate. Default is 3");
		this.hammerheadSharkSpawnWeight = buildInt(builder, "Hammerhead Shark Spawn Weight", "all", 5, 1, 300, "The weight of Hammerhead Sharks in vanilla's spawn rate. Default is 5");
		this.octopusSpawnWeight = buildInt(builder, "Octopus Spawn Weight", "all", 3, 1, 300, "The weight of Octopi in vanilla's spawn rate. Default is 3");
		this.jellyfishSpawnWeight = buildInt(builder, "Jellyfish Spawn Weight", "all", 6, 1, 300, "The weight of Jellyfish in vanilla's spawn rate. Default is 6");
		builder.comment("Forest Config");
		this.grizzlyBearSpawnWeight = buildInt(builder, "Grizzly Bear Spawn Weight", "all", 10, 1, 300, "The weight of Grizzly Bears in vanilla's spawn rate. Default is 10");
		this.burntForestScale = buildDouble(builder, "Burnt Forest Biome Size", "all", 0.1, 0, 300, "The size of the Burnt Forest biome. Default is 0.1");
		builder.comment("Misc Config");
		this.superSecretSettings = buildBoolean(builder, "Super Secret Settings", "all", false, "Even I don't know what this does. Default is false");
	}
	
	private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, String category, boolean defaultValue, String comment) {
		return builder.comment(comment).translation(name).define(name, defaultValue);
	}
	
	private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, String category, int defaultValue, int min, int max, String comment) {
		return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
	}
	
	private static ForgeConfigSpec.DoubleValue buildDouble(ForgeConfigSpec.Builder builder, String name, String category, double defaultValue, double min, double max, String comment) {
		return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
	}

}
