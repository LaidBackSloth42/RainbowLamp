package com.laidbacksloth.rainbowlamp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RainbowLamp implements ModInitializer {
	public static final String MOD_ID = "rainbowlamp";
	public static final Logger LOGGER = LoggerFactory.getLogger("rainbowlamp");

	@Override
	public void onInitialize() {
		BlockRegistry.registerModBlocks();
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.add(BlockRegistry.RAINBOW_LAMP_ITEM));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.add(BlockRegistry.RAINBOW_LAMP_ITEM));
	}
}
