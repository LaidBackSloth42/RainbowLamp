package com.laidbacksloth42.rainbowlamp;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RainbowLamp implements ModInitializer {
	public static final String MOD_ID = "rainbowlamp";
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {
		BlockRegistry.registerModBlocks();
	}
}
