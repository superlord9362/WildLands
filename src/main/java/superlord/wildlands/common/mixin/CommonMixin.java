package superlord.wildlands.common.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.storage.WorldData;
import superlord.wildlands.init.WLSurfaceRules;

@Mixin(MinecraftServer.class)
public class CommonMixin {

    @Shadow
    @Final
    protected WorldData worldData;

	@Inject(method = "createLevels", at = @At("RETURN"))
	private void hackyAddNetherAndEndSurfaceRules(ChunkProgressListener $$0, CallbackInfo ci) {
		LevelStem overworldStem = this.worldData.worldGenSettings().dimensions().get(LevelStem.OVERWORLD);
		ChunkGenerator overworldGenerator = overworldStem.generator();
		if (overworldGenerator != null && overworldGenerator instanceof NoiseBasedChunkGenerator) {
			Object noiseGeneratorSettings = ((NoiseBasedChunkGeneratorAccess) overworldGenerator).wl_getSettings().value();
			((NoiseGeneratorSettingsAccess) noiseGeneratorSettings).wl_setSurfaceRule(SurfaceRules.sequence(WLSurfaceRules.OVERWORLD_SURFACE_RULES, ((NoiseGeneratorSettings) noiseGeneratorSettings).surfaceRule()));
		}
	}

}
