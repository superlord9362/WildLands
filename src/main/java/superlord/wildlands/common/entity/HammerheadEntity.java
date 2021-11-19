package superlord.wildlands.common.entity;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HammerheadEntity extends WaterMobEntity {

	private HammerheadEntity.AttackPhase attackPhase = HammerheadEntity.AttackPhase.CIRCLE;
	private Vector3d orbitOffset = Vector3d.ZERO;
	private BlockPos orbitPosition = BlockPos.ZERO;

	public HammerheadEntity(EntityType<? extends HammerheadEntity> type, World world) {
		super(type, world);
		this.moveController = new HammerheadEntity.MoveHelperController(this);
		this.lookController = new DolphinLookController(this, 10);
	}

	public int getVerticalFaceSpeed() {
		return 1;
	}

	public int getHorizontalFaceSpeed() {
		return 1;
	}

	protected void registerGoals() {
		/**
		this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double)1.2F, true));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, PlayerEntity.class)).setCallsForHelp());
		 */
		this.goalSelector.addGoal(1, new HammerheadEntity.PickAttackGoal());
		this.goalSelector.addGoal(2, new HammerheadEntity.SweepAttackGoal());
		this.goalSelector.addGoal(3, new HammerheadEntity.OrbitPointGoal());
		this.targetSelector.addGoal(1, new HammerheadEntity.AttackPlayerGoal());
	}

	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 60.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.25F).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.applyEnchantments(this, entityIn);
		}
		return flag;
	}

	@SuppressWarnings("deprecation")
	public static boolean func_223364_b(EntityType<HammerheadEntity> p_223364_0_, IWorld p_223364_1_, SpawnReason reason, BlockPos p_223364_3_, Random p_223364_4_) {
		if (p_223364_3_.getY() > 45 && p_223364_3_.getY() < p_223364_1_.getSeaLevel()) {
			Optional<RegistryKey<Biome>> optional = p_223364_1_.func_242406_i(p_223364_3_);
			return (!Objects.equals(optional, Optional.of(Biomes.OCEAN)) || !Objects.equals(optional, Optional.of(Biomes.DEEP_OCEAN))) && p_223364_1_.getFluidState(p_223364_3_).isTagged(FluidTags.WATER);
		} else {
			return false;
		}
	}

	public void tick() {
		super.tick();
		if (this.isAIDisabled()) {
			this.setAir(this.getMaxAir());
		} else {
			if (this.onGround) {
				this.setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F)));
				this.rotationYaw = this.rand.nextFloat() * 360.0F;
				this.onGround = false;
				this.isAirBorne = true;
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void func_208401_a(IParticleData p_208401_1_) {
		for(int i = 0; i < 7; ++i) {
			double d0 = this.rand.nextGaussian() * 0.01D;
			double d1 = this.rand.nextGaussian() * 0.01D;
			double d2 = this.rand.nextGaussian() * 0.01D;
			this.world.addParticle(p_208401_1_, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.2D, this.getPosZRandom(1.0D), d0, d1, d2);
		}
	}

	protected boolean closeToTarget() {
		BlockPos blockpos = this.getNavigator().getTargetPos();
		return blockpos != null ? blockpos.withinDistance(this.getPositionVec(), 12.0D) : false;
	}

	public void travel(Vector3d travelVector) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(this.getAIMoveSpeed(), travelVector);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (this.getAttackTarget() == null) {
				this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(travelVector);
		}
	}

	static class MoveHelperController extends MovementController {
		private final HammerheadEntity hammerhead;

		public MoveHelperController(HammerheadEntity hammerhead) {
			super(hammerhead);
			this.hammerhead = hammerhead;
		}

		public void tick() {
			if (this.hammerhead.isInWater()) {
				this.hammerhead.setMotion(this.hammerhead.getMotion().add(0.0D, 0.005D, 0.0D));
			}
			if (this.action == MovementController.Action.MOVE_TO && !this.hammerhead.getNavigator().noPath()) {
				double d0 = this.posX - this.hammerhead.getPosX();
				double d1 = this.posY - this.hammerhead.getPosY();
				double d2 = this.posZ - this.hammerhead.getPosZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double)2.5000003E-7F) {
					this.mob.setMoveForward(0.0F);
				} else {
					float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
					this.hammerhead.rotationYaw = this.limitAngle(this.hammerhead.rotationYaw, f, 10.0F);
					this.hammerhead.renderYawOffset = this.hammerhead.rotationYaw;
					this.hammerhead.rotationYawHead = this.hammerhead.rotationYaw;
					float f1 = (float)( this.hammerhead.getAttributeValue(Attributes.MOVEMENT_SPEED));
					if (this.hammerhead.isInWater()) {
						this.hammerhead.setAIMoveSpeed(f1);
						float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.hammerhead.rotationPitch = this.limitAngle(this.hammerhead.rotationPitch, f2, 5.0F);
						float f3 = MathHelper.cos(this.hammerhead.rotationPitch * ((float)Math.PI / 180F));
						float f4 = MathHelper.sin(this.hammerhead.rotationPitch * ((float)Math.PI / 180F));
						this.hammerhead.moveForward = f3 * f1;
						this.hammerhead.moveVertical = -f4 * f1;
					} else {
						this.hammerhead.setAIMoveSpeed(f1);
					}
				}
			} else {
				this.hammerhead.setAIMoveSpeed(0.0F);
				this.hammerhead.setMoveStrafing(0.0F);
				this.hammerhead.setMoveVertical(0.0F);
				this.hammerhead.setMoveForward(0.0F);
			}
		}
	}

	static enum AttackPhase {
		CIRCLE,
		SWOOP;
	}

	class AttackPlayerGoal extends Goal {
		private final EntityPredicate field_220842_b = (new EntityPredicate()).setDistance(64.0D);
		private int tickDelay = 20;

		private AttackPlayerGoal() {
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			if (this.tickDelay > 0) {
				--this.tickDelay;
				return false;
			} else {
				this.tickDelay = 60;
				List<PlayerEntity> list = HammerheadEntity.this.world.getTargettablePlayersWithinAABB(this.field_220842_b, HammerheadEntity.this, HammerheadEntity.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
				if (!list.isEmpty()) {
					list.sort(Comparator.<Entity, Double>comparing(Entity::getPosY).reversed());

					for(PlayerEntity playerentity : list) {
						if (HammerheadEntity.this.canAttack(playerentity, EntityPredicate.DEFAULT) && HammerheadEntity.this.getRevengeTarget() == playerentity) {
							HammerheadEntity.this.setAttackTarget(playerentity);
							return true;
						}
					}
				}

				return false;
			}
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting() {
			LivingEntity livingentity = HammerheadEntity.this.getAttackTarget();
			return livingentity != null ? HammerheadEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT) : false;
		}
	}

	class AttackUndeadGoal extends Goal {
		private final EntityPredicate field_220842_b = (new EntityPredicate()).setDistance(64.0D);
		private int tickDelay = 20;

		private AttackUndeadGoal() {
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			if (this.tickDelay > 0) {
				--this.tickDelay;
				return false;
			} else {
				this.tickDelay = 60;
				List<PlayerEntity> list = HammerheadEntity.this.world.getTargettablePlayersWithinAABB(this.field_220842_b, HammerheadEntity.this, HammerheadEntity.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
				if (!list.isEmpty()) {
					list.sort(Comparator.<Entity, Double>comparing(Entity::getPosY).reversed());

					for(LivingEntity entity : list) {
						if (HammerheadEntity.this.canAttack(entity, EntityPredicate.DEFAULT) && entity.getCreatureAttribute() == CreatureAttribute.UNDEAD) {
							HammerheadEntity.this.setAttackTarget(entity);
							return true;
						}
					}
				}

				return false;
			}
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting() {
			LivingEntity livingentity = HammerheadEntity.this.getAttackTarget();
			return livingentity != null ? HammerheadEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT) : false;
		}
	}

	class PickAttackGoal extends Goal {
		private int tickDelay;

		private PickAttackGoal() {
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			LivingEntity livingentity = HammerheadEntity.this.getAttackTarget();
			return livingentity != null ? HammerheadEntity.this.canAttack(HammerheadEntity.this.getAttackTarget(), EntityPredicate.DEFAULT) : false;
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			this.tickDelay = 10;
			HammerheadEntity.this.attackPhase = HammerheadEntity.AttackPhase.CIRCLE;
			this.func_203143_f();
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void resetTask() {
			HammerheadEntity.this.orbitPosition = HammerheadEntity.this.world.getHeight(Heightmap.Type.MOTION_BLOCKING, HammerheadEntity.this.orbitPosition).up(10 + HammerheadEntity.this.rand.nextInt(20));
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (HammerheadEntity.this.attackPhase == HammerheadEntity.AttackPhase.CIRCLE) {
				--this.tickDelay;
				if (this.tickDelay <= 0) {
					HammerheadEntity.this.attackPhase = HammerheadEntity.AttackPhase.SWOOP;
					this.func_203143_f();
					this.tickDelay = (8 + HammerheadEntity.this.rand.nextInt(4)) * 20;
					HammerheadEntity.this.playSound(SoundEvents.ENTITY_PHANTOM_SWOOP, 10.0F, 0.95F + HammerheadEntity.this.rand.nextFloat() * 0.1F);
				}
			}

		}

		private void func_203143_f() {
			HammerheadEntity.this.orbitPosition = HammerheadEntity.this.getAttackTarget().getPosition().up(20 + HammerheadEntity.this.rand.nextInt(20));
			if (HammerheadEntity.this.orbitPosition.getY() < HammerheadEntity.this.world.getSeaLevel()) {
				HammerheadEntity.this.orbitPosition = new BlockPos(HammerheadEntity.this.orbitPosition.getX(), HammerheadEntity.this.world.getSeaLevel() + 1, HammerheadEntity.this.orbitPosition.getZ());
			}

		}
	}

	abstract class MoveGoal extends Goal {
		public MoveGoal() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		protected boolean func_203146_f() {
			return HammerheadEntity.this.orbitOffset.squareDistanceTo(HammerheadEntity.this.getPosX(), HammerheadEntity.this.getPosY(), HammerheadEntity.this.getPosZ()) < 4.0D;
		}
	}


	class SweepAttackGoal extends HammerheadEntity.MoveGoal {
		private SweepAttackGoal() {
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			return HammerheadEntity.this.getAttackTarget() != null && HammerheadEntity.this.attackPhase == HammerheadEntity.AttackPhase.SWOOP;
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting() {
			LivingEntity livingentity = HammerheadEntity.this.getAttackTarget();
			if (livingentity == null) {
				return false;
			} else if (!livingentity.isAlive()) {
				return false;
			} else if (!(livingentity instanceof PlayerEntity) || !((PlayerEntity)livingentity).isSpectator() && !((PlayerEntity)livingentity).isCreative()) {
				if (!this.shouldExecute()) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void resetTask() {
			HammerheadEntity.this.setAttackTarget((LivingEntity)null);
			HammerheadEntity.this.attackPhase = HammerheadEntity.AttackPhase.CIRCLE;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			LivingEntity livingentity = HammerheadEntity.this.getAttackTarget();
			HammerheadEntity.this.orbitOffset = new Vector3d(livingentity.getPosX(), livingentity.getPosYHeight(0.5D), livingentity.getPosZ());
			if (HammerheadEntity.this.getBoundingBox().grow((double)0.2F).intersects(livingentity.getBoundingBox())) {
				HammerheadEntity.this.attackEntityAsMob(livingentity);
				HammerheadEntity.this.attackPhase = HammerheadEntity.AttackPhase.CIRCLE;
				if (!HammerheadEntity.this.isSilent()) {
					HammerheadEntity.this.world.playEvent(1039, HammerheadEntity.this.getPosition(), 0);
				}
			} else if (HammerheadEntity.this.collidedHorizontally || HammerheadEntity.this.hurtTime > 0) {
				HammerheadEntity.this.attackPhase = HammerheadEntity.AttackPhase.CIRCLE;
			}

		}
	}

	class OrbitPointGoal extends HammerheadEntity.MoveGoal {
		private float field_203150_c;
		private float field_203151_d;
		private float field_203152_e;
		private float field_203153_f;

		private OrbitPointGoal() {
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			return HammerheadEntity.this.getAttackTarget() == null || HammerheadEntity.this.attackPhase == HammerheadEntity.AttackPhase.CIRCLE;
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			this.field_203151_d = 5.0F + HammerheadEntity.this.rand.nextFloat() * 10.0F;
			this.field_203152_e = -4.0F + HammerheadEntity.this.rand.nextFloat() * 9.0F;
			this.field_203153_f = HammerheadEntity.this.rand.nextBoolean() ? 1.0F : -1.0F;
			this.func_203148_i();
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (HammerheadEntity.this.rand.nextInt(350) == 0) {
				this.field_203152_e = -4.0F + HammerheadEntity.this.rand.nextFloat() * 9.0F;
			}

			if (HammerheadEntity.this.rand.nextInt(250) == 0) {
				++this.field_203151_d;
				if (this.field_203151_d > 15.0F) {
					this.field_203151_d = 5.0F;
					this.field_203153_f = -this.field_203153_f;
				}
			}

			if (HammerheadEntity.this.rand.nextInt(450) == 0) {
				this.field_203150_c = HammerheadEntity.this.rand.nextFloat() * 2.0F * (float)Math.PI;
				this.func_203148_i();
			}

			if (this.func_203146_f()) {
				this.func_203148_i();
			}

			if (HammerheadEntity.this.orbitOffset.y < HammerheadEntity.this.getPosY() && HammerheadEntity.this.world.getBlockState(HammerheadEntity.this.getPosition().down(1)).getBlock() != Blocks.WATER) {
				this.field_203152_e = Math.max(1.0F, this.field_203152_e);
				this.func_203148_i();
			}

			if (HammerheadEntity.this.orbitOffset.y > HammerheadEntity.this.getPosY() && HammerheadEntity.this.world.getBlockState(HammerheadEntity.this.getPosition().up(1)).getBlock() != Blocks.WATER) {
				this.field_203152_e = Math.min(-1.0F, this.field_203152_e);
				this.func_203148_i();
			}

		}

		private void func_203148_i() {
			if (BlockPos.ZERO.equals(HammerheadEntity.this.orbitPosition)) {
				HammerheadEntity.this.orbitPosition = HammerheadEntity.this.getPosition();
			}

			this.field_203150_c += this.field_203153_f * 15.0F * ((float)Math.PI / 180F);
			HammerheadEntity.this.orbitOffset = Vector3d.copy(HammerheadEntity.this.orbitPosition).add((double)(this.field_203151_d * MathHelper.cos(this.field_203150_c)), (double)(-4.0F + this.field_203152_e), (double)(this.field_203151_d * MathHelper.sin(this.field_203150_c)));
		}
	}


}
