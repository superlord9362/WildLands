package superlord.wildlands.common.entity;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.DamageSourceInit;
import superlord.wildlands.init.WildLandsEffects;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class JellyfishEntity extends WaterMobEntity {
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

	private static final EntityPredicate predicate = (new EntityPredicate()).setDistance(6.0D).allowFriendlyFire().allowInvulnerable();
	private static final DataParameter<Integer> JELLYFISH_VARIANT = EntityDataManager.createKey(JellyfishEntity.class, DataSerializers.VARINT);

	public JellyfishEntity(EntityType<? extends WaterMobEntity> type, World p_i48565_2_) {
		super(type, p_i48565_2_);
		this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new JellyfishEntity.MoveRandomGoal(this));
		this.goalSelector.addGoal(1, new JellyfishEntity.StingGoal(this));
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.JELLYFISH_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.JELLYFISH_HURT;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 19) {
			this.jellyfishRotation = 0.0F;
		} else {
			super.handleStatusUpdate(id);
		}

	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(JELLYFISH_VARIANT, 0);
	}

	public int getJellyfishType() {
		return MathHelper.clamp(dataManager.get(JELLYFISH_VARIANT), 0, 1);
	}

	public void setJellyfishVariant(int type) {
		dataManager.set(JELLYFISH_VARIANT, type);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("JellyfishVariant", this.getJellyfishType());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setJellyfishVariant(compound.getInt("JellyfishVariant"));
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		Optional<RegistryKey<Biome>> biome = worldIn.func_242406_i(this.getPosition());
		if (Objects.equals(biome, Optional.of(Biomes.FROZEN_OCEAN)) || Objects.equals(biome, Optional.of(Biomes.DEEP_FROZEN_OCEAN))) {
			setJellyfishVariant(0);
		} else {
			setJellyfishVariant(1);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public void livingTick() {
		super.livingTick();
		this.prevjellyfishPitch = this.jellyfishPitch;
		this.prevjellyfishYaw = this.jellyfishYaw;
		this.prevjellyfishRotation = this.jellyfishRotation;
		this.jellyfishRotation += this.rotationVelocity;
		this.lastTentacleAngle = this.tentacleAngle;

		if ((double)this.jellyfishRotation > (Math.PI * 2D)) {
			if (this.world.isRemote) {
				this.jellyfishRotation = ((float)Math.PI * 2F);
			} else {
				this.jellyfishRotation = (float)((double)this.jellyfishRotation - (Math.PI * 2D));
				if (this.rand.nextInt(10) == 0) {
					this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
				}

				this.world.setEntityState(this, (byte)19);
			}
		}
		if (this.isInWaterOrBubbleColumn()) {
			if (this.jellyfishRotation < (float)Math.PI) {
				float f = this.jellyfishRotation / (float)Math.PI;
				this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;
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

			if (!this.world.isRemote) {
				this.setMotion((double)(this.randomMotionVecX * this.randomMotionSpeed), (double)(this.randomMotionVecY * this.randomMotionSpeed), (double)(this.randomMotionVecZ * this.randomMotionSpeed));
			}

			Vector3d vector3d = this.getMotion();
			float f1 = MathHelper.sqrt(horizontalMag(vector3d));
			this.renderYawOffset += (-((float)MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI) - this.renderYawOffset) * 0.1F;
			this.rotationYaw = this.renderYawOffset;
			this.jellyfishYaw = (float)((double)this.jellyfishYaw + Math.PI * (double)this.rotateSpeed * 1.5D);
			this.jellyfishPitch += (-((float)MathHelper.atan2((double)f1, vector3d.y)) * (180F / (float)Math.PI) - this.jellyfishPitch) * 0.1F;
		} else {
			this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.jellyfishRotation)) * (float)Math.PI * 0.25F;
			if (!this.world.isRemote) {
				double d0 = this.getMotion().y;
				if (this.isPotionActive(Effects.LEVITATION)) {
					d0 = 0.05D * (double)(this.getActivePotionEffect(Effects.LEVITATION).getAmplifier() + 1);
				} else if (!this.hasNoGravity()) {
					d0 -= 0.08D;
				}

				this.setMotion(0.0D, d0 * (double)0.98F, 0.0D);
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
		private final JellyfishEntity jellyfish;

		public MoveRandomGoal(JellyfishEntity p_i48823_2_) {
			this.jellyfish = p_i48823_2_;
		}

		public boolean shouldExecute() {
			return true;
		}

		public void tick() {
			int i = this.jellyfish.getIdleTime();
			if (i > 100) {
				this.jellyfish.setMovementVector(0.0F, 0.0F, 0.0F);
			} else if (this.jellyfish.getRNG().nextInt(50) == 0 || !this.jellyfish.inWater || !this.jellyfish.hasMovementVector()) {
				float f = this.jellyfish.getRNG().nextFloat() * ((float)Math.PI * 2F);
				float f1 = MathHelper.cos(f) * 0.2F;
				float f2 = -0.1F + this.jellyfish.getRNG().nextFloat() * 0.2F;
				float f3 = MathHelper.sin(f) * 0.2F;
				this.jellyfish.setMovementVector(f1, f2, f3);
			}

		}
	}

	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSourceInit.STING) {
			return false;
		} else {
			return true;
		}
	}

	static class StingGoal extends Goal {

		JellyfishEntity jellyfish;
		LivingEntity entity;

		StingGoal(JellyfishEntity jellyfish) {
			this.jellyfish = jellyfish;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean shouldExecute() {
			this.entity = this.jellyfish.world.getClosestEntityWithinAABB(LivingEntity.class, predicate, this.jellyfish, 1, 1, 1, this.jellyfish.getBoundingBox());
			TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, jellyfish.world);
			if (entity == turtle || entity == jellyfish) {
				return false;
			} else if (entity != null) {
				return true;
			} else {
				return false;
			}
		}

		public boolean shouldContinueExecuting() {
			TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, jellyfish.world);
			if (entity == null || entity == jellyfish || entity == turtle) {
				return false;
			} else {
				return true;
			}
		}

		public void startExecuting() {
			TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, jellyfish.world);
			if (entity == this.jellyfish || entity == turtle) {
				//This shouldn't do anyhting.
			} else {
				this.entity.addPotionEffect(new EffectInstance(WildLandsEffects.STING.get(), 100));
			}
		}

		public void resetTask() {
			this.entity = null;
		}

	}

	public static boolean canSpawn(EntityType<? extends JellyfishEntity> type, IWorld worldIn, SpawnReason reason, BlockPos p_223363_3_, Random randomIn) {
		return worldIn.getBlockState(p_223363_3_).isIn(Blocks.WATER) && worldIn.getBlockState(p_223363_3_.up()).isIn(Blocks.WATER);
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(WildLandsItems.JELLYFISH_SPAWN_EGG.get());
	}

}
