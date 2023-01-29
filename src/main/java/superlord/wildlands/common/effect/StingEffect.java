package superlord.wildlands.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Turtle;
import superlord.wildlands.common.entity.Jellyfish;
import superlord.wildlands.init.WLDamageSources;

public class StingEffect extends MobEffect {

	public StingEffect(MobEffectCategory type, int liquidColor) {
		super(type, liquidColor);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int p_19468_) {
		if (entity.isInWater()) {
			if (!(entity instanceof Turtle || entity instanceof Jellyfish)) {
				entity.hurt(WLDamageSources.STING, 1.0F);
			}
		}
	}

	public boolean isDurationEffectTick(int duration, int amplifier) {
		int k = 25 >> amplifier;
		if (k > 0) {
			return duration % k == 0;
		} else {
			return true;
		}
	}

}
