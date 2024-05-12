package net.logwnn.painfularmor;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PainfulArmor implements ModInitializer {
	public static final String MOD_ID = "painful-armor";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.warn("Hello, World... This is a warning.");
	}
}