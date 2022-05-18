package superlord.worldgentest;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.worldgentest.init.TestBiomes;

@Mod(WorldGenTest.MOD_ID)
public class WorldGenTest {

	public static final String MOD_ID = "world_gen_testing";
	
	public WorldGenTest() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        TestBiomes.BIOMES.register(bus);
	}
	
}
