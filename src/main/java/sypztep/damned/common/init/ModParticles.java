package sypztep.damned.common.init;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import sypztep.damned.client.particle.ShockwaveHorizontalParticle;
import sypztep.damned.client.particle.ShockwaveVerticleParticle;
import sypztep.damned.common.Damned;

public class ModParticles {
    public static final SimpleParticleType SHOCKWAVE_VERTICAL = FabricParticleTypes.simple(true);
    public static final SimpleParticleType SHOCKWAVE_HORIZONTAL = FabricParticleTypes.simple(true);
    public static void init() {
        registerParticle(SHOCKWAVE_VERTICAL,"shockwave_vertical");
        registerParticle(SHOCKWAVE_HORIZONTAL,"shockwave_horizontal");
    }
    private static void registerParticle(SimpleParticleType simpleParticleType,String name) {
        Registry.register(Registries.PARTICLE_TYPE, Damned.id(name),simpleParticleType);
    }
    public static void registerFactor() {
        ParticleFactoryRegistry particleRegistry = ParticleFactoryRegistry.getInstance();
        particleRegistry.register(ModParticles.SHOCKWAVE_VERTICAL, ShockwaveVerticleParticle.Factory::new);
        particleRegistry.register(ModParticles.SHOCKWAVE_HORIZONTAL, ShockwaveHorizontalParticle.Factory::new);
    }
}
