package superlord.wildlands.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.entity.AlligatorEntity;
import superlord.wildlands.common.entity.AnchovyEntity;
import superlord.wildlands.common.entity.CatfishEntity;
import superlord.wildlands.common.entity.ClamEntity;
import superlord.wildlands.common.entity.CoconutEntity;
import superlord.wildlands.common.entity.CrabEntity;
import superlord.wildlands.common.entity.FrogEntity;
import superlord.wildlands.common.entity.HammerheadEntity;
import superlord.wildlands.common.entity.OctopusEntity;
import superlord.wildlands.common.entity.SeaLionEntity;
import superlord.wildlands.common.entity.WLBoatEntity;

public class WildLandsEntities {
	
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, WildLands.MOD_ID);
    
	public static final RegistryObject<EntityType<CatfishEntity>> CATFISH = REGISTER.register("catfish", () -> EntityType.Builder.<CatfishEntity>create(CatfishEntity::new, EntityClassification.WATER_AMBIENT).size(1F, 0.625F).build(new ResourceLocation(WildLands.MOD_ID, "catfish").toString()));
	public static final RegistryObject<EntityType<AlligatorEntity>> ALLIGATOR = REGISTER.register("alligator", () -> EntityType.Builder.<AlligatorEntity>create(AlligatorEntity::new, EntityClassification.CREATURE).size(0.9375F, 0.6875F).build(new ResourceLocation(WildLands.MOD_ID, "alligator").toString()));
	public static final RegistryObject<EntityType<WLBoatEntity>> BOAT = REGISTER.register("boat", () -> EntityType.Builder.<WLBoatEntity>create(WLBoatEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build(new ResourceLocation(WildLands.MOD_ID, "boat").toString()));
	public static final RegistryObject<EntityType<CoconutEntity>> COCONUT = REGISTER.register("coconut", () -> EntityType.Builder.<CoconutEntity>create(CoconutEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "coconut").toString()));
	public static final RegistryObject<EntityType<CrabEntity>> CRAB = REGISTER.register("crab", () -> EntityType.Builder.<CrabEntity>create(CrabEntity::new, EntityClassification.AMBIENT).size(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "crab").toString()));
	public static final RegistryObject<EntityType<ClamEntity>> CLAM = REGISTER.register("clam", () -> EntityType.Builder.<ClamEntity>create(ClamEntity::new, EntityClassification.WATER_AMBIENT).size(1.0F, 1.0F).build(new ResourceLocation(WildLands.MOD_ID, "clam").toString()));
	public static final RegistryObject<EntityType<FrogEntity>> FROG = REGISTER.register("frog", () -> EntityType.Builder.<FrogEntity>create(FrogEntity::new, EntityClassification.AMBIENT).size(0.75F, 0.75F).build(new ResourceLocation(WildLands.MOD_ID, "frog").toString()));
	public static final RegistryObject<EntityType<HammerheadEntity>> HAMMERHEAD = REGISTER.register("hammerhead", () -> EntityType.Builder.<HammerheadEntity>create(HammerheadEntity::new, EntityClassification.AMBIENT).size(0.75F, 0.625F).build(new ResourceLocation(WildLands.MOD_ID, "hammerhead").toString()));
	public static final RegistryObject<EntityType<AnchovyEntity>> ANCHOVY = REGISTER.register("anchovy", () -> EntityType.Builder.<AnchovyEntity>create(AnchovyEntity::new, EntityClassification.WATER_AMBIENT).size(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "anchovy").toString()));
	public static final RegistryObject<EntityType<OctopusEntity>> OCTOPUS = REGISTER.register("octopus", () -> EntityType.Builder.<OctopusEntity>create(OctopusEntity::new, EntityClassification.WATER_AMBIENT).size(1F, 1F).build(new ResourceLocation(WildLands.MOD_ID, "octopus").toString()));
	public static final RegistryObject<EntityType<SeaLionEntity>> SEA_LION = REGISTER.register("sea_lion", () -> EntityType.Builder.<SeaLionEntity>create(SeaLionEntity::new, EntityClassification.CREATURE).size(1F, 2F).build(new ResourceLocation(WildLands.MOD_ID, "sea_lion").toString()));
	
}
