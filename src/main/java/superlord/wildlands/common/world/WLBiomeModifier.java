package superlord.wildlands.common.world;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.wildlands.WildLands;

public class WLBiomeModifier implements BiomeModifier {
	
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(WildLands.MOD_ID, "wildlands_biome_modifiers"), ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, WildLands.MOD_ID);

    public WLBiomeModifier() {
    }
    
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    	if (phase == Phase.ADD ) {
    		WLWorldRegistry.addBiomeSpawns(biome, builder);
    	}
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Codec<? extends BiomeModifier> codec() {
    	return (Codec)SERIALIZER.get();
    }
    
    public static Codec<WLBiomeModifier> makeCodec() {
    	return Codec.unit(WLBiomeModifier::new);
    }
    
}
