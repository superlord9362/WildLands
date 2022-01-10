package superlord.wildlands.common.entity;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WildLandsItems;

public class CatfishEntity extends AbstractFishEntity {

	public CatfishEntity(EntityType<? extends CatfishEntity> type, World worldIn) {
		super(type, worldIn);
	      this.moveController = new CatfishEntity.MoveHelperController(this);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8.0F, 2.2D, 2.2D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, AlligatorEntity.class, 8.0F, 2.2D, 2.2D));
	}

	@Override
	protected ItemStack getFishBucket() {
		return new ItemStack(WildLandsItems.CATFISH_BUCKET.get());
	}

	@Override
	protected SoundEvent getFlopSound() {
		return SoundEvents.ENTITY_COD_FLOP;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 15.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	public static boolean func_223363_b(EntityType<? extends AbstractFishEntity> type, IWorld worldIn, SpawnReason reason, BlockPos p_223363_3_, Random randomIn) {
		return worldIn.getBlockState(p_223363_3_).isIn(Blocks.WATER) && worldIn.getBlockState(p_223363_3_.up()).isIn(Blocks.WATER);
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
	      private final CatfishEntity catfish;

	      public MoveHelperController(CatfishEntity catfish) {
	         super(catfish);
	         this.catfish = catfish;
	      }

	      public void tick() {
	         if (this.catfish.isInWater()) {
	            this.catfish.setMotion(this.catfish.getMotion().add(0.0D, 0.005D, 0.0D));
	         }

	         if (this.action == MovementController.Action.MOVE_TO && !this.catfish.getNavigator().noPath()) {
	            double d0 = this.posX - this.catfish.getPosX();
	            double d1 = this.posY - this.catfish.getPosY();
	            double d2 = this.posZ - this.catfish.getPosZ();
	            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
	            if (d3 < (double)2.5000003E-7F) {
	               this.mob.setMoveForward(0.0F);
	            } else {
	               float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
	               this.catfish.rotationYaw = this.limitAngle(this.catfish.rotationYaw, f, 10.0F);
	               this.catfish.renderYawOffset = this.catfish.rotationYaw;
	               this.catfish.rotationYawHead = this.catfish.rotationYaw;
	               float f1 = (float)(this.speed * this.catfish.getAttributeValue(Attributes.MOVEMENT_SPEED));
	               if (this.catfish.isInWater()) {
	                  this.catfish.setAIMoveSpeed(f1 * 0.02F);
	                  float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
	                  f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
	                  this.catfish.rotationPitch = this.limitAngle(this.catfish.rotationPitch, f2, 5.0F);
	                  float f3 = MathHelper.cos(this.catfish.rotationPitch * ((float)Math.PI / 180F));
	                  float f4 = MathHelper.sin(this.catfish.rotationPitch * ((float)Math.PI / 180F));
	                  this.catfish.moveForward = f3 * f1;
	                  this.catfish.moveVertical = -f4 * f1;
	               } else {
	                  this.catfish.setAIMoveSpeed(f1 * 0.1F);
	               }

	            }
	         } else {
	            this.catfish.setAIMoveSpeed(0.0F);
	            this.catfish.setMoveStrafing(0.0F);
	            this.catfish.setMoveVertical(0.0F);
	            this.catfish.setMoveForward(0.0F);
	         }
	      }
	   }

}
