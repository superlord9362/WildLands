package superlord.wildlands.entity;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import superlord.wildlands.block.CrabBlock;

public class CrabEntity extends CreatureEntity {

	public CrabEntity(EntityType<? extends CrabEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void registerGoals() {
	      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(5, new CrabEntity.HideInSandGoal(this));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
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
	
	static class HideInSandGoal extends RandomWalkingGoal {
	      private Direction facing;
	      private boolean doMerge;

	      public HideInSandGoal(CrabEntity crabEntity) {
	         super(crabEntity, 1.0D, 10);
	         this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	      }

	      public boolean shouldExecute() {
	         if (this.creature.getAttackTarget() != null) {
	            return false;
	         } else if (!this.creature.getNavigator().noPath()) {
	            return false;
	         } else {
	            Random random = this.creature.getRNG();
	            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.creature.world, this.creature) && random.nextInt(10) == 0) {
	               this.facing = Direction.getRandomDirection(random);
	               BlockPos blockpos = (new BlockPos(this.creature.getPosX(), this.creature.getPosY() + 0.5D, this.creature.getPosZ())).offset(this.facing);
	               BlockState blockstate = this.creature.world.getBlockState(blockpos);
	               if (CrabBlock.canContainCrab(blockstate)) {
	                  this.doMerge = true;
	                  return true;
	               }
	            }

	            this.doMerge = false;
	            return super.shouldExecute();
	         }
	      }

	      public boolean shouldContinueExecuting() {
	         return this.doMerge ? false : super.shouldContinueExecuting();
	      }

	      public void startExecuting() {
	         if (!this.doMerge) {
	            super.startExecuting();
	         } else {
	            IWorld iworld = this.creature.world;
	            BlockPos blockpos = (new BlockPos(this.creature.getPosX(), this.creature.getPosY() + 0.5D, this.creature.getPosZ())).offset(this.facing);
	            BlockState blockstate = iworld.getBlockState(blockpos);
	            if (CrabBlock.canContainCrab(blockstate)) {
	               iworld.setBlockState(blockpos, CrabBlock.burrow(blockstate.getBlock()), 3);
	               this.creature.spawnExplosionParticle();
	               this.creature.remove();
	            }

	         }
	      }
	   }

}
