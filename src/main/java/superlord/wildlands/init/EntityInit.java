package superlord.wildlands.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;
import superlord.wildlands.entity.AlligatorEntity;
import superlord.wildlands.entity.CatfishEntity;
import superlord.wildlands.entity.CoconutEntity;
import superlord.wildlands.entity.CrabEntity;
import superlord.wildlands.entity.WLBoatEntity;

public class EntityInit {
	
    public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, WildLands.MOD_ID);
    
	public static final RegistryObject<EntityType<CatfishEntity>> CATFISH = REGISTER.register("catfish", () -> EntityType.Builder.<CatfishEntity>create(CatfishEntity::new, EntityClassification.WATER_AMBIENT).size(1F, 0.625F).build(new ResourceLocation(WildLands.MOD_ID, "catfish").toString()));
	public static final RegistryObject<EntityType<AlligatorEntity>> ALLIGATOR = REGISTER.register("alligator", () -> EntityType.Builder.<AlligatorEntity>create(AlligatorEntity::new, EntityClassification.CREATURE).size(0.9375F, 0.6875F).build(new ResourceLocation(WildLands.MOD_ID, "alligator").toString()));
	public static final RegistryObject<EntityType<WLBoatEntity>> BOAT = REGISTER.register("boat", () -> EntityType.Builder.<WLBoatEntity>create(WLBoatEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build(new ResourceLocation(WildLands.MOD_ID, "boat").toString()));
	public static final RegistryObject<EntityType<CoconutEntity>> COCONUT = REGISTER.register("coconut", () -> EntityType.Builder.<CoconutEntity>create(CoconutEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "coconut").toString()));
	public static final RegistryObject<EntityType<CrabEntity>> CRAB = REGISTER.register("crab", () -> EntityType.Builder.<CrabEntity>create(CrabEntity::new, EntityClassification.AMBIENT).size(0.25F, 0.25F).build(new ResourceLocation(WildLands.MOD_ID, "crab").toString()));
	
}
