package com.laidbacksloth.rainbowlamp;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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

    public void creativeModTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS || event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(BlockRegistry.RAINBOW_LAMP_ITEM.get());
        }
    }
}
