package superlord.wildlands.common.entity.item;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.network.NetworkHooks;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLItems;

public class Coconut extends ThrowableItemProjectile {

	public Coconut(EntityType<? extends Coconut> coconut, Level world) {
		super(coconut, world);
	}

	public Coconut(Level world, LivingEntity thrower) {
		super(WLEntities.COCONUT.get(), thrower, world);
	}

	public Coconut(Level world, double x, double y, double z) {
		super(WLEntities.COCONUT.get(), x, y, z, world);
	}

	protected Item getDefaultItem() {
		return WLItems.COCONUT.get();
	}

	@Override
	protected float getGravity() {
		return 0.15F;
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		if (!(entity instanceof Boat)) {
			entity.hurt(this.damageSources().thrown(this, this.getOwner()), 2);
		}
	}

	private void spawnItem(ItemStack stackIn) {
		ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), stackIn);
		this.level.addFreshEntity(itementity);
	}


	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level.isClientSide) {
			BlockPos pos = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
			if (level.getBlockState(pos).is(Blocks.STONE) || level.getBlockState(pos.below()).is(Blocks.STONE)) {
				this.spawnItem(WLItems.CRACKED_COCONUT.get().getDefaultInstance());
			} else if (result.getType() == HitResult.Type.ENTITY) {
			} else {
				this.spawnItem(WLItems.COCONUT.get().getDefaultInstance());
			}
			this.level.broadcastEntityEvent(this, (byte)3);
			this.discard();
		}
	}

}
