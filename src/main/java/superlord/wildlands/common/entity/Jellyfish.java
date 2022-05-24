package superlord.wildlands.common.entity;

import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WLDamageSources;
import superlord.wildlands.init.WLEffects;
import superlord.wildlands.init.WLItems;
import superlord.wildlands.init.WLSounds;

public class Jellyfish extends WaterAnimal {
	public float jellyfishPitch;
	public float prevjellyfishPitch;
	public float jellyfishYaw;
	public float prevjellyfishYaw;
	public float jellyfishRotation;
	public float prevjellyfishRotation;
	private float randomMotionSpeed;
	private float rotationVelocity;
	private float rotateSpeed;
	private float randomMotionVecX;
	private float randomMotionVecY;
	private float randomMotionVecZ;
	public float tentacleAngle;
	public float lastTentacleAngle;

	private static final EntityDataAccessor<Integer> JELLYFISH_VARIANT = SynchedEntityData.defineId(Jellyfish.class, EntityDataSerializers.INT);
	static final TargetingConditions PREDICATE = TargetingConditions.forNonCombat().range(6.0D);

	public Jellyfish(EntityType<? extends WaterAnimal> type, Level p_i48565_2_) {
		super(type, p_i48565_2_);
		this.rotationVelocity = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new Jellyfish.MoveRandomGoal(this));
		this.goalSelector.addGoal(1, new Jellyfish.StingGoal(this));
	}

	protected SoundEvent getDeathSound() {
		return WLSounds.JELLYFISH_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WLSounds.JELLYFISH_HURT;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 19) {
			this.jellyfishRotation = 0.0F;
		} else {
			super.handleEntityEvent(id);
		}

	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(JELLYFISH_VARIANT, 0);
	}

	public int getJellyfishType() {
		return Mth.clamp(entityData.get(JELLYFISH_VARIANT), 0, 1);
	}

	public void setJellyfishVariant(int type) {
		entityData.set(JELLYFISH_VARIANT, type);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("JellyfishVariant", this.getJellyfishType());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setJellyfishVariant(compound.getInt("JellyfishVariant"));
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		Biome biome = worldIn.getBiome(this.blockPosition()).value();
		String name = biome.getRegistryName().getPath();
		if (name.equals("frozen_ocean") || name.equals("deep_frozen_ocean")) {
			setJellyfishVariant(0);
		} else {
			setJellyfishVariant(1);
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public void aiStep() {
		super.aiStep();
		this.prevjellyfishPitch = this.jellyfishPitch;
		this.prevjellyfishYaw = this.jellyfishYaw;
		this.prevjellyfishRotation = this.jellyfishRotation;
		this.jellyfishRotation += this.rotationVelocity;
		this.lastTentacleAngle = this.tentacleAngle;

		if ((double)this.jellyfishRotation > (Math.PI * 2D)) {
			if (this.level.isClientSide) {
				this.jellyfishRotation = ((float)Math.PI * 2F);
			} else {
				this.jellyfishRotation = (float)((double)this.jellyfishRotation - (Math.PI * 2D));
				if (this.random.nextInt(10) == 0) {
					this.rotationVelocity = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
				}

				this.level.broadcastEntityEvent(this, (byte)19);
			}
		}
		if (this.isInWaterOrBubble()) {
			if (this.jellyfishRotation < (float)Math.PI) {
				float f = this.jellyfishRotation / (float)Math.PI;
				this.tentacleAngle = Mth.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;
				if ((double)f > 0.75D) {
					this.randomMotionSpeed = 1.0F;
					this.rotateSpeed = 1.0F;
				} else {
					this.rotateSpeed *= 0.8F;
				}
			} else {
				this.tentacleAngle = 0.0F;
				this.randomMotionSpeed *= 0.9F;
				this.rotateSpeed *= 0.99F;
			}

			if (!this.level.isClientSide) {
				this.setDeltaMovement((double)(this.randomMotionVecX * this.randomMotionSpeed), (double)(this.randomMotionVecY * this.randomMotionSpeed), (double)(this.randomMotionVecZ * this.randomMotionSpeed));
			}

			Vec3 vector3d = this.getDeltaMovement();
			double f1 = vector3d.horizontalDistance();
			this.yBodyRot += (-((float)Mth.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI) - this.yBodyRot) * 0.1F;
			this.setYRot(this.yBodyRot);
			this.jellyfishYaw = (float)((double)this.jellyfishYaw + Math.PI * (double)this.rotateSpeed * 1.5D);
			this.jellyfishPitch += (-((float)Mth.atan2((double)f1, vector3d.y)) * (180F / (float)Math.PI) - this.jellyfishPitch) * 0.1F;
		} else {
			this.tentacleAngle = Mth.abs(Mth.sin(this.jellyfishRotation)) * (float)Math.PI * 0.25F;
			if (!this.level.isClientSide) {
				double d0 = this.getDeltaMovement().y;
				if (this.hasEffect(MobEffects.LEVITATION)) {
					d0 = 0.05D * (double)(this.getEffect(MobEffects.LEVITATION).getAmplifier() + 1);
				} else if (!this.isNoGravity()) {
					d0 -= 0.08D;
				}

				this.setDeltaMovement(0.0D, d0 * (double)0.98F, 0.0D);
			}

			this.jellyfishPitch = (float)((double)this.jellyfishPitch + (double)(-90.0F - this.jellyfishPitch) * 0.02D);
		}

	}


	public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn) {
		this.randomMotionVecX = randomMotionVecXIn;
		this.randomMotionVecY = randomMotionVecYIn;
		this.randomMotionVecZ = randomMotionVecZIn;
	}

	public boolean hasMovementVector() {
		return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
	}

	class MoveRandomGoal extends Goal {
		private final Jellyfish jellyfish;

		public MoveRandomGoal(Jellyfish p_i48823_2_) {
			this.jellyfish = p_i48823_2_;
		}

		public boolean canUse() {
			return true;
		}

		public void tick() {
			int i = this.jellyfish.getNoActionTime();
			if (i > 100) {
				this.jellyfish.setMovementVector(0.0F, 0.0F, 0.0F);
			} else if (this.jellyfish.getRandom().nextInt(50) == 0 || !this.jellyfish.isInWater() || !this.jellyfish.hasMovementVector()) {
				float f = this.jellyfish.getRandom().nextFloat() * ((float)Math.PI * 2F);
				float f1 = Mth.cos(f) * 0.2F;
				float f2 = -0.1F + this.jellyfish.getRandom().nextFloat() * 0.2F;
				float f3 = Mth.sin(f) * 0.2F;
				this.jellyfish.setMovementVector(f1, f2, f3);
			}

		}
	}

	public boolean hurt(DamageSource source, float amount) {
		if (source == WLDamageSources.STING) {
			return false;
		} else {
			return super.hurt(source, amount);
		}
	}

	static class StingGoal extends Goal {

		Jellyfish jellyfish;
		LivingEntity entity;

		StingGoal(Jellyfish jellyfish) {
			this.jellyfish = jellyfish;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			this.entity = this.jellyfish.level.getNearestEntity(LivingEntity.class, PREDICATE, this.jellyfish, 1, 1, 1, this.jellyfish.getBoundingBox());
			Turtle turtle = new Turtle(EntityType.TURTLE, jellyfish.level);
			if (entity == turtle || entity == jellyfish) {
				return false;
			} else if (entity != null) {
				return true;
			} else {
				return false;
			}
		}

		public boolean canContinueToUse() {
			Turtle turtle = new Turtle(EntityType.TURTLE, jellyfish.level);
			if (entity == null || entity == jellyfish || entity == turtle) {
				return false;
			} else {
				return true;
			}
		}

		public void start() {
			Turtle turtle = new Turtle(EntityType.TURTLE, jellyfish.level);
			if (entity == this.jellyfish || entity == turtle) {
				//This shouldn't do anyhting.
			} else {
				if (entity instanceof LivingEntity) {
					this.entity.addEffect(new MobEffectInstance(WLEffects.STING.get(), 100));
				}
			}
		}

		public void stop() {
			this.entity = null;
		}

	}

	public static boolean canSpawn(EntityType<? extends Jellyfish> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos p_223363_3_, Random randomIn) {
		return worldIn.getBlockState(p_223363_3_).is(Blocks.WATER) && worldIn.getBlockState(p_223363_3_.above()).is(Blocks.WATER);
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WLItems.JELLYFISH_SPAWN_EGG.get());
	}

}
