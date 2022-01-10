package superlord.wildlands.common.entity;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import superlord.wildlands.common.entity.controllers.SwimMoveController;
import superlord.wildlands.init.WildLandsBlocks;

public class HammerheadEntity extends WaterMobEntity {

	public HammerheadEntity(EntityType<? extends HammerheadEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new SwimMoveController(this, 1F);
	}

	private static final Predicate<LivingEntity> UNDEAD_PREDICATE = (mob) -> {
		return mob.getCreatureAttribute() == CreatureAttribute.UNDEAD;
	};

	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new CirclePreyGoal(this, 1F));
		this.goalSelector.addGoal(1, new CirclePearlGoal(this, 1D));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 0.6F, 7));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, GuardianEntity.class, 8.0F, 1.0D, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(2, new WaterAttackGoal<>(this, LivingEntity.class, 50, false, true, UNDEAD_PREDICATE));
		this.targetSelector.addGoal(2, new WaterAttackGoal<>(this, AbstractGroupFishEntity.class, 70, false, true, null));
		this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double)1.2F, true));
		this.goalSelector.addGoal(5, new HurtByTargetGoal(this));
	}

	private static class CirclePreyGoal extends Goal {
		HammerheadEntity shark;
		float circlingTime = 0;
		float circleDistance = 5;
		float maxCirclingTime = 80;
		boolean clockwise = false;

		public CirclePreyGoal(HammerheadEntity shark, float speed) {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
			this.shark = shark;
		}

		@Override
		public boolean shouldExecute() {
			return this.shark.getAttackTarget() != null;
		}

		@Override
		public boolean shouldContinueExecuting() {
			return this.shark.getAttackTarget() != null;
		}

		public void startExecuting(){
			circlingTime = 0;
			maxCirclingTime = 360 + this.shark.rand.nextInt(80);
			circleDistance = 5 + this.shark.rand.nextFloat() * 5;
			clockwise = this.shark.rand.nextBoolean();
		}

		public void resetTask(){
			circlingTime = 0;
			maxCirclingTime = 360 + this.shark.rand.nextInt(80);
			circleDistance = 5 + this.shark.rand.nextFloat() * 5;
			clockwise = this.shark.rand.nextBoolean();
		}

		public void tick(){
			LivingEntity prey = this.shark.getAttackTarget();
			if(prey != null){
				double dist = this.shark.getDistance(prey);
				if(circlingTime >= maxCirclingTime){
					shark.faceEntity(prey, 30.0F, 30.0F);
					shark.getNavigator().tryMoveToEntityLiving(prey, 1.5D);
					if(dist < 2D){
						shark.attackEntityAsMob(prey);
						resetTask();
					}
				}else{
					if(dist <= 25){
						circlingTime++;
						BlockPos circlePos = getSharkCirclePos(prey);
						if(circlePos != null){
							shark.getNavigator().tryMoveToXYZ(circlePos.getX() + 0.5D, circlePos.getY() + 0.5D, circlePos.getZ() + 0.5D, 0.6D);
						}
					}else{
						shark.faceEntity(prey, 30.0F, 30.0F);
						shark.getNavigator().tryMoveToEntityLiving(prey, 0.8D);
					}
				}
			}
		}

		public BlockPos getSharkCirclePos(LivingEntity target) {
			float angle = (0.01745329251F * (clockwise ? -circlingTime : circlingTime));
			double extraX = circleDistance * MathHelper.sin((angle));
			double extraZ = circleDistance * MathHelper.cos(angle);
			BlockPos ground = new BlockPos(target.getPosX() + 0.5F + extraX, shark.getPosY(), target.getPosZ() + 0.5F + extraZ);
			if(shark.world.getFluidState(ground).isTagged(FluidTags.WATER)){
				return ground;

			}
			return null;
		}
	}

	@SuppressWarnings("unused")
	private static class CirclePearlGoal extends MoveToBlockGoal {
		HammerheadEntity shark;
		float circlingTime = 0;
		float circleDistance = 5;
		float maxCirclingTime = 80;
		boolean clockwise = false;

		public CirclePearlGoal(HammerheadEntity shark, double speed) {
			super(shark, speed, 4);
			this.shark = shark;
		}

		@Override
		public boolean shouldExecute() {
			return super.shouldExecute();
		}

		@Override
		public boolean shouldContinueExecuting() {
			return super.shouldContinueExecuting();
		}

		public void startExecuting(){
			circlingTime = 0;
			maxCirclingTime = 360 + this.shark.rand.nextInt(80);
			circleDistance = 5 + this.shark.rand.nextFloat() * 5;
			clockwise = this.shark.rand.nextBoolean();
		}

		public void resetTask(){
			circlingTime = 0;
			maxCirclingTime = 360 + this.shark.rand.nextInt(80);
			circleDistance = 5 + this.shark.rand.nextFloat() * 5;
			clockwise = this.shark.rand.nextBoolean();
		}

		public double getTargetDistanceSq() {
			return 2.0D;
		}

		public boolean shouldMove() {
			return this.timeoutCounter % 100 == 0;
		}
		
		 protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
			 BlockState blockstate = worldIn.getBlockState(pos);
			 return blockstate.getBlock() == WildLandsBlocks.PEARL.get();
		 }

		 public void tick(){
			 LivingEntity prey = this.shark.getAttackTarget();
			 if(prey != null){
				 double dist = this.getTargetDistanceSq();
					 if(dist <= 25){
						 circlingTime++;
						 BlockPos circlePos = getSharkCirclePos(this.destinationBlock);
						 if(circlePos != null){
							 shark.getNavigator().tryMoveToXYZ(circlePos.getX() + 0.5D, circlePos.getY() + 0.5D, circlePos.getZ() + 0.5D, 0.6D);
					 }
				 }
			 }
		 }

		 public BlockPos getSharkCirclePos(BlockPos pos) {
			 BlockPos ground = new BlockPos(this.destinationBlock);
			 if(shark.world.getFluidState(ground).isTagged(FluidTags.WATER)){
				 return ground;

			 }
			 return null;
		 }
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 30.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.35F).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
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

	class WaterAttackGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

		public WaterAttackGoal(MobEntity goalOwner, Class<T> targetClass, boolean checkSight) {
			super(goalOwner, targetClass, checkSight);
		}

		public WaterAttackGoal(MobEntity goalOwner, Class<T> targetClass, boolean checkSight, boolean nearbyOnly) {
			super(goalOwner, targetClass, checkSight, nearbyOnly);
		}

		public WaterAttackGoal(MobEntity goalOwner, Class<T> targetClass, int targetChance, boolean checkSight, boolean nearbyOnly, @Nullable Predicate<LivingEntity> targetPredicate) {
			super(goalOwner, targetClass, targetChance, checkSight, nearbyOnly, targetPredicate);
		}

		protected AxisAlignedBB getTargetableArea(double targetDistance) {
			return this.goalOwner.getBoundingBox().grow(targetDistance, targetDistance, targetDistance);
		}

	}

}
