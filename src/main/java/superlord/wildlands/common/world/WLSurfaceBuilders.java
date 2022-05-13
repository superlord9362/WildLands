package superlord.wildlands.common.world;

import static superlord.wildlands.common.mixin.server.SurfaceRuleDataAccess.invokeSurfaceNoiseAbove;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import superlord.wildlands.init.WildLandsBiomes;
import superlord.wildlands.init.WildLandsBlocks;;

public class WLSurfaceBuilders {

	public static final SurfaceRules.RuleSource BURNT_FOREST = SurfaceRules.ifTrue(SurfaceRules.isBiome(WildLandsBiomes.BURNT_FOREST), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(WildLandsBlocks.CHARRED_GRASS.get().defaultBlockState())));

	public static final SurfaceRules.RuleSource BAYOU = SurfaceRules.ifTrue(SurfaceRules.isBiome(WildLandsBiomes.BAYOU),
			SurfaceRules.sequence(
					SurfaceRules.ifTrue(invokeSurfaceNoiseAbove(1), SurfaceRules.sequence( SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(Blocks.DIRT.defaultBlockState())),
							SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(WildLandsBlocks.MUD.get().defaultBlockState()))
							)),
					SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(Blocks.DIRT.defaultBlockState())),
					SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(Blocks.GRASS.defaultBlockState()))
					)
			);

}
