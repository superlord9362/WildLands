package superlord.wildlands.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WLParticles {
	
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WildLands.MOD_ID);


    public static final RegistryObject<SimpleParticleType> SNORE_PARTICLE = REGISTRY.register("snore_particle", ()-> new SimpleParticleType(false));

}
