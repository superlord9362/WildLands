package superlord.wildlands.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import superlord.wildlands.WildLands;

@Mod.EventBusSubscriber(modid = WildLands.MOD_ID, bus = Bus.MOD)
public class WildLandsSounds {
	
	public static final SoundEvent ALLIGATOR_IDLE;
	public static final SoundEvent ALLIGATOR_HURT;
	public static final SoundEvent ALLIGATOR_DEATH;
	public static final SoundEvent FROG_IDLE;
	public static final SoundEvent FROG_HURT;
	public static final SoundEvent FROG_DEATH;
	public static final SoundEvent CLAM_OPEN;
	public static final SoundEvent CLAM_CLOSE;
	public static final SoundEvent CRAB_IDLE;
	public static final SoundEvent CRAB_HURT;
	public static final SoundEvent CRAB_DEATH;
	public static final SoundEvent SEA_LION_IDLE;
	public static final SoundEvent SEA_LION_HURT;
	public static final SoundEvent SEA_LION_DEATH;
	public static final SoundEvent CATFISH_IDLE;
	public static final SoundEvent CATFISH_FLOP;
	public static final SoundEvent CATFISH_HURT;
	public static final SoundEvent CATFISH_DEATH;
	public static final SoundEvent ANCHOVY_IDLE;
	public static final SoundEvent ANCHOVY_FLOP;
	public static final SoundEvent ANCHOVY_HURT;
	public static final SoundEvent ANCHOVY_DEATH;
	public static final SoundEvent HAMMERHEAD_IDLE;
	public static final SoundEvent HAMMERHEAD_HURT;
	public static final SoundEvent HAMMERHEAD_DEATH;
	public static final SoundEvent JELLYFISH_HURT;
	public static final SoundEvent JELLYFISH_DEATH;
	public static final SoundEvent OCTOPUS_IDLE;
	public static final SoundEvent OCTOPUS_HURT;
	public static final SoundEvent OCTOPUS_DEATH;
	public static final SoundEvent GRIZZLY_IDLE;
	public static final SoundEvent GRIZZLY_HURT;
	public static final SoundEvent GRIZZLY_DEATH;
	public static final SoundEvent GRIZZLY_WARNING;
	
	@SubscribeEvent
	public static void registerSounds(final RegistryEvent.Register<SoundEvent> evt) {
		evt.getRegistry().register(ALLIGATOR_IDLE);
		evt.getRegistry().register(ALLIGATOR_HURT);
		evt.getRegistry().register(ALLIGATOR_DEATH);
		evt.getRegistry().register(FROG_IDLE);
		evt.getRegistry().register(FROG_HURT);
		evt.getRegistry().register(FROG_DEATH);
		evt.getRegistry().register(CLAM_OPEN);
		evt.getRegistry().register(CLAM_CLOSE);
		evt.getRegistry().register(CRAB_IDLE);
		evt.getRegistry().register(CRAB_HURT);
		evt.getRegistry().register(CRAB_DEATH);
		evt.getRegistry().register(SEA_LION_IDLE);
		evt.getRegistry().register(SEA_LION_HURT);
		evt.getRegistry().register(SEA_LION_DEATH);
		evt.getRegistry().register(CATFISH_IDLE);
		evt.getRegistry().register(CATFISH_FLOP);
		evt.getRegistry().register(CATFISH_HURT);
		evt.getRegistry().register(CATFISH_DEATH);
		evt.getRegistry().register(ANCHOVY_IDLE);
		evt.getRegistry().register(ANCHOVY_FLOP);
		evt.getRegistry().register(ANCHOVY_HURT);
		evt.getRegistry().register(ANCHOVY_DEATH);
		evt.getRegistry().register(HAMMERHEAD_IDLE);
		evt.getRegistry().register(HAMMERHEAD_HURT);
		evt.getRegistry().register(HAMMERHEAD_DEATH);
		evt.getRegistry().register(JELLYFISH_HURT);
		evt.getRegistry().register(JELLYFISH_DEATH);
		evt.getRegistry().register(OCTOPUS_IDLE);
		evt.getRegistry().register(OCTOPUS_HURT);
		evt.getRegistry().register(OCTOPUS_DEATH);
		evt.getRegistry().register(GRIZZLY_IDLE);
		evt.getRegistry().register(GRIZZLY_HURT);
		evt.getRegistry().register(GRIZZLY_DEATH);
		evt.getRegistry().register(GRIZZLY_WARNING);
	}
	
	private static SoundEvent createEvent(final String soundName) {
		final ResourceLocation soundID = new ResourceLocation(WildLands.MOD_ID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}
	
	static {
		ALLIGATOR_IDLE = createEvent("alligator_idle");
		ALLIGATOR_HURT = createEvent("alligator_hurt");
		ALLIGATOR_DEATH = createEvent("alligator_death");
		FROG_IDLE = createEvent("frog_idle");
		FROG_HURT = createEvent("frog_hurt");
		FROG_DEATH = createEvent("frog_death");
		CLAM_OPEN = createEvent("clam_open");
		CLAM_CLOSE = createEvent("clam_close");
		CRAB_IDLE = createEvent("crab_idle");
		CRAB_HURT = createEvent("crab_hurt");
		CRAB_DEATH = createEvent("crab_death");
		SEA_LION_IDLE = createEvent("sea_lion_idle");
		SEA_LION_HURT = createEvent("sea_lion_hurt");
		SEA_LION_DEATH = createEvent("sea_lion_death");
		CATFISH_IDLE = createEvent("catfish_idle");
		CATFISH_FLOP = createEvent("catfish_flop");
		CATFISH_HURT = createEvent("catfish_hurt");
		CATFISH_DEATH = createEvent("catfish_death");
		ANCHOVY_IDLE = createEvent("anchovy_idle");
		ANCHOVY_FLOP = createEvent("anchovy_flop");
		ANCHOVY_HURT = createEvent("anchovy_hurt");
		ANCHOVY_DEATH = createEvent("anchovy_death");
		HAMMERHEAD_IDLE = createEvent("hammerhead_idle");
		HAMMERHEAD_HURT = createEvent("hammerhead_hurt");
		HAMMERHEAD_DEATH = createEvent("hammerhead_death");
		JELLYFISH_HURT = createEvent("jellyfish_hurt");
		JELLYFISH_DEATH = createEvent("jellyfish_death");
		OCTOPUS_IDLE = createEvent("octopus_idle");
		OCTOPUS_HURT = createEvent("octopus_hurt");
		OCTOPUS_DEATH = createEvent("octopus_death");
		GRIZZLY_IDLE = createEvent("grizzly_idle");
		GRIZZLY_HURT = createEvent("grizzly_hurt");
		GRIZZLY_DEATH = createEvent("grizzly_death");
		GRIZZLY_WARNING = createEvent("grizzly_warning");
	}

}
