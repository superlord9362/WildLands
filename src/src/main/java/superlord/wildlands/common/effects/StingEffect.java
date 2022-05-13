package superlord.wildlands.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import superlord.wildlands.init.DamageSourceInit;

public class StingEffect extends MobEffect {

	public StingEffect(MobEffectCategory type, int liquidColor) {
		super(type, liquidColor);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.isInWater()) {
			entity.hurt(DamageSourceInit.STING, 1.0F);
		}
	}

	public boolean isReady(int duration, int amplifier) {
		int k = 25 >> amplifier;
		if (k > 0) {
			return duration % k == 0;
		} else {
			return true;
		}
	}

}
