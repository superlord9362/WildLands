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
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import superlord.wildlands.init.WLItems;
import superlord.wildlands.init.WLSounds;

public class Crab extends Animal {

	private static final EntityDataAccessor<Boolean> FIGHTER = SynchedEntityData.defineId(Crab.class, EntityDataSerializers.BOOLEAN);
	private boolean crabRave;
	private BlockPos jukebox;

	@SuppressWarnings("deprecation")
	public Crab(EntityType<? extends Crab> type, Level worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
		this.maxUpStep = 1.0F;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new Crab.AttackPlayerGoal());
		this.goalSelector.addGoal(1, new Crab.CrabMeleeAttackGoal());
		this.goalSelector.addGoal(0, new Crab.RunFromPlayerGoal());
		this.goalSelector.addGoal(0, new Crab.CrabPanicGoal(this, 1.2D));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
	}

	public static boolean canCrabSpawn(EntityType<? extends Animal> animal, LevelAccessor levelIn, MobSpawnType reason, BlockPos pos, RandomSource random) {
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
		if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level.getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
			this.crabRave = false;
			this.jukebox = null;
		}
		super.aiStep();
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public void setRecordPlayingNearby(BlockPos p_29395_, boolean p_29396_) {
		this.jukebox = p_29395_;
		this.crabRave = p_29396_;
	}

	public boolean isCrabRave() {
		return this.crabRave;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		return null;
	}

	protected SoundEvent getAmbientSound() {
		return WLSounds.CRAB_IDLE.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WLSounds.CRAB_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return WLSounds.CRAB_DEATH.get();
	}

	class CrabMeleeAttackGoal extends MeleeAttackGoal {
		public CrabMeleeAttackGoal() {
			super(Crab.this, 1.25D, true);
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
			super(Crab.this, Player.class, 20, true, true, (Predicate<LivingEntity>)null);
		}

		public boolean canUse() {
			this.target = Crab.this.level.getNearestPlayer(Crab.this, 50);
			if (target != null && Crab.this.isFighter()) {
				double d0 = target.distanceToSqr(Crab.this);
				return d0 > 256.0D ? false : this.shouldAttackOrFlee((Player)target);
			} else return false;
		}

		protected double getFollowDistance() {
			return super.getFollowDistance() * 0.5D;
		}

		private boolean shouldAttackOrFlee(Player player) {
			Vec3 vec3 = player.getViewVector(1.0F).normalize();
			Vec3 vec31 = new Vec3(Crab.this.getX() - player.getX(), Crab.this.getEyeY() - player.getEyeY(), Crab.this.getZ() - player.getZ());
			double d0 = vec31.length();
			vec31 = vec31.normalize();
			double d1 = vec3.dot(vec31);
			return d1 > 1.0D - 0.025D / d0 ? player.hasLineOfSight(Crab.this) : false;

		}
	}

	class RunFromPlayerGoal extends AvoidEntityGoal<Player> {
		public RunFromPlayerGoal() {
			super(Crab.this, Player.class, 40, 1D, 1.3D);
		}

		public boolean canUse() {
			this.toAvoid = Crab.this.level.getNearestPlayer(Crab.this, 50);
			if (toAvoid != null && !Crab.this.isFighter()) {
				double d0 = toAvoid.distanceToSqr(Crab.this);
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
			Vec3 vec31 = new Vec3(Crab.this.getX() - player.getX(), Crab.this.getEyeY() - player.getEyeY(), Crab.this.getZ() - player.getZ());
			double d0 = vec31.length();
			vec31 = vec31.normalize();
			double d1 = vec3.dot(vec31);
			return d1 > 1.0D - 0.025D / d0 ? player.hasLineOfSight(Crab.this) : false;
		}
	}
	
	class CrabPanicGoal extends PanicGoal {
		Crab crab;
		
		public CrabPanicGoal(Crab crab, double p_25692_) {
			super(crab, p_25692_);
			this.crab = crab;
		}
		
		public boolean canUse() {
			return super.canUse() && !crab.isFighter();
		}
		
		public boolean canContinueToUse() {
			return super.canContinueToUse();
		}
		
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WLItems.CRAB_SPAWN_EGG.get());
	}
}