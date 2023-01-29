package superlord.wildlands.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class WLDamageSources {
	
	public static final DamageSource CLAM = new DamageCustomDeathMessage("clam");
	public static final DamageSource STING = new DamageCustomDeathTwoMessage("sting");
	
	static class DamageCustomDeathMessage extends DamageSource {
		
		public DamageCustomDeathMessage(String damageTypeIn) {
			super(damageTypeIn);
		}
		
		public Component getDeathMessage(LivingEntity entityLivingBaseIn) {
			LivingEntity livingEntity = entityLivingBaseIn.getKillCredit();
			String s = "death.attack." + this.msgId;
			int index = entityLivingBaseIn.getRandom().nextInt(3);
			String s1 = s + "." + index;
			String s2 = s + ".attacker_" + index;
			return livingEntity != null ?  Component.translatable(s2, entityLivingBaseIn.getDisplayName(), livingEntity.getDisplayName()) :  Component.translatable(s1, entityLivingBaseIn.getDisplayName());
		}
		
	}
	
	static class DamageCustomDeathTwoMessage extends DamageSource {
		
		public DamageCustomDeathTwoMessage(String damageTypeIn) {
			super(damageTypeIn);
		}
		
		public Component getDeathMessage(LivingEntity entityLivingBaseIn) {
			LivingEntity livingEntity = entityLivingBaseIn.getKillCredit();
			String s = "death.attack." + this.msgId;
			int index = entityLivingBaseIn.getRandom().nextInt(2);
			String s1 = s + "." + index;
			String s2 = s + ".attacker_" + index;
			return livingEntity != null ?  Component.translatable(s2, entityLivingBaseIn.getDisplayName(), livingEntity.getDisplayName()) :  Component.translatable(s1, entityLivingBaseIn.getDisplayName());
		}
		
	}

}
