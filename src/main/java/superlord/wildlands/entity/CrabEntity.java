package superlord.wildlands.entity;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import superlord.wildlands.block.CrabBlock;

public class CrabEntity extends AnimalEntity {

	public CrabEntity(EntityType<? extends CrabEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new CrabEntity.HideInSandGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
		this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1.0D));
	}


	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 12.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.applyEnchantments(this, entityIn);
		}
		return flag;
	}

	protected float getWaterSlowDown() {
		return 0;
	}

	public boolean shouldHideFromEntity(PlayerEntity player) {
		Vector3d vector3d = player.getLook(1.0F).normalize();
		Vector3d vector3d1 = new Vector3d(this.getPosX() - player.getPosX(), this.getPosYEye() - player.getPosYEye(), this.getPosZ() - player.getPosZ());
		double d0 = vector3d1.length();
		vector3d1 = vector3d1.normalize();
		double d1 = vector3d.dotProduct(vector3d1);
		return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
	}

	static class HideInSandGoal extends Goal {
		private final CrabEntity crab;
		private LivingEntity targetPlayer;
		private Direction facing;
		private boolean doMerge;

		public HideInSandGoal(CrabEntity crab) {
			this.crab = crab;
			this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			this.targetPlayer = this.crab.getAttackTarget();
	         if (!(this.targetPlayer instanceof PlayerEntity)) {
	            return false;
	         } else {
	            double d0 = this.targetPlayer.getDistanceSq(this.crab);
	            if (d0 > 256.0D) {
	            	return false;
	            } else if (this.crab.shouldHideFromEntity((PlayerEntity)this.targetPlayer)) {
	            	Random random = this.crab.getRNG();
					if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.crab.world, this.crab) && random.nextInt(10) == 0) {
						this.facing = Direction.getRandomDirection(random);
						BlockPos blockpos = (new BlockPos(this.crab.getPosX(), this.crab.getPosY() + 0.5D, this.crab.getPosZ())).offset(this.facing);
						BlockState blockstate = this.crab.world.getBlockState(blockpos);
						if (CrabBlock.canContainCrab(blockstate)) {
							this.doMerge = true;
							return true;
						}
					}
					this.doMerge = false;
					return false;
				}
	         }
			return doMerge;
		}

		public void startExecuting() {
			if (!this.doMerge) {
				super.startExecuting();
			} else {
				IWorld iworld = this.crab.world;
				BlockPos blockpos = (new BlockPos(this.crab.getPosX(), this.crab.getPosY() + 0.5D, this.crab.getPosZ())).offset(this.facing);
				BlockState blockstate = iworld.getBlockState(blockpos);
				if (CrabBlock.canContainCrab(blockstate)) {
					iworld.setBlockState(blockpos, CrabBlock.burrow(blockstate.getBlock()), 3);
					this.crab.spawnExplosionParticle();
					this.crab.remove();
				}

			}
		}

	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return null;
	}
}