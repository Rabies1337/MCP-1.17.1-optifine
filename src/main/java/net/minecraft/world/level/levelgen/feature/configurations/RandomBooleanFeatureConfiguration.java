package net.minecraft.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class RandomBooleanFeatureConfiguration implements FeatureConfiguration
{
    public static final Codec<RandomBooleanFeatureConfiguration> CODEC = RecordCodecBuilder.create((p_67877_) ->
    {
        return p_67877_.group(ConfiguredFeature.CODEC.fieldOf("feature_true").forGetter((p_161049_) -> {
            return p_161049_.featureTrue;
        }), ConfiguredFeature.CODEC.fieldOf("feature_false").forGetter((p_161047_) -> {
            return p_161047_.featureFalse;
        })).apply(p_67877_, RandomBooleanFeatureConfiguration::new);
    });
    public final Supplier < ConfiguredFeature <? , ? >> featureTrue;
    public final Supplier < ConfiguredFeature <? , ? >> featureFalse;

    public RandomBooleanFeatureConfiguration(Supplier < ConfiguredFeature <? , ? >> p_67872_, Supplier < ConfiguredFeature <? , ? >> p_67873_)
    {
        this.featureTrue = p_67872_;
        this.featureFalse = p_67873_;
    }

    public Stream < ConfiguredFeature <? , ? >> getFeatures()
    {
        return Stream.concat(this.featureTrue.get().getFeatures(), this.featureFalse.get().getFeatures());
    }
}
