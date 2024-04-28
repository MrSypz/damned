package sypztep.damned.common.init;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sypztep.damned.client.particle.ShockwaveParticle;
import sypztep.damned.common.Damned;

import java.util.function.BiConsumer;

public interface ModParticles {
    ParticleFactoryRegistry factory = ParticleFactoryRegistry.getInstance();
    SimpleParticleType SHOCKWAVE = FabricParticleTypes.simple(true);
    static void init() {
        initParticles(bind(Registries.PARTICLE_TYPE));
    }

    private static void initParticles(BiConsumer<ParticleType<?>, Identifier> registry) {
        registry.accept(SHOCKWAVE, Damned.id("shockwave"));
    }
    static void registerFactories() {
        factory.register(SHOCKWAVE, ShockwaveParticle.Factory::new);
    }
    private static <T> BiConsumer<T, Identifier> bind(Registry<? super T> registry) {
        return (t, id) -> {
            Registry.register(registry, id, t);
        };
    }
}
