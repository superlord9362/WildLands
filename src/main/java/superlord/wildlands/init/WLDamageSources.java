package superlord.wildlands.init;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class WLDamageSources extends DamageSources {

	public WLDamageSources(RegistryAccess p_270740_) {
		super(p_270740_);
		this.sting = this.source(STING);
	}

	public static final ResourceKey<DamageType> CLAM = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("wildlands:clam"));
	public static final ResourceKey<DamageType> STING = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("wildlands:sting"));


	private final DamageSource sting;
	
	
	public DamageSource sting() {
		return this.sting;
	}

	static class DamageCustomDeathMessage extends DamageSource {

		public DamageCustomDeathMessage(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2, @Nullable Vec3 from) {
			super(damageTypeHolder, entity1, entity2, from);
		}

		public DamageCustomDeathMessage(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2) {
			super(damageTypeHolder, entity1, entity2);
		}

		public DamageCustomDeathMessage(Holder<DamageType> damageTypeHolder, Vec3 from) {
			super(damageTypeHolder, from);
		}

		public DamageCustomDeathMessage(Holder<DamageType> damageTypeHolder, @Nullable Entity entity) {
			super(damageTypeHolder, entity);
		}

		public DamageCustomDeathMessage(Holder<DamageType> p_270475_) {
			super(p_270475_);
		}

		public Component getDeathMessage(LivingEntity entityLivingBaseIn) {
			LivingEntity livingEntity = entityLivingBaseIn.getKillCredit();
			String s = "death.attack." + this.getMsgId();
			int index = entityLivingBaseIn.getRandom().nextInt(3);
			String s1 = s + "." + index;
			String s2 = s + ".attacker_" + index;
			return livingEntity != null ?  Component.translatable(s2, entityLivingBaseIn.getDisplayName(), livingEntity.getDisplayName()) :  Component.translatable(s1, entityLivingBaseIn.getDisplayName());
		}

	}

	static class DamageCustomDeathTwoMessage extends DamageSource {

		public DamageCustomDeathTwoMessage(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2, @Nullable Vec3 from) {
			super(damageTypeHolder, entity1, entity2, from);
		}

		public DamageCustomDeathTwoMessage(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2) {
			super(damageTypeHolder, entity1, entity2);
		}

		public DamageCustomDeathTwoMessage(Holder<DamageType> damageTypeHolder, Vec3 from) {
			super(damageTypeHolder, from);
		}

		public DamageCustomDeathTwoMessage(Holder<DamageType> damageTypeHolder, @Nullable Entity entity) {
			super(damageTypeHolder, entity);
		}

		public DamageCustomDeathTwoMessage(Holder<DamageType> p_270475_) {
			super(p_270475_);
		}

		public Component getDeathMessage(LivingEntity entityLivingBaseIn) {
			LivingEntity livingEntity = entityLivingBaseIn.getKillCredit();
			String s = "death.attack." + this.getMsgId();
			int index = entityLivingBaseIn.getRandom().nextInt(2);
			String s1 = s + "." + index;
			String s2 = s + ".attacker_" + index;
			return livingEntity != null ?  Component.translatable(s2, entityLivingBaseIn.getDisplayName(), livingEntity.getDisplayName()) :  Component.translatable(s1, entityLivingBaseIn.getDisplayName());
		}

	}

}
