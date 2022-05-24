package superlord.wildlands.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import superlord.wildlands.WildLands;
import superlord.wildlands.client.particle.ParticleRegistry;
import superlord.wildlands.client.particle.SnoreParticle;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WLParticles {
	
	public static final SimpleParticleType SNORE_PARTICLE = registerBasicParticle("snore_particle");

	
	private static SimpleParticleType registerBasicParticle(String name) {
		return ParticleRegistry.registerParticle(name, new SimpleParticleType(false));
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerFactories(ParticleFactoryRegisterEvent e) {
		ParticleEngine particles = Minecraft.getInstance().particleEngine;

		particles.register(SNORE_PARTICLE, SnoreParticle.Provider::new);
	}

}
