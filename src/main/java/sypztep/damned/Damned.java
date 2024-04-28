package sypztep.damned;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Damned implements ModInitializer {
	public static final String MODID = "damned";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static Identifier id (String path) {
		return new Identifier(MODID,path);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}