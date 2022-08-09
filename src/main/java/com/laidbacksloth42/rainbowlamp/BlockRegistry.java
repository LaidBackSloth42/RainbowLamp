package com.laidbacksloth42.rainbowlamp;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class BlockRegistry {
    public static final Block RAINBOW_LAMP_BLOCK = Registry.register(Registry.BLOCK, new Identifier(RainbowLamp.MOD_ID, "rainbow_lamp"),
            new RainbowLampBlock(FabricBlockSettings.of(Material.REDSTONE_LAMP).strength(0.3F).sounds(BlockSoundGroup.GLASS).allowsSpawning(BlockRegistry::always)
                    .luminance((state) -> state.get(RainbowLampBlock.COLOR) == 0 ? 0 : 15)));

    public static final Item RAINBOW_LAMP_ITEM = Registry.register(Registry.ITEM, new Identifier(RainbowLamp.MOD_ID, "rainbow_lamp"),
            new BlockItem(RAINBOW_LAMP_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE)));

    public static void registerModBlocks() {
        RainbowLamp.LOGGER.info("Registering ModBlocks for " + RainbowLamp.MOD_ID);
    }

    private static Boolean always(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return true;
    }
}
