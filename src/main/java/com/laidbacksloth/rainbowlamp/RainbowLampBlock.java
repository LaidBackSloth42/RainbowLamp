package com.laidbacksloth.rainbowlamp;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RainbowLampBlock extends Block {
    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    public static final IntegerProperty COLOR = IntegerProperty.create("color", 0, 15);

    public RainbowLampBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.defaultBlockState().setValue(POWER, 0).setValue(COLOR, 0));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        int power = pContext.getLevel().getBestNeighborSignal(pContext.getClickedPos());
        return this.defaultBlockState().setValue(POWER, power).setValue(COLOR, power);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWER);
        pBuilder.add(COLOR);
    }

    @Override
    public void neighborChanged(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Block pBlock, @NotNull BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide && pState.getValue(POWER) != pLevel.getBestNeighborSignal(pPos)) {
            int power = pLevel.getBestNeighborSignal(pPos);
            pLevel.setBlock(pPos, pState.setValue(POWER, power).setValue(COLOR, power), 2);
        }
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (!pLevel.isClientSide && !pLevel.hasNeighborSignal(pPos) && pHand == InteractionHand.MAIN_HAND && !pPlayer.isShiftKeyDown()) {
            int color = pState.getValue(COLOR);
            if (color < 15) {
                color++;
            } else {
                color = 0;
            }
            pLevel.setBlock(pPos, pState.setValue(COLOR, color), 2);
        }
        return InteractionResult.SUCCESS;
    }
}
