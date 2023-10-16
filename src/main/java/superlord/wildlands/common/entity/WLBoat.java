package superlord.wildlands.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;
import superlord.wildlands.init.WLBlocks;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLItems;

public class WLBoat extends Boat {

	private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(WLBoat.class, EntityDataSerializers.INT);

	public WLBoat(EntityType<? extends Entity> entityType, Level level) {
		super(WLEntities.BOAT.get(), level);
	}

	public WLBoat(Level level, double positionX, double positionY, double positionZ) {
		super(WLEntities.BOAT.get(), level);
		this.setPos(positionX, positionY, positionZ);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = positionX;
		this.yo = positionY;
		this.zo = positionZ;
	}

	public WLBoat(SpawnEntity spawnEntity, Level level) {
		this(WLEntities.BOAT.get(), level);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("WoodType", this.getWLBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("WoodType", 8)) this.setWLBoatType(WLBoatTypes.byName(compound.getString("WoodType")));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BOAT_TYPE, WLBoatTypes.CHARRED.ordinal());
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public void setWLBoatType(WLBoatTypes type) {
		this.entityData.set(BOAT_TYPE, type.ordinal());
	}

	public WLBoatTypes getWLBoatType() {
		return WLBoatTypes.byId(this.entityData.get(BOAT_TYPE));
	}

	@Override
	public Boat.Type getVariant() {
		return Boat.Type.OAK;
	}

	@Override
	public void setVariant(Boat.Type p_38333_) {
	}

	@Override
	public Item getDropItem() {
		switch(this.getWLBoatType()) {
		case COCONUT:
			return WLItems.COCONUT_BOAT.get();
		case CYPRESS:
			return WLItems.CYPRESS_BOAT.get();
		case CHARRED:
		default:
			return WLItems.CHARRED_BOAT.get();
		}
	}
	
	public enum WLBoatTypes {
		COCONUT(WLBlocks.COCONUT_PLANKS.get(), "coconut"),
		CYPRESS(WLBlocks.CYPRESS_PLANKS.get(), "cypress"),
		CHARRED(WLBlocks.CHARRED_PLANKS.get(), "charred");
		
		private final String name;
		private final Block planks;
		
		WLBoatTypes(Block planks, String name) {
			this.name = name;
			this.planks = planks;
		}
		
		public String getName() {
			return this.name;
		}
		
		public Block getPlanks() {
			return this.planks;
		}
		
		public String toString() {
			return this.name;
		}
		
		public static WLBoatTypes byId(int id) {
			WLBoatTypes[] boatEntityType = values();
			if (id < 0 || id >= boatEntityType.length) {
				id = 0;
			}
			return boatEntityType[id];
		}
		
		public static WLBoatTypes byName(String name) {
			WLBoatTypes[] boatEntityType = values();
			
			for (int i = 0; i < boatEntityType.length; ++i) {
				if (boatEntityType[i].getName().equals(name)) {
					return boatEntityType[i];
				}
			}
			return boatEntityType[0];
		}
		
	}



}
