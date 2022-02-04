package superlord.wildlands.common.entity;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsParticles;
import superlord.wildlands.init.WildLandsSounds;

public class GrizzlyEntity extends AnimalEntity {
	private int warningSoundTicks;

	public GrizzlyEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	private static final DataParameter<Integer> FOOD_COUNT = EntityDataManager.createKey(GrizzlyEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(GrizzlyEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(GrizzlyEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> HONEY = EntityDataManager.createKey(GrizzlyEntity.class, DataSerializers.BOOLEAN);

	protected void registerData() {
		super.registerData();
		this.dataManager.register(FOOD_COUNT, 0);
		this.dataManager.register(SLEEPING, false);
		this.dataManager.register(SITTING, false);
		this.dataManager.register(HONEY, false);
	}

	protected void playWarningSound() {
		if (this.warningSoundTicks <= 0) {
			this.playSound(WildLandsSounds.GRIZZLY_WARNING, 1.0F, this.getSoundPitch());
			this.warningSoundTicks = 40;
		}

	}

	protected SoundEvent getAmbientSound() {
		if (!this.isSleeping()) {
			return WildLandsSounds.GRIZZLY_IDLE;
		} else {
			return null;
		}
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.GRIZZLY_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.GRIZZLY_HURT;
	}

	public int getFoodCount() {
		return MathHelper.clamp(dataManager.get(FOOD_COUNT), 0, 20);
	}

	public void setFoodCount(int type) {
		dataManager.set(FOOD_COUNT, type);
	}

	public boolean isSleeping() {
		return dataManager.get(SLEEPING);
	}

	public void setSleeping(boolean sleeping) {
		dataManager.set(SLEEPING, sleeping);
	}

	public boolean isSitting() {
		return dataManager.get(SITTING);
	}

	public void setSitting(boolean sitting) {
		dataManager.set(SITTING, sitting);
	}

	public boolean hasHoney() {
		return dataManager.get(HONEY);
	}

	public void setHoney(boolean honey) {
		dataManager.set(HONEY, honey);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("FoodCount", this.getFoodCount());
		compound.putBoolean("Sleeping", this.isSleeping());
		compound.putBoolean("Sitting", this.isSitting());
		compound.putBoolean("Honey", this.hasHoney());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setFoodCount(compound.getInt("FoodCount"));
		this.setSleeping(compound.getBoolean("Sleeping"));
		this.setSitting(compound.getBoolean("Sitting"));
		this.setHoney(compound.getBoolean("Honey"));
	}

	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == Items.SALMON;
	}

	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
	}

	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.getDamageType() == DamageSource.SWEET_BERRY_BUSH.damageType) {
			return false;
		} else {
			return super.attackEntityFrom(source, amount);
		}
	}

	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, SalmonEntity.class, false));
		this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(9, new GrizzlyEntity.HibernateGoal());
		this.goalSelector.addGoal(2, new GrizzlyEntity.EatHoneyGoal((double)1.2F, 12, 2));
		this.goalSelector.addGoal(3, new GrizzlyEntity.EatBerriesGoal((double)1.2F, 12, 2));
		this.goalSelector.addGoal(5, new GrizzlyEntity.MeleeAttackGoal());
		this.goalSelector.addGoal(1, new GrizzlyEntity.PanicGoal());
		this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new GrizzlyEntity.HurtByTargetGoal());
		this.targetSelector.addGoal(2, new GrizzlyEntity.AttackPlayerGoal());
		this.goalSelector.addGoal(5, new GrizzlyEntity.FindItemsGoal());
	}

	public boolean canPickUpItem(ItemStack itemstackIn) {
		EquipmentSlotType equipmentslottype = MobEntity.getSlotForItemStack(itemstackIn);
		if (!this.getItemStackFromSlot(equipmentslottype).isEmpty()) {
			return false;
		} else {
			return equipmentslottype == EquipmentSlotType.MAINHAND && super.canPickUpItem(itemstackIn);
		}
	}

	private void spitOutItem(ItemStack stackIn) {
		if (!stackIn.isEmpty() && !this.world.isRemote) {
			ItemEntity itementity = new ItemEntity(this.world, this.getPosX() + this.getLookVec().x, this.getPosY() + 1.0D, this.getPosZ() + this.getLookVec().z, stackIn);
			itementity.setPickupDelay(40);
			itementity.setThrowerId(this.getUniqueID());
			this.playSound(SoundEvents.ENTITY_FOX_SPIT, 1.0F, 1.0F);
			this.world.addEntity(itementity);
		}
	}

	public void tick() {
		super.tick();
		int ticks = 1200;
		int eatTicks = 0;
		if (this.warningSoundTicks > 0) {
			--this.warningSoundTicks;
		}
		if (this.hasHoney()) {
			ticks--;
			if (ticks == 0) {
				ticks = 1200;
				this.setHoney(false);
			}
		}

		if(this.isSleeping()) {
			world.addParticle(WildLandsParticles.SNORE_PARTICLE, this.getPosX(), this.getPosY() + 0.2F, this.getPosZ(), 0, 0, 0);
		}

		if (this.getItemStackFromSlot(EquipmentSlotType.MAINHAND) == Items.SWEET_BERRIES.getDefaultInstance() || this.getItemStackFromSlot(EquipmentSlotType.MAINHAND) == Items.HONEYCOMB.getDefaultInstance() || this.getItemStackFromSlot(EquipmentSlotType.MAINHAND) == Items.SALMON.getDefaultInstance()) {
			ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			eatTicks++;
			if (eatTicks <= 800) {
				Vector3d vector3d = (new Vector3d(((double)this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).rotatePitch(-this.rotationPitch * ((float)Math.PI / 180F)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F));
				this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, itemstack), this.getPosX() + this.getLookVec().x / 2.0D, this.getPosY(), this.getPosZ() + this.getLookVec().z / 2.0D, vector3d.x, vector3d.y + 0.05D, vector3d.z);
			}
			if (eatTicks == 1000) {
				this.setItemStackToSlot(EquipmentSlotType.MAINHAND, Items.AIR.getDefaultInstance());
				eatTicks = 0;
			}
		} else if (this.getItemStackFromSlot(EquipmentSlotType.MAINHAND) != null && !(this.getItemStackFromSlot(EquipmentSlotType.MAINHAND) == Items.SWEET_BERRIES.getDefaultInstance() || this.getItemStackFromSlot(EquipmentSlotType.MAINHAND) == Items.HONEYCOMB.getDefaultInstance() || this.getItemStackFromSlot(EquipmentSlotType.MAINHAND) == Items.SALMON.getDefaultInstance())) {
			ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			this.spitOutItem(itemstack);
		}

	}

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.applyEnchantments(this, entityIn);
		}
		return flag;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 30.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		GrizzlyEntity entity = new GrizzlyEntity(WildLandsEntities.GRIZZLY.get(), this.world);
		entity.onInitialSpawn(p_241840_1_, this.world.getDifficultyForLocation(new BlockPos(entity.getPositionVec())), SpawnReason.BREEDING, (ILivingEntityData)null, (CompoundNBT)null);
		return entity;
	}

	public class HibernateGoal extends Goal {

		int tickTimer;

		@Override
		public boolean shouldExecute() {
			if (GrizzlyEntity.this.getFoodCount() >= 20) {
				return true;
			} else return false;
		}

		public void tick() {
			super.tick();
			tickTimer++;
		}

		@Override
		public boolean shouldContinueExecuting() {
			List<PlayerEntity> players = GrizzlyEntity.this.world.getEntitiesWithinAABB(PlayerEntity.class, GrizzlyEntity.this.getBoundingBox().grow(5, 5, 5));
			if (!players.isEmpty()) {
				for (PlayerEntity player : players) {
					if (!player.isCreative()) {
						return false;
					}
				}
			}
			if (tickTimer >= 96000 || GrizzlyEntity.this.getRevengeTarget() != null) {
				return false;
			} else return true;
		}

		@Override
		public void startExecuting() {
			GrizzlyEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
			GrizzlyEntity.this.setSleeping(true);
			GrizzlyEntity.this.setFoodCount(0);
		}

		public void resetTask() {
			GrizzlyEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25D);
			GrizzlyEntity.this.setSleeping(false);
			List<PlayerEntity> players = GrizzlyEntity.this.world.getEntitiesWithinAABB(PlayerEntity.class, GrizzlyEntity.this.getBoundingBox().grow(5, 5, 5));
			if (!players.isEmpty()) {
				for (PlayerEntity player : players) {
					GrizzlyEntity.this.setAttackTarget(player);
				}
			}
		}

	}

	public class EatBerriesGoal extends MoveToBlockGoal {
		protected int field_220731_g;

		public EatBerriesGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
			super(GrizzlyEntity.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
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
			return blockstate.isIn(Blocks.SWEET_BERRY_BUSH) && blockstate.get(SweetBerryBushBlock.AGE) >= 2;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (this.getIsAboveDestination()) {
				if (this.field_220731_g >= 40) {
					this.eatBerry();
				} else {
					++this.field_220731_g;
				}
			} else if (!this.getIsAboveDestination() && GrizzlyEntity.this.rand.nextFloat() < 0.05F) {
			}

			super.tick();
		}

		protected void eatBerry() {
			if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(GrizzlyEntity.this.world, GrizzlyEntity.this)) {
				BlockState blockstate = GrizzlyEntity.this.world.getBlockState(this.destinationBlock);
				if (blockstate.isIn(Blocks.SWEET_BERRY_BUSH)) {
					int i = blockstate.get(SweetBerryBushBlock.AGE);
					blockstate.with(SweetBerryBushBlock.AGE, Integer.valueOf(1));
					GrizzlyEntity.this.setSitting(true);
					int j = 1 + GrizzlyEntity.this.world.rand.nextInt(2) + (i == 3 ? 1 : 0);
					ItemStack itemstack = GrizzlyEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
					if (itemstack.isEmpty()) {
						GrizzlyEntity.this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
						--j;
					}

					if (j > 0) {
						Block.spawnAsEntity(GrizzlyEntity.this.world, this.destinationBlock, new ItemStack(Items.SWEET_BERRIES, j));
					}

					GrizzlyEntity.this.playSound(SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, 1.0F, 1.0F);
					GrizzlyEntity.this.world.setBlockState(this.destinationBlock, blockstate.with(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
				}
			}
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			return !GrizzlyEntity.this.isSleeping() && super.shouldExecute();
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			this.field_220731_g = 0;
			super.startExecuting();
			GrizzlyEntity.this.setFoodCount(GrizzlyEntity.this.getFoodCount() + 1);
		}

		public void resetTask() {
			super.resetTask();
			GrizzlyEntity.this.setSitting(false);
			GrizzlyEntity.this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.AIR));
		}
	}

	public class EatHoneyGoal extends MoveToBlockGoal {
		protected int field_220731_g;

		public EatHoneyGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
			super(GrizzlyEntity.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
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
			return (blockstate.isIn(Blocks.BEE_NEST) && blockstate.get(BeehiveBlock.HONEY_LEVEL) >= 5) || (blockstate.isIn(Blocks.BEEHIVE) && blockstate.get(BeehiveBlock.HONEY_LEVEL) >= 5);
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (this.getIsAboveDestination()) {
				if (this.field_220731_g >= 40) {
					this.eatBerry();
				} else {
					++this.field_220731_g;
				}
			} else if (!this.getIsAboveDestination() && GrizzlyEntity.this.rand.nextFloat() < 0.05F) {
			}

			super.tick();
		}

		protected void eatBerry() {
			if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(GrizzlyEntity.this.world, GrizzlyEntity.this)) {
				BlockState blockstate = GrizzlyEntity.this.world.getBlockState(this.destinationBlock);
				if (blockstate.isIn(Blocks.BEE_NEST) || blockstate.isIn(Blocks.BEEHIVE)) {
					int i = blockstate.get(BeehiveBlock.HONEY_LEVEL);
					blockstate.with(BeehiveBlock.HONEY_LEVEL, Integer.valueOf(0));
					GrizzlyEntity.this.setSitting(true);
					int j = 1 + GrizzlyEntity.this.world.rand.nextInt(2) + (i == 3 ? 1 : 0);
					ItemStack itemstack = GrizzlyEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
					if (itemstack.isEmpty()) {
						GrizzlyEntity.this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.HONEYCOMB));
						GrizzlyEntity.this.setHoney(true);
						--j;
					}

					if (j > 0) {
					}

					GrizzlyEntity.this.world.setBlockState(this.destinationBlock, blockstate.with(BeehiveBlock.HONEY_LEVEL, Integer.valueOf(0)), 2);
				}
			}
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			return !GrizzlyEntity.this.isSleeping() && super.shouldExecute();
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			this.field_220731_g = 0;
			super.startExecuting();
			GrizzlyEntity.this.setFoodCount(GrizzlyEntity.this.getFoodCount() + 1);
		}

		public void resetTask() {
			super.resetTask();
			GrizzlyEntity.this.setSitting(false);
			GrizzlyEntity.this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.AIR));
		}
	}

	class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {
		public MeleeAttackGoal() {
			super(GrizzlyEntity.this, 1.25D, true);
		}

		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d0 = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d0 && this.func_234040_h_()) {
				this.func_234039_g_();
				this.attacker.attackEntityAsMob(enemy);
			} else if (distToEnemySqr <= d0 * 2.0D) {
				if (this.func_234040_h_()) {
					this.func_234039_g_();
				}

				if (this.func_234041_j_() <= 10) {
					GrizzlyEntity.this.playWarningSound();
				}
			} else {
				this.func_234039_g_();
			}

		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void resetTask() {
			super.resetTask();
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double)(6.0F + attackTarget.getWidth());
		}
	}

	class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
		public PanicGoal() {
			super(GrizzlyEntity.this, 2.0D);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			return !GrizzlyEntity.this.isChild() && !GrizzlyEntity.this.isBurning() ? false : super.shouldExecute();
		}
	}

	class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
		public AttackPlayerGoal() {
			super(GrizzlyEntity.this, PlayerEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			if (GrizzlyEntity.this.isChild()) {
				return false;
			} else {
				if (super.shouldExecute()) {
					for(GrizzlyEntity polarbearentity : GrizzlyEntity.this.world.getEntitiesWithinAABB(GrizzlyEntity.class, GrizzlyEntity.this.getBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
						if (polarbearentity.isChild()) {
							return true;
						}
					}
				}

				return false;
			}
		}

		protected double getTargetDistance() {
			return super.getTargetDistance() * 0.5D;
		}
	}

	class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
		public HurtByTargetGoal() {
			super(GrizzlyEntity.this);
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			super.startExecuting();
			if (GrizzlyEntity.this.isChild()) {
				this.alertOthers();
				this.resetTask();
			}

		}

		protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
			if (mobIn instanceof PolarBearEntity && !mobIn.isChild()) {
				super.setAttackTarget(mobIn, targetIn);
			}

		}
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(WildLandsItems.GRIZZLY_BEAR_SPAWN_EGG.get());
	}

	class FindItemsGoal extends Goal {
		public FindItemsGoal() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			if (!GrizzlyEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
				return false;
			} else if (GrizzlyEntity.this.getAttackTarget() == null && GrizzlyEntity.this.getRevengeTarget() == null) {
				if (!GrizzlyEntity.this.isSleeping()) {
					return false;
				} else if (GrizzlyEntity.this.getRNG().nextInt(10) != 0) {
					return false;
				} else {
					List<ItemEntity> list = GrizzlyEntity.this.world.getEntitiesWithinAABB(ItemEntity.class, GrizzlyEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D));
					return !list.isEmpty() && GrizzlyEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty();
				}
			} else {
				return false;
			}
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			List<ItemEntity> list = GrizzlyEntity.this.world.getEntitiesWithinAABB(ItemEntity.class, GrizzlyEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D));
			ItemStack itemstack = GrizzlyEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			if (itemstack.isEmpty() && !list.isEmpty()) {
				GrizzlyEntity.this.getNavigator().tryMoveToEntityLiving(list.get(0), (double)1.2F);
			}

		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			List<ItemEntity> list = GrizzlyEntity.this.world.getEntitiesWithinAABB(ItemEntity.class, GrizzlyEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D));
			if (!list.isEmpty()) {
				GrizzlyEntity.this.getNavigator().tryMoveToEntityLiving(list.get(0), (double)1.2F);
			}

		}
	}

}
