package superlord.wildlands.common.world.feature.config;

import com.mojang.serialization.Codec;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class DuckweedConfig implements IFeatureConfig {
	public static int count;

	@SuppressWarnings("static-access")
	public DuckweedConfig(int count) {
		this.count = count;
	}

	public static final Codec<DuckweedConfig> field_236558_a_;
	public static final DuckweedConfig field_236559_b_ = new DuckweedConfig(count);

	static {
		field_236558_a_ = Codec.unit(() -> {
			return field_236559_b_;
		});
	}

}
