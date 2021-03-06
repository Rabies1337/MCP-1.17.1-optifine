package net.minecraft.world.level.levelgen.feature.stateproviders;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;

public class BlockStateProviderType<P extends BlockStateProvider>
{
    public static final BlockStateProviderType<SimpleStateProvider> SIMPLE_STATE_PROVIDER = register("simple_state_provider", SimpleStateProvider.CODEC);
    public static final BlockStateProviderType<WeightedStateProvider> WEIGHTED_STATE_PROVIDER = register("weighted_state_provider", WeightedStateProvider.CODEC);
    public static final BlockStateProviderType<PlainFlowerProvider> PLAIN_FLOWER_PROVIDER = register("plain_flower_provider", PlainFlowerProvider.CODEC);
    public static final BlockStateProviderType<ForestFlowerProvider> FOREST_FLOWER_PROVIDER = register("forest_flower_provider", ForestFlowerProvider.CODEC);
    public static final BlockStateProviderType<RotatedBlockProvider> ROTATED_BLOCK_PROVIDER = register("rotated_block_provider", RotatedBlockProvider.CODEC);
    public static final BlockStateProviderType<RandomizedIntStateProvider> RANDOMIZED_INT_STATE_PROVIDER = register("randomized_int_state_provider", RandomizedIntStateProvider.CODEC);
    private final Codec<P> codec;

    private static <P extends BlockStateProvider> BlockStateProviderType<P> register(String pName, Codec<P> pCodec)
    {
        return Registry.register(Registry.BLOCKSTATE_PROVIDER_TYPES, pName, new BlockStateProviderType<>(pCodec));
    }

    private BlockStateProviderType(Codec<P> p_68760_)
    {
        this.codec = p_68760_;
    }

    public Codec<P> codec()
    {
        return this.codec;
    }
}
