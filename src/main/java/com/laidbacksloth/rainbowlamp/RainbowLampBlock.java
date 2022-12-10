package com.laidbacksloth.rainbowlamp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RainbowLampBlock extends Block {
    public static final IntProperty POWER;
    public static final IntProperty COLOR;

    public RainbowLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWER, 0).with(COLOR, 0));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int power = ctx.getWorld().getReceivedRedstonePower(ctx.getBlockPos());
        return this.getDefaultState().with(POWER, power).with(COLOR, power);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER);
        builder.add(COLOR);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient && state.get(POWER) != world.getReceivedRedstonePower(pos)) {
            int power = world.getReceivedRedstonePower(pos);
            world.setBlockState(pos, state.with(POWER, power).with(COLOR, power), 2);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient && !world.isReceivingRedstonePower(pos) && hand == Hand.MAIN_HAND && !player.isSneaking()) {
            int color = state.get(COLOR);
            if (color < 15) {
                color++;
            } else {
                color = 0;
            }
            world.setBlockState(pos, state.with(COLOR, color), 2);
        }
        return ActionResult.SUCCESS;
    }

    static {
        POWER = Properties.POWER;
        COLOR = IntProperty.of("color", 0, 15);
    }
}
