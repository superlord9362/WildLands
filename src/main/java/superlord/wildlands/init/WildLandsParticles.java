package superlord.wildlands.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.ParticleRegistry;
import superlord.wildlands.client.particle.SnoreParticle;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WildLandsParticles {
	
	public static final BasicParticleType SNORE_PARTICLE = registerBasicParticle("snore_particle");

	
	private static BasicParticleType registerBasicParticle(String name) {
		return ParticleRegistry.registerParticle(name, new BasicParticleType(false));
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerFactories(ParticleFactoryRegisterEvent e) {
		ParticleManager particles = Minecraft.getInstance().particles;

		particles.registerFactory(SNORE_PARTICLE, SnoreParticle.Factory::new);
	}

}
