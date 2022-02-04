package superlord.wildlands.common.entity;

import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class CrabEntity extends AnimalEntity {

	private static final DataParameter<Boolean> FIGHTER = EntityDataManager.createKey(CrabEntity.class, DataSerializers.BOOLEAN);

	public CrabEntity(EntityType<? extends CrabEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void registerGoals() {
		this.targetSelector.addGoal(1, new CrabEntity.AttackPlayerGoal());
		this.targetSelector.addGoal(2, new CrabEntity.RunFromPlayerGoal());
		this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1.0D));
	}

	public boolean isFighter() {
		return this.dataManager.get(FIGHTER);
	}

	private void setFighter(boolean isFighter) {
		this.dataManager.set(FIGHTER, isFighter);
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		Random rand = new Random();
		int birthNumber = rand.nextInt(9);
		if (birthNumber == 0) {
			this.setFighter(true);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(FIGHTER, false);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("IsFighter", this.isFighter());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setFighter(compound.getBoolean("IsFighter"));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 6.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
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
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return null;
	}

	private boolean shouldAttackOrFlee(PlayerEntity player) {
		Vector3d vector3d = player.getLook(1.0F).normalize();
		Vector3d vector3d1 = new Vector3d(this.getPosX() - player.getPosX(), this.getPosYEye() - player.getPosYEye(), this.getPosZ() - player.getPosZ());
		double d0 = vector3d1.length();
		vector3d1 = vector3d1.normalize();
		double d1 = vector3d.dotProduct(vector3d1);
		return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
	}

	protected SoundEvent getAmbientSound() {
		return WildLandsSounds.CRAB_IDLE;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.CRAB_HURT;
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.CRAB_DEATH;
	}

	class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
		private LivingEntity targetPlayer;

		public AttackPlayerGoal() {
			super(CrabEntity.this, PlayerEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
		}

		public boolean shouldExecute() {
			this.targetPlayer = CrabEntity.this.getAttackTarget();
			if (!(this.targetPlayer instanceof PlayerEntity) || !CrabEntity.this.isFighter()) {
				return false;
			}else {
				double d0 = this.targetPlayer.getDistanceSq(CrabEntity.this);
				return d0 > 256.0D ? false : CrabEntity.this.shouldAttackOrFlee((PlayerEntity)this.targetPlayer);
			}
		}

		protected double getTargetDistance() {
			return super.getTargetDistance() * 0.5D;
		}
	}

	class RunFromPlayerGoal extends AvoidEntityGoal<PlayerEntity> {
		private LivingEntity targetPlayer;

		public RunFromPlayerGoal() {
			super(CrabEntity.this, PlayerEntity.class, 20, 1.3D, 1);
		}

		public boolean shouldExecute() {
			this.targetPlayer = this.avoidTarget;
			if (!(this.targetPlayer instanceof PlayerEntity) || CrabEntity.this.isFighter()) {
				return false;
			} else {
				double d0 = this.targetPlayer.getDistanceSq(CrabEntity.this);
				return d0 > 256.0D ? false : CrabEntity.this.shouldAttackOrFlee((PlayerEntity)this.targetPlayer);
			}
		}
		
		public void tick() {
			super.tick();
			System.out.println("RUN!");
		}
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(WildLandsItems.CRAB_SPAWN_EGG.get());
	}
}