package superlord.wildlands.common.entity;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.fml.network.NetworkHooks;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;

public class CoconutEntity extends ProjectileItemEntity {

	public CoconutEntity(EntityType<? extends CoconutEntity> coconut, World world) {
		super(coconut, world);
	}

	public CoconutEntity(World world, LivingEntity thrower) {
		super(WildLandsEntities.COCONUT.get(), thrower, world);
	}

	public CoconutEntity(World world, double x, double y, double z) {
		super(WildLandsEntities.COCONUT.get(), x, y, z, world);
	}

	protected Item getDefaultItem() {
		return WildLandsItems.COCONUT.get();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.15F;
	}

	@Nonnull
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	protected void onEntityHit(EntityRayTraceResult result) {
		super.onEntityHit(result);
		Entity entity = result.getEntity();
		entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), 2);
	}
	
	private void spawnItem(ItemStack stackIn) {
		ItemEntity itementity = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), stackIn);
		this.world.addEntity(itementity);
	}


	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);
		if (!this.world.isRemote) {
			BlockPos pos = new BlockPos(result.getHitVec());
			if (world.getBlockState(pos).isIn(Blocks.STONE)) {
				this.spawnItem(WildLandsItems.CRACKED_COCONUT.get().getDefaultInstance());
			} else if (result.getType() == RayTraceResult.Type.ENTITY) {
			} else {
				this.spawnItem(WildLandsItems.COCONUT.get().getDefaultInstance());
			}
			this.world.setEntityState(this, (byte)3);
			this.remove();
		}
	}

}
