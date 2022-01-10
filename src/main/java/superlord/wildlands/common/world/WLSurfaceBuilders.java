package superlord.wildlands.common.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import superlord.wildlands.common.util.WorldGenRegistrationHelper;
import superlord.wildlands.common.world.surfacebuilder.BayouSB;
import superlord.wildlands.common.world.surfacebuilder.BurntForestSB;
import superlord.wildlands.init.WildLandsBlocks;

public class WLSurfaceBuilders {
	
	public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();
	
	public static final SurfaceBuilder<SurfaceBuilderConfig> BAYOU = WorldGenRegistrationHelper.createSurfaceBuilder("bayou", new BayouSB(SurfaceBuilderConfig.field_237203_a_));
	public static final SurfaceBuilder<SurfaceBuilderConfig> BURNT_FOREST = WorldGenRegistrationHelper.createSurfaceBuilder("burnt_forest", new BurntForestSB(SurfaceBuilderConfig.field_237203_a_));

	public static void init() {
		
	}
	
	public static class Configs {
		public static final SurfaceBuilderConfig MUD = new SurfaceBuilderConfig(WildLandsBlocks.MUD.get().getDefaultState(), WildLandsBlocks.DRIED_MUD.get().getDefaultState(), Blocks.DIRT.getDefaultState());
		public static final SurfaceBuilderConfig CHARRED_GRASS = new SurfaceBuilderConfig(WildLandsBlocks.CHARRED_GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState());
	}
	
}
