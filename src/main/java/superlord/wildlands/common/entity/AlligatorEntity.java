package superlord.wildlands.common.entity;

import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.pathfinding.WalkAndSwimNodeProcessor;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;

public class AlligatorEntity extends AnimalEntity implements IAngerable {

	private static final DataParameter<Boolean> WARNING = EntityDataManager.createKey(AlligatorEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FLOATING = EntityDataManager.createKey(AlligatorEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> ALBINO = EntityDataManager.createKey(AlligatorEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> NORMAL = EntityDataManager.createKey(AlligatorEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> DARK = EntityDataManager.createKey(AlligatorEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> LIGHT = EntityDataManager.createKey(AlligatorEntity.class, DataSerializers.BOOLEAN);
	private int warningSoundTicks;
	private static final RangedInteger field_234217_by_ = TickRangeConverter.convertRange(20, 39);
	private int field_234218_bz_;
	private UUID field_234216_bA_;

	public AlligatorEntity(EntityType<? extends AlligatorEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new AlligatorEntity.MoveHelperController(this);
	}

	public boolean isWarning() {
		return this.dataManager.get(WARNING);
	}

	private void setWarning(boolean isWarning) {
		this.dataManager.set(WARNING, isWarning);
	}

	public boolean isFloating() {
		return this.dataManager.get(FLOATING);
	}

	private void setFloating(boolean isFloating) {
		this.dataManager.set(FLOATING, isFloating);
	}

	public boolean isAlbino() {
		return this.dataManager.get(ALBINO);
	}

	private void setAlbino(boolean isAlbino) {
		this.dataManager.set(ALBINO, isAlbino);
	}

	public boolean isNormal() {
		return this.dataManager.get(NORMAL);
	}

	private void setNormal(boolean isNormal) {
		this.dataManager.set(NORMAL, isNormal);
	}

	public boolean isLight() {
		return this.dataManager.get(LIGHT);
	}

	private void setLight(boolean isLight) {
		this.dataManager.set(LIGHT, isLight);
	}

	public boolean isDark() {
		return this.dataManager.get(DARK);
	}

	private void setDark(boolean isDark) {
		this.dataManager.set(DARK, isDark);
	}

	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == WildLandsItems.RAW_CATFISH.get();
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new BreatheAirGoal(this));
		this.goalSelector.addGoal(1, new AlligatorEntity.MeleeAttackGoal());
		this.goalSelector.addGoal(1, new AlligatorEntity.PanicGoal());
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(0, new AlligatorEntity.SwimGoal(this));
		this.targetSelector.addGoal(2, new AlligatorEntity.AttackPlayerGoal());
		this.targetSelector.addGoal(2, new AlligatorEntity.AttackCatfishGoal());
		this.targetSelector.addGoal(1, new AlligatorEntity.HurtByTargetGoal());
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, CatfishEntity.class, 10, true, false, this::func_233680_b_));
		this.targetSelector.addGoal(5, new ResetAngerGoal<>(this, false));
	}

	public int getMaxAir() {
		return 4800;
	}

	protected int determineNextAir(int currentAir) {
		return this.getMaxAir();
	}

	protected PathNavigator createNavigator(World worldIn) {
		return new AlligatorEntity.WalkAndSwimPathNavigator(this, worldIn);
	}
	
	@Override
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (heldItem.getItem() == Items.WATER_BUCKET && this.isAlive() && this.isChild()) {
        	playSound(SoundEvents.ITEM_BUCKET_FILL_FISH, 1.0F, 1.0F);
        	heldItem.shrink(1);
        	ItemStack itemstack1 = new ItemStack(WildLandsItems.ALLIGATOR_BUCKET.get());
        	this.setBucketData(itemstack1);
        	if (!this.world.isRemote) {
        		CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) player, itemstack1);
        	}
        	if(heldItem.isEmpty()) {
        		player.setHeldItem(hand, itemstack1);
        	} else if (!player.inventory.addItemStackToInventory(itemstack1)) {
        		player.dropItem(itemstack1, false);
        	}
        	this.remove();
        	return ActionResultType.SUCCESS;
        }
        return super.func_230254_b_(player, hand);
	}
	
	private void setBucketData(ItemStack bucket) {
		CompoundNBT nbt = bucket.getOrCreateTag();
		nbt.putBoolean("Albino", this.isAlbino());
		nbt.putBoolean("Normal", this.isNormal());
		nbt.putBoolean("Dark", this.isDark());
		nbt.putBoolean("Light", this.isLight());
		nbt.putInt("GrowingAge", this.getGrowingAge());
		if (this.hasCustomName()) {
			bucket.setDisplayName(this.getCustomName());
		}
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 45.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D);
	}

	public boolean notMoving() {
		if (this.isInWater() && this.getNavigator().noPath()) {
			this.setFloating(true);
			return true;
		} else {
			this.setFloating(false);
			return false;
		}
	}

	class WalkAndSwimPathNavigator extends SwimmerPathNavigator {
		WalkAndSwimPathNavigator(AlligatorEntity alligator, World worldIn) {
			super(alligator, worldIn);
		}

		/**
		 * If on ground or swimming and can swim
		 */
		protected boolean canNavigate() {
			return true;
		}

		protected PathFinder getPathFinder(int p_179679_1_) {
			this.nodeProcessor = new WalkAndSwimNodeProcessor();
			return new PathFinder(this.nodeProcessor, p_179679_1_);
		}

		@SuppressWarnings("deprecation")
		public boolean canEntityStandOnPos(BlockPos pos) {
			if (this.entity instanceof AlligatorEntity) {
				return this.world.getBlockState(pos).isIn(Blocks.WATER);
			}

			return !this.world.getBlockState(pos.down()).isAir();
		}
	}


	protected void registerData() {
		super.registerData();
		this.dataManager.register(WARNING, false);
		this.dataManager.register(FLOATING, false);
		this.dataManager.register(ALBINO, false);
		this.dataManager.register(NORMAL, false);
		this.dataManager.register(LIGHT, false);
		this.dataManager.register(DARK, false);
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.readAngerNBT((ServerWorld)this.world, compound);
		this.setWarning(compound.getBoolean("Warning"));
		this.setFloating(compound.getBoolean("Floating"));
		this.setAlbino(compound.getBoolean("Albino"));
		this.setLight(compound.getBoolean("Light"));
		this.setDark(compound.getBoolean("Dark"));
		this.setNormal(compound.getBoolean("Normal"));
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		this.writeAngerNBT(compound);
		compound.putBoolean("Warning", this.isWarning());
		compound.putBoolean("Floating", this.isFloating());
		compound.putBoolean("Albino", this.isAlbino());
		compound.putBoolean("Normal", this.isNormal());
		compound.putBoolean("Light", this.isLight());
		compound.putBoolean("Dark", this.isDark());
	}

	public void func_230258_H__() {
		this.setAngerTime(field_234217_by_.getRandomWithinRange(this.rand));
	}

	public void setAngerTime(int time) {
		this.field_234218_bz_ = time;
	}

	public int getAngerTime() {
		return this.field_234218_bz_;
	}

	public void setAngerTarget(@Nullable UUID target) {
		this.field_234216_bA_ = target;
	}

	public UUID getAngerTarget() {
		return this.field_234216_bA_;
	}


	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		int color = rand.nextInt(99);
		if (color <= 32) {
			this.setLight(true);
		} else if (color > 32 && color <= 65) {
			this.setNormal(true);
		} else if (color > 65 && color <= 98) {
			this.setDark(true);
		} else {
			this.setAlbino(true);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return WildLandsEntities.ALLIGATOR.get().create(p_241840_1_);
	}

	public void tick() {
		super.tick();
		if (this.warningSoundTicks > 0) {
			--this.warningSoundTicks;
		}

		if (!this.world.isRemote) {
			this.func_241359_a_((ServerWorld)this.world, true);
		}
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.applyEnchantments(this, entityIn);
		}

		return flag;
	}

	protected float getWaterSlowDown() {
		return 0.98F;
	}

	class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
		public HurtByTargetGoal() {
			super(AlligatorEntity.this);
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			super.startExecuting();
			if (AlligatorEntity.this.isChild()) {
				this.alertOthers();
				this.resetTask();
			}

		}

		protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
			if (mobIn instanceof AlligatorEntity && !mobIn.isChild()) {
				super.setAttackTarget(mobIn, targetIn);
			}

		}
	}

	class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {
		public MeleeAttackGoal() {
			super(AlligatorEntity.this, 1.25D, true);
		}

		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d0 = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d0 && this.func_234040_h_()) {
				this.func_234039_g_();
				this.attacker.attackEntityAsMob(enemy);
				AlligatorEntity.this.setWarning(false);
			} else if (distToEnemySqr <= d0 * 2.0D) {
				if (this.func_234040_h_()) {
					AlligatorEntity.this.setWarning(false);
					this.func_234039_g_();
				}

				if (this.func_234041_j_() <= 10) {
					AlligatorEntity.this.setWarning(true);
				}
			} else {
				this.func_234039_g_();
				AlligatorEntity.this.setWarning(false);
			}

		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void resetTask() {
			AlligatorEntity.this.setWarning(false);
			super.resetTask();
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double)(4.0F + attackTarget.getWidth());
		}
	}

	class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
		public PanicGoal() {
			super(AlligatorEntity.this, 2.0D);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			return !AlligatorEntity.this.isChild() && !AlligatorEntity.this.isBurning() ? false : super.shouldExecute();
		}
	}

	static class MoveHelperController extends MovementController {
		private final AlligatorEntity alligator;

		MoveHelperController(AlligatorEntity alligator) {
			super(alligator);
			this.alligator = alligator;
		}

		public void tick() {
			if (this.alligator.areEyesInFluid(FluidTags.WATER)) {
				this.alligator.setMotion(this.alligator.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.alligator.getNavigator().noPath()) {
				float f = (float)(this.speed * this.alligator.getAttributeValue(Attributes.MOVEMENT_SPEED));
				this.alligator.setAIMoveSpeed(MathHelper.lerp(0.125F, this.alligator.getAIMoveSpeed(), f));
				double d0 = this.posX - this.alligator.getPosX();
				double d1 = this.posY - this.alligator.getPosY();
				double d2 = this.posZ - this.alligator.getPosZ();
				if (d1 != 0.0D) {
					double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
					this.alligator.setMotion(this.alligator.getMotion().add(0.0D, (double)this.alligator.getAIMoveSpeed() * (d1 / d3) * 0.1D, 0.0D));
				}

				if (d0 != 0.0D || d2 != 0.0D) {
					float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
					this.alligator.rotationYaw = this.limitAngle(this.alligator.rotationYaw, f1, 90.0F);
					this.alligator.renderYawOffset = this.alligator.rotationYaw;
				}

			} else {
				this.alligator.setAIMoveSpeed(0.0F);
			}
		}
	}

	protected boolean func_212800_dy() {
		return true;
	}

	static class SwimGoal extends RandomSwimmingGoal {
		private final AlligatorEntity alligator;

		public SwimGoal(AlligatorEntity alligator) {
			super(alligator, 1.0D, 40);
			this.alligator = alligator;
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			return this.alligator.func_212800_dy() && super.shouldExecute();
		}
	}

	class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
		public AttackPlayerGoal() {
			super(AlligatorEntity.this, PlayerEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		@SuppressWarnings("unused")
		public boolean shouldExecute() {
			if (AlligatorEntity.this.isChild()) {
				return false;
			} else {
				if (super.shouldExecute()) {
					for(AlligatorEntity alligatorEntity : AlligatorEntity.this.world.getEntitiesWithinAABB(AlligatorEntity.class, AlligatorEntity.this.getBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
						return true;
					}
				}

				return false;
			}
		}

		protected double getTargetDistance() {
			return super.getTargetDistance() * 0.5D;
		}
	}

	class AttackCatfishGoal extends NearestAttackableTargetGoal<CatfishEntity> {
		public AttackCatfishGoal() {
			super(AlligatorEntity.this, CatfishEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		@SuppressWarnings("unused")
		public boolean shouldExecute() {
			if (AlligatorEntity.this.isChild()) {
				return false;
			} else {
				if (super.shouldExecute()) {
					for(AlligatorEntity alligatorEntity : AlligatorEntity.this.world.getEntitiesWithinAABB(AlligatorEntity.class, AlligatorEntity.this.getBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
						return true;
					}
				}

				return false;
			}
		}

		protected double getTargetDistance() {
			return super.getTargetDistance() * 0.5D;
		}
	}

}
