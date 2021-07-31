package superlord.wildlands.entity;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import superlord.wildlands.init.EntityInit;
import superlord.wildlands.init.ItemInit;

public class CoconutEntity extends ProjectileItemEntity {

	public CoconutEntity(EntityType<? extends CoconutEntity> coconut, World world) {
		super(coconut, world);
	}

	public CoconutEntity(World world, LivingEntity thrower) {
		super(EntityInit.COCONUT.get(), thrower, world);
	}

	public CoconutEntity(World world, double x, double y, double z) {
		super(EntityInit.COCONUT.get(), x, y, z, world);
	}

	protected Item getDefaultItem() {
		return ItemInit.COCONUT.get();
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

	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte)3);
			this.remove();
		}
	}

}
