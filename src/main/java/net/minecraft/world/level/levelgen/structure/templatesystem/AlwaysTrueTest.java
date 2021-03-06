package net.minecraft.world.level.levelgen.structure.templatesystem;

import com.mojang.serialization.Codec;
import java.util.Random;
import net.minecraft.world.level.block.state.BlockState;

public class AlwaysTrueTest extends RuleTest
{
    public static final Codec<AlwaysTrueTest> CODEC;
    public static final AlwaysTrueTest INSTANCE = new AlwaysTrueTest();

    private AlwaysTrueTest()
    {
    }

    public boolean test(BlockState pState, Random pRand)
    {
        return true;
    }

    protected RuleTestType<?> getType()
    {
        return RuleTestType.ALWAYS_TRUE_TEST;
    }

    static
    {
        CODEC = Codec.unit(() ->
        {
            return INSTANCE;
        });
    }
}
