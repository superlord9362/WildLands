package superlord.wildlands.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import superlord.wildlands.init.WLItems;
import superlord.wildlands.init.WLSounds;

public class Catfish extends AbstractFish {

	public Catfish(EntityType<? extends Catfish> type, Level worldIn) {
		super(type, worldIn);
		this.moveControl = new Catfish.MoveHelperController(this);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<Player>(this, Player.class, 8.0F, 2.2D, 2.2D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<Alligator>(this, Alligator.class, 8.0F, 2.2D, 2.2D));
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(WLItems.CATFISH_BUCKET.get());
	}

	protected SoundEvent getAmbientSound() {
		return null;
		//return WLSounds.CATFISH_IDLE;
	}

	protected SoundEvent getDeathSound() {
		return WLSounds.CATFISH_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WLSounds.CATFISH_HURT;
	}

	protected SoundEvent getFlopSound() {
		return WLSounds.CATFISH_FLOP;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.MOVEMENT_SPEED, 0.15D);
	}

	public void travel(Vec3 p_27490_) {
	      if (this.isEffectiveAi() && this.isInWater()) {
	         this.moveRelative(0.01F, p_27490_);
	         this.move(MoverType.SELF, this.getDeltaMovement());
	         this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
	         if (this.getTarget() == null) {
	            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
	         }
	      } else {
	         super.travel(p_27490_);
	      }

	   }

	static class MoveHelperController extends MoveControl {
		private final Catfish fish;

		public MoveHelperController(Catfish catfish) {
			super(catfish);
			this.fish = catfish;
		}

		public void tick() {
			if (this.fish.isEyeInFluid(FluidTags.WATER)) {
				this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MoveControl.Operation.MOVE_TO && !this.fish.getNavigation().isDone()) {
				float f = (float)(this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
				this.fish.setSpeed(Mth.lerp(0.125F, this.fish.getSpeed(), f));
				double d0 = this.wantedX - this.fish.getX();
				double d1 = this.wantedY - this.fish.getY();
				double d2 = this.wantedZ - this.fish.getZ();
				if (d1 != 0.0D) {
					double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
					this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
				}

				if (d0 != 0.0D || d2 != 0.0D) {
					float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
					this.fish.setYRot(this.rotlerp(this.fish.getYRot(), f1, 90.0F));
					this.fish.yBodyRot = this.fish.getYRot();
				}

			} else {
				this.fish.setSpeed(0.0F);
			}
		}
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WLItems.CATFISH_SPAWN_EGG.get());
	}

}
