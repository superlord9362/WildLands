package superlord.wildlands.common.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.levelgen.SurfaceRules;

@Mixin(SurfaceRuleData.class)
public class SurfaceRuleDataAccess {

	@Invoker("surfaceNoiseAbove")
	public static SurfaceRules.ConditionSource invokeSurfaceNoiseAbove(double d) {
		throw new Error("Mixin did not apply!");
	}

}
