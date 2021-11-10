package net.minecraft.world.level.levelgen.feature;

import com.mojang.serialization.Codec;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;

public class RandomSelectorFeature extends Feature<RandomFeatureConfiguration>
{
    public RandomSelectorFeature(Codec<RandomFeatureConfiguration> p_66619_)
    {
        super(p_66619_);
    }

    public boolean place(FeaturePlaceContext<RandomFeatureConfiguration> p_160212_)
    {
        RandomFeatureConfiguration randomfeatureconfiguration = p_160212_.config();
        Random random = p_160212_.random();
        WorldGenLevel worldgenlevel = p_160212_.level();
        ChunkGenerator chunkgenerator = p_160212_.chunkGenerator();
        BlockPos blockpos = p_160212_.origin();

        for (WeightedConfiguredFeature weightedconfiguredfeature : randomfeatureconfiguration.features)
        {
            if (random.nextFloat() < weightedconfiguredfeature.chance)
            {
                return weightedconfiguredfeature.place(worldgenlevel, chunkgenerator, random, blockpos);
            }
        }

        return randomfeatureconfiguration.defaultFeature.get().place(worldgenlevel, chunkgenerator, random, blockpos);
    }
}
