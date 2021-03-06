package net.minecraft.world.level.levelgen.feature.blockplacers;

import com.mojang.serialization.Codec;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockPlacer
{
    public static final Codec<BlockPlacer> CODEC = Registry.BLOCK_PLACER_TYPES.dispatch(BlockPlacer::type, BlockPlacerType::codec);

    public abstract void place(LevelAccessor pLevel, BlockPos pPos, BlockState pState, Random pRandom);

    protected abstract BlockPlacerType<?> type();
}
