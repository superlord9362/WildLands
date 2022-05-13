package superlord.wildlands.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.AlligatorEntity;
import superlord.wildlands.common.entity.AnchovyEntity;
import superlord.wildlands.common.entity.CatfishEntity;
import superlord.wildlands.common.entity.CoconutEntity;
import superlord.wildlands.common.entity.CrabEntity;
import superlord.wildlands.common.entity.FrogEntity;
import superlord.wildlands.common.entity.GrizzlyEntity;
import superlord.wildlands.common.entity.HammerheadEntity;
import superlord.wildlands.common.entity.JellyBallEntity;
import superlord.wildlands.common.entity.JellyfishEntity;
import superlord.wildlands.common.entity.OctopusEntity;
import superlord.wildlands.common.entity.SeaLionEntity;
import superlord.wildlands.common.entity.WLBoatEntity;

public class WildLandsEntities {
	
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, WildLands.MOD_ID);
    
	public static final RegistryObject<EntityType<CatfishEntity>> CATFISH = REGISTER.register("catfish", () -> EntityType.Builder.<CatfishEntity>of(CatfishEntity::new, MobCategory.WATER_AMBIENT).sized(1F, 0.625F).build(new ResourceLocation(WildLands.MOD_ID, "catfish").toString()));
	public static final RegistryObject<EntityType<AlligatorEntity>> ALLIGATOR = REGISTER.register("alligator", () -> EntityType.Builder.<AlligatorEntity>of(AlligatorEntity::new, MobCategory.CREATURE).sized(0.9375F, 0.6875F).build(new ResourceLocation(WildLands.MOD_ID, "alligator").toString()));
	public static final RegistryObject<EntityType<WLBoatEntity>> BOAT = REGISTER.register("boat", () -> EntityType.Builder.<WLBoatEntity>of(WLBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(WildLands.MOD_ID, "boat").toString()));
	public static final RegistryObject<EntityType<CoconutEntity>> COCONUT = REGISTER.register("coconut", () -> EntityType.Builder.<CoconutEntity>of(CoconutEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "coconut").toString()));
	public static final RegistryObject<EntityType<JellyBallEntity>> JELLY_BALL = REGISTER.register("jelly_ball", () -> EntityType.Builder.<JellyBallEntity>of(JellyBallEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "jelly_ball").toString()));
	public static final RegistryObject<EntityType<CrabEntity>> CRAB = REGISTER.register("crab", () -> EntityType.Builder.<CrabEntity>of(CrabEntity::new, MobCategory.AMBIENT).sized(0.75F, 0.75F).build(new ResourceLocation(WildLands.MOD_ID, "crab").toString()));
	//public static final RegistryObject<EntityType<ClamEntity>> CLAM = REGISTER.register("clam", () -> EntityType.Builder.<ClamEntity>of(ClamEntity::new, MobCategory.WATER_AMBIENT).sized(1.0F, 1.0F).build(new ResourceLocation(WildLands.MOD_ID, "clam").toString()));
	public static final RegistryObject<EntityType<FrogEntity>> FROG = REGISTER.register("frog", () -> EntityType.Builder.<FrogEntity>of(FrogEntity::new, MobCategory.AMBIENT).sized(0.75F, 0.75F).build(new ResourceLocation(WildLands.MOD_ID, "frog").toString()));
	public static final RegistryObject<EntityType<HammerheadEntity>> HAMMERHEAD = REGISTER.register("hammerhead", () -> EntityType.Builder.<HammerheadEntity>of(HammerheadEntity::new, MobCategory.AMBIENT).sized(0.75F, 0.625F).build(new ResourceLocation(WildLands.MOD_ID, "hammerhead").toString()));
	public static final RegistryObject<EntityType<AnchovyEntity>> ANCHOVY = REGISTER.register("anchovy", () -> EntityType.Builder.<AnchovyEntity>of(AnchovyEntity::new, MobCategory.WATER_AMBIENT).sized(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "anchovy").toString()));
	public static final RegistryObject<EntityType<OctopusEntity>> OCTOPUS = REGISTER.register("octopus", () -> EntityType.Builder.<OctopusEntity>of(OctopusEntity::new, MobCategory.WATER_AMBIENT).sized(1F, 1F).build(new ResourceLocation(WildLands.MOD_ID, "octopus").toString()));
	public static final RegistryObject<EntityType<SeaLionEntity>> SEA_LION = REGISTER.register("sea_lion", () -> EntityType.Builder.<SeaLionEntity>of(SeaLionEntity::new, MobCategory.CREATURE).sized(1F, 2F).build(new ResourceLocation(WildLands.MOD_ID, "sea_lion").toString()));
	public static final RegistryObject<EntityType<JellyfishEntity>> JELLYFISH = REGISTER.register("jellyfish", () -> EntityType.Builder.<JellyfishEntity>of(JellyfishEntity::new, MobCategory.WATER_AMBIENT).sized(1F, 1F).build(new ResourceLocation(WildLands.MOD_ID, "jellyfish").toString()));
	public static final RegistryObject<EntityType<GrizzlyEntity>> GRIZZLY = REGISTER.register("grizzly_bear", () -> EntityType.Builder.<GrizzlyEntity>of(GrizzlyEntity::new, MobCategory.CREATURE).sized(2F, 2F).build(new ResourceLocation(WildLands.MOD_ID, "grizzly_bear").toString()));
	
}
