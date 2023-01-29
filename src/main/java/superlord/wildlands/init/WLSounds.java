package superlord.wildlands.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Bus.MOD)
public class WLSounds {
	
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, WildLands.MOD_ID);
	
	public static final RegistryObject<SoundEvent> ALLIGATOR_IDLE = createSoundEvent("alligator_idle");
	public static final RegistryObject<SoundEvent> ALLIGATOR_HURT = createSoundEvent("alligator_hurt");
	public static final RegistryObject<SoundEvent> ALLIGATOR_DEATH = createSoundEvent("alligator_death");
	public static final RegistryObject<SoundEvent> FROG_IDLE = createSoundEvent("frog_idle");
	public static final RegistryObject<SoundEvent> FROG_HURT = createSoundEvent("frog_hurt");
	public static final RegistryObject<SoundEvent> FROG_DEATH = createSoundEvent("frog_death");
	public static final RegistryObject<SoundEvent> CLAM_OPEN = createSoundEvent("clam_open");
	public static final RegistryObject<SoundEvent> CLAM_CLOSE = createSoundEvent("clam_close");
	public static final RegistryObject<SoundEvent> CRAB_IDLE = createSoundEvent("crab_idle");
	public static final RegistryObject<SoundEvent> CRAB_HURT = createSoundEvent("crab_hurt");
	public static final RegistryObject<SoundEvent> CRAB_DEATH = createSoundEvent("crab_death");
	public static final RegistryObject<SoundEvent> SEA_LION_IDLE = createSoundEvent("sea_lion_idle");
	public static final RegistryObject<SoundEvent> SEA_LION_HURT = createSoundEvent("sea_lion_hurt");
	public static final RegistryObject<SoundEvent> SEA_LION_DEATH = createSoundEvent("sea_lion_death");
	public static final RegistryObject<SoundEvent> CATFISH_IDLE = createSoundEvent("catfish_idle");
	public static final RegistryObject<SoundEvent> CATFISH_FLOP = createSoundEvent("catfish_flop");
	public static final RegistryObject<SoundEvent> CATFISH_HURT = createSoundEvent("catfish_hurt");
	public static final RegistryObject<SoundEvent> CATFISH_DEATH = createSoundEvent("catfish_death");
	public static final RegistryObject<SoundEvent> ANCHOVY_IDLE = createSoundEvent("anchovy_idle");
	public static final RegistryObject<SoundEvent> ANCHOVY_FLOP = createSoundEvent("anchovy_flop");
	public static final RegistryObject<SoundEvent> ANCHOVY_HURT = createSoundEvent("anchovy_hurt");
	public static final RegistryObject<SoundEvent> ANCHOVY_DEATH = createSoundEvent("anchovy_death");
	public static final RegistryObject<SoundEvent> HAMMERHEAD_IDLE = createSoundEvent("hammerhead_idle");
	public static final RegistryObject<SoundEvent> HAMMERHEAD_HURT = createSoundEvent("hammerhead_hurt");
	public static final RegistryObject<SoundEvent> HAMMERHEAD_DEATH = createSoundEvent("hammerhead_death");
	public static final RegistryObject<SoundEvent> JELLYFISH_HURT = createSoundEvent("jellyfish_hurt");
	public static final RegistryObject<SoundEvent> JELLYFISH_DEATH = createSoundEvent("jellyfish_death");
	public static final RegistryObject<SoundEvent> OCTOPUS_IDLE = createSoundEvent("octopus_idle");
	public static final RegistryObject<SoundEvent> OCTOPUS_HURT = createSoundEvent("octopus_hurt");
	public static final RegistryObject<SoundEvent> OCTOPUS_DEATH = createSoundEvent("octopus_death");
	public static final RegistryObject<SoundEvent> GRIZZLY_IDLE = createSoundEvent("grizzly_idle");
	public static final RegistryObject<SoundEvent> GRIZZLY_HURT = createSoundEvent("grizzly_hurt");
	public static final RegistryObject<SoundEvent> GRIZZLY_DEATH = createSoundEvent("grizzly_death");
	public static final RegistryObject<SoundEvent> GRIZZLY_WARNING = createSoundEvent("grizzly_warning");
	public static final RegistryObject<SoundEvent> GRIZZLY_EATING = createSoundEvent("grizzly_eating");
	
	private static RegistryObject<SoundEvent> createSoundEvent(final String soundName) {
        return REGISTRY.register(soundName, () -> new SoundEvent(new ResourceLocation(WildLands.MOD_ID, soundName)));
    }

}
