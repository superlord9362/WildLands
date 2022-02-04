package superlord.wildlands.common.entity;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.pathfinding.WalkAndSwimNodeProcessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class SeaLionEntity extends AnimalEntity {
	public int eatTicks;

	public SeaLionEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new SeaLionEntity.MoveHelperController(this);
	}
	
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new SeaLionEntity.GoToWaterGoal(this, 1.2));
		this.goalSelector.addGoal(3, new SeaLionEntity.FishingGoal(this));
		this.goalSelector.addGoal(1, new SeaLionEntity.SwimGoal(this));
		this.goalSelector.addGoal(3, new SeaLionEntity.FindItemsGoal());
		this.goalSelector.addGoal(0, new BreatheAirGoal(this));
	}
	
	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		ItemStack heldItem = player.getHeldItem(hand);
		if (heldItem.getItem() == WildLandsItems.ANCHOVY.get() && this.isAlive() && this.hasItemInSlot(EquipmentSlotType.MAINHAND)) {
			ItemStack seaLionItem = this.getHeldItemMainhand();
			heldItem.shrink(1);
			int dropChance = this.rand.nextInt(3);
			if(dropChance == 0) {
				this.spawnItem(seaLionItem);
				this.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.AIR));
			}
		}
		return super.func_230254_b_(player, hand);
	}
	
	protected SoundEvent getAmbientSound() {
		return WildLandsSounds.SEA_LION_IDLE;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.SEA_LION_HURT;
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.SEA_LION_DEATH;
	}
	
	class WalkAndSwimPathNavigator extends SwimmerPathNavigator {
		WalkAndSwimPathNavigator(SeaLionEntity seaLion, World worldIn) {
			super(seaLion, worldIn);
		}
		protected boolean canNavigate() {
			return true;
		}

		protected PathFinder getPathFinder(int p_179679_1_) {
			this.nodeProcessor = new WalkAndSwimNodeProcessor();
			return new PathFinder(this.nodeProcessor, p_179679_1_);
		}

		@SuppressWarnings("deprecation")
		public boolean canEntityStandOnPos(BlockPos pos) {
			if (this.entity instanceof SeaLionEntity) {
				return this.world.getBlockState(pos).isIn(Blocks.WATER);
			}

			return !this.world.getBlockState(pos.down()).isAir();
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
	
	static class MoveHelperController extends MovementController {
		private final SeaLionEntity seaLion;

		MoveHelperController(SeaLionEntity seaLion) {
			super(seaLion);
			this.seaLion = seaLion;
		}

		public void tick() {
			if (this.seaLion.areEyesInFluid(FluidTags.WATER)) {
				this.seaLion.setMotion(this.seaLion.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.seaLion.getNavigator().noPath()) {
				float f = (float)(this.speed * this.seaLion.getAttributeValue(Attributes.MOVEMENT_SPEED));
				this.seaLion.setAIMoveSpeed(MathHelper.lerp(0.125F, this.seaLion.getAIMoveSpeed(), f));
				double d0 = this.posX - this.seaLion.getPosX();
				double d1 = this.posY - this.seaLion.getPosY();
				double d2 = this.posZ - this.seaLion.getPosZ();
				if (d1 != 0.0D) {
					double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
					this.seaLion.setMotion(this.seaLion.getMotion().add(0.0D, (double)this.seaLion.getAIMoveSpeed() * (d1 / d3) * 0.1D, 0.0D));
				}

				if (d0 != 0.0D || d2 != 0.0D) {
					float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
					this.seaLion.rotationYaw = this.limitAngle(this.seaLion.rotationYaw, f1, 90.0F);
					this.seaLion.renderYawOffset = this.seaLion.rotationYaw;
				}

			} else {
				this.seaLion.setAIMoveSpeed(0.0F);
			}
		}
	}
	
	static class SwimGoal extends RandomSwimmingGoal {
		private final SeaLionEntity seaLion;

		public SwimGoal(SeaLionEntity seaLion) {
			super(seaLion, 1.0D, 40);
			this.seaLion = seaLion;
		}

		public boolean shouldExecute() {
			return this.seaLion.func_212800_dy() && super.shouldExecute();
		}
	}
	
	protected boolean func_212800_dy() {
		return true;
	}
	
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == Items.TROPICAL_FISH;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 30.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	protected PathNavigator createNavigator(World worldIn) {
		return new SeaLionEntity.WalkAndSwimPathNavigator(this, worldIn);
	}


	private void spawnItem(ItemStack stackIn) {
		ItemEntity itementity = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), stackIn);
		this.world.addEntity(itementity);
	}

	protected void updateEquipmentIfNeeded(ItemEntity itemEntity) {
		ItemStack itemstack = itemEntity.getItem();
		if (this.canEquipItem(itemstack)) {
			int i = itemstack.getCount();
			if (i > 1) {
				this.spawnItem(itemstack.split(i - 1));
			}
			this.triggerItemPickupTrigger(itemEntity);
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, itemstack.split(1));
			this.inventoryHandsDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
			this.onItemPickup(itemEntity, itemstack.getCount());
			itemEntity.remove();
			this.eatTicks = 0;
		}

	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 45) {
			ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			if (!itemstack.isEmpty()) {
				for(int i = 0; i < 8; ++i) {
					Vector3d vector3d = (new Vector3d(((double)this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).rotatePitch(-this.rotationPitch * ((float)Math.PI / 180F)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F));
					this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, itemstack), this.getPosX() + this.getLookVec().x / 2.0D, this.getPosY(), this.getPosZ() + this.getLookVec().z / 2.0D, vector3d.x, vector3d.y + 0.05D, vector3d.z);
				}
			}
		} else {
			super.handleStatusUpdate(id);
		}

	}

	public void livingTick() {
		if (!this.world.isRemote && this.isAlive() && this.isServerWorld()) {
			++this.eatTicks;
			ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
			ItemStack air = Items.AIR.getDefaultInstance();
			if (this.canEatItem(itemstack)) {
				if (this.eatTicks > 100) {
					this.eatTicks = 0;
					itemstack.shrink(1);
					this.setItemStackToSlot(EquipmentSlotType.MAINHAND, air);
				}
			}

		}
		super.livingTick();
	}

	private boolean canEatItem(ItemStack itemStackIn) {
		return itemStackIn.getItem().isFood();
	}

	static class GoToWaterGoal extends MoveToBlockGoal {
		private final SeaLionEntity seaLion;

		private GoToWaterGoal(SeaLionEntity seaLion, double speedIn) {
			super(seaLion, seaLion.isChild() ? 2.0D : speedIn, 24);
			this.seaLion = seaLion;
			this.field_203112_e = -1;
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting() {
			return !this.seaLion.isInWater() && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.seaLion.world, this.destinationBlock);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean shouldExecute() {
			if (this.seaLion.isChild() && !this.seaLion.isInWater()) {
				return super.shouldExecute();
			} else {
				return !this.seaLion.isInWater() ? super.shouldExecute() : false;
			}
		}

		public boolean shouldMove() {
			return this.timeoutCounter % 160 == 0;
		}

		/**
		 * Return true to set given position as destination
		 */
		protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
			return worldIn.getBlockState(pos).isIn(Blocks.WATER);
		}
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		SeaLionEntity entity = new SeaLionEntity(WildLandsEntities.SEA_LION.get(), this.world);
		entity.onInitialSpawn(p_241840_1_, this.world.getDifficultyForLocation(new BlockPos(entity.getPositionVec())), SpawnReason.BREEDING, (ILivingEntityData)null, (CompoundNBT)null);
		return entity;
	}
	
	static class FishingGoal extends Goal {
		private static final ResourceLocation FISHING_LOOT = new ResourceLocation("gameplay/fishing");
        private static final Predicate<BlockState> IS_WATER = BlockStateMatcher.forBlock(Blocks.WATER);
		
		private final SeaLionEntity entity;
		private int fishingTimer;
		private int fishTimer;
		
		public FishingGoal(SeaLionEntity entity) {
			this.entity = entity;
			setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean shouldExecute() {
			if (fishTimer > 0) {
				--fishTimer;
				return false;
			}
			if (entity.getRNG().nextInt(entity.isChild() ? 100 : 1000) != 0) {
				return false;
			} else {
				BlockPos blockpos = entity.getPosition();
				if (IS_WATER.test(entity.world.getBlockState(blockpos))) {
					return true;
				} else {
					return entity.world.getBlockState(blockpos.down()).isIn(Blocks.WATER);
				}
			}
		}
		
		@Override
		public void startExecuting() {
			fishingTimer = 40;
			fishTimer = 6000;
			entity.world.setEntityState(entity, (byte) 10);
			entity.getNavigator().clearPath();
		}
		
		@Override
		public void resetTask() {
			fishingTimer = 0;
		}
		
		@Override
		public boolean shouldContinueExecuting() {
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
				BlockPos blockpos = entity.getPosition();
				BlockPos blockpos1 = blockpos.down();
				if (entity.world.getBlockState(blockpos1).isIn(Blocks.WATER)) {
					entity.world.playEvent(2001, blockpos1, Block.getStateId(Blocks.WATER.getDefaultState()));
					MinecraftServer server = entity.world.getServer();
					if (server != null) {
						List<ItemStack> items = server.getLootTableManager().getLootTableFromLocation(FISHING_LOOT).generate(new LootContext.Builder((ServerWorld) entity.world).withRandom(entity.getRNG()).build(LootParameterSets.EMPTY));
						InventoryHelper.dropItems(entity.world, blockpos, NonNullList.from(ItemStack.EMPTY, items.toArray(new ItemStack[0])));
					}
				}
			}
		}
	}
	
	class FindItemsGoal extends Goal {
	      public FindItemsGoal() {
	         this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	      }

	      /**
	       * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
	       * method as well.
	       */
	      public boolean shouldExecute() {
	         if (!SeaLionEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty()) {
	            return false;
	         } else if (SeaLionEntity.this.getAttackTarget() == null && SeaLionEntity.this.getRevengeTarget() == null) {
	        	 if (SeaLionEntity.this.getRNG().nextInt(10) != 0) {
	               return false;
	            } else {
	               List<ItemEntity> list = SeaLionEntity.this.world.getEntitiesWithinAABB(ItemEntity.class, SeaLionEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D));
	               return !list.isEmpty() && SeaLionEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty();
	            }
	         } else {
	            return false;
	         }
	      }

	      /**
	       * Keep ticking a continuous task that has already been started
	       */
	      public void tick() {
	         List<ItemEntity> list = SeaLionEntity.this.world.getEntitiesWithinAABB(ItemEntity.class, SeaLionEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D));
	         ItemStack itemstack = SeaLionEntity.this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
	         if (itemstack.isEmpty() && !list.isEmpty()) {
	        	 SeaLionEntity.this.getNavigator().tryMoveToEntityLiving(list.get(0), (double)1.2F);
	         }

	      }

	      /**
	       * Execute a one shot task or start executing a continuous task
	       */
	      public void startExecuting() {
	         List<ItemEntity> list = SeaLionEntity.this.world.getEntitiesWithinAABB(ItemEntity.class, SeaLionEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D));
	         if (!list.isEmpty()) {
	        	 SeaLionEntity.this.getNavigator().tryMoveToEntityLiving(list.get(0), (double)1.2F);
	         }

	      }
	   }
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(WildLandsItems.SEA_LION_SPAWN_EGG.get());
	}

}
