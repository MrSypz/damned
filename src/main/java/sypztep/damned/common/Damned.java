package sypztep.damned.common;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sypztep.damned.common.init.ModParticles;

public class Damned implements ModInitializer {
	public static final String MODID = "damned";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static Identifier id (String path) {
		return new Identifier(MODID,path);
	}
	@Override
	public void onInitialize() {
		ModParticles.init();

		LOGGER.info("Hello Fabric world!");
	}
}