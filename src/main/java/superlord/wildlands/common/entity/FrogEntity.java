package superlord.wildlands.common.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class FrogEntity extends Animal {
	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int jumpDelayTicks;
	private static final EntityDataAccessor<Integer> MOISTNESS = SynchedEntityData.defineId(FrogEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(FrogEntity.class, EntityDataSerializers.BOOLEAN);

	public FrogEntity(EntityType<? extends FrogEntity> p_i50247_1_, Level p_i50247_2_) {
		super(p_i50247_1_, p_i50247_2_);
		this.jumpControl = new FrogEntity.JumpHelperController(this);
		this.moveControl = new FrogEntity.MoveHelperController(this);
		this.setSpeedModifier(0.0D);
	}

	public int getMoistness() {
		return this.entityData.get(MOISTNESS);
	}

	public void setMoistness(int p_211137_1_) {
		this.entityData.set(MOISTNESS, p_211137_1_);
	}

	private boolean isFromBucket() {
		return this.entityData.get(FROM_BUCKET);
	}

	public void setFromBucket(boolean p_203706_1_) {
		this.entityData.set(FROM_BUCKET, p_203706_1_);
	}

	protected SoundEvent getAmbientSound() {
		return WildLandsSounds.FROG_IDLE;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.FROG_HURT;
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.FROG_DEATH;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FrogEntity.FrogPanicGoal(this, 2.2D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new FrogEntity.AdultWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
		this.goalSelector.addGoal(4, new FrogEntity.FrogFindWaterGoal(this));
		this.goalSelector.addGoal(4, new FrogEntity.SitOnLilypadGoal(1, 5, 5));
		this.goalSelector.addGoal(4, new FrogEntity.SwimGoal(this));
		this.goalSelector.addGoal(4, new FrogEntity.GoToWaterGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, AlligatorEntity.class, 8.0F, 2.2D, 2.2D));
	}

	protected float getJumpPower() {
		if (!this.horizontalCollision && (!this.moveControl.hasWanted() || !(this.moveControl.getWantedY() > this.getY() + 0.5D))) {
			Path path = this.navigation.getPath();
			if (path != null && !path.isDone()) {
				Vec3 vec3 = path.getNextEntityPos(this);
				if (vec3.y > this.getY() + 0.5D) {
					return 0.5F;
				}
			}

			return this.moveControl.getSpeedModifier() <= 0.6D ? 0.2F : 0.3F;
		} else {
			return 0.5F;
		}
	}

	static class SwimGoal extends RandomSwimmingGoal {

		public SwimGoal(FrogEntity frog) {
			super(frog, 1.0D, 40);
		}

		public boolean canUse() {
			return super.canUse();
		}
	}

	/**
	 * Causes this entity to do an upwards motion (jumping).
	 */
	protected void jumpFromGround() {
		super.jumpFromGround();
		double d0 = this.moveControl.getSpeedModifier();
		if (d0 > 0.0D) {
			double d1 = this.getDeltaMovement().horizontalDistanceSqr();
			if (d1 < 0.01D) {
				this.moveRelative(0.1F, new Vec3(0.0D, 0.0D, 1.0D));
			}
		}

		if (!this.level.isClientSide) {
			this.level.broadcastEntityEvent(this, (byte)1);
		}

	}

	public float getJumpCompletion(float p_29736_) {
		return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_29736_) / (float)this.jumpDuration;
	}

	public void setSpeedModifier(double p_29726_) {
		this.getNavigation().setSpeedModifier(p_29726_);
		this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), p_29726_);
	}

	public void setJumping(boolean p_29732_) {
		super.setJumping(p_29732_);
	}

	public void startJumping() {
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}

	public void customServerAiStep() {
		if (this.jumpDelayTicks > 0) {
			--this.jumpDelayTicks;
		}

		if (this.onGround) {
			if (!this.wasOnGround) {
				this.setJumping(false);
				this.checkLandingDelay();
			}

			FrogEntity.JumpHelperController frogentity$jumphelpercontroller = (FrogEntity.JumpHelperController)this.jumpControl;
			if (!frogentity$jumphelpercontroller.wantJump()) {
				if (this.moveControl.hasWanted() && this.jumpDelayTicks == 0) {
					Path path = this.navigation.getPath();
					Vec3 vector3d = new Vec3(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
					if (path != null && !path.isDone()) {
						vector3d = path.getNextEntityPos(this);
					}

					this.facePoint(vector3d.x, vector3d.z);
					this.startJumping();
				}
			} else if (!frogentity$jumphelpercontroller.canJump()) {
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MOISTNESS, 2400);
		this.entityData.define(FROM_BUCKET, false);
	}

	public boolean shouldSpawnRunningEffects() {
		return false;
	}

	private void facePoint(double p_29687_, double p_29688_) {
		this.setYRot((float)(Mth.atan2(p_29688_ - this.getZ(), p_29687_ - this.getX()) * (double)(180F / (float)Math.PI)) - 90.0F);
	}

	private void enableJumpControl() {
		((FrogEntity.JumpHelperController)this.jumpControl).setCanJump(true);
	}

	private void disableJumpControl() {
		((FrogEntity.JumpHelperController)this.jumpControl).setCanJump(false);
	}

	private void setLandingDelay() {
		if (this.moveControl.getSpeedModifier() < 2.2D) {
			this.jumpDelayTicks = 10;
		} else {
			this.jumpDelayTicks = 1;
		}

	}

	private void checkLandingDelay() {
		this.setLandingDelay();
		this.disableJumpControl();
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void aiStep() {
		super.aiStep();
		if (this.jumpTicks != this.jumpDuration) {
			++this.jumpTicks;
		} else if (this.jumpDuration != 0) {
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}

	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3F);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("Moistness", this.getMoistness());
		compound.putBoolean("FromBucket", this.isFromBucket());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setMoistness(compound.getInt("Moistness"));
		this.setFromBucket(compound.getBoolean("FromBucket"));
	}

	private boolean isFrogBreedingItem(Item itemIn) {
		return itemIn == Items.SPIDER_EYE;
	}

	public boolean isBreedingItem(ItemStack stack) {
		return this.isFrogBreedingItem(stack.getItem());
	}

	public class JumpHelperController extends JumpControl {
		private final FrogEntity frog;
		private boolean canJump;

		public JumpHelperController(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public boolean wantJump() {
			return this.jump;
		}

		public boolean canJump() {
			return this.canJump;
		}

		public void setCanJump(boolean p_29759_) {
			this.canJump = p_29759_;
		}

		public void tick() {
			if (!frog.isBaby()) {
				if (this.jump) {
					this.frog.startJumping();
					this.jump = false;
				}
			}
		}
	}

	static class MoveHelperController extends MoveControl {
		private final FrogEntity frog;
		private double nextJumpSpeed;

		public MoveHelperController(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public void tick() {
			if (!frog.isBaby()) {
				if (this.frog.onGround && !this.frog.jumping && !((FrogEntity.JumpHelperController)this.frog.jumpControl).wantJump()) {
					this.frog.setSpeedModifier(0.0D);
				} else if (this.hasWanted()) {
					this.frog.setSpeedModifier(this.nextJumpSpeed);
				}
			} else {
				if (this.frog.isInWater()) {
					this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
				}

				if (this.operation == MoveControl.Operation.MOVE_TO && !this.frog.getNavigation().isDone()) {
					float f = (float)(this.frog.getAttributeValue(Attributes.MOVEMENT_SPEED));
					this.frog.setSpeed(Mth.lerp(0.125F, this.frog.getSpeed(), f));
					double d0 = this.wantedX - this.frog.getX();
					double d1 = this.wantedY - this.frog.getY();
					double d2 = this.wantedZ - this.frog.getZ();
					if (d1 != 0.0D) {
						double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
						this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, (double)this.frog.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
					}

					if (d0 != 0.0D || d2 != 0.0D) {
						float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
						this.frog.setYRot(this.rotlerp(this.frog.getYRot(), f1, 90.0F));
						this.frog.yBodyRot = this.frog.getYRot();
					}
				} else {
					this.frog.setSpeed(0.0F);
				}
			}
			super.tick();
		}

		/**
		 * Sets the speed and location to move to
		 */
		public void setWantedPosition(double x, double y, double z, double speedIn) {
			if (this.frog.isInWater()) {
				speedIn = 1.5D;
			}

			super.setWantedPosition(x, y, z, speedIn);
			if (speedIn > 0.0D) {
				this.nextJumpSpeed = speedIn;
			}

		}
	}

	static class FrogPanicGoal extends PanicGoal {
		private final FrogEntity frog;
		double speed;

		public FrogPanicGoal(FrogEntity frog, double speedIn) {
			super(frog, speedIn);
			this.frog = frog;
			this.speed = speedIn;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			super.tick();
			this.frog.setSpeed((float)speed);
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mobe) {
		FrogEntity entity = new FrogEntity(WildLandsEntities.FROG.get(), this.level);
		entity.finalizeSpawn(level, this.level.getCurrentDifficultyAt(new BlockPos(entity.getEyePosition())), MobSpawnType.BREEDING, (SpawnGroupData)null, (CompoundTag)null);
		return entity;
	}

	static class FrogFindWaterGoal extends TryFindWaterGoal {

		FrogEntity frog;

		public FrogFindWaterGoal(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public boolean canUse() {
			return super.canUse() && (frog.getMoistness() <= 500);
		}

	}

	public class SitOnLilypadGoal extends MoveToBlockGoal {
		protected int field_220731_g;

		public SitOnLilypadGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
			super(FrogEntity.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
		}

		public double getTargetDistanceSq() {
			return 2.0D;
		}

		public boolean shouldRecalculatePath() {
			return this.tryTicks % 100 == 0;
		}

		protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
			BlockState blockstate = worldIn.getBlockState(pos);
			return blockstate.is(Blocks.LILY_PAD);
		}



		public void tick() {
			if (this.isReachedTarget()) {
				if (this.field_220731_g >= 40) {
					FrogEntity.this.setDeltaMovement(Vec3.ZERO);
				} else {
					++this.field_220731_g;
				}
			}
			super.tick();
		}

		public boolean canUse() {
			return !FrogEntity.this.isBaby() && super.canUse();
		}


		public void start() {
			this.field_220731_g = 0;
			super.start();
		}
	}

	public int getMaxAirSupply() {
		return 4800;
	}

	protected int determineNextAir(int currentAir) {
		return this.getMaxAirSupply();
	}

	protected void handleAirSupply(int p_209207_1_) {
		if (this.isBaby()) {
			if (this.isAlive() && !this.isInWaterOrBubble()) {
				this.setAirSupply(p_209207_1_ - 1);
				if (this.getAirSupply() == -20) {
					this.setAirSupply(0);
					this.hurt(DamageSource.DROWN, 2.0F);
				}
			} else {
				this.setAirSupply(300);
			}
		} 
	}

	public void tick() {
		super.tick();
		if (this.isNoAi()) {
			this.setAirSupply(this.getMaxAirSupply());
		} else {
			if (this.isInWaterOrBubble()) {
				this.setMoistness(2400);
			} else {
				this.setMoistness(this.getMoistness() - 1);
				if (this.getMoistness() <= 0) {
					this.hurt(DamageSource.DRY_OUT, 1.0F);
				}
			}
		}
	}

	public boolean canBreatheUnderwater() {
		if (this.isBaby()) {
			return true;
		} else {
			return false;
		}
	}

	public void travel(Vec3 travelVector) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), travelVector);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(travelVector);
		}

	}

	public boolean shouldDespawnInPeaceful() {
		return super.shouldDespawnInPeaceful() || this.isFromBucket();
	}

	public boolean canDespawn(double distanceToClosestPlayer) {
		return !this.isFromBucket() && !this.hasCustomName();
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (!this.isBaby()) {
			if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
				this.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
				itemstack.shrink(1);
				ItemStack itemstack1 = this.getFishBucket();
				this.setBucketData(itemstack1);
				if (!this.level.isClientSide) {
					CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, itemstack1);
				}

				if (itemstack.isEmpty()) {
					player.setItemInHand(hand, itemstack);
				} else if (!player.getInventory().add(itemstack1)) {
					player.drop(itemstack1, false);
				}

				this.discard();
				return InteractionResult.SUCCESS;
			} else {
				return super.mobInteract(player, hand);
			}
		} else {
			return super.mobInteract(player, hand);
		}
	}

	public ItemStack getFishBucket() {
		return new ItemStack(WildLandsItems.FROG_BUCKET.get());
	}

	protected void setBucketData(ItemStack bucket) {
		if (this.hasCustomName()) {
			bucket.setHoverName(this.getCustomName());
		}
	}

	class AdultWalkingGoal extends RandomStrollGoal {

		public AdultWalkingGoal(FrogEntity creatureIn, double speedIn) {
			super(creatureIn, speedIn);
		}

		public boolean canUse() {
			if (!FrogEntity.this.isBaby() && super.canUse()) {
				return true;
			} else {
				return false;
			}
		}

	}

	static class GoToWaterGoal extends MoveToBlockGoal {
		private final FrogEntity frog;

		private GoToWaterGoal(FrogEntity frog, double speedIn) {
			super(frog, frog.isBaby() ? 2.0D : speedIn, 24);
			this.frog = frog;
			this.verticalSearchStart = -1;
		}

		public boolean canContinueToUse() {
			return !this.frog.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.frog.level, this.blockPos);
		}

		public boolean canUse() {
			if (this.frog.isBaby() && !this.frog.isInWater() && super.canUse()) {
				return true;
			} else {
				return false;
			}
		}

		public boolean shouldMove() {
			return this.tryTicks % 160 == 0;
		}

		protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
			return worldIn.getBlockState(pos).is(Blocks.WATER);
		}
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WildLandsItems.FROG_SPAWN_EGG.get());
	}

}