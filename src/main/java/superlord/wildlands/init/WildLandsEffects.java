package superlord.wildlands.init;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.effects.StingEffect;

public class WildLandsEffects {
	
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, WildLands.MOD_ID);
	
	public static final RegistryObject<Effect> STING = EFFECTS.register("sting", () -> new StingEffect(EffectType.HARMFUL, 0xFB87A7	));

}
