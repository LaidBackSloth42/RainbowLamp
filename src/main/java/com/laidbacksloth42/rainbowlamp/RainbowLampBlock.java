package com.laidbacksloth42.rainbowlamp;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class RainbowLampBlock extends Block {
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    public RainbowLampBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.defaultBlockState().setValue(POWER, 0));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWER, pContext.getLevel().getBestNeighborSignal(pContext.getClickedPos()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWER);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            int flag = pState.getValue(POWER);
            if (flag != pLevel.getBestNeighborSignal(pPos)) {
                flag = pLevel.getBestNeighborSignal(pPos);
                pLevel.setBlock(pPos, pState.setValue(POWER, flag), 2);
            }
        }
    }
}
