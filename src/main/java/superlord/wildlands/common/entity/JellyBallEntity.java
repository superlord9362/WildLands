package superlord.wildlands.common.entity;

import javax.annotation.Nonnull;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import superlord.wildlands.init.WildLandsEffects;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;

public class JellyBallEntity extends ThrowableItemProjectile {

	public JellyBallEntity(EntityType<? extends JellyBallEntity> coconut, Level world) {
		super(coconut, world);
	}

	public JellyBallEntity(Level world, LivingEntity thrower) {
		super(WildLandsEntities.JELLY_BALL.get(), thrower, world);
	}

	public JellyBallEntity(Level world, double x, double y, double z) {
		super(WildLandsEntities.JELLY_BALL.get(), x, y, z, world);
	}

	protected Item getDefaultItem() {
		return WildLandsItems.JELLY_BALL.get();
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}


	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		if (entity instanceof LivingEntity) {
			if (!(entity instanceof Turtle) || !(entity instanceof JellyfishEntity)) {
				((LivingEntity) entity).addEffect(new MobEffectInstance(WildLandsEffects.STING.get(), 100));
			}
		}
	}

	protected void onHit(HitResult result) {
		super.onHit(result);
		this.discard();
	}

}
