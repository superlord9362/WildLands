package superlord.wildlands.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import superlord.wildlands.common.entity.WLBoat.WLBoatTypes;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLItems;

public class WLChestBoat extends ChestBoat {

	private static final EntityDataAccessor<Integer> CHEST_BOAT_TYPE = SynchedEntityData.defineId(WLChestBoat.class, EntityDataSerializers.INT);

	public WLChestBoat(EntityType<? extends Entity> entityType, Level world) {
		super(WLEntities.CHEST_BOAT.get(), world);
	}

	public WLChestBoat(Level world, double positionX, double positionY, double positionZ) {
		super(WLEntities.CHEST_BOAT.get(), world);
		this.setPos(positionX, positionY, positionZ);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = positionX;
		this.yo = positionY;
		this.zo = positionZ;
	}

	public WLChestBoat(PlayMessages.SpawnEntity spawnEntity, Level world) {
		this(WLEntities.CHEST_BOAT.get(), world);
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("WoodType", this.getWLChestBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("WoodType", 8)) this.setWLChestBoatType(WLBoatTypes.byName(compound.getString("WoodType")));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CHEST_BOAT_TYPE, WLBoatTypes.CHARRED.ordinal());
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public void setWLChestBoatType(WLBoatTypes type) {
		this.entityData.set(CHEST_BOAT_TYPE, type.ordinal());
	}

	public WLBoatTypes getWLChestBoatType() {
		return WLBoatTypes.byId(this.entityData.get(CHEST_BOAT_TYPE));
	}

	@Override
	public Boat.Type getBoatType() {
		return Boat.Type.OAK;
	}

	@Override
	public void setType(Boat.Type boatType) {
	}

	@Override
	public Item getDropItem() {
		switch(this.getWLChestBoatType()) {
		case CYPRESS:
			return WLItems.CYPRESS_CHEST_BOAT.get();
		case COCONUT:
			return WLItems.COCONUT_CHEST_BOAT.get();
		case CHARRED:
		default:
			return WLItems.CHARRED_CHEST_BOAT.get();
		}
	}

}
