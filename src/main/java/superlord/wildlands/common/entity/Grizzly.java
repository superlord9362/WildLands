package superlord.wildlands.common.entity;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLItems;
import superlord.wildlands.init.WLParticles;
import superlord.wildlands.init.WLSounds;

public class Grizzly extends Animal
{
	private int warningSoundTicks;

	public Grizzly(EntityType<? extends Animal> type, Level worldIn) {
		super(type, worldIn);
		this.setCanPickUpLoot(true);
	}

	private static final EntityDataAccessor<Integer> FOOD_COUNT = SynchedEntityData.defineId(Grizzly.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(Grizzly.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(Grizzly.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> HONEY = SynchedEntityData.defineId(Grizzly.class, EntityDataSerializers.BOOLEAN);
	int eatTicks;
	int snoreTicks = 0;
	int honeyTicks = 12000;

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(FOOD_COUNT, 0);
		this.entityData.define(SLEEPING, false);
		this.entityData.define(SITTING, false);
		this.entityData.define(HONEY, false);
	}

	protected void playWarningSound() {
		if (this.warningSoundTicks <= 0) {
			this.playSound(WLSounds.GRIZZLY_WARNING.get(), 1.0F, this.getSoundVolume());
			this.warningSoundTicks = 40;
		}

	}

	public int getEatTicks() {
		return eatTicks;
	}

	private void dropItemStack(ItemStack stackIn) {
		ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), stackIn);
		this.level.addFreshEntity(itementity);
	}

	protected void pickUpItem(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		if (this.canHoldItem(itemstack)) {
			int i = itemstack.getCount();
			if (i > 1) {
				this.dropItemStack(itemstack.split(i - 1));
			}
			this.spitOutItem(this.getItemBySlot(EquipmentSlot.MAINHAND));
			this.onItemPickup(itemEntity);
			this.setItemSlot(EquipmentSlot.MAINHAND, itemstack.split(1));
			this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
			this.take(itemEntity, itemstack.getCount());
			itemEntity.discard();
			this.eatTicks = 0;
		}

	}

	private void spitOutItem(ItemStack p_28602_) {
		if (!p_28602_.isEmpty() && !this.level.isClientSide) {
			ItemEntity itementity = new ItemEntity(this.level, this.getX() + this.getLookAngle().x, this.getY() + 1.0D, this.getZ() + this.getLookAngle().z, p_28602_);
			itementity.setPickUpDelay(40);
			itementity.setThrower(this.getUUID());
			this.playSound(SoundEvents.FOX_SPIT, 1.0F, 1.0F);
			this.level.addFreshEntity(itementity);
		}
	}


	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 45) {
			ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
			if (!itemstack.isEmpty()) {
				for(int i = 0; i < 8; ++i) {
					Vec3 vec3 = (new Vec3(((double)this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).xRot(-this.getXRot() * ((float)Math.PI / 180F)).yRot(-this.getYRot() * ((float)Math.PI / 180F));
					this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, itemstack), this.getX() + this.getLookAngle().x / 2.0D, this.getY(), this.getZ() + this.getLookAngle().z / 2.0D, vec3.x, vec3.y + 0.05D, vec3.z);
				}
			}
		} else {
			super.handleEntityEvent(id);
		}

	}

	private boolean canEatItem(ItemStack itemStackIn) {
		return itemStackIn.isEdible() || itemStackIn.getItem() == Items.HONEYCOMB;
	}

	public boolean canEquipItem(ItemStack stack) {
		ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
		return itemstack.isEmpty() && this.canEatItem(stack) && !this.isSleeping();
	}

	protected SoundEvent getAmbientSound() {
		if (!this.isSleeping()) {
			return WLSounds.GRIZZLY_IDLE.get();
		} else {
			return null;
		}
	}

	protected SoundEvent getDeathSound() {
		return WLSounds.GRIZZLY_DEATH.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WLSounds.GRIZZLY_HURT.get();
	}

	public int getFoodCount() {
		return Mth.clamp(entityData.get(FOOD_COUNT), 0, 10);
	}

	public void setFoodCount(int type) {
		entityData.set(FOOD_COUNT, type);
	}

	public boolean isSleeping() {
		return entityData.get(SLEEPING);
	}

	public void setSleeping(boolean sleeping) {
		entityData.set(SLEEPING, sleeping);
	}

	public boolean isSitting() {
		return entityData.get(SITTING);
	}

	public void setSitting(boolean sitting) {
		entityData.set(SITTING, sitting);
	}

	public boolean hasHoney() {
		return entityData.get(HONEY);
	}

	public void setHoney(boolean honey) {
		entityData.set(HONEY, honey);
	}
	
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("FoodCount", this.getFoodCount());
		compound.putBoolean("Sleeping", this.isSleeping());
		compound.putBoolean("Sitting", this.isSitting());
		compound.putBoolean("Honey", this.hasHoney());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setFoodCount(compound.getInt("FoodCount"));
		this.setSleeping(compound.getBoolean("Sleeping"));
		this.setSitting(compound.getBoolean("Sitting"));
		this.setHoney(compound.getBoolean("Honey"));
	}

	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == Items.SALMON;
	}

	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.POLAR_BEAR_STEP, 0.15F, 1.0F);
	}

	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.SWEET_BERRY_BUSH)) {
			return false;
		} else {
			return super.hurt(source, amount);
		}
	}

	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<Salmon>(this, Salmon.class, false));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(10, new Grizzly.GrizzlyLookRandomlyGoal(this));
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(9, new Grizzly.HibernateGoal());
		this.goalSelector.addGoal(2, new Grizzly.EatHoneyGoal((double)1.2F, 12, 2));
		this.goalSelector.addGoal(3, new Grizzly.EatBerriesGoal((double)1.2F, 12, 2));
		this.goalSelector.addGoal(0, new Grizzly.GrizzlyMeleeAttackGoal());
		this.goalSelector.addGoal(1, new Grizzly.GrizzlyPanicGoal());
		this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(0, new Grizzly.GrizzlyHurtByTargetGoal());
		this.targetSelector.addGoal(0, new Grizzly.AttackPlayerGoal());
		this.goalSelector.addGoal(5, new Grizzly.FindItemsGoal());
	}

	public void tick() {
		super.tick();
		if (this.warningSoundTicks > 0) {
			--this.warningSoundTicks;
		}
		if (this.hasHoney()) {
			if (this.isInWater()) {
				this.setHoney(false);
			}
			honeyTicks--;
			if (honeyTicks == 0) {
				honeyTicks = 1200;
				this.setHoney(false);
			}
		}

		if (this.isSitting() || this.isSleeping()) {
			this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
		} else {
			this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		}

		if(this.isSleeping()) {
			snoreTicks++;
			if (snoreTicks % 10 == 0) {
				level.addParticle(WLParticles.SNORE_PARTICLE.get(), this.getX(), this.getY() + 0.2F, this.getZ(), 0, 0, 0);
			}
		}

	}

	public void aiStep() {
		int foodCount = this.getFoodCount();
		if (!this.level.isClientSide && this.isAlive() && this.isEffectiveAi()) {
			++this.eatTicks;
			ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
			if (this.canEatItem(itemstack)) {
				if (itemstack.getItem() == Items.HONEYCOMB) {
					this.setHoney(true);
				}
				this.setSitting(true);
				if (this.eatTicks > 200) {
					level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), WLSounds.GRIZZLY_EATING.get(), SoundSource.NEUTRAL, 1, 1);
					ItemStack itemstack1 = itemstack.finishUsingItem(this.level, this);
					if (!itemstack1.isEmpty()) {
						this.setItemSlot(EquipmentSlot.MAINHAND, itemstack1);
					}
					this.eatTicks = 0;
					itemstack.shrink(1);
					this.setSitting(false);
					this.setFoodCount(foodCount + 1);
				}
				if (eatTicks % 20 == 0) {
					level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), WLSounds.GRIZZLY_EATING.get(), SoundSource.NEUTRAL, 1, 1);
				}
				if (this.getTarget() != null) {
					this.eatTicks = 0;
					ItemStack itemstack1 = itemstack.finishUsingItem(this.level, this);
					this.setItemSlot(EquipmentSlot.MAINHAND, itemstack1);
					this.setSitting(false);
				}
			}
		}
		super.aiStep();
	}

	public boolean doHurtTarget(Entity entityIn) {
		boolean flag = entityIn.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, entityIn);
		}
		return flag;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 6.0D);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
		Grizzly entity = new Grizzly(WLEntities.GRIZZLY.get(), this.level);
		entity.finalizeSpawn(p_241840_1_, this.level.getCurrentDifficultyAt(new BlockPos((int) entity.getEyePosition().x, (int) entity.getEyePosition().y, (int) entity.getEyePosition().z)), MobSpawnType.BREEDING, (SpawnGroupData)null, (CompoundTag)null);
		return entity;
	}

	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		if (spawnDataIn == null) {
			spawnDataIn = new AgeableMob.AgeableMobGroupData(1.0F);
		}
		int sleepChance = random.nextInt(10);
		if (sleepChance == 0) {
			this.setFoodCount(10);
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public class HibernateGoal extends Goal {

		int tickTimer;

		@Override
		public boolean canUse() {
			if (Grizzly.this.getFoodCount() >= 10) {
				return true;
			} else return false;
		}

		public void tick() {
			super.tick();
			tickTimer++;
		}

		@Override
		public boolean canContinueToUse() {
			List<Player> players = Grizzly.this.level.getEntitiesOfClass(Player.class, Grizzly.this.getBoundingBox().inflate(5, 5, 5));
			if (!players.isEmpty()) {
				for (Player player : players) {
					if (!player.isCreative()) {
						return false;
					}
				}
			}
			if (tickTimer >= 96000 || Grizzly.this.getTarget() != null) {
				return false;
			} else return true;
		}

		@Override
		public void start() {
			Grizzly.this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
			Grizzly.this.setSleeping(true);
			Grizzly.this.setFoodCount(0);
		}

		public void stop() {
			Grizzly.this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25D);
			Grizzly.this.setSleeping(false);
			List<Player> players = Grizzly.this.level.getEntitiesOfClass(Player.class, Grizzly.this.getBoundingBox().inflate(5, 5, 5));
			if (!players.isEmpty()) {
				for (Player player : players) {
					Grizzly.this.setTarget(player);
				}
			}
		}

	}

	public class EatBerriesGoal extends MoveToBlockGoal {
		protected int field_220731_g;

		public EatBerriesGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
			super(Grizzly.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
		}

		public double acceptedDistance() {
			return 2.0D;
		}

		public boolean shouldRecalculatePath() {
			return this.tryTicks % 100 == 0;
		}

		/**
		 * Return true to set given position as destination
		 */
		protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
			BlockState blockstate = worldIn.getBlockState(pos);
			return blockstate.is(Blocks.SWEET_BERRY_BUSH) && blockstate.getValue(SweetBerryBushBlock.AGE) >= 2 || CaveVines.hasGlowBerries(blockstate);
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (this.isReachedTarget()) {
				if (this.field_220731_g >= 40) {
					this.onReachedTarget();
				} else {
					++this.field_220731_g;
				}
			}

			super.tick();
		}

		protected void onReachedTarget() {
			if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(Grizzly.this.level, Grizzly.this)) {
				BlockState blockstate = Grizzly.this.level.getBlockState(this.blockPos);
				if (blockstate.is(Blocks.SWEET_BERRY_BUSH)) {
					this.eatBerry(blockstate);
				} else if (CaveVines.hasGlowBerries(blockstate)) {
					this.pickGlowBerry(blockstate);
				}

			}
		}

		private void pickGlowBerry(BlockState p_148927_) {
			CaveVines.use(Grizzly.this, p_148927_, Grizzly.this.level, this.blockPos);
		}

		private void eatBerry(BlockState p_148929_) {
			if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(Grizzly.this.level, Grizzly.this)) {
				BlockState blockstate = Grizzly.this.level.getBlockState(this.blockPos);
				if (blockstate.is(Blocks.SWEET_BERRY_BUSH)) {
					int i = blockstate.getValue(SweetBerryBushBlock.AGE);
					blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1));
					int j = 1 + Grizzly.this.level.random.nextInt(2) + (i == 3 ? 1 : 0);
					ItemStack itemstack = Grizzly.this.getItemBySlot(EquipmentSlot.MAINHAND);
					if (itemstack.isEmpty()) {
						Grizzly.this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
						--j;
					}

					if (j > 0) {
						Block.popResource(Grizzly.this.level, this.blockPos, new ItemStack(Items.SWEET_BERRIES, j));
					}

					Grizzly.this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1.0F, 1.0F);
					Grizzly.this.level.setBlock(this.blockPos, blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
				}
			}
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean canUse() {
			return !Grizzly.this.isSleeping() && super.canUse();
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			this.field_220731_g = 0;
			super.start();
		}

		public void stop() {
			super.stop();
		}
	}

	public class EatHoneyGoal extends MoveToBlockGoal {
		protected int field_220731_g;

		public EatHoneyGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
			super(Grizzly.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
		}

		public double acceptedDistance() {
			return 2.0D;
		}

		public boolean shouldRecalculatePath() {
			return this.tryTicks % 100 == 0;
		}

		/**
		 * Return true to set given position as destination
		 */
		protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
			BlockState blockstate = worldIn.getBlockState(pos);
			return (blockstate.is(Blocks.BEEHIVE) && blockstate.getValue(BeehiveBlock.HONEY_LEVEL) >= 5) ||  (blockstate.is(Blocks.BEE_NEST) && blockstate.getValue(BeehiveBlock.HONEY_LEVEL) >= 5);
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (this.isReachedTarget()) {
				if (this.field_220731_g >= 40) {
					this.eatHoney();
				} else {
					++this.field_220731_g;
				}
			}

			super.tick();
		}

		protected void eatHoney() {
			if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(Grizzly.this.level, Grizzly.this)) {
				BlockState blockstate = Grizzly.this.level.getBlockState(this.blockPos);
				if (blockstate.is(Blocks.BEEHIVE) || blockstate.is(Blocks.BEE_NEST)) {
					int i = blockstate.getValue(BeehiveBlock.HONEY_LEVEL);
					blockstate.setValue(BeehiveBlock.HONEY_LEVEL, Integer.valueOf(1));
					int j = 1 + Grizzly.this.level.random.nextInt(2) + (i == 3 ? 1 : 0);
					ItemStack itemstack = Grizzly.this.getItemBySlot(EquipmentSlot.MAINHAND);
					if (itemstack.isEmpty()) {
						Grizzly.this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.HONEYCOMB));
						Grizzly.this.setHoney(true);
						--j;
					}

					if (j > 0) {
						Block.popResource(Grizzly.this.level, this.blockPos, new ItemStack(Items.HONEYCOMB, j));
					}
					Grizzly.this.level.setBlock(this.blockPos, blockstate.setValue(BeehiveBlock.HONEY_LEVEL, Integer.valueOf(1)), 2);
				}
			}
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean canUse() {
			return !Grizzly.this.isSleeping() && super.canUse();
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			this.field_220731_g = 0;
			super.start();
		}

		public void stop() {
			super.stop();
		}
	}

	class GrizzlyMeleeAttackGoal extends MeleeAttackGoal {
		public GrizzlyMeleeAttackGoal() {
			super(Grizzly.this, 1.25D, true);
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

				if (this.getTicksUntilNextAttack() <= 10) {
					Grizzly.this.playWarningSound();
				}
			} else {
				this.resetAttackCooldown();
			}

		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void stop() {
			super.stop();
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double)(8.0F + attackTarget.getBbWidth());
		}
	}

	class GrizzlyPanicGoal extends PanicGoal {
		public GrizzlyPanicGoal() {
			super(Grizzly.this, 2.0D);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean canUse() {
			return !Grizzly.this.isBaby() && !Grizzly.this.isOnFire() ? false : super.canUse();
		}
	}

	class AttackPlayerGoal extends NearestAttackableTargetGoal<Player> {
		public AttackPlayerGoal() {
			super(Grizzly.this, Player.class, 20, true, true, (Predicate<LivingEntity>)null);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean canUse() {
			if (Grizzly.this.isBaby()) {
				return false;
			} else {
				if (super.canUse()) {
					for(Grizzly polarbearentity : Grizzly.this.level.getEntitiesOfClass(Grizzly.class, Grizzly.this.getBoundingBox().inflate(8.0D, 4.0D, 8.0D))) {
						if (polarbearentity.isBaby()) {
							return true;
						}
					}
				}

				return false;
			}
		}

		protected double getFollowDistance() {
			return super.getFollowDistance() * 0.5D;
		}
	}

	class GrizzlyHurtByTargetGoal extends HurtByTargetGoal {
		public GrizzlyHurtByTargetGoal() {
			super(Grizzly.this);
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			super.start();
			if (Grizzly.this.isBaby()) {
				this.alertOthers();
				this.stop();
			}

		}

		protected void alertOther(Mob mobIn, LivingEntity targetIn) {
			if (mobIn instanceof Grizzly && !mobIn.isBaby()) {
				super.alertOther(mobIn, targetIn);
			}

		}
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WLItems.GRIZZLY_BEAR_SPAWN_EGG.get());
	}

	class FindItemsGoal extends Goal {
		public FindItemsGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean canUse() {
			if (!Grizzly.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
				return false;
			} else if (Grizzly.this.getTarget() == null) {
				if (!Grizzly.this.isSleeping()) {
					return false;
				} else if (Grizzly.this.getRandom().nextInt(10) != 0) {
					return false;
				} else {
					List<ItemEntity> list = Grizzly.this.level.getEntitiesOfClass(ItemEntity.class, Grizzly.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
					return !list.isEmpty() && Grizzly.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
				}
			} else {
				return false;
			}
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			List<ItemEntity> list = Grizzly.this.level.getEntitiesOfClass(ItemEntity.class, Grizzly.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			ItemStack itemstack = Grizzly.this.getItemBySlot(EquipmentSlot.MAINHAND);
			if (itemstack.isEmpty() && !list.isEmpty()) {
				Grizzly.this.getNavigation().moveTo(list.get(0), (double)1.2F);
			}

		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			List<ItemEntity> list = Grizzly.this.level.getEntitiesOfClass(ItemEntity.class, Grizzly.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (!list.isEmpty()) {
				Grizzly.this.getNavigation().moveTo(list.get(0), (double)1.2F);
			}

		}
	}

	class GrizzlyLookRandomlyGoal extends RandomLookAroundGoal {

		public GrizzlyLookRandomlyGoal(Mob entitylivingIn) {
			super(entitylivingIn);
		}

		public boolean canUse() {
			return super.canUse() && !Grizzly.this.isSleeping();
		}

	}

}
