package superlord.wildlands.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import superlord.wildlands.init.BlockInit;
import superlord.wildlands.init.EntityInit;
import superlord.wildlands.init.ItemInit;

public class WLBoatEntity extends BoatEntity {
	
	private static final DataParameter<Integer> WL_BOAT_TYPE = EntityDataManager.createKey(WLBoatEntity.class, DataSerializers.VARINT);
	private BoatEntity.Status status;
	@SuppressWarnings("unused")
	private double lastYd;
	
	public WLBoatEntity(World world, double x, double y, double z) {
		this(EntityInit.BOAT.get(), world);
		this.setPosition(x, y, z);
		this.setMotion(Vector3d.ZERO);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}
	
	public WLBoatEntity(EntityType<? extends BoatEntity> boatEntityType, World world) {
		super(boatEntityType, world);
	}
	
	public WLBoatEntity(FMLPlayMessages.SpawnEntity packet, World world) {
		super(EntityInit.BOAT.get(), world);
	}
	
	@Override
	public Item getItemBoat() {
		switch(this.getWLBoatType()) {
			default:
				return ItemInit.BALD_CYPRESS_BOAT.get();
			case COCONUT:
				return ItemInit.COCONUT_BOAT.get();
		}
	}
	
	public Block getPlanks() {
		switch(this.getWLBoatType()) {
			default:
				return BlockInit.CYPRESS_PLANKS.get();
			case COCONUT:
				return BlockInit.COCONUT_PLANKS.get();
		}
	}
	
	public WLType getWLBoatType() {
		return WLType.byId(this.dataManager.get(WL_BOAT_TYPE));
	}
	
	public void setWLBoatType(WLType boatType) {
		this.dataManager.set(WL_BOAT_TYPE, boatType.ordinal());
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(WL_BOAT_TYPE, WLType.BALD_CYPRESS.ordinal());
	}
	
	@Override
	protected void writeAdditional(CompoundNBT compound) {
		compound.putString("WLType", this.getWLBoatType().getName());
	}
	
	@Override
	protected void readAdditional(CompoundNBT compound) {
		if (compound.contains("WLType", 8)) {
			this.setWLBoatType(WLType.getTypeFromString(compound.getString("WLType")));
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void performHurtAnimation() {
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void updateFallState(double y, boolean onGround, BlockState state, BlockPos pos) {
		this.lastYd = this.getMotion().y;
		if (!this.isPassenger()) {
			if (onGround) {
				if (this.fallDistance > 3.0F) {
					if (this.status != BoatEntity.Status.ON_LAND) {
						this.fallDistance = 0.0f;
						return;
					}
					this.onLivingFall(this.fallDistance, 1.0F);
					if (!this.world.isRemote && !this.removed) {
						this.remove();
						if(this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
							for(int i = 0; i < 3; ++i) {
								this.entityDropItem(this.getPlanks());
							}
							for (int j = 0; j < 2; ++j) {
								this.entityDropItem(Items.STICK);
							}
							this.entityDropItem(Blocks.AIR);
						}
					}
				}
				this.fallDistance = 0.0F;
			} else if (!this.world.getFluidState((new BlockPos(this.getPositionVec())).down()).isTagged(FluidTags.WATER) && y < 0.0D) {
				this.fallDistance = (float) ((double) this.fallDistance - y);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!this.world.isRemote && !this.removed) {
			if (source instanceof IndirectEntityDamageSource && source.getTrueSource() != null && this.isPassenger(source.getTrueSource())) {
				return false;
			} else {
				this.setForwardDirection(-this.getForwardDirection());
				this.setTimeSinceHit(10);
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.markVelocityChanged();
				boolean flag = source.getTrueSource() instanceof PlayerEntity && ((PlayerEntity)source.getTrueSource()).abilities.isCreativeMode;
				if (flag || this.getDamageTaken() > 40.0F) {
					if (!flag && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
						this.entityDropItem(this.getItemBoat());
					}
					this.remove();
				}
				return true;
			}
		} else {
			return true;
		}
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	public enum WLType {
		BALD_CYPRESS("bald_cypress"),
		COCONUT("coconut");
		
		private final String name;
		
		WLType(String name) {
			this.name = name;
		}
		
		public static WLType byId(int id) {
			WLType[] aWLBoatEntityType$WLType = values();
			if (id < 0 || id >= aWLBoatEntityType$WLType.length) {
				id = 0;
			}
			return aWLBoatEntityType$WLType[id];
		}
		
		public static WLType getTypeFromString(String name) {
			WLType[] aWLBoatEntity$WLType = values();
			for (WLType WLType : aWLBoatEntity$WLType) {
				if (WLType.getName().equals(name)) {
					return WLType;
				}
			}
			return aWLBoatEntity$WLType[0];
		}
		
		public String getName() {
			return this.name;
		}
		
		public String toString() {
			return this.name;
		}
	}

}
