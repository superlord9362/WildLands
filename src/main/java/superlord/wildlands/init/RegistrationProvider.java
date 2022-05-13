package superlord.wildlands.init;


import java.util.Collection;
import java.util.ServiceLoader;
import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.RegistryObject;

public interface RegistrationProvider<T> {
	
	static <T> RegistrationProvider<T> get(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        return Factory.INSTANCE.create(resourceKey, modId);
    }

    static <T> RegistrationProvider<T> get(Registry<T> registry, String modId) {
        return Factory.INSTANCE.create(registry, modId);
    }

    <I extends T> RegistryObject<I> register(String name, Supplier<? extends I> supplier);

    Collection<RegistryObject<T>> getEntries();

    String getModId();

    public interface Factory {

        Factory INSTANCE = Util.make(() -> {
            final var loader = ServiceLoader.load(Factory.class);
            final var it = loader.iterator();
            if (!it.hasNext()) {
                throw new RuntimeException("No BygRegistrationProvider Factory was found on the classpath!");
            } else {
                final Factory factory = it.next();
                if (it.hasNext()) {
                    throw new RuntimeException("More than one BygRegistrationProvider Factory was found on the classpath!");
                }
                return factory;
            }
        });

        <T> RegistrationProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId);

        default <T> RegistrationProvider<T> create(Registry<T> registry, String modId) {
            return create(registry.key(), modId);
        }
    }

}
