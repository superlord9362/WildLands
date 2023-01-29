package superlord.wildlands.common.entity;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import superlord.wildlands.init.WLItems;
import superlord.wildlands.init.WLSounds;


public class Hammerhead extends WaterAnimal {

	public Hammerhead(EntityType<? extends Hammerhead> type, Level worldIn) {
		super(type, worldIn);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}

	private static final Predicate<LivingEntity> UNDEAD_PREDICATE = (mob) -> {
		return mob.getMobType() == MobType.UNDEAD;
	};

	protected PathNavigation createNavigation(Level worldIn) {
		return new WaterBoundPathNavigation(this, worldIn);
	}

	public boolean doHurtTarget(Entity p_28319_) {
		boolean flag = p_28319_.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, p_28319_);
		}
		return flag;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new CirclePreyGoal(this, 1F));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 0.6F, 7));
		this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(9, new AvoidEntityGoal<Guardian>(this, Guardian.class, 8.0F, 1.0D, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(2, new WaterAttackGoal<>(this, LivingEntity.class, 50, false, true, UNDEAD_PREDICATE));
		this.targetSelector.addGoal(2, new WaterAttackGoal<>(this, AbstractSchoolingFish.class, 70, false, true, null));
		this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double)1.2F, true));
		this.goalSelector.addGoal(5, new HurtByTargetGoal(this));
	}

	protected SoundEvent getAmbientSound() {
		return WLSounds.HAMMERHEAD_IDLE.get();
	}

	protected SoundEvent getDeathSound() {
		return WLSounds.HAMMERHEAD_DEATH.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WLSounds.HAMMERHEAD_HURT.get();
	}

	private static class CirclePreyGoal extends Goal {
		Hammerhead shark;
		float circlingTime = 0;
		float circleDistance = 5;
		float maxCirclingTime = 80;
		boolean clockwise = false;

		public CirclePreyGoal(Hammerhead shark, float speed) {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
			this.shark = shark;
		}

		@Override
		public boolean canUse() {
			return this.shark.getTarget() != null;
		}

		@Override
		public boolean canContinueToUse() {
			return this.shark.getTarget() != null;
		}

		public void start(){
			circlingTime = 0;
			maxCirclingTime = 360 + this.shark.random.nextInt(80);
			circleDistance = 5 + this.shark.random.nextFloat() * 5;
			clockwise = this.shark.random.nextBoolean();
		}

		public void stop(){
			circlingTime = 0;
			maxCirclingTime = 360 + this.shark.random.nextInt(80);
			circleDistance = 5 + this.shark.random.nextFloat() * 5;
			clockwise = this.shark.random.nextBoolean();
		}

		public void tick(){
			LivingEntity prey = this.shark.getTarget();
			if(prey != null){
				double dist = this.shark.distanceToSqr(prey);
				if(circlingTime >= maxCirclingTime){
					shark.lookAt(prey, 30.0F, 30.0F);
					shark.getNavigation().moveTo(prey, 1.5D);
					if(dist < 2D){
						shark.doHurtTarget(prey);
						stop();
					}
				}else{
					if(dist <= 25){
						circlingTime++;
						BlockPos circlePos = getSharkCirclePos(prey);
						if(circlePos != null){
							shark.getNavigation().moveTo(circlePos.getX() + 0.5D, circlePos.getY() + 0.5D, circlePos.getZ() + 0.5D, 0.6D);
						}
					}else{
						shark.lookAt(prey, 30.0F, 30.0F);
						shark.getNavigation().moveTo(prey, 0.8D);
					}
				}
			}
		}

		public BlockPos getSharkCirclePos(LivingEntity target) {
			float angle = (0.01745329251F * (clockwise ? -circlingTime : circlingTime));
			double extraX = circleDistance * Mth.sin((angle));
			double extraZ = circleDistance * Mth.cos(angle);
			BlockPos ground = new BlockPos(target.getX() + 0.5F + extraX, shark.getY(), target.getZ() + 0.5F + extraZ);
			if(shark.level.getFluidState(ground).is(FluidTags.WATER)){
				return ground;

			}
			return null;
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.MOVEMENT_SPEED, (double)1.2F).add(Attributes.ATTACK_DAMAGE, 6.0D);
	}

	@SuppressWarnings("deprecation")
	public static boolean func_223364_b(EntityType<Hammerhead> hammerhead, LevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random) {
		if (pos.getY() > 45 && pos.getY() < level.getSeaLevel()) {
			return level.getFluidState(pos).is(FluidTags.WATER);
		} else {
			return false;
		}
	}

	public void travel(Vec3 p_28383_) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), p_28383_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(p_28383_);
		}

	}

	class WaterAttackGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

		public WaterAttackGoal(Mob goalOwner, Class<T> targetClass, boolean checkSight) {
			super(goalOwner, targetClass, checkSight);
		}

		public WaterAttackGoal(Mob goalOwner, Class<T> targetClass, boolean checkSight, boolean nearbyOnly) {
			super(goalOwner, targetClass, checkSight, nearbyOnly);
		}

		public WaterAttackGoal(Mob goalOwner, Class<T> targetClass, int targetChance, boolean checkSight, boolean nearbyOnly, @Nullable Predicate<LivingEntity> targetPredicate) {
			super(goalOwner, targetClass, targetChance, checkSight, nearbyOnly, targetPredicate);
		}

		protected AABB getTargetableArea(double targetDistance) {
			return this.mob.getBoundingBox().inflate(targetDistance, targetDistance, targetDistance);
		}

	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WLItems.HAMMERHEAD_SPAWN_EGG.get());
	}

}
