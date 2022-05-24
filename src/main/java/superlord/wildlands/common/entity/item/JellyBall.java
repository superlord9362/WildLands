package superlord.wildlands.common.entity.item;

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
import superlord.wildlands.common.entity.Jellyfish;
import superlord.wildlands.init.WLEffects;
import superlord.wildlands.init.WLEntities;
import superlord.wildlands.init.WLItems;

public class JellyBall extends ThrowableItemProjectile {

	public JellyBall(EntityType<? extends JellyBall> coconut, Level world) {
		super(coconut, world);
	}

	public JellyBall(Level world, LivingEntity thrower) {
		super(WLEntities.JELLY_BALL.get(), thrower, world);
	}

	public JellyBall(Level world, double x, double y, double z) {
		super(WLEntities.JELLY_BALL.get(), x, y, z, world);
	}

	protected Item getDefaultItem() {
		return WLItems.JELLY_BALL.get();
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
			if (!(entity instanceof Turtle) || !(entity instanceof Jellyfish)) {
				((LivingEntity) entity).addEffect(new MobEffectInstance(WLEffects.STING.get(), 100));
			}
		}
	}

	protected void onHit(HitResult result) {
		super.onHit(result);
		this.discard();
	}

}
