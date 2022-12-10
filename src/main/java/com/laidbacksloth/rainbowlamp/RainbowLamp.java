package com.laidbacksloth.rainbowlamp;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("rainbowlamp")
public class RainbowLamp {
    public static final String MOD_ID = "rainbowlamp";

    public RainbowLamp() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.register(eventBus);
        eventBus.addListener(this::creativeModTabs);
    }

    public void creativeModTabs(CreativeModeTabEvent.BuildContents event) {
        event.registerSimple(CreativeModeTabs.REDSTONE_BLOCKS, BlockRegistry.RAINBOW_LAMP_ITEM.get());
        event.registerSimple(CreativeModeTabs.FUNCTIONAL_BLOCKS, BlockRegistry.RAINBOW_LAMP_ITEM.get());
    }
}
