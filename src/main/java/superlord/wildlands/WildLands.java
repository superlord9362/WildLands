package superlord.wildlands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.wildlands.client.network.WLPacketHandler;
import superlord.wildlands.common.CommonEvents;
import superlord.wildlands.common.core.world.WLConfiguredFeatures;
import superlord.wildlands.common.core.world.WLFeatures;
import superlord.wildlands.common.entity.AlligatorEntity;
import superlord.wildlands.common.entity.CatfishEntity;
import superlord.wildlands.common.entity.ClamEntity;
import superlord.wildlands.common.entity.CrabEntity;
import superlord.wildlands.common.entity.FrogEntity;
import superlord.wildlands.common.entity.HammerheadEntity;
import superlord.wildlands.common.world.WLDecorators;
import superlord.wildlands.common.world.WLSurfaceBuilders;
import superlord.wildlands.init.WLTileEntities;
import superlord.wildlands.init.WildLandsBiomes;
import superlord.wildlands.init.WildLandsBlocks;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;

@Mod(WildLands.MOD_ID)
@Mod.EventBusSubscriber(modid = WildLands.MOD_ID)
public class WildLands {
	
    public static final String MOD_ID = "wildlands";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final List<Runnable> CALLBACKS = new ArrayList<>();
    
    public WildLands() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerCommon);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerClient);
        bus.addListener(this::setup);
    	WildLandsBlocks.REGISTER.register(bus);
    	WildLandsEntities.REGISTER.register(bus);
    	WildLandsItems.REGISTER.register(bus);
    	WLTileEntities.REGISTER.register(bus);
    	WLFeatures.TREE_DECORATOR_REGISTRY.register(bus);
		WLPacketHandler.registerPackets();
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerBiomes(BiomeLoadingEvent event) {
    	String name = event.getName().getPath();
    	if (event.getCategory() == Biome.Category.OCEAN) {
    		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> WLConfiguredFeatures.DOLERITE_DISK);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WLConfiguredFeatures.DOLERITE_ROCK);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_DECORATION).add(() -> WLConfiguredFeatures.GABBRO_DISK);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WLConfiguredFeatures.GABBRO_ROCK);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WLConfiguredFeatures.OLIVINE_GABBRO_ROCK);
    	}
    	if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean") || name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("beach")) {
    		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> WLConfiguredFeatures.FINE_SAND_DISK);
    	}
    	if (name.equals("warm_ocean") || name.equals("deep_warm_ocean")) {
    		event.getSpawns().getSpawner(EntityClassification.AMBIENT).add(new MobSpawnInfo.Spawners(WildLandsEntities.CLAM.get(), 5, 1, 3));
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WLConfiguredFeatures.STARFISH);
    	}
    	if (name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean")) {
    		event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(WildLandsEntities.HAMMERHEAD.get(), 5, 3, 6));
    	}
    	if (name.equals("ocean") || name.equals("deep_ocean") || name.equals("cold_ocean") || name.equals("deep_cold_ocean") || name.equals("frozen_ocean") || name.equals("deep_frozen_ocean") || name.equals("stone_shore")) {
    		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> WLConfiguredFeatures.CONGLOMERATE_DISK);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WLConfiguredFeatures.CONGLOMERATE_ROCK);
    	}
    	if (name.equals("beach")) {
    		event.getSpawns().getSpawner(EntityClassification.AMBIENT).add(new MobSpawnInfo.Spawners(WildLandsEntities.CRAB.get(), 5, 1, 3));
    		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> WLConfiguredFeatures.FINE_SAND_DISK);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WLConfiguredFeatures.RANDOM_COCONUT_TREE);
    	}
    	if (name.equals("swamp")) {
    		event.getSpawns().getSpawner(EntityClassification.AMBIENT).add(new MobSpawnInfo.Spawners(WildLandsEntities.FROG.get(), 15, 1, 3));
    		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> WLConfiguredFeatures.MUD_DISK);
    	}
    }
    
    private void registerClient(FMLClientSetupEvent event) {
    	CALLBACKS.forEach(Runnable::run);
    	CALLBACKS.clear();
    }
    
	public void setup(final FMLCommonSetupEvent event) {

        CommonEvents.setup();
	}
    
    public final static ItemGroup BLOCK_GROUP = new ItemGroup("wildlands_block_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(WildLandsBlocks.MUD.get().asItem());
        }
    };
    
    public final static ItemGroup ITEM_GROUP = new ItemGroup("wildlands_item_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(WildLandsItems.OLIVINE.get());
        }
    };
    
    public final static ItemGroup SPAWN_EGG_GROUP = new ItemGroup("wildlands_spawn_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(WildLandsItems.CATFISH_SPAWN_EGG.get());
        }
    };
    
    private void registerCommon(FMLCommonSetupEvent event) {
        registerEntityAttributes();
        }
    
    private void commonSetup(FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(WildLandsEntities.CATFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(WildLandsEntities.ALLIGATOR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(WildLandsEntities.CRAB.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(WildLandsEntities.CLAM.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, ClamEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(WildLandsEntities.FROG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(WildLandsEntities.HAMMERHEAD.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, HammerheadEntity::func_223364_b);
        event.enqueueWork(() -> {
    		WildLandsBiomes.addBiomeEntries();
    		WildLandsBiomes.fillBiomeDictionary();
    	});
    }
    
    private void registerEntityAttributes() {
        GlobalEntityTypeAttributes.put(WildLandsEntities.CATFISH.get(), CatfishEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(WildLandsEntities.ALLIGATOR.get(), AlligatorEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(WildLandsEntities.CRAB.get(), CrabEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(WildLandsEntities.CLAM.get(), ClamEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(WildLandsEntities.FROG.get(), FrogEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(WildLandsEntities.HAMMERHEAD.get(), HammerheadEntity.createAttributes().create());
    }
    
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class WLWorldGenRegistries {
    	@SubscribeEvent
    	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
    		WildLandsBiomes.init();
    		WildLandsBiomes.biomeList.sort(Comparator.comparingInt(WildLandsBiomes.PreserveBiomeOrder::getOrderPosition));
    		WildLandsBiomes.biomeList.forEach(preserverBiomeOrder -> event.getRegistry().register(preserverBiomeOrder.getBiome()));
    	}
    	
    	@SubscribeEvent
        public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
    		  WLFeatures.init();
              WLFeatures.features.forEach(feature -> event.getRegistry().register(feature));
    	}
    	
    	@SubscribeEvent
        public static void registerDecorators(RegistryEvent.Register<Placement<?>> event) {
    		WLDecorators.init();
    		WLDecorators.decorators.forEach(decorator -> event.getRegistry().register(decorator));
    	}
    	
    	 @SubscribeEvent
         public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
    		 WLSurfaceBuilders.init();
             WLSurfaceBuilders.surfaceBuilders.forEach(surfaceBuilder -> event.getRegistry().register(surfaceBuilder));
    	 }
    }

}
