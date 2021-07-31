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
import superlord.wildlands.core.world.WLConfiguredFeatures;
import superlord.wildlands.core.world.WLFeatures;
import superlord.wildlands.entity.AlligatorEntity;
import superlord.wildlands.entity.CatfishEntity;
import superlord.wildlands.entity.CrabEntity;
import superlord.wildlands.init.BiomeInit;
import superlord.wildlands.init.BlockInit;
import superlord.wildlands.init.EntityInit;
import superlord.wildlands.init.ItemInit;
import superlord.wildlands.world.WLDecorators;
import superlord.wildlands.world.WLSurfaceBuilders;

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
    	BlockInit.REGISTER.register(bus);
    	EntityInit.REGISTER.register(bus);
    	ItemInit.REGISTER.register(bus);
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
    		System.out.println("Biome Modifiactions Added!");
    	}
    	if (name.equals("lukewarm_ocean") || name.equals("deep_lukewarm_ocean") || name.equals("warm_ocean") || name.equals("deep_warm_ocean") || name.equals("beach")) {
    		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> WLConfiguredFeatures.FINE_SAND_DISK);
    	}
    	if (name.equals("warm_ocean") || name.equals("deep_warm_ocean")) {
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WLConfiguredFeatures.STARFISH);
    	}
    	if (name.equals("ocean") || name.equals("deep_ocean") || name.equals("cold_ocean") || name.equals("deep_cold_ocean") || name.equals("frozen_ocean") || name.equals("deep_frozen_ocean") || name.equals("stone_shore")) {
    		event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> WLConfiguredFeatures.CONGLOMERATE_DISK);
    		event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> WLConfiguredFeatures.CONGLOMERATE_ROCK);
    	}
    	if (name.equals("beach")) {
    		event.getSpawns().getSpawner(EntityClassification.AMBIENT).add(new MobSpawnInfo.Spawners(EntityInit.CRAB.get(), 5, 1, 3));
    	}
    }
    
    private void registerClient(FMLClientSetupEvent event) {
    	CALLBACKS.forEach(Runnable::run);
    	CALLBACKS.clear();
    }
    
    public final static ItemGroup BLOCK_GROUP = new ItemGroup("wildlands_block_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.MUD.get().asItem());
        }
    };
    
    public final static ItemGroup ITEM_GROUP = new ItemGroup("wildlands_item_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.OLIVINE.get());
        }
    };
    
    public final static ItemGroup SPAWN_EGG_GROUP = new ItemGroup("wildlands_spawn_item_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.CATFISH_SPAWN_EGG.get());
        }
    };
    
    private void registerCommon(FMLCommonSetupEvent event) {
        registerEntityAttributes();
        }
    
    private void commonSetup(FMLCommonSetupEvent event) {
        EntitySpawnPlacementRegistry.register(EntityInit.CATFISH.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(EntityInit.ALLIGATOR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        event.enqueueWork(() -> {
    		BiomeInit.addBiomeEntries();
    		BiomeInit.fillBiomeDictionary();
    		System.out.println("Added biomes!");
    	});
    }
    
    private void registerEntityAttributes() {
        GlobalEntityTypeAttributes.put(EntityInit.CATFISH.get(), CatfishEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(EntityInit.ALLIGATOR.get(), AlligatorEntity.createAttributes().create());
        GlobalEntityTypeAttributes.put(EntityInit.CRAB.get(), CrabEntity.createAttributes().create());
    }
    
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class WLWorldGenRegistries {
    	@SubscribeEvent
    	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
    		BiomeInit.init();
    		BiomeInit.biomeList.sort(Comparator.comparingInt(BiomeInit.PreserveBiomeOrder::getOrderPosition));
    		BiomeInit.biomeList.forEach(preserverBiomeOrder -> event.getRegistry().register(preserverBiomeOrder.getBiome()));
    		System.out.println("Registered biomes!");
    	}
    	
    	@SubscribeEvent
        public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
    		  WLFeatures.init();
              WLFeatures.features.forEach(feature -> event.getRegistry().register(feature));
      		System.out.println("Registered features!");
    	}
    	
    	@SubscribeEvent
        public static void registerDecorators(RegistryEvent.Register<Placement<?>> event) {
    		WLDecorators.init();
    		WLDecorators.decorators.forEach(decorator -> event.getRegistry().register(decorator));
    		System.out.println("Registered decorators!");
    	}
    	
    	 @SubscribeEvent
         public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
    		 WLSurfaceBuilders.init();
             WLSurfaceBuilders.surfaceBuilders.forEach(surfaceBuilder -> event.getRegistry().register(surfaceBuilder));
    	 }
    }

}
