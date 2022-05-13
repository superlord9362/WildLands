package superlord.wildlands.common.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import superlord.wildlands.init.WildLandsBlocks;
import superlord.wildlands.init.WildLandsItems;
import superlord.wildlands.init.WildLandsSounds;

public class OctopusEntity extends WaterAnimal {

	private static final EntityDataAccessor<Boolean> GRAVEL = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> SAND = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> GABBRO = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DOLERITE = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DIRT = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> BLUE_CORAL = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> YELLOW_CORAL = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> RED_CORAL = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> PURPLE_CORAL = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> PINK_CORAL = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DEFAULT = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> STONE = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> SCARED = SynchedEntityData.defineId(OctopusEntity.class, EntityDataSerializers.BOOLEAN);

	public OctopusEntity(EntityType<? extends OctopusEntity> type, Level p_i48565_2_) {
		super(type, p_i48565_2_);
	}
	
	protected SoundEvent getAmbientSound() {
		return WildLandsSounds.OCTOPUS_IDLE;
	}

	protected SoundEvent getDeathSound() {
		return WildLandsSounds.OCTOPUS_DEATH;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return WildLandsSounds.OCTOPUS_HURT;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(1, new OctopusEntity.CamoflaugeGoal(this));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<Player>(this, Player.class, 10, 1, 1.2F));
		this.goalSelector.addGoal(0, new OctopusEntity.SwimGoal(this));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(GRAVEL, false);
		this.entityData.define(SAND, false);
		this.entityData.define(GABBRO, false);
		this.entityData.define(DOLERITE, false);
		this.entityData.define(DIRT, false);
		this.entityData.define(BLUE_CORAL, false);
		this.entityData.define(YELLOW_CORAL, false);
		this.entityData.define(RED_CORAL, false);
		this.entityData.define(PURPLE_CORAL, false);
		this.entityData.define(PINK_CORAL, false);
		this.entityData.define(DEFAULT, false);
		this.entityData.define(STONE, false);
		this.entityData.define(SCARED, false);
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setOnGravel(compound.getBoolean("Gravel"));
		this.setOnSand(compound.getBoolean("Sand"));
		this.setOnGabbro(compound.getBoolean("Gabbro"));
		this.setOnDolerite(compound.getBoolean("Dolerite"));
		this.setOnDirt(compound.getBoolean("Dirt"));
		this.setOnBlueCoral(compound.getBoolean("BlueCoral"));
		this.setOnYellowCoral(compound.getBoolean("YellowCoral"));
		this.setOnRedCoral(compound.getBoolean("RedCoral"));
		this.setOnPurpleCoral(compound.getBoolean("PurpleCoral"));
		this.setOnPinkCoral(compound.getBoolean("PinkCoral"));
		this.setOnStone(compound.getBoolean("Stone"));
		this.setDefault(compound.getBoolean("Default"));
		this.setScared(compound.getBoolean("Scared"));
	}

	public static boolean canSpawn(EntityType<? extends OctopusEntity> type, LevelAccessor worldIn, MobSpawnType reason, BlockPos p_223363_3_, Random randomIn) {
		return worldIn.getBlockState(p_223363_3_).is(Blocks.WATER) && worldIn.getBlockState(p_223363_3_.above()).is(Blocks.WATER);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Gravel", this.isOnGravel());
		compound.putBoolean("Sand", this.isOnSand());
		compound.putBoolean("Gabbro", this.isOnGabbro());
		compound.putBoolean("Dolerite", this.isOnDolerite());
		compound.putBoolean("Dirt", this.isOnDirt());
		compound.putBoolean("BlueCoral", this.isOnBlueCoral());
		compound.putBoolean("YellowCoral", this.isOnYellowCoral());
		compound.putBoolean("RedCoral", this.isOnRedCoral());
		compound.putBoolean("PurpleCoral", this.isOnPurpleCoral());
		compound.putBoolean("PinkCoral", this.isOnPinkCoral());
		compound.putBoolean("Stone", this.isOnStone());
		compound.putBoolean("Default", this.isDefault());
		compound.putBoolean("Scared", this.isScared());
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.FOLLOW_RANGE, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	public boolean isOnGravel() {
		return this.entityData.get(GRAVEL);
	}

	private void setOnGravel(boolean inOnGravel) {
		this.entityData.set(GRAVEL, inOnGravel);
	}

	public boolean isOnSand() {
		return this.entityData.get(SAND);
	}

	private void setOnSand(boolean inOnSand) {
		this.entityData.set(SAND, inOnSand);
	}

	public boolean isOnGabbro() {
		return this.entityData.get(GABBRO);
	}

	private void setOnGabbro(boolean inOnGabbro) {
		this.entityData.set(GABBRO, inOnGabbro);
	}

	public boolean isScared() {
		return this.entityData.get(SCARED);
	}

	private void setScared(boolean isScared) {
		this.entityData.set(SCARED, isScared);
	}

	public boolean isOnDolerite() {
		return this.entityData.get(DOLERITE);
	}

	private void setOnDolerite(boolean inOnDolerite) {
		this.entityData.set(DOLERITE, inOnDolerite);
	}

	public boolean isOnDirt() {
		return this.entityData.get(DIRT);
	}

	private void setOnDirt(boolean inOnDirt) {
		this.entityData.set(DIRT, inOnDirt);
	}

	public boolean isOnBlueCoral() {
		return this.entityData.get(BLUE_CORAL);
	}

	private void setOnBlueCoral(boolean inOnBlueCoral) {
		this.entityData.set(BLUE_CORAL, inOnBlueCoral);
	}

	public boolean isOnYellowCoral() {
		return this.entityData.get(YELLOW_CORAL);
	}

	private void setOnYellowCoral(boolean inOnYellowCoral) {
		this.entityData.set(YELLOW_CORAL, inOnYellowCoral);
	}

	public boolean isOnRedCoral() {
		return this.entityData.get(RED_CORAL);
	}

	private void setOnRedCoral(boolean inOnRedCoral) {
		this.entityData.set(RED_CORAL, inOnRedCoral);
	}

	public boolean isOnPurpleCoral() {
		return this.entityData.get(PURPLE_CORAL);
	}

	private void setOnPurpleCoral(boolean inOnPurpleCoral) {
		this.entityData.set(PURPLE_CORAL, inOnPurpleCoral);
	}

	public boolean isOnPinkCoral() {
		return this.entityData.get(PINK_CORAL);
	}

	private void setOnPinkCoral(boolean inOnPinkCoral) {
		this.entityData.set(PINK_CORAL, inOnPinkCoral);
	}

	public boolean isDefault() {
		return this.entityData.get(DEFAULT);
	}

	private void setDefault(boolean isDefault) {
		this.entityData.set(DEFAULT, isDefault);
	}

	public boolean isOnStone() {
		return this.entityData.get(STONE);
	}

	private void setOnStone(boolean inOnStone) {
		this.entityData.set(STONE, inOnStone);
	}

	public void resetModes() {
		this.setOnGravel(false);
		this.setOnSand(false);
		this.setOnGabbro(false);
		this.setOnDolerite(false);
		this.setOnDirt(false);
		this.setOnBlueCoral(false);
		this.setOnYellowCoral(false);
		this.setOnRedCoral(false);
		this.setOnPurpleCoral(false);
		this.setOnPinkCoral(false);
		this.setDefault(false);
		this.setOnStone(false);
	}

	public class CamoflaugeGoal extends Goal {
		OctopusEntity octopus;
		int tickCounter;

		CamoflaugeGoal(OctopusEntity octopus) {
			this.octopus = octopus;
		}

		@Override
		public boolean canUse() {
			List<Player> players = octopus.level.getEntitiesOfClass(Player.class, octopus.getBoundingBox().inflate(5, 5, 5));
			for (Player player : players) {
				if (!player.isCreative()) {
					if (!players.isEmpty() && !octopus.isScared()) {
						return true;
					}
				} else {
					return false;
				}
			}
			return false;
		}

		public void start() {

		}

		public void tick() {
			super.tick();
			tickCounter++;
			BlockPos pos = octopus.blockPosition();
			BlockState state = level.getBlockState(pos.below());
			Block block = state.getBlock();
			if (block == Blocks.STONE || state.getBlock() == Blocks.COBBLESTONE || block == WildLandsBlocks.CONGLOMERATE.get()) {
				resetModes();
				setOnStone(true);
			} else if (block == Blocks.GRAVEL) {
				resetModes();
				setOnGravel(true);
			} else if (block == Blocks.SAND || block == WildLandsBlocks.FINE_SAND.get()) {
				resetModes();
				setOnSand(true);
			} else if (block == WildLandsBlocks.GABBRO.get() || block == WildLandsBlocks.OLIVINE_GABBRO.get()) {
				resetModes();
				setOnGabbro(true);
			} else if (block == WildLandsBlocks.DOLERITE.get()) {
				resetModes();
				setOnDolerite(true);
			} else if (block == Blocks.DIRT || block == Blocks.COARSE_DIRT) {
				resetModes();
				setOnDirt(true);
			} else if (block == Blocks.TUBE_CORAL || block == Blocks.TUBE_CORAL_BLOCK || block == Blocks.TUBE_CORAL_FAN || block == Blocks.TUBE_CORAL_WALL_FAN) {
				resetModes();
				setOnBlueCoral(true);
			} else if (block == Blocks.HORN_CORAL || block == Blocks.HORN_CORAL_BLOCK || block == Blocks.HORN_CORAL_FAN || block == Blocks.HORN_CORAL_WALL_FAN) {
				resetModes();
				setOnYellowCoral(true);
			} else if (block == Blocks.FIRE_CORAL || block == Blocks.FIRE_CORAL_BLOCK || block == Blocks.FIRE_CORAL_FAN || block == Blocks.FIRE_CORAL_WALL_FAN) {
				resetModes();
				setOnRedCoral(true);
			} else if (block == Blocks.BUBBLE_CORAL || block == Blocks.BUBBLE_CORAL_BLOCK || block == Blocks.BUBBLE_CORAL_FAN || block == Blocks.BUBBLE_CORAL_WALL_FAN) {
				resetModes();
				setOnPurpleCoral(true);
			} else if (block == Blocks.BRAIN_CORAL || block == Blocks.BRAIN_CORAL_BLOCK || block == Blocks.BRAIN_CORAL_FAN || block == Blocks.BRAIN_CORAL_WALL_FAN) {
				resetModes();
				setOnPinkCoral(true);
			} else {
				resetModes();
				setDefault(true);
			}
		}

		public boolean shouldContinueExecuting() {
			List<Player> players = octopus.level.getEntitiesOfClass(Player.class, octopus.getBoundingBox().inflate(5, 5, 5));
			if(players.isEmpty()) {
				resetGoal();
				return false;
			} else return true;
		}

		public void resetGoal() {
			resetModes();
			setDefault(true);
		}
	}

	protected PathNavigation createNavigation(Level worldIn) {
		return new WaterBoundPathNavigation(this, worldIn);
	}

	static class SwimGoal extends PanicGoal {
		private final OctopusEntity octopus;

		public SwimGoal(OctopusEntity octopus) {
			super(octopus, 2.5D);
			this.octopus = octopus;
		}

		public boolean canUse() {
			return super.canUse();
		}

		public void start() {
			octopus.setDeltaMovement(0, 0.2, 0);
			this.octopus.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
			this.isRunning = true;
			octopus.setScared(true);
		}

		public void tick() {
			super.tick();
			octopus.level.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, octopus.getX(), octopus.getY(), octopus.getZ(), 1, 1, 1);
		}

		public void resetTask() {
			octopus.setDeltaMovement(0, -0.2, 0);
			this.isRunning = false;
			octopus.setScared(false);
		}

		public boolean shouldContinueExecuting() {
			return !octopus.getNavigation().isDone();
		}
	}
	
	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(WildLandsItems.OCTOPUS_SPAWN_EGG.get());
	}

}
