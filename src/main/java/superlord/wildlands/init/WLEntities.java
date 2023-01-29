package superlord.wildlands.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.Alligator;
import superlord.wildlands.common.entity.Anchovy;
import superlord.wildlands.common.entity.Catfish;
import superlord.wildlands.common.entity.Crab;
import superlord.wildlands.common.entity.Grizzly;
import superlord.wildlands.common.entity.Hammerhead;
import superlord.wildlands.common.entity.Jellyfish;
import superlord.wildlands.common.entity.Octopus;
import superlord.wildlands.common.entity.SeaLion;
import superlord.wildlands.common.entity.WLBoat;
import superlord.wildlands.common.entity.WLChestBoat;
import superlord.wildlands.common.entity.item.Coconut;
import superlord.wildlands.common.entity.item.JellyBall;

public class WLEntities {
	
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WildLands.MOD_ID);
    
	public static final RegistryObject<EntityType<Catfish>> CATFISH = REGISTER.register("catfish", () -> EntityType.Builder.<Catfish>of(Catfish::new, MobCategory.WATER_AMBIENT).sized(1F, 0.625F).build(new ResourceLocation(WildLands.MOD_ID, "catfish").toString()));
	public static final RegistryObject<EntityType<Alligator>> ALLIGATOR = REGISTER.register("alligator", () -> EntityType.Builder.<Alligator>of(Alligator::new, MobCategory.CREATURE).sized(0.9375F, 0.6875F).build(new ResourceLocation(WildLands.MOD_ID, "alligator").toString()));
	public static final RegistryObject<EntityType<WLBoat>> BOAT = REGISTER.register("boat", () -> EntityType.Builder.<WLBoat>of(WLBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(WildLands.MOD_ID, "boat").toString()));
	public static final RegistryObject<EntityType<WLChestBoat>> CHEST_BOAT = REGISTER.register("chest_boat", () -> EntityType.Builder.<WLChestBoat>of(WLChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F).build(new ResourceLocation(WildLands.MOD_ID, "chest_boat").toString()));
	public static final RegistryObject<EntityType<Coconut>> COCONUT = REGISTER.register("coconut", () -> EntityType.Builder.<Coconut>of(Coconut::new, MobCategory.MISC).sized(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "coconut").toString()));
	public static final RegistryObject<EntityType<JellyBall>> JELLY_BALL = REGISTER.register("jelly_ball", () -> EntityType.Builder.<JellyBall>of(JellyBall::new, MobCategory.MISC).sized(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "jelly_ball").toString()));
	public static final RegistryObject<EntityType<Crab>> CRAB = REGISTER.register("crab", () -> EntityType.Builder.<Crab>of(Crab::new, MobCategory.AMBIENT).sized(0.75F, 0.75F).build(new ResourceLocation(WildLands.MOD_ID, "crab").toString()));
	//public static final RegistryObject<EntityType<ClamEntity>> CLAM = REGISTER.register("clam", () -> EntityType.Builder.<ClamEntity>of(ClamEntity::new, MobCategory.WATER_AMBIENT).sized(1.0F, 1.0F).build(new ResourceLocation(WildLands.MOD_ID, "clam").toString()));
	//public static final RegistryObject<EntityType<Frog>> FROG = REGISTER.register("frog", () -> EntityType.Builder.<Frog>of(Frog::new, MobCategory.AMBIENT).sized(0.75F, 0.75F).build(new ResourceLocation(WildLands.MOD_ID, "frog").toString()));
	public static final RegistryObject<EntityType<Hammerhead>> HAMMERHEAD = REGISTER.register("hammerhead", () -> EntityType.Builder.<Hammerhead>of(Hammerhead::new, MobCategory.AMBIENT).sized(0.75F, 0.625F).build(new ResourceLocation(WildLands.MOD_ID, "hammerhead").toString()));
	public static final RegistryObject<EntityType<Anchovy>> ANCHOVY = REGISTER.register("anchovy", () -> EntityType.Builder.<Anchovy>of(Anchovy::new, MobCategory.WATER_AMBIENT).sized(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "anchovy").toString()));
	public static final RegistryObject<EntityType<Octopus>> OCTOPUS = REGISTER.register("octopus", () -> EntityType.Builder.<Octopus>of(Octopus::new, MobCategory.WATER_AMBIENT).sized(1F, 1F).build(new ResourceLocation(WildLands.MOD_ID, "octopus").toString()));
	public static final RegistryObject<EntityType<SeaLion>> SEA_LION = REGISTER.register("sea_lion", () -> EntityType.Builder.<SeaLion>of(SeaLion::new, MobCategory.CREATURE).sized(1F, 2F).build(new ResourceLocation(WildLands.MOD_ID, "sea_lion").toString()));
	public static final RegistryObject<EntityType<Jellyfish>> JELLYFISH = REGISTER.register("jellyfish", () -> EntityType.Builder.<Jellyfish>of(Jellyfish::new, MobCategory.WATER_AMBIENT).sized(1F, 1F).build(new ResourceLocation(WildLands.MOD_ID, "jellyfish").toString()));
	public static final RegistryObject<EntityType<Grizzly>> GRIZZLY = REGISTER.register("grizzly_bear", () -> EntityType.Builder.<Grizzly>of(Grizzly::new, MobCategory.CREATURE).sized(2F, 2F).build(new ResourceLocation(WildLands.MOD_ID, "grizzly_bear").toString()));
	
}
