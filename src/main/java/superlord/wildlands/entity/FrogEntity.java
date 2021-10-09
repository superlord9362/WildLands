package superlord.wildlands.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.EntityInit;
import superlord.wildlands.init.ItemInit;

public class FrogEntity extends AnimalEntity {
	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int currentMoveTypeDuration;
	private static final DataParameter<Integer> MOISTNESS = EntityDataManager.createKey(FrogEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> FROM_BUCKET = EntityDataManager.createKey(FrogEntity.class, DataSerializers.BOOLEAN);

	public int getMoistness() {
		return this.dataManager.get(MOISTNESS);
	}

	public void setMoistness(int p_211137_1_) {
		this.dataManager.set(MOISTNESS, p_211137_1_);
	}

	public FrogEntity(EntityType<? extends FrogEntity> type, World worldIn) {
		super(type, worldIn);
		this.jumpController = new FrogEntity.JumpHelperController(this);
		this.moveController = new FrogEntity.MoveHelperController(this);
	}

	public int getMaxAir() {
		return 4800;
	}

	protected int determineNextAir(int currentAir) {
		return this.getMaxAir();
	}

	public boolean preventDespawn() {
		return super.preventDespawn() || this.isFromBucket();
	}

	public boolean canDespawn(double distanceToClosestPlayer) {
		return !this.isFromBucket() && !this.hasCustomName();
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(11, new LookAtGoal(this, PlayerEntity.class, 10.0F));
		this.goalSelector.addGoal(0, new BreatheAirGoal(this));
		this.goalSelector.addGoal(6, new RandomSwimmingGoal(this, 1.0D, 2));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(0, new FrogEntity.FrogFindWaterGoal(this));
		this.goalSelector.addGoal(7, new FrogEntity.SitOnLilypadGoal(1, 20, 2));
	}

	protected void updateAir(int p_209207_1_) {
		if (this.isChild()) {
			if (this.isAlive() && !this.isInWaterOrBubbleColumn()) {
				this.setAir(p_209207_1_ - 1);
				if (this.getAir() == -20) {
					this.setAir(0);
					this.attackEntityFrom(DamageSource.DROWN, 2.0F);
				}
			} else {
				this.setAir(300);
			}
		}
	}

	static class TadpoleMoveHelperController extends MovementController {
		private final FrogEntity frog;

		TadpoleMoveHelperController(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public void tick() {
			if (this.frog.areEyesInFluid(FluidTags.WATER)) {
				this.frog.setMotion(this.frog.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.frog.getNavigator().noPath()) {
				float f = (float)(this.speed * this.frog.getAttributeValue(Attributes.MOVEMENT_SPEED));
				this.frog.setAIMoveSpeed(MathHelper.lerp(0.125F, this.frog.getAIMoveSpeed(), f));
				double d0 = this.posX - this.frog.getPosX();
				double d1 = this.posY - this.frog.getPosY();
				double d2 = this.posZ - this.frog.getPosZ();
				if (d1 != 0.0D) {
					double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
					this.frog.setMotion(this.frog.getMotion().add(0.0D, (double)this.frog.getAIMoveSpeed() * (d1 / d3) * 0.1D, 0.0D));
				}

				if (d0 != 0.0D || d2 != 0.0D) {
					float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
					this.frog.rotationYaw = this.limitAngle(this.frog.rotationYaw, f1, 90.0F);
					this.frog.renderYawOffset = this.frog.rotationYaw;
				}

			} else {
				this.frog.setAIMoveSpeed(0.0F);
			}
		}
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOISTNESS, 2400);
		this.dataManager.register(FROM_BUCKET, false);
	}

	private boolean isFromBucket() {
		return this.dataManager.get(FROM_BUCKET);
	}

	public void setFromBucket(boolean p_203706_1_) {
		this.dataManager.set(FROM_BUCKET, p_203706_1_);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("Moistness", this.getMoistness());
		compound.putBoolean("FromBucket", this.isFromBucket());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setMoistness(compound.getInt("Moistness"));
		this.setFromBucket(compound.getBoolean("FromBucket"));
	}

	public ActionResultType func_230254_b_(PlayerEntity p_230254_1_, Hand p_230254_2_) {
		ItemStack itemstack = p_230254_1_.getHeldItem(p_230254_2_);
		if (!this.isChild()) {
			if (itemstack.getItem() == Items.WATER_BUCKET && this.isAlive()) {
				this.playSound(SoundEvents.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);
				itemstack.shrink(1);
				ItemStack itemstack1 = this.getFishBucket();
				this.setBucketData(itemstack1);
				if (!this.world.isRemote) {
					CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity)p_230254_1_, itemstack1);
				}

				if (itemstack.isEmpty()) {
					p_230254_1_.setHeldItem(p_230254_2_, itemstack1);
				} else if (!p_230254_1_.inventory.addItemStackToInventory(itemstack1)) {
					p_230254_1_.dropItem(itemstack1, false);
				}

				this.remove();
				return ActionResultType.func_233537_a_(this.world.isRemote);
			} else {
				return super.func_230254_b_(p_230254_1_, p_230254_2_);
			}
		} else {
			return super.func_230254_b_(p_230254_1_, p_230254_2_);
		}
	}

	public ItemStack getFishBucket() {
		return new ItemStack(ItemInit.FROG_BUCKET.get());
	}

	protected void setBucketData(ItemStack bucket) {
		if (this.hasCustomName()) {
			bucket.setDisplayName(this.getCustomName());
		}
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		FrogEntity entity = new FrogEntity(EntityInit.FROG.get(), this.world);
		entity.onInitialSpawn(p_241840_1_, this.world.getDifficultyForLocation(new BlockPos(entity.getPositionVec())), SpawnReason.BREEDING, (ILivingEntityData)null, (CompoundNBT)null);
		return entity;
	}

	public void travel(Vector3d travelVector) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.01F, travelVector);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (this.getAttackTarget() == null) {
				this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(travelVector);
		}
	}

	public void tick() {
		super.tick();
		if (this.isAIDisabled()) {
			this.setAir(this.getMaxAir());
		} else {
			if (this.isInWaterRainOrBubbleColumn()) {
				this.setMoistness(2400);
			} else {
				this.setMoistness(this.getMoistness() - 1);
				if (this.getMoistness() <= 0) {
					this.attackEntityFrom(DamageSource.DRYOUT, 1.0F);
				}
			}
		}
	}

	protected float getJumpUpwardsMotion() {
		if (!this.collidedHorizontally && (!this.moveController.isUpdating() || !(this.moveController.getY() > this.getPosY() + 0.5D))) {
			Path path = this.navigator.getPath();
			if (path != null && !path.isFinished()) {
				Vector3d vector3d = path.getPosition(this);
				if (vector3d.y > this.getPosY() + 0.5D) {
					return 0.5F;
				}
			}

			return this.moveController.getSpeed() <= 0.6D ? 0.2F : 0.3F;
		} else {
			return 0.5F;
		}
	}

	protected void jump() {
		super.jump();
		double d0 = this.moveController.getSpeed();
		if (d0 > 0.0D) {
			double d1 = horizontalMag(this.getMotion());
			if (d1 < 0.01D) {
				this.moveRelative(0.1F, new Vector3d(0.0D, 0.0D, 1.0D));
			}
		}

		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte)1);
		}

	}

	@OnlyIn(Dist.CLIENT)
	public float getJumpCompletion(float p_175521_1_) {
		return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_175521_1_) / (float)this.jumpDuration;
	}

	public void setMovementSpeed(double newSpeed) {
		this.getNavigator().setSpeed(newSpeed);
		this.moveController.setMoveTo(this.moveController.getX(), this.moveController.getY(), this.moveController.getZ(), newSpeed);
	}

	public void setJumping(boolean jumping) {
		super.setJumping(jumping);
	}

	public class JumpHelperController extends JumpController {
		private final FrogEntity frog;
		private boolean canJump;

		public JumpHelperController(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public boolean getIsJumping() {
			return this.isJumping;
		}

		public boolean canJump() {
			return this.canJump;
		}

		public void setCanJump(boolean canJumpIn) {
			this.canJump = canJumpIn;
		}

		/**
		 * Called to actually make the entity jump if isJumping is true.
		 */
		public void tick() {
			if (this.isJumping) {
				this.frog.startJumping();
				this.isJumping = false;
			}

		}
	}

	public void startJumping() {
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}

	public boolean canBreatheUnderwater() {
		if (this.isChild()) {
			return true;
		} else {
			return false;
		}
	}

	public void updateAITasks() {
		if (this.onGround) {
			if (!this.wasOnGround) {
				this.setJumping(false);
				this.checkLandingDelay();
			}
			FrogEntity.JumpHelperController frogentity$jumphelpercontroller = (FrogEntity.JumpHelperController)this.jumpController;
			if (!frogentity$jumphelpercontroller.getIsJumping()) {
				if (this.moveController.isUpdating() && this.currentMoveTypeDuration == 0) {
					Path path = this.navigator.getPath();
					Vector3d vector3d = new Vector3d(this.moveController.getX(), this.moveController.getY(), this.moveController.getZ());
					if (path != null && !path.isFinished()) {
						vector3d = path.getPosition(this);
					}

					this.calculateRotationYaw(vector3d.x, vector3d.z);
					this.startJumping();
				}
			} else if (!frogentity$jumphelpercontroller.canJump()) {
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	private void calculateRotationYaw(double x, double z) {
		this.rotationYaw = (float)(MathHelper.atan2(z - this.getPosZ(), x - this.getPosX()) * (double)(180F / (float)Math.PI)) - 90.0F;
	}

	private void enableJumpControl() {
		((FrogEntity.JumpHelperController)this.jumpController).setCanJump(true);
	}

	private void disableJumpControl() {
		((FrogEntity.JumpHelperController)this.jumpController).setCanJump(false);
	}

	private void updateMoveTypeDuration() {
		if (this.moveController.getSpeed() < 2.2D) {
			this.currentMoveTypeDuration = 10;
		} else {
			this.currentMoveTypeDuration = 1;
		}

	}

	private void checkLandingDelay() {
		this.updateMoveTypeDuration();
		this.disableJumpControl();
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void livingTick() {
		super.livingTick();
		if (this.jumpTicks != this.jumpDuration) {
			++this.jumpTicks;
		} else if (this.jumpDuration != 0) {
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}

	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 6.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.3F);
	}

	static class MoveHelperController extends MovementController {
		private final FrogEntity frog;
		private double nextJumpSpeed;

		public MoveHelperController(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public void tick() {
			if (this.frog.onGround && !this.frog.isJumping && !((FrogEntity.JumpHelperController)this.frog.jumpController).getIsJumping()) {
				this.frog.setMovementSpeed(0.0D);
			} else if (this.isUpdating()) {
				this.frog.setMovementSpeed(this.nextJumpSpeed);
			}

			super.tick();
		}

		/**
		 * Sets the speed and location to move to
		 */
		public void setMoveTo(double x, double y, double z, double speedIn) {
			if (this.frog.isInWater()) {
				speedIn = 1.5D;
			}

			super.setMoveTo(x, y, z, speedIn);
			if (speedIn > 0.0D) {
				this.nextJumpSpeed = speedIn;
			}

		}
	}

	static class FrogFindWaterGoal extends FindWaterGoal {

		FrogEntity frog;

		public FrogFindWaterGoal(FrogEntity frog) {
			super(frog);
			this.frog = frog;
		}

		public boolean shouldExecute() {
			return super.shouldExecute() && (frog.getMoistness() <= 500);
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

		public boolean shouldMove() {
			return this.timeoutCounter % 100 == 0;
		}

		/**
		 * Return true to set given position as destination
		 */
		protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
			BlockState blockstate = worldIn.getBlockState(pos);
			return blockstate.isIn(Blocks.LILY_PAD);
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (this.getIsAboveDestination()) {
				if (this.field_220731_g >= 40) {
					FrogEntity.this.setMotion(Vector3d.ZERO);
				} else {
					++this.field_220731_g;
				}
			}
			super.tick();
		}

		public boolean shouldExecute() {
			return !FrogEntity.this.isChild() && super.shouldExecute();
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			this.field_220731_g = 0;
			super.startExecuting();
		}
	}

}
