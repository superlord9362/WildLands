package superlord.wildlands.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import superlord.wildlands.init.WLBlocks;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLItems;

public class WLBoat extends Boat {

	private static final EntityDataAccessor<Integer> WL_BOAT_TYPE = SynchedEntityData.defineId(WLBoat.class, EntityDataSerializers.INT);
	private Boat.Status status;
	@SuppressWarnings("unused")
	private double lastYd;

	public WLBoat(Level world, double x, double y, double z) {
		this(WLEntities.BOAT.get(), world);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public WLBoat(EntityType<? extends Boat> boatEntityType, Level world) {
		super(boatEntityType, world);
	}

	@Override
	public Item getDropItem() {
		switch(this.getWLBoatType()) {
		default:
			return WLItems.BALD_CYPRESS_BOAT.get();
		case COCONUT:
			return WLItems.COCONUT_BOAT.get();
		case CHARRED:
			return WLItems.CHARRED_BOAT.get();
		}
	}

	public Block getPlanks() {
		switch(this.getWLBoatType()) {
		default:
			return WLBlocks.CYPRESS_PLANKS.get();
		case COCONUT:
			return WLBlocks.COCONUT_PLANKS.get();
		case CHARRED:
			return WLBlocks.CHARRED_PLANKS.get();
		}
	}

	public WLType getWLBoatType() {
		return WLType.byId(this.entityData.get(WL_BOAT_TYPE));
	}

	public void setWLBoatType(WLType boatType) {
		this.entityData.set(WL_BOAT_TYPE, boatType.ordinal());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(WL_BOAT_TYPE, WLType.BALD_CYPRESS.ordinal());
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putString("WLType", this.getWLBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("WLType", 8)) {
			this.setWLBoatType(WLType.getTypeFromString(compound.getString("WLType")));
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateHurt() {
		this.setHurtDir(-this.getHurtDir());
		this.setHurtTime(10);
		this.setDamage(this.getDamage() * 11.0F);
	}

	@Override
	protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (onGround) {
				if (this.fallDistance > 3.0F) {
					if (this.status != Boat.Status.ON_LAND) {
						this.fallDistance = 0.0f;
						return;
					}
					this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
					if (!this.level.isClientSide && !this.isRemoved()) {
						this.kill();
						if(this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							for(int i = 0; i < 3; ++i) {
								this.spawnAtLocation(this.getPlanks());
							}
							for (int j = 0; j < 2; ++j) {
								this.spawnAtLocation(Items.STICK);
							}
							this.spawnAtLocation(Blocks.AIR);
						}
					}
				}
				this.fallDistance = 0.0F;
			} else if (!this.level.getFluidState((new BlockPos(this.blockPosition())).below()).is(FluidTags.WATER) && y < 0.0D) {
				this.fallDistance = (float) ((double) this.fallDistance - y);
			}
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!this.level.isClientSide && !this.isRemoved()) {
			this.setHurtDir(-this.getHurtDir());
			this.setHurtTime(10);
			this.setDamage(this.getDamage() + amount * 10.0F);
			this.markHurt();
			this.gameEvent(GameEvent.ENTITY_DAMAGED, source.getEntity());
			boolean flag = source.getEntity() instanceof Player && ((Player)source.getEntity()).getAbilities().instabuild;
			if (flag || this.getDamage() > 40.0F) {
				if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
					this.spawnAtLocation(this.getDropItem());
				}

				this.discard();
			}

			return true;
		} else {
			return true;
		}
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

	public enum WLType {
		BALD_CYPRESS("bald_cypress"),
		COCONUT("coconut"),
		CHARRED("charred");

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
