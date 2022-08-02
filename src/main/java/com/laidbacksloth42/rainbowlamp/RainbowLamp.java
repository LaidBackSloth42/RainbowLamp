package com.laidbacksloth42.rainbowlamp;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("rainbowlamp")
public class RainbowLamp {
    public static final String MOD_ID = "rainbowlamp";

    public RainbowLamp() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.register(eventBus);
    }
}
