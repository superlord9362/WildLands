package superlord.wildlands.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.RisingParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SnoreParticle  extends RisingParticle {
	protected SnoreParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale, IAnimatedSprite spriteWithAge) {
		super(world, x, y, z, 0.1F, 0.1F, 0.1F, motionX, motionY, motionZ, scale, spriteWithAge, 1F, 8, 0.004D, true);
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new SnoreParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 1.5F, this.spriteSet);
		}
	}

}
