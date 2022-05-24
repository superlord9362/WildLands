package superlord.wildlands.common.entity;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLItems;
import superlord.wildlands.init.WLSounds;

public class SeaLion extends Animal {

	public int ticksSinceEaten;

	@SuppressWarnings("deprecation")
	public SeaLion(EntityType<? extends SeaLion> type, Level worldIn) {
		super(type, worldIn);
		this.setCanPickUpLoot(true);
		this.moveControl = new SeaLion.MoveHelperController(this);
		this.maxUpStep = 1.0F;
	}


	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new SeaLion.GoToWaterGoal(this, 1.2));
		this.goalSelector.addGoal(3, new SeaLion.FishingGoal(this));
		this.goalSelector.addGoal(1, new SeaLion.SwimGoal(this));
		this.goalSelector.addGoal(3, new SeaLion.FindItemsGoal());
		this.goalSelector.addGoal(0, new BreathAirGoal(this));
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
			this.ticksSinceEaten = 0;
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

	public void aiStep() {
		if (!this.level.isClientSide && this.isAlive() && this.isEffectiveAi()) {
			++this.ticksSinceEaten;
			ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
			if (this.canEatItem(itemstack)) {
				if (this.ticksSinceEaten > 100) {
					ItemStack itemstack1 = itemstack.finishUsingItem(this.level, this);
					if (!itemstack1.isEmpty()) {
						this.setItemSlot(EquipmentSlot.MAINHAND, itemstack1);
					}
					this.ticksSinceEaten = 0;
					itemstack.shrink(1);
				}
			}

		}
		super.aiStep();
	}

	private boolean canEatItem(ItemStack itemStackIn) {
		return itemStackIn.getItem() == WLItems.ANCHOVY.get();
	}

	public boolean canEquipItem(ItemStack stack) {
		ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
		return itemstack.isEmpty() && this.canEatItem(stack) && !this.isSleeping();
	}

	class FindItemsGoal extends Goal {
		public FindItemsGoal() {
	         this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {
			if (!SeaLion.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
				return false;
			} 
			if (SeaLion.this.getRandom().nextInt(reducedTickDelay(10)) != 0) {
				return false;
			} else {
				List<ItemEntity> list = SeaLion.this.level.getEntitiesOfClass(ItemEntity.class, SeaLion.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
				return !list.isEmpty() && SeaLion.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
			}
		}


		public void tick() {
			List<ItemEntity> list = SeaLion.this.level.getEntitiesOfClass(ItemEntity.class, SeaLion.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			ItemStack itemstack = SeaLion.this.getItemBySlot(EquipmentSlot.MAINHAND);
			if (itemstack.isEmpty() && !list.isEmpty()) {
				SeaLion.this.getNavigation().moveTo(list.get(0), (double)1.2F);
			}

		}

		public void start() {
			List<ItemEntity> list = SeaLion.this.level.getEntitiesOfClass(ItemEntity.class, SeaLion.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (!list.isEmpty()) {
				SeaLion.this.getNavigation().moveTo(list.get(0), (double)1.2F);
			}

		}
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WLItems.SEA_LION_SPAWN_EGG.get());
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldItem = player.getItemInHand(hand);
		if (heldItem.getItem() == WLItems.ANCHOVY.get() && this.isAlive() && this.hasItemInSlot(EquipmentSlot.MAINHAND)) {
			ItemStack seaLionItem = this.getMainHandItem();
			heldItem.shrink(1);
			int dropChance = this.random.nextInt(3);
			if(dropChance == 0) {
				this.spitOutItem(seaLionItem);
				this.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(WLItems.ANCHOVY.get()));
			}
		}
		return super.mobInteract(player, hand);
	}

	protected SoundEvent getAmbientSound() {
		return WLSounds.SEA_LION_IDLE;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WLSounds.SEA_LION_HURT;
	}

	protected SoundEvent getDeathSound() {
		return WLSounds.SEA_LION_DEATH;
	}

	class WalkAndSwimPathNavigator extends WaterBoundPathNavigation {
		WalkAndSwimPathNavigator(SeaLion sealion, Level worldIn) {
			super(sealion, worldIn);
		}

		protected boolean canUpdatePath() {
			return true;
		}

		protected PathFinder createPathFinder(int p_179679_1_) {
			this.nodeEvaluator = new AmphibiousNodeEvaluator(true);
			return new PathFinder(this.nodeEvaluator, p_179679_1_);
		}

		public boolean isStableDestination(BlockPos pos) {
			return !this.level.getBlockState(pos.below()).isAir();
		}
	}

	public int getMaxAir() {
		return 4800;
	}

	protected int determineNextAir(int currentAir) {
		return this.getMaxAir();
	}

	protected float getWaterSlowDown() {
		return 0.98F;
	}

	static class MoveHelperController extends MoveControl {
		private final SeaLion seaLion;

		MoveHelperController(SeaLion seaLion) {
			super(seaLion);
			this.seaLion = seaLion;
		}

		public void tick() {
			if (this.seaLion.isInWater()) {
				this.seaLion.setDeltaMovement(this.seaLion.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MoveControl.Operation.MOVE_TO && !this.seaLion.getNavigation().isDone()) {
				float f = (float)(this.speedModifier * this.seaLion.getAttributeValue(Attributes.MOVEMENT_SPEED));
				this.seaLion.setSpeed(Mth.lerp(0.125F, this.seaLion.getSpeed(), f));
				double d0 = this.wantedX - this.seaLion.getX();
				double d1 = this.wantedY - this.seaLion.getY();
				double d2 = this.wantedZ - this.seaLion.getZ();
				if (d1 != 0.0D) {
					double d3 = (double)Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
					this.seaLion.setDeltaMovement(this.seaLion.getDeltaMovement().add(0.0D, (double)this.seaLion.getSpeed() * (d1 / d3) * 0.1D, 0.0D));
				}

				if (d0 != 0.0D || d2 != 0.0D) {
					float f1 = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
					this.seaLion.yRotO = this.rotlerp(this.seaLion.getYRot(), f1, 90.0F);
					this.seaLion.yBodyRot = this.seaLion.getYRot();
				}

			} else {
				this.seaLion.setSpeed(0.0F);
			}
		}
	}

	static class SwimGoal extends RandomSwimmingGoal {
		private final SeaLion seaLion;

		public SwimGoal(SeaLion seaLion) {
			super(seaLion, 1.0D, 40);
			this.seaLion = seaLion;
		}

		public boolean canUse() {
			return this.seaLion.func_212800_dy() && super.canUse();
		}
	}

	protected boolean func_212800_dy() {
		return true;
	}

	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == Items.TROPICAL_FISH;
	}

	public static boolean canSeaLionSpawn(EntityType<? extends Animal> animal, LevelAccessor levelIn, MobSpawnType reason, BlockPos pos, Random random) {
		return (levelIn.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || levelIn.getBlockState(pos.below()).is(net.minecraftforge.common.Tags.Blocks.SAND)) && isBrightEnoughToSpawn(levelIn, pos);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	protected PathNavigation createNavigation(Level worldIn) {
		return new SeaLion.WalkAndSwimPathNavigator(this, worldIn);
	}

	static class GoToWaterGoal extends MoveToBlockGoal {
		private final SeaLion seaLion;

		private GoToWaterGoal(SeaLion seaLion, double speedIn) {
			super(seaLion, seaLion.isBaby() ? 2.0D : speedIn, 24);
			this.seaLion = seaLion;
			this.verticalSearchStart = -1;
		}

		public boolean canContinueToUse() {
			return !this.seaLion.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.seaLion.level, this.blockPos);
		}

		public boolean canUse() {
			if (this.seaLion.isBaby() && !this.seaLion.isInWater()) {
				return super.canUse();
			} else {
				return !this.seaLion.isInWater() ? super.canUse() : false;
			}
		}

		public boolean shouldRecalculatePath() {
			return this.tryTicks % 160 == 0;
		}

		protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
			return worldIn.getBlockState(pos).is(Blocks.WATER);
		}
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
		SeaLion entity = new SeaLion(WLEntities.SEA_LION.get(), this.level);
		entity.finalizeSpawn(level, this.level.getCurrentDifficultyAt(new BlockPos(entity.getEyePosition())), MobSpawnType.BREEDING, (SpawnGroupData)null, (CompoundTag)null);
		return entity;
	}

	static class FishingGoal extends Goal {
		private static final ResourceLocation FISHING_LOOT = new ResourceLocation("gameplay/fishing");
		private static final Predicate<BlockState> IS_WATER = BlockStatePredicate.forBlock(Blocks.WATER);

		private final SeaLion entity;
		private int fishingTimer;
		private int fishTimer;

		public FishingGoal(SeaLion entity) {
			this.entity = entity;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			if (fishTimer > 0) {
				--fishTimer;
				return false;
			}
			if (entity.getRandom().nextInt(entity.isBaby() ? 100 : 1000) != 0) {
				return false;
			} else {
				BlockPos blockpos = entity.blockPosition();
				if (IS_WATER.test(entity.level.getBlockState(blockpos))) {
					return true;
				} else {
					return entity.level.getBlockState(blockpos.below()).is(Blocks.WATER);
				}
			}
		}

		@Override
		public void start() {
			fishingTimer = 40;
			fishTimer = 6000;
			entity.level.broadcastEntityEvent(entity, (byte) 10);
			entity.getNavigation().stop();
		}

		@Override
		public void stop() {
			fishingTimer = 0;
		}

		@Override
		public boolean canContinueToUse() {
			return fishingTimer > 0;
		}

		@Override
		public void tick() {
			if (fishTimer > 0) {
				--fishTimer;
			}
			if (fishingTimer > 0) {
				--fishingTimer;
			}
			if (fishingTimer == 25) {
				BlockPos blockpos = entity.blockPosition();
				BlockPos blockpos1 = blockpos.below();
				if (entity.level.getBlockState(blockpos1).is(Blocks.WATER)) {
					MinecraftServer server = entity.level.getServer();
					if (server != null) {
						List<ItemStack> items = server.getLootTables().get(FISHING_LOOT).getRandomItems(new LootContext.Builder((ServerLevel) entity.level).withRandom(entity.getRandom()).create(LootContextParamSet.builder().build()));
						Containers.dropContents(entity.level, blockpos, NonNullList.of(ItemStack.EMPTY, items.toArray(new ItemStack[0])));
					}
				}
			}
		}
	}

}
