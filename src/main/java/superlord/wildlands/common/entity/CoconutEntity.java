package superlord.wildlands.common.entity;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.network.NetworkHooks;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;

public class CoconutEntity extends ThrowableItemProjectile {

	public CoconutEntity(EntityType<? extends CoconutEntity> coconut, Level world) {
		super(coconut, world);
	}

	public CoconutEntity(Level world, LivingEntity thrower) {
		super(WildLandsEntities.COCONUT.get(), thrower, world);
	}

	public CoconutEntity(Level world, double x, double y, double z) {
		super(WildLandsEntities.COCONUT.get(), x, y, z, world);
	}

	protected Item getDefaultItem() {
		return WildLandsItems.COCONUT.get();
	}

	@Override
	protected float getGravity() {
		return 0.15F;
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		entity.hurt(DamageSource.thrown(this, this.getOwner()), 2);
	}

	private void spawnItem(ItemStack stackIn) {
		ItemEntity itementity = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), stackIn);
		this.level.addFreshEntity(itementity);
	}


	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level.isClientSide) {
			BlockPos pos = new BlockPos(result.getLocation());
			if (level.getBlockState(pos).is(Blocks.STONE) || level.getBlockState(pos.below()).is(Blocks.STONE)) {
				this.spawnItem(WildLandsItems.CRACKED_COCONUT.get().getDefaultInstance());
			} else if (result.getType() == HitResult.Type.ENTITY) {
			} else {
				this.spawnItem(WildLandsItems.COCONUT.get().getDefaultInstance());
			}
			this.level.broadcastEntityEvent(this, (byte)3);
			this.discard();
		}
	}

}
