package superlord.wildlands.common.entity;

import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class CrabEntity extends Animal {

	private static final EntityDataAccessor<Boolean> FIGHTER = SynchedEntityData.defineId(CrabEntity.class, EntityDataSerializers.BOOLEAN);
	private boolean crabRave;
	private BlockPos jukebox;

	public CrabEntity(EntityType<? extends CrabEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new CrabEntity.AttackPlayerGoal());
		this.goalSelector.addGoal(1, new CrabEntity.CrabMeleeAttackGoal());
		this.goalSelector.addGoal(0, new CrabEntity.RunFromPlayerGoal());
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
	}

	public static boolean canCrabSpawn(EntityType<? extends Animal> animal, LevelAccessor levelIn, MobSpawnType reason, BlockPos pos, Random random) {
		return (levelIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || levelIn.getBlockState(pos.below()).is(net.minecraftforge.common.Tags.Blocks.SAND)) && isBrightEnoughToSpawn(levelIn, pos);
	}

	public boolean isFighter() {
		return this.entityData.get(FIGHTER);
	}

	private void setFighter(boolean isFighter) {
		this.entityData.set(FIGHTER, isFighter);
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		Random rand = new Random();
		int birthNumber = rand.nextInt(9);
		if (birthNumber == 0) {
			this.setFighter(true);
		}
		return super.finalizeSpawn(levelIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(FIGHTER, false);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("IsFighter", this.isFighter());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setFighter(compound.getBoolean("IsFighter"));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	public boolean doHurtTarget(Entity entityIn) {
		boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, entityIn);
		}
		return flag;
	}

	public void aiStep() {
		super.aiStep();
		if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level.getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
			this.crabRave = false;
			this.jukebox = null;
		}
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	@OnlyIn(Dist.CLIENT)
	public void setPartying(BlockPos pos, boolean isPartying) {
		this.jukebox = pos;
		this.crabRave = isPartying;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isPartying() {
		return this.crabRave;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return null;
	}

	protected SoundEvent getAmbientSound() {
		return WildLandsSounds.CRAB_IDLE;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.CRAB_HURT;
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.CRAB_DEATH;
	}

	class CrabMeleeAttackGoal extends MeleeAttackGoal {
		public CrabMeleeAttackGoal() {
			super(CrabEntity.this, 1.25D, true);
		}

		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d0 = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d0 && this.isTimeToAttack()) {
				this.resetAttackCooldown();
				this.mob.doHurtTarget(enemy);
			} else if (distToEnemySqr <= d0 * 2.0D) {
				if (this.isTimeToAttack()) {
					this.resetAttackCooldown();
				}
			} else {
				this.resetAttackCooldown();
			}

		}

		public void stop() {
			super.stop();
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double)(3.0F + attackTarget.getBbWidth());
		}
	}

	class AttackPlayerGoal extends NearestAttackableTargetGoal<Player> {

		public AttackPlayerGoal() {
			super(CrabEntity.this, Player.class, 20, true, true, (Predicate<LivingEntity>)null);
		}

		public boolean canUse() {
			this.target = CrabEntity.this.level.getNearestPlayer(CrabEntity.this, 50);
			if (target != null && CrabEntity.this.isFighter()) {
				double d0 = target.distanceToSqr(CrabEntity.this);
				return d0 > 256.0D ? false : this.shouldAttackOrFlee((Player)target);
			} else return false;
		}

		protected double getFollowDistance() {
			return super.getFollowDistance() * 0.5D;
		}

		private boolean shouldAttackOrFlee(Player player) {
			Vec3 vec3 = player.getViewVector(1.0F).normalize();
			Vec3 vec31 = new Vec3(CrabEntity.this.getX() - player.getX(), CrabEntity.this.getEyeY() - player.getEyeY(), CrabEntity.this.getZ() - player.getZ());
			double d0 = vec31.length();
			vec31 = vec31.normalize();
			double d1 = vec3.dot(vec31);
			return d1 > 1.0D - 0.025D / d0 ? player.hasLineOfSight(CrabEntity.this) : false;

		}
	}

	class RunFromPlayerGoal extends AvoidEntityGoal<Player> {
		public RunFromPlayerGoal() {
			super(CrabEntity.this, Player.class, 40, 1D, 1.3D);
		}

		public boolean canUse() {
			this.toAvoid = CrabEntity.this.level.getNearestPlayer(CrabEntity.this, 50);
			if (toAvoid != null && !CrabEntity.this.isFighter()) {
				double d0 = toAvoid.distanceToSqr(CrabEntity.this);
				return d0 > 256.0D ? false : (this.shouldAttackOrFlee(toAvoid) && super.canUse());
			} else return false;
		}

		public boolean canContinueToUse() {
			return !this.pathNav.isDone();
		}

		public void start() {
			this.pathNav.moveTo(this.path, 1.0D);
		}

		public void tick() {
			super.tick();
			if (this.mob.distanceToSqr(this.toAvoid) < 49.0D) {
				this.mob.getNavigation().setSpeedModifier(1.3D);
			} else {
				this.mob.getNavigation().setSpeedModifier(1.0D);
			}
		}

		private boolean shouldAttackOrFlee(Player player) {
			Vec3 vec3 = player.getViewVector(1.0F).normalize();
			Vec3 vec31 = new Vec3(CrabEntity.this.getX() - player.getX(), CrabEntity.this.getEyeY() - player.getEyeY(), CrabEntity.this.getZ() - player.getZ());
			double d0 = vec31.length();
			vec31 = vec31.normalize();
			double d1 = vec3.dot(vec31);
			return d1 > 1.0D - 0.025D / d0 ? player.hasLineOfSight(CrabEntity.this) : false;
		}
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WildLandsItems.CRAB_SPAWN_EGG.get());
	}
}