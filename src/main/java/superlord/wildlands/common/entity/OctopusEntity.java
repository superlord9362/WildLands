package superlord.wildlands.common.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import superlord.wildlands.init.WildLandsBlocks;

public class OctopusEntity extends WaterMobEntity {

	private static final DataParameter<Boolean> GRAVEL = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SAND = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> GABBRO = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> DOLERITE = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> DIRT = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> BLUE_CORAL = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> YELLOW_CORAL = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RED_CORAL = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PURPLE_CORAL = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PINK_CORAL = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> DEFAULT = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> STONE = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SCARED = EntityDataManager.createKey(OctopusEntity.class, DataSerializers.BOOLEAN);

	public OctopusEntity(EntityType<? extends OctopusEntity> type, World p_i48565_2_) {
		super(type, p_i48565_2_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new OctopusEntity.CamoflaugeGoal(this));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<PlayerEntity>(this, PlayerEntity.class, 10, 1, 1.2F));
		this.goalSelector.addGoal(0, new OctopusEntity.SwimGoal(this));
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(GRAVEL, false);
		this.dataManager.register(SAND, false);
		this.dataManager.register(GABBRO, false);
		this.dataManager.register(DOLERITE, false);
		this.dataManager.register(DIRT, false);
		this.dataManager.register(BLUE_CORAL, false);
		this.dataManager.register(YELLOW_CORAL, false);
		this.dataManager.register(RED_CORAL, false);
		this.dataManager.register(PURPLE_CORAL, false);
		this.dataManager.register(PINK_CORAL, false);
		this.dataManager.register(DEFAULT, false);
		this.dataManager.register(STONE, false);
		this.dataManager.register(SCARED, false);
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
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

	public static boolean canSpawn(EntityType<? extends OctopusEntity> type, IWorld worldIn, SpawnReason reason, BlockPos p_223363_3_, Random randomIn) {
		return worldIn.getBlockState(p_223363_3_).isIn(Blocks.WATER) && worldIn.getBlockState(p_223363_3_.up()).isIn(Blocks.WATER);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
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

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 16.0D).createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	public boolean isOnGravel() {
		return this.dataManager.get(GRAVEL);
	}

	private void setOnGravel(boolean inOnGravel) {
		this.dataManager.set(GRAVEL, inOnGravel);
	}

	public boolean isOnSand() {
		return this.dataManager.get(SAND);
	}

	private void setOnSand(boolean inOnSand) {
		this.dataManager.set(SAND, inOnSand);
	}

	public boolean isOnGabbro() {
		return this.dataManager.get(GABBRO);
	}

	private void setOnGabbro(boolean inOnGabbro) {
		this.dataManager.set(GABBRO, inOnGabbro);
	}

	public boolean isScared() {
		return this.dataManager.get(SCARED);
	}

	private void setScared(boolean isScared) {
		this.dataManager.set(SCARED, isScared);
	}

	public boolean isOnDolerite() {
		return this.dataManager.get(DOLERITE);
	}

	private void setOnDolerite(boolean inOnDolerite) {
		this.dataManager.set(DOLERITE, inOnDolerite);
	}

	public boolean isOnDirt() {
		return this.dataManager.get(DIRT);
	}

	private void setOnDirt(boolean inOnDirt) {
		this.dataManager.set(DIRT, inOnDirt);
	}

	public boolean isOnBlueCoral() {
		return this.dataManager.get(BLUE_CORAL);
	}

	private void setOnBlueCoral(boolean inOnBlueCoral) {
		this.dataManager.set(BLUE_CORAL, inOnBlueCoral);
	}

	public boolean isOnYellowCoral() {
		return this.dataManager.get(YELLOW_CORAL);
	}

	private void setOnYellowCoral(boolean inOnYellowCoral) {
		this.dataManager.set(YELLOW_CORAL, inOnYellowCoral);
	}

	public boolean isOnRedCoral() {
		return this.dataManager.get(RED_CORAL);
	}

	private void setOnRedCoral(boolean inOnRedCoral) {
		this.dataManager.set(RED_CORAL, inOnRedCoral);
	}

	public boolean isOnPurpleCoral() {
		return this.dataManager.get(PURPLE_CORAL);
	}

	private void setOnPurpleCoral(boolean inOnPurpleCoral) {
		this.dataManager.set(PURPLE_CORAL, inOnPurpleCoral);
	}

	public boolean isOnPinkCoral() {
		return this.dataManager.get(PINK_CORAL);
	}

	private void setOnPinkCoral(boolean inOnPinkCoral) {
		this.dataManager.set(PINK_CORAL, inOnPinkCoral);
	}

	public boolean isDefault() {
		return this.dataManager.get(DEFAULT);
	}

	private void setDefault(boolean isDefault) {
		this.dataManager.set(DEFAULT, isDefault);
	}

	public boolean isOnStone() {
		return this.dataManager.get(STONE);
	}

	private void setOnStone(boolean inOnStone) {
		this.dataManager.set(STONE, inOnStone);
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

		CamoflaugeGoal(OctopusEntity octopus) {
			this.octopus = octopus;
		}

		@Override
		public boolean shouldExecute() {
			List<PlayerEntity> players = octopus.world.getEntitiesWithinAABB(PlayerEntity.class, octopus.getBoundingBox().grow(5, 5, 5));
			if (!players.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}

		public void tick() {
			super.tick();
			BlockPos pos = octopus.getPosition();
			BlockState state = world.getBlockState(pos.down());
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
			List<PlayerEntity> players = octopus.world.getEntitiesWithinAABB(PlayerEntity.class, octopus.getBoundingBox().grow(5, 5, 5));
			if(players.isEmpty()) {
				resetGoal();
				return false;
			} else return true;
		}

		public void resetGoal() {
			System.out.println("Hello");
			resetModes();
			setDefault(true);
		}
	}

	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	static class SwimGoal extends RandomSwimmingGoal {
		private final OctopusEntity octopus;

		public SwimGoal(OctopusEntity octopus) {
			super(octopus, 1.0D, 40);
			this.octopus = octopus;
		}

		public boolean shouldExecute() {
			return super.shouldExecute() && octopus.getRevengeTarget() != null;
		}
	}

}
