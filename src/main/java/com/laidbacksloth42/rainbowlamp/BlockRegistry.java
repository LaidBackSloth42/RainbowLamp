package com.laidbacksloth42.rainbowlamp;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RainbowLamp.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RainbowLamp.MOD_ID);

    public static final RegistryObject<Block> RAINBOW_LAMP_BLOCK = BLOCKS.register("rainbow_lamp",
            () -> new RainbowLampBlock(BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).strength(0.3F).sound(SoundType.GLASS).isValidSpawn(BlockRegistry::always)
                    .lightLevel((state) -> state.getValue(RainbowLampBlock.POWER) == 0 ? 0 : 15)));

    public static final RegistryObject<Item> RAINBOW_LAMP_ITEM =  ITEMS.register("rainbow_lamp",
            () -> new BlockItem(RAINBOW_LAMP_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

    private static Boolean always(BlockState p_50810_, BlockGetter p_50811_, BlockPos p_50812_, EntityType<?> p_50813_) {
        return true;
    }
}