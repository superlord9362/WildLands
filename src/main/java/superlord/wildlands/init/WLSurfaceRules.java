package superlord.wildlands.init;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class WLSurfaceRules {
	
    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);
	
    public static final SurfaceRules.RuleSource CHARRED_GRASS_SURFACE = SurfaceRules.sequence(SurfaceRules.ifTrue(WATER_CHECK, SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(WLBlocks.CHARRED_GRASS.get().defaultBlockState()))), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.state(Blocks.DIRT.defaultBlockState())));

    public static final SurfaceRules.RuleSource CHARRED_GRASS = SurfaceRules.ifTrue(SurfaceRules.isBiome(WLBiomes.BURNT_FOREST), SurfaceRules.sequence(CHARRED_GRASS_SURFACE));
    
    public static final SurfaceRules.RuleSource OVERWORLD_ABOVE_PRELIMINARY_SURFACE = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), SurfaceRules.sequence(CHARRED_GRASS));
    public static final SurfaceRules.RuleSource OVERWORLD_SURFACE_RULES = SurfaceRules.sequence(OVERWORLD_ABOVE_PRELIMINARY_SURFACE);

    
}
