package sypztep.damned.client;

import net.fabricmc.api.ClientModInitializer;
import sypztep.damned.common.init.ModParticles;

public class DamnedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModParticles.registerFactories();
    }
}
