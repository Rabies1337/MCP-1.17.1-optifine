package net.minecraft.world.level.levelgen;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StrongholdConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

public class StructureSettings
{
    public static final Codec<StructureSettings> CODEC = RecordCodecBuilder.create((p_64596_) ->
    {
        return p_64596_.group(StrongholdConfiguration.CODEC.optionalFieldOf("stronghold").forGetter((p_158913_) -> {
            return Optional.ofNullable(p_158913_.stronghold);
        }), Codec.simpleMap(Registry.STRUCTURE_FEATURE, StructureFeatureConfiguration.CODEC, Registry.STRUCTURE_FEATURE).fieldOf("structures").forGetter((p_158911_) -> {
            return p_158911_.structureConfig;
        })).apply(p_64596_, StructureSettings::new);
    });
    public static final ImmutableMap < StructureFeature<?>, StructureFeatureConfiguration > DEFAULTS = ImmutableMap. < StructureFeature<?>, StructureFeatureConfiguration > builder().put(StructureFeature.VILLAGE, new StructureFeatureConfiguration(32, 8, 10387312)).put(StructureFeature.DESERT_PYRAMID, new StructureFeatureConfiguration(32, 8, 14357617)).put(StructureFeature.IGLOO, new StructureFeatureConfiguration(32, 8, 14357618)).put(StructureFeature.JUNGLE_TEMPLE, new StructureFeatureConfiguration(32, 8, 14357619)).put(StructureFeature.SWAMP_HUT, new StructureFeatureConfiguration(32, 8, 14357620)).put(StructureFeature.PILLAGER_OUTPOST, new StructureFeatureConfiguration(32, 8, 165745296)).put(StructureFeature.STRONGHOLD, new StructureFeatureConfiguration(1, 0, 0)).put(StructureFeature.OCEAN_MONUMENT, new StructureFeatureConfiguration(32, 5, 10387313)).put(StructureFeature.END_CITY, new StructureFeatureConfiguration(20, 11, 10387313)).put(StructureFeature.WOODLAND_MANSION, new StructureFeatureConfiguration(80, 20, 10387319)).put(StructureFeature.BURIED_TREASURE, new StructureFeatureConfiguration(1, 0, 0)).put(StructureFeature.MINESHAFT, new StructureFeatureConfiguration(1, 0, 0)).put(StructureFeature.RUINED_PORTAL, new StructureFeatureConfiguration(40, 15, 34222645)).put(StructureFeature.SHIPWRECK, new StructureFeatureConfiguration(24, 4, 165745295)).put(StructureFeature.OCEAN_RUIN, new StructureFeatureConfiguration(20, 8, 14357621)).put(StructureFeature.BASTION_REMNANT, new StructureFeatureConfiguration(27, 4, 30084232)).put(StructureFeature.NETHER_BRIDGE, new StructureFeatureConfiguration(27, 4, 30084232)).put(StructureFeature.NETHER_FOSSIL, new StructureFeatureConfiguration(2, 1, 14357921)).build();
    public static final StrongholdConfiguration DEFAULT_STRONGHOLD;
    private final Map < StructureFeature<?>, StructureFeatureConfiguration > structureConfig;
    @Nullable
    private final StrongholdConfiguration stronghold;

    public StructureSettings(Optional<StrongholdConfiguration> p_64586_, Map < StructureFeature<?>, StructureFeatureConfiguration > p_64587_)
    {
        this.stronghold = p_64586_.orElse((StrongholdConfiguration)null);
        this.structureConfig = p_64587_;
    }

    public StructureSettings(boolean p_64589_)
    {
        this.structureConfig = Maps.newHashMap(DEFAULTS);
        this.stronghold = p_64589_ ? DEFAULT_STRONGHOLD : null;
    }

    public Map < StructureFeature<?>, StructureFeatureConfiguration > structureConfig()
    {
        return this.structureConfig;
    }

    @Nullable
    public StructureFeatureConfiguration getConfig(StructureFeature<?> p_64594_)
    {
        return this.structureConfig.get(p_64594_);
    }

    @Nullable
    public StrongholdConfiguration stronghold()
    {
        return this.stronghold;
    }

    static
    {
        for (StructureFeature<?> structurefeature : Registry.STRUCTURE_FEATURE)
        {
            if (!DEFAULTS.containsKey(structurefeature))
            {
                throw new IllegalStateException("Structure feature without default settings: " + Registry.STRUCTURE_FEATURE.getKey(structurefeature));
            }
        }

        DEFAULT_STRONGHOLD = new StrongholdConfiguration(32, 3, 128);
    }
}
