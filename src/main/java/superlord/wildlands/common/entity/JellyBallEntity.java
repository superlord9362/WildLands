package superlord.wildlands.common.entity;

import javax.annotation.Nonnull;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import superlord.wildlands.init.WildLandsEffects;
import superlord.wildlands.init.WildLandsEntities;
import superlord.wildlands.init.WildLandsItems;

public class JellyBallEntity extends ProjectileItemEntity {

	public JellyBallEntity(EntityType<? extends JellyBallEntity> coconut, World world) {
		super(coconut, world);
	}

	public JellyBallEntity(World world, LivingEntity thrower) {
		super(WildLandsEntities.JELLY_BALL.get(), thrower, world);
	}

	public JellyBallEntity(World world, double x, double y, double z) {
		super(WildLandsEntities.JELLY_BALL.get(), x, y, z, world);
	}

	protected Item getDefaultItem() {
		return WildLandsItems.JELLY_BALL.get();
	}

	@Nonnull
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	protected void onEntityHit(EntityRayTraceResult result) {
		super.onEntityHit(result);
		Entity entity = result.getEntity();
		if (entity instanceof LivingEntity) {
			if (!(entity instanceof TurtleEntity) || !(entity instanceof JellyfishEntity)) {
				((LivingEntity) entity).addPotionEffect(new EffectInstance(WildLandsEffects.STING.get(), 100));
			}
		}
	}

	protected void onImpact(RayTraceResult result) {
		super.onImpact(result);
		this.remove();
	}

}
