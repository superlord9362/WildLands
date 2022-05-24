package superlord.wildlands.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;
import superlord.wildlands.common.effect.StingEffect;

public class WLEffects {
	
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, WildLands.MOD_ID);
	
	public static final RegistryObject<MobEffect> STING = EFFECTS.register("sting", () -> new StingEffect(MobEffectCategory.HARMFUL, 0xFB87A7));

}
