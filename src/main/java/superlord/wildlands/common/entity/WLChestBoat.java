package superlord.wildlands.common.entity;

import javax.annotation.Nullable;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLItems;

public class WLChestBoat extends WLBoat implements HasCustomInventoryScreen, ContainerEntity {
	private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
	@Nullable
	private ResourceLocation lootTable;
	private long lootTableSeed;

	private static final EntityDataAccessor<Integer> CHEST_BOAT_TYPE = SynchedEntityData.defineId(WLChestBoat.class, EntityDataSerializers.INT);

	public WLChestBoat(EntityType<? extends Entity> entityType, Level world) {
		super(WLEntities.CHEST_BOAT.get(), world);
	}

	protected float getSinglePassengerXOffset() {
		return 0.15F;
	}

	protected int getMaxPassengers() {
		return 1;
	}

	public WLChestBoat(Level world, double positionX, double positionY, double positionZ) {
		super(WLEntities.CHEST_BOAT.get(), world);
		this.setPos(positionX, positionY, positionZ);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = positionX;
		this.yo = positionY;
		this.zo = positionZ;
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("WoodType", this.getWLChestBoatType().getName());
		this.addChestVehicleSaveData(compound);
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("WoodType", 8)) this.setWLChestBoatType(WLBoatTypes.byName(compound.getString("WoodType")));
		this.readChestVehicleSaveData(compound);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CHEST_BOAT_TYPE, WLBoatTypes.CHARRED.ordinal());
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public void setWLChestBoatType(WLBoatTypes type) {
		this.entityData.set(CHEST_BOAT_TYPE, type.ordinal());
	}

	public WLBoatTypes getWLChestBoatType() {
		return WLBoatTypes.byId(this.entityData.get(CHEST_BOAT_TYPE));
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

	public void destroy(DamageSource p_219892_) {
		super.destroy(p_219892_);
		this.chestVehicleDestroyed(p_219892_, this.level(), this);
	}

	@SuppressWarnings("resource")
	public void remove(Entity.RemovalReason p_219894_) {
		if (!this.level().isClientSide && p_219894_.shouldDestroy()) {
			Containers.dropContents(this.level(), this, this);
		}

		super.remove(p_219894_);
	}

	public InteractionResult interact(Player p_219898_, InteractionHand p_219899_) {
		if (this.canAddPassenger(p_219898_) && !p_219898_.isSecondaryUseActive()) {
			return super.interact(p_219898_, p_219899_);
		} else {
			InteractionResult interactionresult = this.interactWithContainerVehicle(p_219898_);
			if (interactionresult.consumesAction()) {
				this.gameEvent(GameEvent.CONTAINER_OPEN, p_219898_);
				PiglinAi.angerNearbyPiglins(p_219898_, true);
			}

			return interactionresult;
		}
	}

	@SuppressWarnings("resource")
	public void openCustomInventoryScreen(Player p_219906_) {
		p_219906_.openMenu(this);
		if (!p_219906_.level().isClientSide) {
			this.gameEvent(GameEvent.CONTAINER_OPEN, p_219906_);
			PiglinAi.angerNearbyPiglins(p_219906_, true);
		}

	}

	public int getContainerSize() {
		return 27;
	}

	public ItemStack getItem(int p_18941_) {
		return this.getChestVehicleItem(p_18941_);
	}

	@Override
	public ItemStack removeItem(int p_18942_, int p_18943_) {
		return this.removeChestVehicleItem(p_18942_, p_18943_);
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_18951_) {
		return this.removeChestVehicleItemNoUpdate(p_18951_);
	}

	@Override
	public void setItem(int p_18944_, ItemStack p_18945_) {
		this.setChestVehicleItem(p_18944_, p_18945_);
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player p_18946_) {
		return this.isChestVehicleStillValid(p_18946_);
	}

	@Override
	public void clearContent() {
		this.clearChestVehicleContent();
	}

	@Override
	public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
		if (this.lootTable != null && p_39956_.isSpectator()) {
			return null;
		} else {
			this.unpackLootTable(p_39955_.player);
			return ChestMenu.threeRows(p_39954_, p_39955_, this);
		}
	}

	public void unpackLootTable(@Nullable Player p_219914_) {
		this.unpackChestVehicleLootTable(p_219914_);
	}

	@Override
	public ResourceLocation getLootTable() {
		return this.lootTable;
	}

	@Override
	public void setLootTable(ResourceLocation p_219926_) {
		this.lootTable = p_219926_;
	}

	@Override
	public long getLootTableSeed() {
		return this.lootTableSeed;
	}

	@Override
	public void setLootTableSeed(long p_219925_) {
		this.lootTableSeed = p_219925_;
	}

	@Override
	public NonNullList<ItemStack> getItemStacks() {
		return this.itemStacks;
	}

	@Override
	public void clearItemStacks() {
		this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
	}

}
