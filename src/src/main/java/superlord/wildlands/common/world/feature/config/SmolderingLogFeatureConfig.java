package superlord.wildlands.common.world.feature.config;

import com.mojang.serialization.Codec;

import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class SmolderingLogFeatureConfig implements FeatureConfiguration {
	
	public static int count;
	
	@SuppressWarnings("static-access")
	public SmolderingLogFeatureConfig(int count) {
		this.count = count;
	}
	
	public static final Codec<SmolderingLogFeatureConfig> field_236558_a_;
	public static final SmolderingLogFeatureConfig field_236559_b_ = new SmolderingLogFeatureConfig(count);
	
	static {
		field_236558_a_ = Codec.unit(() -> {
			return field_236559_b_;
		});
	}

}
