package superlord.wildlands.common.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import superlord.wildlands.init.WildLandsBlocks;
import superlord.wildlands.common.util.WorldGenRegistrationHelper;
import superlord.wildlands.common.world.surfacebuilder.BayouSB;

public class WLSurfaceBuilders {
	
	public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> BAYOU = WorldGenRegistrationHelper.createSurfaceBuilder("bayou", new BayouSB(SurfaceBuilderConfig.field_237203_a_));

	public static void init() {
		
	}
	
	public static class Configs {
		public static final SurfaceBuilderConfig MUD = new SurfaceBuilderConfig(WildLandsBlocks.MUD.get().getDefaultState(), WildLandsBlocks.DRIED_MUD.get().getDefaultState(), Blocks.DIRT.getDefaultState());
	}
	
}
