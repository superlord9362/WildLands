package superlord.wildlands.common.effects;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import superlord.wildlands.common.entity.JellyfishEntity;
import superlord.wildlands.init.DamageSourceInit;
import superlord.wildlands.init.WildLandsEntities;

public class StingEffect extends Effect {

	public StingEffect(EffectType type, int liquidColor) {
		super(type, liquidColor);
	}

	@Override
	public void performEffect(LivingEntity entity, int amplifier) {
		JellyfishEntity jellyfish = new JellyfishEntity(WildLandsEntities.JELLYFISH.get(), entity.world);
		TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, entity.world);
		if (entity == jellyfish || entity == turtle) {
			return;
		} else {
			entity.attackEntityFrom(DamageSourceInit.STING, 1.0F);
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
